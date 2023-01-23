package com.github.ryuzu.TestWebServer.file.operation.models.information;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.ryuzu.TestWebServer.utilities.FileOperationUtilities;
import lombok.Value;

import java.io.File;
import java.math.BigDecimal;

@Value
public class FileInformationEntity {
    String name;
    BigDecimal size;
    String unit;
    String lastUpdate;
    String lastEditor;
    String type;

    public FileInformationEntity(File file) {
        this.name = file.getName();
        this.size = FileOperationUtilities.getFileSize(file.length());
        this.unit = FileOperationUtilities.getUnit(file.length());
        this.type = file.isFile() ? "file" : "directory";
        this.lastUpdate = null;
        this.lastEditor = null;
    }
}
