package com.teamper.common.model.event;

import com.teamper.server.base.service.model.BaseDto;

public class ResourceCreatedEvent<T extends BaseDto> extends ResourceEvent {
    private T type;

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }
}
