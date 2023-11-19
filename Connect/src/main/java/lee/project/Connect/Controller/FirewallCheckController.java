package lee.project.Connect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/check")
public class FirewallCheckController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/firewall")
    public List<String> checkFirewall() {
        List<String> urlList = new ArrayList<>();
        urlList.add("https://www.naver.com/");
        urlList.add("http://14.42.2.189");

        List<String> connectedFail = new ArrayList<>();

        for (String url : urlList) {
            try {
                ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
                System.out.println(forEntity.getHeaders());
            } catch (ResourceAccessException re) {
                connectedFail.add(url);
            } catch (Exception e) {
                System.out.println("exception ocurred, msg = " + e.getMessage());
            }
        }
        return connectedFail;
    }
}
