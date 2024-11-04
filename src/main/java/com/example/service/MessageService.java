package com.example.service;

import java.util.List;
import com.example.entity.Message;

public interface MessageService {

    public Message addMessage(Message m);
    public Message getMessage(int id);
    public List<Message> getAllMessages();
    public Message deleteMessage(int id);
    public Message editMessage(int id, String newMessage);
}
