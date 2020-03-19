package com.kakao.api.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
public class TodoItemRequestDTO {
    private Long content;
    private LocalDateTime created;
    private LocalDateTime updated;
}
