package jessca.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class StudyApplicationTests {

	public String addDate(Date date, Integer days){

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,days);
		String formatDate = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);
		String finalDate = simpleDateFormat.format(calendar.getTime());
		return finalDate;
	}

	@Test
	void contextLoads() {
		Date date = new Date();
		String formatDate = addDate(date,-4);
		System.out.println(formatDate);
	}

}
