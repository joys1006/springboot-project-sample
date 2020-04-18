package com.todo.api.dto.request;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class SignInRequestDto {
    @ApiParam(value = "회원 ID : 아이디", required = true)
    public String id;
    @ApiParam(value = "비밀번호", required = true)
    public String password;
}
