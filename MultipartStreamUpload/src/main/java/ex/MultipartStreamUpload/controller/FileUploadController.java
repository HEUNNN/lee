package ex.MultipartStreamUpload.controller;

import ex.MultipartStreamUpload.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.fileupload.MultipartStream;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping("/multipartstream-upload")
    public void upload(HttpServletRequest request) throws IOException {
        MultipartStream multipartStream = new MultipartStream(request.getInputStream(), getBoundary(request.getContentType()).getBytes());
        fileUploadService.multipartStreamUpload("http://localhost:8081/multipart-receive", multipartStream);
    }

    @PostMapping("/multipartfile-upload")
    public String multipartFileUpload(@RequestParam(value = "file", required = false) List<MultipartFile> multipartFiles) throws IOException {
        fileUploadService.multipartFileUpload("http://localhost:8081/multipart-receive", multipartFiles);
        return "Hello";
    }

    private static String getBoundary(String contentType) {
        String[] parts = contentType.split(";");
        for (String part : parts) {
            part = part.trim();
            if (part.startsWith("boundary=")) {
                return part.substring("boundary=".length());
            }
        }
        return null;
    }
}
