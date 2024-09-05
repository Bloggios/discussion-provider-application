package com.bloggios.discussion.dao.repository.postgress;

import com.bloggios.discussion.modal.DiscussionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.dao.repository
 * Created_on - September 05 - 2024
 * Created_at - 23:00
 */

public interface DiscussionEntityRepository extends JpaRepository<DiscussionEntity, String> {
}
