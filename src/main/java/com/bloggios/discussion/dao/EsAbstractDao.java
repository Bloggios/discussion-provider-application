package com.bloggios.discussion.dao;

import com.bloggios.discussion.enums.DaoStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.dao
 * Created_on - September 05 - 2024
 * Created_at - 22:54
 */

public abstract class EsAbstractDao<A, B extends ElasticsearchRepository<A, String>> {

    private static final Logger logger = LoggerFactory.getLogger(EsAbstractDao.class);

    protected final B repository;

    protected EsAbstractDao(
            B repository
    ) {
        this.repository = repository;
    }

    public final A initOperation(DaoStatus status, A a) {
        return switch (status) {
            case CREATE -> initCreate(a);
            case UPDATE -> initUpdate(a);
        };
    }

    private A initUpdate(A a) {
        logger.info("Elasticsearch Init Operation (Update) @{}", new Date());
        return repository.save(a);
    }

    private A initCreate(A a) {
        logger.info("Elasticsearch Init Operation (Create) @{}", new Date());
        return repository.save(a);
    }
}
