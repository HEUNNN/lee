package ex.MultipartStreamReceive.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class FileReceiveController {

    private static final String STORAGE_PATH = "/Users/leehyeeun/Documents/streamUpload-storage/";

    @PostMapping("/multipart-receive")
    public String multipart(@RequestPart List<MultipartFile> file) throws IOException {

        for (MultipartFile multipartFile : file) {

            String path = STORAGE_PATH + multipartFile.getOriginalFilename();

            try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
                fileOutputStream.write(multipartFile.getBytes());
                System.out.println("saved: " + path);
            }
        }
        return "Hello";
    }
}
