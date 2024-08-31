package com.bloggios.discussion.payload.response;

import com.bloggios.discussion.payload.TopicsYmlDataProvider;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.payload.response
 * Created_on - August 30 - 2024
 * Created_at - 22:48
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicsListResponse {

    private List<TopicsYmlDataProvider> object;
}
