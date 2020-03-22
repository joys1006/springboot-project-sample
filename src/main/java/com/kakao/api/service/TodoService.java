package com.kakao.api.service;

import com.kakao.api.domain.TodoEntity;
import com.kakao.api.dto.request.TodoItemRequestDTO;
import com.kakao.api.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;


    public Page<TodoEntity> getTodoItems(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.Direction.DESC, "id");

        return todoRepository.findAll(pageRequest);
    }

    @Transactional
    public void setTodoItem(TodoItemRequestDTO request) {
        TodoEntity todoEntity = new TodoEntity();

        todoEntity.setContent(request.getContent());

        todoRepository.save(todoEntity);
    }

    public void deleteTodoItem(Long id) {
        todoRepository.deleteById(id);
    }
}
