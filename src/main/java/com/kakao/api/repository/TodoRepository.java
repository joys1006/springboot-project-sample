package com.kakao.api.repository;

import com.kakao.api.domain.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<TodoEntity, Long> {


}
