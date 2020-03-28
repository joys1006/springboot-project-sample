package com.todo.api.controller;

import com.todo.api.domain.TodoEntity;
import com.todo.api.dto.request.TodoItemSearchDTO;
import com.todo.api.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/todos")
@Api(value = "할일 리스트 컨트롤러", description = "할일 리스트 목업 데이터", tags = {"todo-list-controller"})
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    @ApiOperation(value = "할일목록")
    public Page<TodoEntity> getLists(
            @Valid TodoItemSearchDTO request
    ) {
        return todoService.getTodoItems(request);
    }

    @PostMapping
    @ApiOperation(value = "할일등록")
    public HttpStatus postTodoItem(
            @RequestBody TodoEntity request
    ) {
        try {
            todoService.setTodoItem(request);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
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
