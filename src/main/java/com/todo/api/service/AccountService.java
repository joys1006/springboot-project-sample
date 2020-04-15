package com.todo.api.service;

import com.todo.api.handler.authentication.JwtTokenProvider;
import com.todo.api.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountService  {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

}
