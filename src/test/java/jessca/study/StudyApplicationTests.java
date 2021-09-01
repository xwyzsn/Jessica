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
	public String getToday(){
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		String formatDate = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);
		return simpleDateFormat.format(calendar.getTime());
	}

	@Test
	void contextLoads() {

	}

}
