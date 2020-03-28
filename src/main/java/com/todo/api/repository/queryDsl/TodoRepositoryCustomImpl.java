package com.todo.api.repository.queryDsl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.api.domain.QTodoEntity;
import com.todo.api.domain.TodoEntity;
import com.todo.api.dto.request.TodoItemSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TodoRepositoryCustomImpl extends QuerydslRepositorySupport implements TodoRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     */
    public TodoRepositoryCustomImpl() {
        super(TodoEntity.class);
    }

}
