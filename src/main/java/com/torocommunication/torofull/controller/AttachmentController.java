package com.torocommunication.torofull.controller;



import com.torocommunication.torofull.constants.FileUploadUtil;
import com.torocommunication.torofull.entities.Attachment;
import com.torocommunication.torofull.models.Formatter;
import com.torocommunication.torofull.service.serviceInterface.AttachmentServiceInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Amemorte
 * @since 11/08/2022
 */
@RestController
@RequestMapping("api/uploads")
@CrossOrigin("*")
public class AttachmentController extends DataFormatter<String> {


    private final AttachmentServiceInterface attachmentService;

    public AttachmentController(AttachmentServiceInterface attachmentService) {
        this.attachmentService = attachmentService;
    }


    @PostMapping("/saveFile")
    public Formatter<List<String>> saveProduit(@RequestParam("file") MultipartFile[] files) throws Exception {

        List<String> values = new ArrayList<>() ;
        for (MultipartFile file : files) {
            Attachment attachment = null;
            String downloadURl = "";

            attachment = attachmentService.saveAttachment(file);

            String fileName1 = attachment.getFileName();


            String uploadDIr = "doc-pdf/" + attachment.getId();

            FileUploadUtil.saveFile(uploadDIr, fileName1, file);


            downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(attachment.getId())
                    .toUriString();
          values.add(attachment.getId()+"");
        }

        return renderDataArray(true,values,"files uploaded");
    }


    private static final String EXTERNAL_FILE_PATH = "doc-pdf/";

    @RequestMapping("/file/{fileId}")
    public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileId") String fileId) throws Exception {

        String fileName;
        Attachment attachment = attachmentService.getAttachment(fileId);
        fileName=attachment.getFileName();

        File file = new File(EXTERNAL_FILE_PATH +"/"+attachment.getId()+"/"+ fileName);
        if (file.exists()) {

            //get the mimetype
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                //unknown mimetype so set the mimetype to application/octet-stream
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);


            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));


            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());

        }
    }
}
