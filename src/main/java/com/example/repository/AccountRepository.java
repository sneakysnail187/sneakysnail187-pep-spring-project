package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
accountId int primary key auto_increment,
username varchar(255) not null unique,
password varchar(255)
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    Account findAccountByUsername(String username);
}
