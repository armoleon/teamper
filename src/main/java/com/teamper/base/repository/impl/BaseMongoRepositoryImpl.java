package com.teamper.base.repository.impl;

import com.teamper.base.repository.BaseMongoRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;

public class BaseMongoRepositoryImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements BaseMongoRepository<T, ID> {

    public BaseMongoRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }
}
