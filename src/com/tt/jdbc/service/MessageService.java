package com.tt.jdbc.service;

import com.tt.jdbc.dao.MessageDao;
import com.tt.jdbc.entity.Message;

import java.util.List;

public class MessageService {
    MessageDao messageDao = null;
    public MessageService(){
        messageDao = new MessageDao();
    }

    public List<Message> getMessages(int page, int pageSie){
        return messageDao.getMessages(page, pageSie);
    }

    public int countMessages(){
        return messageDao.countMessages();
    }
}
