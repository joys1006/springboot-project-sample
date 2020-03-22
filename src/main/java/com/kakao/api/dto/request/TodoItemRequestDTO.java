package com.kakao.api.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoItemRequestDTO {
    private String content;
    private int page;
    private int pageSize;
}
