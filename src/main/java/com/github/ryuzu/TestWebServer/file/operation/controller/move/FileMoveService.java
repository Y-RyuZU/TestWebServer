package com.github.ryuzu.TestWebServer.file.operation.controller.move;

import com.github.ryuzu.TestWebServer.security.entity.AccountDetails;
import com.google.common.io.Files;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FileMoveService {
    public void move(AccountDetails details, File from, File to) throws IOException {
        Files.move(from, to);
//        FileOperationLog.builder().account(details.getUser())
//                .path(from.getPath())
//                .type(FileOperationLogType.MOVE)
//                .detail(to.getPath())
//                .build().carve();
    }
}
