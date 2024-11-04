package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public AccountService(MessageRepository messageRepo, AccountRepository accountRepo){
        this.messageRepository = messageRepo;
        this.accountRepository = accountRepo;
    }

    public Account registerAccount(Account a){
        if(accountRepository.findAccountByUsername(a.getUsername()) == null
        && a.getUsername() != "" && a.getPassword().length() > 3){
            return accountRepository.save(a);
        }
        return null;
    }

    public Account loginAccount(Account a){
        Optional<Account> target = Optional.ofNullable(accountRepository.findAccountByUsername(a.getUsername()));
        if(target.isPresent() && a.getPassword().equals(target.get().getPassword())){
            return target.get();
        }
        return null;
    }

    public List<Message> getAccountMessages(Account a){
        Optional<Account> target = Optional.ofNullable(accountRepository.findAccountByUsername(a.getUsername()));
        if(target.isPresent()){
            return messageRepository.getAccountMessages(target.get().getAccountId());
        }
        return new ArrayList<Message>();
    }
}
