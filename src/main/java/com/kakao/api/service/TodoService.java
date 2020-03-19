package com.kakao.api.service;

import com.kakao.api.domain.TodoEntity;
import com.kakao.api.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Iterable<TodoEntity> getTodoItems() {
        return todoRepository.findAll();
    }

}
