package lee.project.sample.ApiSample.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

@RestController
public class SampleController {

    @PostMapping("/basic/1")
    public String basicController1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String method = request.getMethod();
        String contentType = request.getHeaders("Content-Type").nextElement();
        ServletInputStream inputStream = request.getInputStream();
        String requestBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println(requestBody);
        return "hello";
    }

    @PostMapping("/basic/2")
    public String basicController2(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> requestBody) throws IOException {
        // Content-Type: application/x-www-form-urlencoded 미지원 -> @RequestBody는 application/x-www-form-urlencoded content-type을 지원하지 않는다.
        // Cotnent-Type: application/json 지원
        String method = request.getMethod();
        String contentType = request.getHeaders("Content-Type").nextElement();
        return "HTTP Method:" + method + ", Content-Type:" + contentType + ", Body=" + requestBody.toString();
    }

    @PostMapping("/basic/3")
    public String basicController3(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> requestBody) throws IOException {
        // Content-Type: application/x-www-form-urlencoded 미지원 -> @RequestBody는 application/x-www-form-urlencoded content-type을 지원하지 않는다.
        // Cotnent-Type: application/json 지원
        String method = request.getMethod();
        String contentType = request.getHeaders("Content-Type").nextElement();
        return "HTTP Method:" + method + ", Content-Type:" + contentType + ", Body=" + requestBody.toString();
    }
}
