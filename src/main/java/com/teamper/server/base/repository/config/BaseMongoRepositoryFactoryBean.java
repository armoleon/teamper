package com.teamper.server.base.repository.config;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

public class BaseMongoRepositoryFactoryBean<R extends MongoRepository<T, I>, T, I extends Serializable> extends MongoRepositoryFactoryBean<R, T, I> {

    @Override
    protected RepositoryFactorySupport getFactoryInstance(MongoOperations operations) {
        return new BaseRepositoryFactory<T, I>(operations);
    }

}
