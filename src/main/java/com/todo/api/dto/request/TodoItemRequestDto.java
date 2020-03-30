package com.todo.api.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class TodoItemRequestDto implements Serializable {
    private String content;
    private Boolean isSuccess = false;
}
