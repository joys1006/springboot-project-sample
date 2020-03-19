package com.kakao.api.dto.response;

import com.sun.tools.javac.util.List;
import lombok.Builder;

import java.awt.*;
import java.util.Optional;

@Builder
public class TodoitemsResponseDTO {
    private Long total;
    private Long page;
    private List<TodoItemResponseDTO> list;
}
