package org.dnd4.yorijori.domain.common;

import lombok.Data;

@Data
public class Result<T> extends BaseResult{
    private T data;

    public Result(T data){
        this.setData(data);
    }
}
