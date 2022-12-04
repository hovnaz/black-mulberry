package com.black.mulberry.core.util;

import com.black.mulberry.core.exception.FileNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Random;

@Component
@Slf4j
public class IOUtil {

    public byte[] getAllBytesByUrl(String fileUrl) {
        try {
            log.info("request get image");
            InputStream inputStream = new FileInputStream(fileUrl);
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            log.error("fail to take image");
            throw new FileNotExistException(e.getMessage());
        }
    }

    public String saveImage(String path, MultipartFile file) {
        log.info("Request save file to path: {}", path);
        if (!file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newFile = new File(path + fileName);
            try {
                file.transferTo(newFile);
            } catch (IOException e) {
                log.error("Fail to save image with filename: {}", file.getName());
                throw new FileNotExistException("Fail to save image with filename: " + fileName);
            }
            return fileName;
        }
        log.error("Fail to save image");
        throw new FileNotExistException("File not found or is Empty");
    }
}
