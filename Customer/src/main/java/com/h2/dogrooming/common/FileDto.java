package com.h2.dogrooming.common;

import lombok.Data;

@Data
public class FileDto {
    private String uuid;
    private String fileName;
    private String contentType;

    public FileDto(String uuid, String fileName, String contentType) {
        this.uuid = uuid;
        this.fileName = fileName;
        this.contentType = contentType;
    }
}
