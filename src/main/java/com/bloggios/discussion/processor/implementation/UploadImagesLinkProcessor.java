package com.bloggios.discussion.processor.implementation;

import com.bloggios.discussion.modal.embeddable.ImageLinksEntity;
import com.bloggios.discussion.payload.record.UploadImagePayloadRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
public class UploadImagesLinkProcessor implements Function<UploadImagePayloadRecord, List<ImageLinksEntity>> {

    @Override
    public List<ImageLinksEntity> apply(UploadImagePayloadRecord uploadImagePayloadRecord) {
        List<MultipartFile> images = uploadImagePayloadRecord.files();

        return List.of();
    }
}
