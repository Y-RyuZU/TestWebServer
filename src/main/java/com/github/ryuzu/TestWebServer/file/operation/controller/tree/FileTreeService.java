package com.github.ryuzu.TestWebServer.file.operation.controller.tree;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
public class FileTreeService {
    public FileTreeEntity getFileTree(File file) {
        return new FileTreeEntity(file);
    }

    public DirectoryTreeEntity getDirectoryTree(File file) {
        return new DirectoryTreeEntity(file);
    }

    record FileTreeEntity(String name, String path, List<String> files, FileTreeEntity[] children) {
        public FileTreeEntity(File file) {
            this(file.getName(), file.getPath(), List.of(file.list()), Arrays.stream(file.listFiles(File::isDirectory)).map(FileTreeEntity::new).toArray(FileTreeEntity[]::new));
        }
    }

    record DirectoryTreeEntity(String path, String name, DirectoryTreeEntity[] children) {
        public DirectoryTreeEntity(File file) {
            this(
                    file.getPath().replace((System.getProperty("user.dir") + "/" + "src/").replace("/" , "\\"), ""),
                    file.getName(), Arrays.stream(file.listFiles(File::isDirectory)).map(DirectoryTreeEntity::new).toArray(DirectoryTreeEntity[]::new));
        }
    }
}
