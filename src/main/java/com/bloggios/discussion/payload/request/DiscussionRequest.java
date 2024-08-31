package com.bloggios.discussion.payload.request;

import com.bloggios.authenticationconfig.payload.AuthenticatedUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.apache.commons.lang.mutable.Mutable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.payload.request
 * Created_on - August 29 - 2024
 * Created_at - 23:15
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscussionRequest {

    private String title;
    private String html;
    private String detailsText;
    private List<MultipartFile> images;
    private List<String> topics;
    private AuthenticatedUser authenticatedUser;
}
