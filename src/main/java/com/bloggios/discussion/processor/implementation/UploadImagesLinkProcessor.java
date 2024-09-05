package com.bloggios.discussion.processor.implementation;

import com.bloggios.discussion.constants.EnvironmentConstants;
import com.bloggios.discussion.constants.InternalExceptionCodes;
import com.bloggios.discussion.exception.payloads.BadRequestException;
import com.bloggios.discussion.file.UploadFile;
import com.bloggios.discussion.modal.embeddable.ImageLinksEntity;
import com.bloggios.discussion.payload.record.UploadImagePayloadRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.processor.implementation
 * Created_on - September 02 - 2024
 * Created_at - 23:22
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class UploadImagesLinkProcessor implements Function<UploadImagePayloadRecord, List<ImageLinksEntity>> {

    private final UploadFile uploadFile;
    private final Environment environment;

    @Override
    public List<ImageLinksEntity> apply(UploadImagePayloadRecord uploadImagePayloadRecord) {
        List<MultipartFile> images = uploadImagePayloadRecord.files();
        List<CompletableFuture<ImageLinksEntity>> futures = images
                .stream()
                .map(image -> uploadAsync(uploadImagePayloadRecord, image))
                .toList();

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        return allOf
                .thenApply(future -> futures
                        .stream()
                        .map(CompletableFuture::join)
                        .toList()).join();
    }

    private CompletableFuture<ImageLinksEntity> uploadAsync(UploadImagePayloadRecord uploadImagePayloadRecord, MultipartFile image) {
        UploadImagePayloadRecord record = new UploadImagePayloadRecord(
                uploadImagePayloadRecord.path(),
                uploadImagePayloadRecord.userId(),
                image
        );
        return CompletableFuture.supplyAsync(() -> {
            try {
                String imageName = uploadFile.apply(record);
                String type = imageName.substring(imageName.lastIndexOf("."));
                return ImageLinksEntity
                        .builder()
                        .size(String.valueOf(image.getSize()))
                        .type(type)
                        .name(imageName)
                        .link(generateLink(imageName, uploadImagePayloadRecord.uploadFor()))
                        .build();
            } catch (BadRequestException e) {
                throw new BadRequestException(InternalExceptionCodes.ASYNC_IMAGE_UPLOAD_ERROR);
            }
        }).exceptionally(ex -> {
            log.error("""
                    Exception Occurred While Uploading Images Asynchronously
                    Message : {}
                    Stack Trace : {}
                    """, ex.getMessage(), ex.getStackTrace());
            throw new BadRequestException(InternalExceptionCodes.INTERNAL_ERROR);
        });
    }

    private String generateLink(String imageName, String uploadFor) {
        String profile = environment.getProperty(EnvironmentConstants.APPLICATION_PROFILE);
        String url = environment.getProperty(EnvironmentConstants.ASSETS);
        return url +
                "/" +
                uploadFor +
                "/" +
                LocalDate.now().getMonth().toString() +
                "/" +
                imageName;
    }
}
