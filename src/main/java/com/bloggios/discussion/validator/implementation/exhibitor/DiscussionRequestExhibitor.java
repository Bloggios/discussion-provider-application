package com.bloggios.discussion.validator.implementation.exhibitor;

import com.bloggios.discussion.payload.request.DiscussionRequest;
import com.bloggios.discussion.utils.AsyncUtils;
import com.bloggios.discussion.validator.Exhibitor;
import com.bloggios.discussion.validator.implementation.businessvalidator.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.validator.implementation.exhibitor
 * Created_on - September 02 - 2024
 * Created_at - 23:04
 */

@Component
@RequiredArgsConstructor
public class DiscussionRequestExhibitor implements Exhibitor<DiscussionRequest> {

    private final DiscussionDetailsValidator discussionDetailsValidator;
    private final DiscussionImagesValidator discussionImagesValidator;
    private final HtmlValidator htmlValidator;
    private final TitleValidator titleValidator;
    private final TopicsValidator topicsValidator;

    @Override
    public void validate(DiscussionRequest discussionRequest) {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> discussionDetailsValidator.validate(discussionRequest.getDetailsText()));
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.runAsync(() -> discussionImagesValidator.validate(discussionRequest));
        CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.runAsync(() -> htmlValidator.validate(discussionRequest));
        CompletableFuture<Void> voidCompletableFuture3 = CompletableFuture.runAsync(() -> titleValidator.validate(discussionRequest.getTitle()));
        CompletableFuture<Void> voidCompletableFuture4 = CompletableFuture.runAsync(() -> topicsValidator.validate(discussionRequest.getTopics()));
        AsyncUtils.getAsyncResult(CompletableFuture.allOf(
                voidCompletableFuture,
                voidCompletableFuture1,
                voidCompletableFuture2,
                voidCompletableFuture3,
                voidCompletableFuture4
        ));
    }
}
