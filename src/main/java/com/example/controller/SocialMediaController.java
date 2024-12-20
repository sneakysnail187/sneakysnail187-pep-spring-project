package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.DuplicateUsernameException;
import com.example.entity.Edit;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    @Lazy
    private MessageService messageService;

    @Autowired
    @Lazy
    private AccountService accountService;

    @GetMapping("/messages")
    public List<Message> getAllMessages(){ //to do
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity getMessage(@PathVariable int message_id){
        Message m = messageService.getMessage(message_id);
        if(m != null){
            return ResponseEntity.status(200).body(m);
        }
        return ResponseEntity.status(200).body(null);
    }

    @GetMapping("/accounts/{account_id}/messages")
    public List<Message> getAccountMessages(@PathVariable int account_id){
        return accountService.getAccountMessages(account_id);
    }

    @PostMapping("/messages")
    public ResponseEntity addMessage(@RequestBody Message message){
        Message m = messageService.addMessage(message);
        if(m != null){
            return ResponseEntity.status(200).body(m);
        }
        return ResponseEntity.status(400).body(null);
    }

    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity deleteMessage(@PathVariable int message_id){
        Message m = messageService.deleteMessage(message_id);
        if(m != null){
            return ResponseEntity.status(200).body(1);
        }
        return ResponseEntity.status(200).body(null);
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity editMessage(@PathVariable int message_id, @RequestBody String newMessage) throws JsonMappingException, JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Edit deserialized = objectMapper.readValue(newMessage, Edit.class);
        Message m = messageService.editMessage(message_id, deserialized.getmessageText());
        if(m != null){
            return ResponseEntity.status(200).body(1);
        }
        return ResponseEntity.status(400).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Account acc){
        Optional<Account> target = Optional.ofNullable(accountService.loginAccount(acc));
        if(target.isPresent()){
            return ResponseEntity.status(200).body(target.get());
        }
        return ResponseEntity.status(401).body(null);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Account acc){
      
        Optional<Account> target = Optional.ofNullable(accountService.registerAccount(acc));
        if(target.isPresent()){
            return ResponseEntity.status(200).body(target.toString());
        }
        return ResponseEntity.status(400).body(null);
    }

    
    @ExceptionHandler({DuplicateUsernameException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody String handleNotFound(DuplicateUsernameException ex) {
        return ex.getMessage();
    }

}
