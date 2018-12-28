package com.tt.jdbc.servlet;

import com.tt.jdbc.entity.Message;
import com.tt.jdbc.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/message/list.do")
public class MessageListServlet extends HttpServlet {
    MessageService messageService = null;
    @Override
    public void init() throws ServletException {
        messageService = new MessageService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        int page = 1;   //默认页码为1
        int pageSize = 5;
        if(pageStr != null && !"".equals(pageStr))
            page = Integer.parseInt(pageStr);

        List<Message> messageList = messageService.getMessages(page, pageSize);   //分页查询全部留言
        int count = messageService.countMessages();

        int last = count % pageSize == 0 ? (count / pageSize) : (count / pageSize + 1);
        req.setAttribute("last", last);
        req.setAttribute("page", page);
        req.setAttribute("messages", messageList);

        req.getRequestDispatcher("/WEB-INF/views/bus/message_list.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        messageService = null;
    }
}
