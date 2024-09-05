package com.bloggios.discussion.controller;

import com.bloggios.discussion.constants.EndpointConstants;
import com.bloggios.discussion.payload.response.TopicsListResponse;
import com.bloggios.discussion.service.TopicService;
import com.bloggios.discussion.utils.AsyncUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.controller
 * Created_on - August 30 - 2024
 * Created_at - 22:50
 */

@RestController
@RequestMapping(EndpointConstants.TopicsController.BASE_PATH)
@RequiredArgsConstructor
public class TopicsController {

    private final TopicService topicService;

    @GetMapping
    public ResponseEntity<TopicsListResponse> getTopicsList() {
        return ResponseEntity.ok(AsyncUtils.getAsyncResult(topicService.topicsList()));
    }
}
