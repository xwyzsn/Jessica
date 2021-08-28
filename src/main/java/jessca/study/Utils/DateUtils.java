package jessca.study.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Component
public class DateUtils {

    public String addDate(Date date, Integer days){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        String formatDate = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);
        String finalDate = simpleDateFormat.format(calendar.getTime());
        return finalDate;
    }


}
