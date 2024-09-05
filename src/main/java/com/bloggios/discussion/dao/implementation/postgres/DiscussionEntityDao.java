package com.bloggios.discussion.dao.implementation.postgres;

import com.bloggios.discussion.dao.PgAbstractDao;
import com.bloggios.discussion.dao.repository.postgress.DiscussionEntityRepository;
import com.bloggios.discussion.modal.DiscussionEntity;
import org.springframework.stereotype.Component;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.dao.implementation.postgres
 * Created_on - September 05 - 2024
 * Created_at - 23:02
 */

@Component
public class DiscussionEntityDao extends PgAbstractDao<DiscussionEntity, DiscussionEntityRepository> {

    protected DiscussionEntityDao(DiscussionEntityRepository repository) {
        super(repository);
    }
}
