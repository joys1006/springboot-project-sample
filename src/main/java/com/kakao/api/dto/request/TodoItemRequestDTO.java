package com.kakao.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class TodoItemRequestDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
