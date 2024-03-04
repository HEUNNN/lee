package lee.project.sample.convert.util;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class FileUtil {

    public String readFiles(String fileName) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            System.out.println("inputStream is null.");
            return "";
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader).lines().collect(Collectors.joining());
    }
}
