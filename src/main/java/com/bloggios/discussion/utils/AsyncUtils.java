package com.bloggios.discussion.utils;

import com.bloggios.discussion.constants.InternalExceptionCodes;
import com.bloggios.discussion.exception.payloads.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.utils
 * Created_on - September 02 - 2024
 * Created_at - 22:55
 */

@Slf4j
public class AsyncUtils {

    private static final Logger logger = LoggerFactory.getLogger(AsyncUtils.class);

    public static <T> T getAsyncResult(CompletableFuture<T> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException ex) {
            throw handleException(ex.getCause());
        }
    }

    private static RuntimeException handleException(Throwable cause) {
        if (cause instanceof BadRequestException badRequestException) {
            return badRequestException;
        } else {
            logger.error("Exception Caused in Async Utils : {}", cause.getMessage(), cause);
            return new BadRequestException(InternalExceptionCodes.INTERNAL_ERROR);
        }
    }

    private AsyncUtils() {}
}
