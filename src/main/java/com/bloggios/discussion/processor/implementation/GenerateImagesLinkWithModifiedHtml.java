package com.bloggios.discussion.processor.implementation;

import com.bloggios.discussion.constants.EnvironmentConstants;
import com.bloggios.discussion.constants.ServiceConstants;
import com.bloggios.discussion.modal.embeddable.ImageLinksEntity;
import com.bloggios.discussion.payload.record.DiscussionImagesAndHtmlRecord;
import com.bloggios.discussion.payload.record.UploadImagePayloadRecord;
import com.bloggios.discussion.payload.request.DiscussionRequest;
import com.bloggios.discussion.processor.AddImageLinksHtmlProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.processor.implementation
 * Created_on - September 02 - 2024
 * Created_at - 23:20
 */

@Component
@RequiredArgsConstructor
public class GenerateImagesLinkWithModifiedHtml {

    private final UploadImagesLinkProcessor uploadImagesLinkProcessor;
    private final Environment environment;
    private final AddImageLinksHtmlProcessor addImageLinksHtmlProcessor;

    public DiscussionImagesAndHtmlRecord process(DiscussionRequest discussionRequest) {
        List<ImageLinksEntity> imageLinks;
        String modifiedHtml;
        if (Objects.nonNull(discussionRequest.getImages())) {
            imageLinks = uploadImagesLinkProcessor.apply(
                    new UploadImagePayloadRecord(
                            environment.getProperty(EnvironmentConstants.DISCUSSION_IMAGES_PATH),
                            discussionRequest.getAuthenticatedUser().getUserId(),
                            discussionRequest.getImages(),
                            ServiceConstants.DISCUSSION_IMAGE
                    )
            );
            modifiedHtml = addImageLinksHtmlProcessor.process(discussionRequest.getHtml(), imageLinks);
        } else {
            imageLinks = null;
            modifiedHtml = discussionRequest.getHtml();
        }
        return new DiscussionImagesAndHtmlRecord(
                imageLinks,
                modifiedHtml
        );
    }
}
