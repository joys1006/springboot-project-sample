package com.todo.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class TodoItemSearchDTO implements Serializable {
    private String content;
    private Boolean isSuccess;

    private int page = 1;

    private int size = 10;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
