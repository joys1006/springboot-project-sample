package com.todo.api.dto.request;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class SignUpRequestDto {
    @ApiParam(value = "회원ID", required = true)
    public String id;
    @ApiParam(value = "비밀번호", required = true)
    public String password;
    @ApiParam(value = "이름", required = true)
    public String name;
}
