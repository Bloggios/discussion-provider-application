package com.bloggios.discussion.validator.implementation.businessvalidator;

import com.bloggios.discussion.constants.DataExceptionCodes;
import com.bloggios.discussion.exception.payloads.BadRequestException;
import com.bloggios.discussion.validator.BusinessValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.validator.implementation.businessvalidator
 * Created_on - September 02 - 2024
 * Created_at - 23:01
 */

@Component
public class DiscussionDetailsValidator implements BusinessValidator<String> {

    @Override
    public void validate(String detailsText) {
        if (ObjectUtils.isEmpty(detailsText))
            throw new BadRequestException(DataExceptionCodes.DETAILS_NOT_PRESENT);
        if (detailsText.length() > 2500) {
            throw new BadRequestException(DataExceptionCodes.DISCUSSION_DETAILS_LIMIT_EXCEED);
        }
    }
}
