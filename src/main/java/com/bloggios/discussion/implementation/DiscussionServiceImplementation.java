package com.bloggios.discussion.implementation;

import com.bloggios.discussion.payload.request.DiscussionRequest;
import com.bloggios.discussion.payload.response.ModuleResponse;
import com.bloggios.discussion.service.DiscussionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.implementation
 * Created_on - August 29 - 2024
 * Created_at - 23:13
 */

@Service
@Slf4j
public class DiscussionServiceImplementation implements DiscussionService {

    @Override
    public ModuleResponse addDiscussion(String title, String html, String detailsText, List<MultipartFile> images, List<String> topics) {
        DiscussionRequest discussionRequest = DiscussionRequest
                .builder()
                .title(title)
                .html(html)
                .detailsText(detailsText)
                .images(images)
                .topics(topics)
                .build();
        return null;
    }
}
