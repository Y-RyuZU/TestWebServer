package com.github.ryuzu.TestWebServer.file.operation.controller.upload;

import com.github.ryuzu.TestWebServer.file.operation.models.log.FileOperationLog;
import com.github.ryuzu.TestWebServer.file.operation.models.log.FileOperationLogType;
import com.github.ryuzu.TestWebServer.security.service.AccountDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileUploadService {
    public void upload(AccountDetails details, File folder, MultipartFile file) {
        try(var stream = new BufferedOutputStream(new FileOutputStream(folder))) {
            stream.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        FileOperationLog.builder().account(details.getUser())
//                .path(folder.getPath() + "/" + file.getOriginalFilename())
//                .type(FileOperationLogType.UPLOAD)
//                .build().carve();
    }
}
