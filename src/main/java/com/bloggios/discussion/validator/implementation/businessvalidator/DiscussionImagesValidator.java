package com.bloggios.discussion.validator.implementation.businessvalidator;

import com.bloggios.discussion.constants.DataExceptionCodes;
import com.bloggios.discussion.exception.payloads.BadRequestException;
import com.bloggios.discussion.payload.request.DiscussionRequest;
import com.bloggios.discussion.validator.BusinessValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - discussion-provider-application
 * Package - com.bloggios.discussion.validator.implementation.businessvalidator
 * Created_on - August 31 - 2024
 * Created_at - 23:11
 */

@Component
public class DiscussionImagesValidator implements BusinessValidator<DiscussionRequest> {

    @Override
    public void validate(DiscussionRequest discussionRequest) {
        List<MultipartFile> multipartFiles = discussionRequest.getImages();
        if (multipartFiles.size() > 5) throw new BadRequestException(DataExceptionCodes.IMAGES_LIMIT_EXCEEDED);
        checkForDuplicate(multipartFiles);
        multipartFiles.forEach(this::validateImage);
    }

    public void validateImage(MultipartFile multipartFile) {
        if (!isImageByExtension(multipartFile)) throw new BadRequestException(DataExceptionCodes.NOT_IMAGE_TYPE);
        DataSize dataSize = DataSize.ofKilobytes(800);
        if (multipartFile.getSize() > dataSize.toBytes()) {
            throw new BadRequestException(DataExceptionCodes.IMAGE_SIZE_LIMIT_EXCEED);
        }
    }

    private static boolean isImageByExtension(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        if (Objects.isNull(originalFilename)) {
            throw new BadRequestException(DataExceptionCodes.INVALID_IMAGE_NAME);
        }
        String fileExtension = getFileExtension(originalFilename);
        return isImageExtension(fileExtension);
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }

    private static boolean isImageExtension(String extension) {
        String[] imageExtensions = {"jpg", "jpeg", "png", "bmp", "svg"};
        for (String a : imageExtensions) {
            if (extension.equals(a)) {
                return true;
            }
        }
        return false;
    }

    private static void checkForDuplicate(List<MultipartFile> images) {
        HashSet<String> files = new HashSet<>();
        for (MultipartFile file : images) {
            String uniquesIdentifier = file.getOriginalFilename() + "-" + file.getSize();
            if (!files.add(uniquesIdentifier)) {
                throw new BadRequestException(DataExceptionCodes.DUPLICATE_IMAGES_NOT_ALLOWED_TO_UPLOAD);
            }
        }
    }
}
