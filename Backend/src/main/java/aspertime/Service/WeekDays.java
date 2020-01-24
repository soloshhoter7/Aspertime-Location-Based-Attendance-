package aspertime.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class WeekDays {
	public ArrayList<String> ReturnWeekDays() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		ArrayList<String> WeekDates = new ArrayList<String>();
		LocalDateTime now = LocalDateTime.now();
		for (int i = 1; i <= 7; i++) {
			LocalDateTime date = now.minusDays(i);
			WeekDates.add(date.format(format));
		}
		System.out.println(WeekDates);
		return WeekDates;
	}	
}