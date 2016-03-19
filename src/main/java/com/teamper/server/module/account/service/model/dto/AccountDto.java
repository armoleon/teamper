package com.teamper.server.module.account.service.model.dto;

import com.teamper.server.base.service.model.BaseDto;

public class AccountDto extends BaseDto {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
