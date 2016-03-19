package com.teamper.server.module.account.repository;

import com.teamper.server.base.repository.BaseRepository;
import com.teamper.server.module.account.repository.model.entity.AccountEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<AccountEntity, String> {
    AccountEntity findByEmailAndPassword(String email, String password);
}
