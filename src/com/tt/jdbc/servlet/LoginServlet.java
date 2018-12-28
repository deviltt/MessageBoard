package com.tt.jdbc.servlet;

import com.tt.jdbc.entity.User;
import com.tt.jdbc.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main.do")
public class LoginServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.login(username, password);
        if(user != null){
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/message/list.do").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("/login.do").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        userService = null;
    }
}
