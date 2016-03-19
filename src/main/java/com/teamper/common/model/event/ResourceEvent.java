package com.teamper.common.model.event;

public abstract class ResourceEvent {
    Boolean success = false;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
