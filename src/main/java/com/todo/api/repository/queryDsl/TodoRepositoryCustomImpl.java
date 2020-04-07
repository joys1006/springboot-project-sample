package com.todo.api.repository.queryDsl;

import com.todo.api.domain.TodoEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
