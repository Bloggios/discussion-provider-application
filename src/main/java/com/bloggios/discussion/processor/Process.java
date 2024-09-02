package com.bloggios.discussion.processor;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.processor
 * Created_on - September 02 - 2024
 * Created_at - 23:19
 */

@FunctionalInterface
public interface Process<A> {

    void process(A a);
}
