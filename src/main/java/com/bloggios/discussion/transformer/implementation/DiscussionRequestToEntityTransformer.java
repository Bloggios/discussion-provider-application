package com.bloggios.discussion.transformer.implementation;

import com.bloggios.authenticationconfig.util.IpUtils;
import com.bloggios.discussion.modal.DiscussionEntity;
import com.bloggios.discussion.payload.record.DiscussionImagesAndHtmlRecord;
import com.bloggios.discussion.payload.request.DiscussionRequest;
import com.bloggios.discussion.utils.TopicsTransformUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

import static com.bloggios.discussion.constants.EnvironmentConstants.APPLICATION_VERSION;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.transformer.implementation
 * Created_on - September 05 - 2024
 * Created_at - 22:45
 */

@Component
@RequiredArgsConstructor
public class DiscussionRequestToEntityTransformer {

    private final Environment environment;

    public DiscussionEntity transform(
            DiscussionRequest discussionRequest,
            DiscussionImagesAndHtmlRecord discussionImagesAndHtmlRecord,
            String finalHtml
    ) {
        return DiscussionEntity
                .builder()
                .title(discussionRequest.getTitle())
                .details(finalHtml)
                .detailsText(discussionRequest.getDetailsText())
                .userId(discussionRequest.getAuthenticatedUser().getUserId())
                .dateCreated(new Date())
                .remoteAddress(IpUtils.getRemoteAddress(discussionRequest.getHttpServletRequest()))
                .imageLinks(discussionImagesAndHtmlRecord.imageLinks())
                .topics(TopicsTransformUtil.getTopicsEmbeddableList(discussionRequest.getTopics()))
                .apiVersion(environment.getProperty(APPLICATION_VERSION))
                .version(UUID.randomUUID().toString())
                .build();
    }
}
