package pl.michalkruk.cinema.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

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
}
