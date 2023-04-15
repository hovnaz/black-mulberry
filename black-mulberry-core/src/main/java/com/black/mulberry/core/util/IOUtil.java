package com.black.mulberry.core.util;

import com.black.mulberry.core.exception.FileNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class provides utility methods for working with files.
 */
@Component
@Slf4j
public class IOUtil {

    /**
     * Reads all bytes from a file with the given URL.
     *
     * @param fileUrl the URL of the file to read
     * @return a byte array containing the contents of the file
     * @throws FileNotExistException if the file does not exist or cannot be read
     */
    public byte[] getAllBytesByUrl(String fileUrl) {
        try {
            log.info("Request to get image");
            InputStream inputStream = new FileInputStream(fileUrl);
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            log.error("Failed to read image with URL: {}", fileUrl);
            throw new FileNotExistException(e.getMessage());
        }
    }

    /**
     * Saves a file to the specified path.
     *
     * @param path the path to save the file to
     * @param file the file to save
     * @return the name of the saved file
     * @throws FileNotExistException if the file cannot be saved
     */
    public String saveImage(String path, MultipartFile file) {
        log.info("Request to save file to path: {}", path);
        if (!file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newFile = new File(path + fileName);
            try {
                file.transferTo(newFile);
            } catch (IOException e) {
                log.error("Failed to save image with filename: {}", file.getName());
                throw new FileNotExistException("Failed to save image with filename: " + fileName);
            }
            return fileName;
        }
        log.error("Failed to save image");
        throw new FileNotExistException("File not found or is empty");
    }
}
