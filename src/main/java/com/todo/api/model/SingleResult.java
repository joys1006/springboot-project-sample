package com.todo.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult<T> extends CommonResult {
    private T data;

    public void setData(T data) {
        this.data = data;
    }
}
