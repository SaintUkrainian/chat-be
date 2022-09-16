package com.github.saintukrainian.chatapp.controller.advice;

import com.github.saintukrainian.chatapp.exception.LoginFailException;
import com.github.saintukrainian.chatapp.exception.RegistrationFailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthControllerAdvice {

  @ExceptionHandler(LoginFailException.class)
  public ResponseEntity<LoginFailException> loginFail(LoginFailException e) {
    return ResponseEntity.badRequest().body(e);
  }

  @ExceptionHandler(RegistrationFailException.class)
  public ResponseEntity<RegistrationFailException> registrationFail(RegistrationFailException e) {
    return ResponseEntity.badRequest().body(e);
  }
}
