package com.bloggios.discussion.service;

import com.bloggios.discussion.payload.response.ModuleResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.service
 * Created_on - August 29 - 2024
 * Created_at - 23:09
 */

public interface DiscussionService {

    ModuleResponse addDiscussion(
            String title,
            String html,
            String detailsText,
            List<MultipartFile> images,
            List<String> topics
    );
}
