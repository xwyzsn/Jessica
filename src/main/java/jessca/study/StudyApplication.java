package jessca.study;

import jessca.study.service.MailService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan("*.mapper")
@EnableScheduling
@EnableAsync
public class StudyApplication {



	public static void main(String[] args) {

		SpringApplication.run(StudyApplication.class, args);
	}


}
