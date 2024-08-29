package com.bloggios.discussion.validator;

import com.bloggios.discussion.payload.request.DiscussionRequest;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.validator
 * Created_on - August 29 - 2024
 * Created_at - 23:18
 */

@FunctionalInterface
public interface BusinessValidator<A> {

    void validate(A a);
}
