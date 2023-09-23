package ex.MultipartStreamUpload.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.fileupload.MultipartStream;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final RestTemplate restTemplate;
    private static final String CONTENT_DISPOSITION_KEY = "Content-Disposition:";

    public void multipartStreamUpload(String url, MultipartStream multipartStream) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();

        boolean nextPart = multipartStream.skipPreamble();

        while (nextPart) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            String multipartStreamHeader = multipartStream.readHeaders();

            multipartStream.readBodyData(outputStream);
            byte[] byteArray = outputStream.toByteArray();

//            printHeadersAndBody(multipartStreamHeader, byteArray);

            ContentDisposition disposition = getContentDisposition(getOriginalFileName(multipartStreamHeader));

            HttpEntity<ByteArrayResource> fileEntity = getFileEntityByByteArray(disposition, byteArray);
            multiValueMap.add("file", fileEntity);

            nextPart = multipartStream.readBoundary();
        }

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(multiValueMap, headers);
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    public void multipartFileUpload(String url, List<MultipartFile> multipartFiles) throws IOException {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();

        for (MultipartFile multipartFile : multipartFiles) {

            ContentDisposition disposition = getContentDisposition(multipartFile.getOriginalFilename());

            byte[] byteArray = multipartFile.getBytes();

//            printHeadersAndBody(disposition.toString(), byteArray);

            HttpEntity<ByteArrayResource> fileEntity = getFileEntityByByteArray(disposition, byteArray);
            multiValueMap.add("file", fileEntity);
        }

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(multiValueMap, headers);
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    private static String getOriginalFileName(String streamHeaders) {
        String[] lines = streamHeaders.split("\\n");
        for (String line : lines) {
            if (line.contains(CONTENT_DISPOSITION_KEY)) {
                String contentDisposition = line.substring(line.indexOf(CONTENT_DISPOSITION_KEY) + CONTENT_DISPOSITION_KEY.length());
                String[] parts = contentDisposition.split(";");
                for (String part : parts) {
                    if (part.trim().startsWith("filename")) {
                        String filename = part.substring(part.indexOf("=") + 1).trim();
                        filename = filename.replaceAll("\"", "");
                        return filename;
                    }
                }
            }
        }
        throw new IllegalArgumentException("this is not a file");
    }

    public void printHeadersAndBody(String multipartDataHeader, byte[] multipartDataBodyBytes) {
        System.out.println("==================================");
        System.out.println("multipart data's headers: " + multipartDataHeader.trim());

        System.out.print("bodyByteData: ");
        for (byte aByte : multipartDataBodyBytes) {
            System.out.print(aByte + " ");
        }
        System.out.println("==================================");
    }

    public ContentDisposition getContentDisposition(String originFileName) {
        return ContentDisposition
                .builder("form-data")
                .name("file")
                .filename(originFileName)
                .build();
    }

    public HttpEntity<ByteArrayResource> getFileEntityByByteArray(ContentDisposition disposition, byte[] byteArray) {

        MultiValueMap<String, String> innerHeader = new LinkedMultiValueMap<>();
        innerHeader.add(HttpHeaders.CONTENT_DISPOSITION, disposition.toString());
        return new HttpEntity<>(new ByteArrayResource(byteArray), innerHeader);
    }

}
