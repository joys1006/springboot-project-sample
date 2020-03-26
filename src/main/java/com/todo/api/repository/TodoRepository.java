package com.todo.api.repository;

import com.todo.api.domain.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long>, TodoRepositoryCustom {
}
