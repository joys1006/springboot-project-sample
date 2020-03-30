package com.todo.api.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.todo.api.domain.QTodoEntity;
import com.todo.api.domain.TodoEntity;
import com.todo.api.dto.request.TodoItemSearchDTO;
import com.todo.api.repository.queryDsl.TodoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long>, QuerydslPredicateExecutor<TodoEntity>, TodoRepositoryCustom {
    default Predicate makeTodoSearchQuery(TodoItemSearchDTO params) {

        BooleanBuilder builder = new BooleanBuilder();
        QTodoEntity qTodoEntity = QTodoEntity.todoEntity;

        if (params.getContent() != null && params.getContent() != "") {
            builder.and(qTodoEntity.content.like('%' + params.getContent() + '%'));
        }

        if (params.getIsSuccess() != null) {
            builder.and(qTodoEntity.isSuccess.eq(params.getIsSuccess()));
        }

        if (params.getStartDate() != null && params.getEndDate() != null) {
            LocalDateTime startDate = params.getStartDate().atTime(0,0,0);
            LocalDateTime endDate = params.getEndDate().atTime(23,59,59);

            builder.and(qTodoEntity.createdAt.between(startDate, endDate));
        }

        return builder;
    }
}
