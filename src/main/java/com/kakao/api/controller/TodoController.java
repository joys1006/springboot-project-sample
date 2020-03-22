package com.kakao.api.controller;

import com.kakao.api.domain.TodoEntity;
import com.kakao.api.dto.request.TodoItemRequestDTO;
import com.kakao.api.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/todos")
@Api(value = "할일 리스트 컨트롤러", description = "할일 리스트 목업 데이터", tags = {"todo-list-controller"})
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    @ApiOperation(value = "할일목록")
    public Page<TodoEntity> getLists(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return todoService.getTodoItems(page, pageSize);
    }

    @PostMapping
    @ApiOperation(value = "할일등록")
    public HttpStatus postTodoItem(TodoItemRequestDTO request) {
        try {
            todoService.setTodoItem(request);
            return HttpStatus.OK;

        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @DeleteMapping
    @ApiOperation(value = "할일삭제")
    public HttpStatus deleteTodoItem(
            @RequestParam(value = "id", required = true) Long id
    ) {
        try {
            todoService.deleteTodoItem(id);

            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
