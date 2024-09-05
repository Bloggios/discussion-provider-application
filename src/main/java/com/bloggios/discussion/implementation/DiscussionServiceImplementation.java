package com.bloggios.discussion.implementation;

import com.bloggios.discussion.dao.implementation.postgres.DiscussionEntityDao;
import com.bloggios.discussion.enums.DaoStatus;
import com.bloggios.discussion.modal.DiscussionEntity;
import com.bloggios.discussion.payload.record.DiscussionImagesAndHtmlRecord;
import com.bloggios.discussion.payload.request.DiscussionRequest;
import com.bloggios.discussion.payload.response.ModuleResponse;
import com.bloggios.discussion.processor.implementation.GenerateImagesLinkWithModifiedHtml;
import com.bloggios.discussion.processor.implementation.HtmlDataManipulation;
import com.bloggios.discussion.service.DiscussionService;
import com.bloggios.discussion.transformer.implementation.DiscussionRequestToEntityTransformer;
import com.bloggios.discussion.validator.implementation.exhibitor.DiscussionRequestExhibitor;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class DiscussionServiceImplementation implements DiscussionService {

    private final DiscussionRequestExhibitor discussionRequestExhibitor;
    private final GenerateImagesLinkWithModifiedHtml generateImagesLinkWithModifiedHtml;
    private final HtmlDataManipulation htmlDataManipulation;
    private final DiscussionRequestToEntityTransformer discussionRequestToEntityTransformer;
    private final DiscussionEntityDao discussionEntityDao;

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
        discussionRequestExhibitor.validate(discussionRequest);
        DiscussionImagesAndHtmlRecord imagesAndHtmlRecord = generateImagesLinkWithModifiedHtml.process(discussionRequest);
        String finalHtml = htmlDataManipulation.process(imagesAndHtmlRecord.modifiedHtml());
        DiscussionEntity discussionEntity = discussionRequestToEntityTransformer.transform(discussionRequest, imagesAndHtmlRecord, finalHtml);
        DiscussionEntity discussionEntityResponse = discussionEntityDao.initOperation(DaoStatus.CREATE, discussionEntity);
        // elasticsearch implementation
        return null;
    }
}
