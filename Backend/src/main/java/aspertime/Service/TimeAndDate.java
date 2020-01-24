package aspertime.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class TimeAndDate {
	private String time;
	private String date;

	public TimeAndDate() {
		Date date1 = new Date();
		Calendar cal = Calendar.getInstance();
		date1 = cal.getTime();
		TimeZone istTimeZone =TimeZone.getTimeZone("Asia/Kolkata");
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setTimeZone(istTimeZone);
		dateFormat1.setTimeZone(istTimeZone);
		String time = dateFormat.format(date1);
		String date = dateFormat1.format(date1);
		this.time = time;
		this.date = date;

	}

	public String getTime() {
		return time;
	}

	public String getDate() {
		return date;
	}
  
	public int getRemainingDays() {
		Calendar cal = Calendar.getInstance();
		int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int remainingDays = totalDays - dayOfMonth;
        return remainingDays;
	}
}