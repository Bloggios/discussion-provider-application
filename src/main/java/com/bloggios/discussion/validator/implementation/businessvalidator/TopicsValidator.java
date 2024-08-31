package com.bloggios.discussion.validator.implementation.businessvalidator;

import com.bloggios.discussion.constants.DataExceptionCodes;
import com.bloggios.discussion.constants.ResponseErrorMessageConstants;
import com.bloggios.discussion.exception.payloads.BadRequestException;
import com.bloggios.discussion.payload.TopicsYmlDataProvider;
import com.bloggios.discussion.validator.BusinessValidator;
import com.bloggios.discussion.ymlparser.TopicDataParser;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.web.mappings.MappingsEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.validator.implementation.businessvalidator
 * Created_on - August 31 - 2024
 * Created_at - 22:45
 */

@Component
@RequiredArgsConstructor
public class TopicsValidator implements BusinessValidator<List<String>> {

    private final TopicDataParser topicDataParser;
    private final MappingsEndpoint mappingsEndpoint;

    @Override
    public void validate(List<String> strings) {
        List<String> storedTags = topicDataParser
                .provider
                .values()
                .stream()
                .map(TopicsYmlDataProvider::getTag)
                .toList();
        if (strings.size() > 5) {
            throw new BadRequestException(DataExceptionCodes.TAGS_LIMIT_EXCEED);
        }
        Map<Boolean, List<String>> partitionedList = strings
                .stream()
                .collect(Collectors.partitioningBy(storedTags::contains));
        List<String> notPresent = partitionedList.get(false);
        if (!CollectionUtils.isEmpty(notPresent)) {
            throw new BadRequestException(
                    DataExceptionCodes.TAGS_NOT_PRESENT,
                    String.format(ResponseErrorMessageConstants.TAGS_NOT_PRESENT, String.join(", ", notPresent))
            );
        }
    }
}
