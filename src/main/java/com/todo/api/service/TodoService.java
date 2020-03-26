package com.todo.api.service;

import com.todo.api.domain.TodoEntity;
import com.todo.api.dto.request.TodoItemRequestDTO;
import com.todo.api.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Page<TodoEntity> getTodoItems(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by("id").ascending());

        return todoRepository.findAll(pageRequest);
    }

    @Transactional
    public void setTodoItem(TodoEntity request) {
        todoRepository.save(request);
    }

    public void deleteTodoItem(Long id) {
        todoRepository.deleteById(id);
    }
}
