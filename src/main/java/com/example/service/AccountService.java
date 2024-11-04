package com.example.service;

import java.util.List;
import com.example.entity.Account;
import com.example.entity.Message;

public interface AccountService {

    public Account registerAccount(Account a);
    public Account loginAccount(Account a);
    public List<Message> getAccountMessages(int a);
    
}
