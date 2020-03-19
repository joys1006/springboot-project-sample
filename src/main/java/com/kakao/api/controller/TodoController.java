package com.kakao.api.controller;

import com.kakao.api.domain.TodoEntity;
import com.kakao.api.dto.request.TodoItemRequestDTO;
import com.kakao.api.dto.response.TodoitemsResponseDTO;
import com.kakao.api.service.MockupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/todos")
@Api(value = "할일 리스트 컨트롤러", description = "할일 리스트 목업 데이터", tags = {"todo-list-controller"})
public class TodoController {

    @Autowired
    private MockupService mockupService;

    @GetMapping
    @ApiOperation(value = "할일목록")
    public List<TodoitemsResponseDTO> getLists(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return mockupService.data("json/todo.json");
    }

    @PostMapping
    @ApiOperation(value = "할일등록")
    public HttpStatus postList(TodoItemRequestDTO request) {
        RestTemplate rest = new RestTemplate();
        try {
            rest.postForEntity("json/todo.json", request, TodoItemRequestDTO.class);

            return HttpStatus.OK;

        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
