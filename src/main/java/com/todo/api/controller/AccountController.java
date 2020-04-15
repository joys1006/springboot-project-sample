package com.todo.api.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/account")
@Api(value = "할일 리스트 컨트롤러", description = "로그인/회원가입 컨트롤러", tags = {"account-controller"})
public class AccountController {

}
