package com.teamper.module.account.service.impl;

import com.teamper.common.model.ResourceCreatedEvent;
import com.teamper.module.account.repository.AccountRepository;
import com.teamper.module.account.repository.model.AccountEntity;
import com.teamper.module.account.service.AccountService;
import com.teamper.module.account.service.model.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.teamper.base.converter.BaseConverter.toDto;
import static com.teamper.base.converter.BaseConverter.toEntity;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResourceCreatedEvent createAccount(AccountDto accountDto) {
        AccountEntity savedAccountEntity = accountRepository.save(toEntity(accountDto, AccountEntity.class));
        ResourceCreatedEvent<AccountDto> resourceCreatedEvent = new ResourceCreatedEvent<>();
        resourceCreatedEvent.setSuccess(true);
        resourceCreatedEvent.setType(toDto(savedAccountEntity, AccountDto.class));
        return resourceCreatedEvent;
    }


}
