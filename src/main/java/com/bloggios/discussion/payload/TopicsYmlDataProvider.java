package com.bloggios.discussion.payload;

import lombok.*;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.payload
 * Created_on - August 30 - 2024
 * Created_at - 22:38
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicsYmlDataProvider {

    private String tag;
    private String category;
}
