package com.teamper.module.account.repository;

import com.teamper.base.repository.BaseRepository;
import com.teamper.module.account.repository.model.AccountEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<AccountEntity, String> {
    AccountEntity findByEmailAndPassword(String email, String password);
}
