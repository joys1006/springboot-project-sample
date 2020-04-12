package com.todo.api.controller;

import com.todo.api.domain.TodoEntity;
import com.todo.api.dto.request.TodoItemRequestDto;
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
@RequestMapping("/todo")
@Api(value = "할일 리스트 컨트롤러", description = "할일 리스트 목업 데이터", tags = {"todo-list-controller"})
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/{id}")
    @ApiOperation(value = "할일")
    public TodoEntity getTodoItem(
            @PathVariable Long id
    ) {
        return todoService.getTodoItem(id);
    }

    @GetMapping("/lists")
    @ApiOperation(value = "할일목록")
    public Page<TodoEntity> getLists(
            @Valid TodoItemSearchDTO request
    ) {
        return todoService.getTodoItems(request);
    }

    @PostMapping("/create")
    @ApiOperation(value = "할일등록")
    public HttpStatus postTodoItem(
            @RequestBody TodoItemRequestDto request
    ) {
        try {
            todoService.setTodoItem(request);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "할일수정")
    public HttpStatus putTodoItem(
            @PathVariable Long id,
            @RequestBody TodoItemRequestDto request
    ) {
        try {
            todoService.putTodoItem(id, request);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "할일삭제")
    public HttpStatus deleteTodoItem(
            @PathVariable Long id
    ) {
        try {
            todoService.deleteTodoItem(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
