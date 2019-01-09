package pl.michalkruk.cinema.util;

import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class FileService {

    public static String encodeImageWithBase64(String path) {
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(fileContent);
    }

    public static String storeFile(String path, MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println("FILENAME: " + fileName);
        String generatedFileName = UUID.randomUUID().toString();
        System.out.println("Generated filename: " + generatedFileName);

        try {
            Files.copy(file.getInputStream(), Paths.get(path + generatedFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return generatedFileName;
    }
}
