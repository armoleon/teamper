package com.teamper.server.module.account.service.impl;

import com.teamper.common.model.event.ResourceCreatedEvent;
import com.teamper.server.module.account.converter.AccountConverter;
import com.teamper.server.module.account.repository.AccountRepository;
import com.teamper.server.module.account.repository.model.entity.AccountEntity;
import com.teamper.server.module.account.service.AccountService;
import com.teamper.server.module.account.service.model.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResourceCreatedEvent createAccount(AccountDto accountDto) {
        AccountEntity savedAccountEntity = accountRepository.save(AccountConverter.toEntity(accountDto, AccountEntity.class));
        ResourceCreatedEvent<AccountDto> resourceCreatedEvent = new ResourceCreatedEvent<>();
        resourceCreatedEvent.setSuccess(true);
        resourceCreatedEvent.setType(AccountConverter.toDto(savedAccountEntity, AccountDto.class));
        return resourceCreatedEvent;
    }


}
