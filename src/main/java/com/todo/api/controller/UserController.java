package com.todo.api.controller;

import com.todo.api.dto.request.SignInRequestDto;
import com.todo.api.dto.request.SignUpRequestDto;
import com.todo.api.model.CommonResult;
import com.todo.api.model.SingleResult;
import com.todo.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/account")
@Api(value = "할일 리스트 컨트롤러", description = "로그인/회원가입 컨트롤러", tags = {"account-controller"})
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "로그인", notes = "로그인 API")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@Valid SignInRequestDto request) {
       return userService.signin(request);
    }

    @ApiOperation(value = "회원가입", notes = "회원가입 API")
    @PostMapping(value = "/signup")
    public CommonResult singUp(@Valid SignUpRequestDto request) {
        return userService.signUp(request);
    }

}
