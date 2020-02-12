package gloncak.jozef.jsp.integratespringjsp2;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public class DateConversionTest {

//    @Test
    public void simpleDateConversionTest() throws ParseException {
        DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        Date parsedDate = dateInstance.parse("4.4.2019");
        Instant instant = parsedDate.toInstant();
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        System.out.println(localDate);

    }

//    @Test
    public void simpleDateFormationTest() {
        DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        Instant instant = LocalDate.of(2019, 4, 4).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        String formatted =
                dateInstance.format(Date.from(instant));
        System.out.println(formatted);
    }
}
