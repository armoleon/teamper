package com.teamper.common.model;

import com.teamper.base.service.model.BaseDto;

public class ResourceGetEvent<T extends BaseDto> extends ResourceEvent {

    private T type;

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }
}
