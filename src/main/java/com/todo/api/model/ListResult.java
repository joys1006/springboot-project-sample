package com.todo.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResult<T> extends CommonResult {
    private List<T> list;

    public void setList(List<T> list) {
        this.list = list;
    }
}
