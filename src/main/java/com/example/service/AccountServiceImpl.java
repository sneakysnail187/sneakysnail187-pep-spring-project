package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.DuplicateUsernameException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    AccountRepository accountRepository;


    public Account registerAccount(Account a) throws DuplicateUsernameException{
        if(accountRepository.findAccountByUsername(a.getUsername()) != null){
            throw new DuplicateUsernameException("Someone else has username: " + a.getUsername());
        }
        else if(a.getUsername() == "" || a.getPassword().length() < 4){
            return null;
        }
        return accountRepository.save(a);
    }

    public Account loginAccount(Account a){
        Optional<Account> target = Optional.ofNullable(accountRepository.findAccountByUsername(a.getUsername()));
        if(target.isPresent() && a.getPassword().equals(target.get().getPassword())){
            return target.get();
        }
        return null;
    }

    public List<Message> getAccountMessages(int a){
        Optional<Account> target = accountRepository.findById(a);
        if(target.isPresent()){
            return messageRepository.getAccountMessages(target.get().getAccountId());
        }
        return new ArrayList<Message>();
    }
}
