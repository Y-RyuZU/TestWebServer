package com.github.ryuzu.TestWebServer.file.operation.controller.tree;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
            this(file.getName(), file.getPath(), List.of(file.list()), Arrays.stream(Optional.ofNullable(file.listFiles(File::isDirectory)).orElse(new File[0])).map(FileTreeEntity::new).toArray(FileTreeEntity[]::new));
        }
    }

    record DirectoryTreeEntity(String path, String name, DirectoryTreeEntity[] children) {
        public DirectoryTreeEntity(File file) {
            this(
                    file.getPath().replace(System.getProperty("user.dir"), "").replace("\\" , "/"),
                    file.getName(),
                    Arrays.stream(Optional.ofNullable(file.listFiles(File::isDirectory)).orElse(new File[0])).map(DirectoryTreeEntity::new).toArray(DirectoryTreeEntity[]::new)
            );
        }
    }
}
