package jessca.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("*.mapper")
public class StudyApplication {

	public static void main(String[] args) {

		SpringApplication.run(StudyApplication.class, args);
	}

}
