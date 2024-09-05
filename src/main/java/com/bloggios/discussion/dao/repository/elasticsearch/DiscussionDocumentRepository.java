package com.bloggios.discussion.dao.repository.elasticsearch;

import com.bloggios.discussion.document.DiscussionDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.dao.repository.elasticsearch
 * Created_on - September 05 - 2024
 * Created_at - 23:01
 */

public interface DiscussionDocumentRepository extends ElasticsearchRepository<DiscussionDocument, String> {
}
