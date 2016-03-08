package com.teamper.module.account.repository.model;

import com.google.common.base.Objects;
import com.teamper.base.repository.model.BaseEntity;
import org.springframework.data.annotation.Id;

public class AccountEntity extends BaseEntity {

    @Id
    private String id;

    private String email;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public int hashCode() {
        return Objects.hashCode(id, email, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountEntity that = (AccountEntity) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(email, that.email) &&
                Objects.equal(password, that.password);
    }
}
