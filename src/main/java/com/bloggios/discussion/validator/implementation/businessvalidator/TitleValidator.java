package com.bloggios.discussion.validator.implementation.businessvalidator;

import com.bloggios.discussion.constants.DataExceptionCodes;
import com.bloggios.discussion.exception.payloads.BadRequestException;
import com.bloggios.discussion.utils.WordsCounter;
import com.bloggios.discussion.validator.BusinessValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.validator.implementation.businessvalidator
 * Created_on - August 29 - 2024
 * Created_at - 23:19
 */

@Component
public class TitleValidator implements BusinessValidator<String> {

    @Override
    public void validate(String title) {
        if (Objects.isNull(title) || ObjectUtils.isEmpty(title)) {
            throw new BadRequestException(DataExceptionCodes.TITLE_MANDATORY);
        }
        int words = WordsCounter.countWords(title);
        if (words > 25)
            throw new BadRequestException(DataExceptionCodes.TITLE_WORD_EXCEED);
        if (title.length() > 150)
            throw new BadRequestException(DataExceptionCodes.TITLE_LENGTH_EXCEED);
    }
}
