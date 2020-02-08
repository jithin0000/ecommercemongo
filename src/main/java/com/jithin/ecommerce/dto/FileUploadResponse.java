package com.jithin.ecommerce.dto;

import lombok.Data;

@Data
public class FileUploadResponse {
    private final String fileName;

    public FileUploadResponse(String fileName) {
        this.fileName = fileName;
    }
}
