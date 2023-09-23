package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EsApplication {

	public static void main(String[] args) {
//		System.out.println("=========================="); // argument 사용 예시
//		for (String arg : args) {
//			System.out.println("---------------------------");
//			String[] split = arg.split("=");
//			System.out.println(split[0]);
//			System.out.println(split[1]);
//			System.out.println("---------------------------");
//		}
//		System.out.println("==========================");
		SpringApplication.run(EsApplication.class, args);
	}

}
