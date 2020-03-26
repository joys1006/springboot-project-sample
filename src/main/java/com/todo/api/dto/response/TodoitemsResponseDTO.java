package com.todo.api.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import lombok.Data;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TodoitemsResponseDTO {
    private Long total;
    private Long page;
    private List<TodoitemsResponseDTO> list;
}
