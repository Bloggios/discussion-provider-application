package com.bloggios.discussion.utils;

import com.bloggios.discussion.modal.embeddable.TopicsEmbeddable;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.utils
 * Created_on - September 05 - 2024
 * Created_at - 22:50
 */

@UtilityClass
public class TopicsTransformUtil {

    public static List<TopicsEmbeddable> getTopicsEmbeddableList(List<String> topics) {
        if (CollectionUtils.isEmpty(topics)) return null;
        return topics
                .stream()
                .map(topic -> TopicsEmbeddable.builder().topic(topic).build())
                .toList();
    }
}
