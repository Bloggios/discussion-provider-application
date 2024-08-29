package com.bloggios.discussion.utils;

import lombok.experimental.UtilityClass;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.utils
 * Created_on - August 29 - 2024
 * Created_at - 23:29
 */

@UtilityClass
public class WordsCounter {

    public static int countWords(String input) {
        if (Objects.isNull(input) || ObjectUtils.isEmpty(input)) {
            return 0;
        }
        String[] words = input.split("\\s+");
        return (int)Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .count();
    }
}
