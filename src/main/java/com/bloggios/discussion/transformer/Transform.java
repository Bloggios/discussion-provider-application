package com.bloggios.discussion.transformer;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.transformer
 * Created_on - September 05 - 2024
 * Created_at - 22:44
 */

@FunctionalInterface
public interface Transform<A, B> {

    A transform(B b);
}
