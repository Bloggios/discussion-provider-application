package com.bloggios.discussion.service;

import com.bloggios.discussion.payload.response.TopicsListResponse;

import java.util.concurrent.CompletableFuture;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.service
 * Created_on - August 30 - 2024
 * Created_at - 22:48
 */

public interface TopicService {

    CompletableFuture<TopicsListResponse> topicsList();
}
