import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

public class Test {

	public static void main(String[] args) {
		
	LocalDate date;
		
	// Problem 1
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		date = LocalDate.of(2022, 5, 3);
		System.out.println("Date in 52 days : " + date.plusDays(52).format(dateFormatter));
		System.out.println("Date 86 days ago : " + date.minusDays(86).format(dateFormatter));
		
	// Problem 2
		
		date = LocalDate.parse("2021-11-12");
		System.out.println(ChronoUnit.DAYS.between(date, LocalDate.now()));
		
	// Problem 3
		
		String value = "$5,231.11";
		float num = Float.parseFloat(value.replaceAll("[^0-9.]", "")) * 3;
		value = String.format("$%,.2f", num);
		System.out.println(value);

	}
	

}
