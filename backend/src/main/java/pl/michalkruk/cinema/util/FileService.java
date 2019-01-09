package pl.michalkruk.cinema.util;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {

    private final String noImageFoundPath;

    public FileService(@Value("${no.image.location}") String noImageFoundPath) {
        this.noImageFoundPath = noImageFoundPath;
    }

    public String encodeImageWithBase64(String path) {
        File file = new File(path);

        if (!file.exists() || file.isDirectory()) {
            file = new File(noImageFoundPath);
        }

        return getFileBase64(file);
    }

    public String storeFile(String path, MultipartFile file) {
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

    private String getFileBase64(File file) {
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(fileContent);
    }
}
