package com.bloggios.discussion.implementation;

import com.bloggios.discussion.constants.BeanConstants;
import com.bloggios.discussion.payload.TopicsYmlDataProvider;
import com.bloggios.discussion.payload.response.TopicsListResponse;
import com.bloggios.discussion.service.TopicService;
import com.bloggios.discussion.ymlparser.TopicDataParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.implementation
 * Created_on - August 30 - 2024
 * Created_at - 22:51
 */

@Service
@Slf4j
public class TopicServiceImplementation implements TopicService {

    private final TopicDataParser topicDataParser;

    public TopicServiceImplementation(
            TopicDataParser topicDataParser
    ) {
        this.topicDataParser = topicDataParser;
    }

    @Override
    @Async(BeanConstants.ASYNC_TASK_EXTERNAL_POOL)
    public CompletableFuture<TopicsListResponse> topicsList() {
        long startTime = System.currentTimeMillis();
        Map<String, TopicsYmlDataProvider> provider = topicDataParser.provider;
        List<TopicsYmlDataProvider> topicsYmlDataProviders = provider.values().stream().toList();
        TopicsListResponse build = TopicsListResponse
                .builder()
                .object(topicsYmlDataProviders)
                .build();
        log.info("Execution Time (Get Topics List) : {}ms", System.currentTimeMillis() - startTime);
        return CompletableFuture.completedFuture(build);
    }
}
