package com.torocommunication.torofull.service.serviceInterface;



import com.torocommunication.torofull.entities.Attachment;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Amemorte
 * @since 11/08/2022
 */
public interface AttachmentServiceInterface {
    Attachment saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;
}
