package com.teamper.server.base.repository.config;

import com.teamper.server.base.repository.impl.BaseMongoRepositoryImpl;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.BasicMongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.TypeInformation;

import java.io.Serializable;

public class BaseRepositoryFactory<T, ID extends Serializable> extends MongoRepositoryFactory {

    private MongoOperations mongoOperations;

    public BaseRepositoryFactory(MongoOperations mongoOperations) {
        super(mongoOperations);
        this.mongoOperations = mongoOperations;
    }

    @SuppressWarnings("unchecked")
    protected Object getTargetRepository(RepositoryMetadata repositoryMetadata) {
        TypeInformation<T> information = ClassTypeInformation.from((Class<T>) repositoryMetadata.getDomainType());
        MongoPersistentEntity<T> pe = new BasicMongoPersistentEntity<>(information);
        MongoEntityInformation<T, ID> mongoEntityInformation = new MappingMongoEntityInformation<>(pe);
        return new BaseMongoRepositoryImpl<>(mongoEntityInformation, mongoOperations);
    }

    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return BaseMongoRepositoryImpl.class;
    }
}