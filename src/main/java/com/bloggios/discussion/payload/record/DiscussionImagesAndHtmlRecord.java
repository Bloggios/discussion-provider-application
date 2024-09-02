package com.bloggios.discussion.payload.record;

import com.bloggios.discussion.modal.embeddable.ImageLinksEntity;

import java.util.List;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - blog-provider-application
 * Package - com.bloggios.blog.payload.record
 * Created_on - June 01 - 2024
 * Created_at - 19:31
 */

public record DiscussionImagesAndHtmlRecord(
        List<ImageLinksEntity> imageLinks,
        String modifiedHtml
) {
}
