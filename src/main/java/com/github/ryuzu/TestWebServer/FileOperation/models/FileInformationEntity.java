package com.github.ryuzu.TestWebServer.FileOperation.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

@Value
public class FileInformationEntity {
    String name;
    int size;
    String unit;
    String lastUpdate;
    String lastEditor;
}
