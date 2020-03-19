package com.kakao.api.dto.response;

import lombok.Builder;

@Builder
public class TodoItemResponseDTO {
    private Long id;
    private String content;
}
