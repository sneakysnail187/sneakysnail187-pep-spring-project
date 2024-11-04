package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/*
messageId int primary key auto_increment,
postedBy int,
messageText varchar(255),
timePostedEpoch bigint,
foreign key (postedBy) references account(accountId)
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    @Query("from Message where postedBy = :account_num")
    List<Message> getAccountMessages(@Param("account_num") int postedBy);

}
