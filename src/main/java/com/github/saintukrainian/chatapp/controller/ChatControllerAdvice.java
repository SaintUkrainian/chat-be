package com.github.saintukrainian.chatapp.controller;

import com.github.saintukrainian.chatapp.exception.ChatCreationException;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ChatControllerAdvice {

  @MessageExceptionHandler(ChatCreationException.class)
  @SendToUser("/errors")
  public ChatCreationException chatCreationException(ChatCreationException e) {
    return e;
  }
}
