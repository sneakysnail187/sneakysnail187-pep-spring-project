package com.example.service;

import com.example.entity.Message;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    @Lazy
    MessageRepository messageRepository;

    @Autowired
    @Lazy
    AccountRepository accountRepository;
    
    public Message addMessage(Message m){
        if(m.getMessageText() == "" || m.getMessageText().length() > 255 
        || accountRepository.getById(m.getPostedBy()) == null){
            return null;
        }
        return messageRepository.save(m);
    }

    public Message getMessage(int id){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }
        return null;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message deleteMessage(int id){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent()){
            messageRepository.deleteById(id);
            return optionalMessage.get();
        }
        return null;
    }

    public Message editMessage(int id, String newMessage){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent() && newMessage != "" 
        && newMessage.length() < 256){
            Message message = optionalMessage.get();
            message.setMessageText(newMessage);
            return messageRepository.save(message);
        }
        return null;
    }
}
