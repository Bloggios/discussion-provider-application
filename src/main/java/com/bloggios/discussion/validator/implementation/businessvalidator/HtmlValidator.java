package com.bloggios.discussion.validator.implementation.businessvalidator;

import com.bloggios.discussion.constants.DataExceptionCodes;
import com.bloggios.discussion.constants.ResponseErrorMessageConstants;
import com.bloggios.discussion.exception.payloads.BadRequestException;
import com.bloggios.discussion.payload.request.DiscussionRequest;
import com.bloggios.discussion.validator.BusinessValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.validator.implementation.businessvalidator
 * Created_on - September 02 - 2024
 * Created_at - 22:37
 */

@Component
public class HtmlValidator implements BusinessValidator<DiscussionRequest> {

    @Override
    public void validate(DiscussionRequest discussionRequest) {
        String html = discussionRequest.getHtml();
        List<MultipartFile> images = discussionRequest.getImages();
        if (Objects.isNull(html) || ObjectUtils.isEmpty(html))
            throw new BadRequestException(DataExceptionCodes.DETAILS_NOT_PRESENT);
        Document parse = Jsoup.parse(html);
        Elements htmlImages = parse.select("img");
        if (!htmlImages.isEmpty()) {
            if (Objects.isNull(images)) {
                throw new BadRequestException(DataExceptionCodes.IMAGES_NULL,
                        String.format(ResponseErrorMessageConstants.IMAGES_NULL, htmlImages.size()));
            }

            if (htmlImages.size() != images.size()) {
                throw new BadRequestException(DataExceptionCodes.IMAGES_LIST_SIZE_NOT_MATCHED_WITH_HTML,
                        String.format(ResponseErrorMessageConstants.IMAGES_LIST_SIZE_NOT_MATCHED_WITH_HTML, htmlImages.size()));
            }
        } else {
            if (!CollectionUtils.isEmpty(images)) {
                throw new BadRequestException(DataExceptionCodes.IMAGES_LIST_SIZE_NOT_MATCHED_WITH_HTML);
            }
        }
    }
}
