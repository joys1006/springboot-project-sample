package com.todo.api.service;

import com.querydsl.core.types.Predicate;
import com.todo.api.domain.TodoEntity;
import com.todo.api.dto.request.TodoItemRequestDto;
import com.todo.api.dto.request.TodoItemSearchDTO;
import com.todo.api.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Cacheable(value = "todoItmsCache", key = "#id")
    public TodoEntity getTodoItem(Long id) {
        return todoRepository.findById(id).get();
    }

    @Cacheable(value = "todoItemsCache", key = "#id")
    public Page<TodoEntity> getTodoItems(TodoItemSearchDTO params) {
        PageRequest pageRequest = PageRequest.of(params.getPage() - 1, params.getSize(), Sort.by("id").ascending());
        Predicate pageSearch = todoRepository.makeTodoSearchQuery(params);

        return todoRepository.findAll(pageSearch, pageRequest);
    }

    @Transactional
    public void setTodoItem(TodoItemRequestDto request) {
        TodoEntity todoEntity = new TodoEntity();

        todoEntity.setContent(request.getContent());
        todoEntity.setIsSuccess(request.getIsSuccess());

        todoRepository.save(todoEntity);
    }

    @Transactional
    public void putTodoItem(Long id, TodoItemRequestDto request) {
        Optional<TodoEntity> todoEntity = todoRepository.findById(id);

        todoEntity.ifPresent(item -> {
            item.setContent(request.getContent());
            item.setIsSuccess(request.getIsSuccess());
            todoRepository.save(item);
        });
    }

    public void deleteTodoItem(Long id) {
        todoRepository.deleteById(id);
    }
}
