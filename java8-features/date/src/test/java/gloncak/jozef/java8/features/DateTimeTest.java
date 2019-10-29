package gloncak.jozef.java8.features;


import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTimeTest {

    @Test
    public void setTimeTest() {
        LocalTime localTime = LocalTime.of(14, 45, 35);
        Assert.assertEquals(14, localTime.get(ChronoField.HOUR_OF_DAY));
        Assert.assertEquals(45, localTime.get(ChronoField.MINUTE_OF_HOUR));
        Assert.assertEquals(35, localTime.get(ChronoField.SECOND_OF_MINUTE));
    }

    @Test
    public void parseTimeTest() {
        LocalTime parsedTime = LocalTime.parse("15:16:17.32");
        Assert.assertEquals(15, parsedTime.getHour());
        Assert.assertEquals(16, parsedTime.getMinute());
        Assert.assertEquals(17, parsedTime.getSecond());
        Assert.assertEquals(320000000, parsedTime.getNano());
    }

    @Test
    public void setDateTest() {
        LocalDate localDate = LocalDate.of(2019, Month.APRIL, 30);
        Assert.assertEquals(2019, localDate.get(ChronoField.YEAR));
        Assert.assertEquals(4, localDate.get(ChronoField.MONTH_OF_YEAR));
        Assert.assertEquals(30, localDate.get(ChronoField.DAY_OF_MONTH));
    }

    @Test
    public void parseDateTest() {
        LocalDate parsedDate = LocalDate.parse("1985-03-30");
        Assert.assertEquals(1985, parsedDate.getYear());
        Assert.assertEquals(3, parsedDate.getMonthValue());
        Assert.assertEquals(30, parsedDate.getDayOfMonth());
    }

    @Test
    public void withDateTimeTest() {
        LocalDateTime localDateTime = LocalDateTime.of(2000, Month.AUGUST, 4, 19, 3, 33, 730000000);

        //set concrete date through TemporalField
        LocalDateTime newLocalDate = localDateTime.with(ChronoField.YEAR, 2010).with(ChronoField.MINUTE_OF_HOUR, 15);
        Assert.assertEquals("2010-08-04T19:15:33.730", newLocalDate.toString());

        //set concrete date through TemporalAdjuster
        newLocalDate = localDateTime.with(Month.APRIL).with(Year.of(1900));
        Assert.assertEquals("1900-04-04T19:03:33.730", newLocalDate.toString());

        //set concrete date through concrete method for particular date time part
        newLocalDate = localDateTime.withYear(2019).withMonth(7).withSecond(44);
        Assert.assertEquals("2019-07-04T19:03:44.730", newLocalDate.toString());
    }

    @Test
    public void localDateTimeFromLegacyDate() {
        GregorianCalendar legacyDate = new GregorianCalendar();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(legacyDate.toInstant(), ZoneId.systemDefault());
        Assert.assertEquals(legacyDate.get(Calendar.YEAR), localDateTime.getYear());
        Assert.assertEquals(legacyDate.get(Calendar.MONTH) + 1, localDateTime.getMonthValue());
        Assert.assertEquals(legacyDate.get(Calendar.SECOND), localDateTime.getSecond());
        //..... remaining date time part
    }


    /**
     * Tests that the same moment defined in various time zones is the same numerical value
     * <p>
     * numerical value is obtained as number of ms from begining of epoch.
     */
    @Test
    public void zonedDateTime() {
        ZonedDateTime parsedZonedDateTime = ZonedDateTime.parse("1990-08-23T08:04:32+02:00");
        ZonedDateTime parsedZonedDateTime2 = ZonedDateTime.parse("1990-08-23T06:04:32Z");
        ZonedDateTime parsedZonedDateTime3 = ZonedDateTime.parse("1990-08-22T23:04:32-07:00");
        Assert.assertNotEquals(parsedZonedDateTime.getHour(), parsedZonedDateTime2.getHour());

        long dateTimeInMs1 = parsedZonedDateTime.toInstant().toEpochMilli();
        long dateTimeInMs2 = parsedZonedDateTime2.toInstant().toEpochMilli();
        long dateTimeInMs3 = parsedZonedDateTime3.toInstant().toEpochMilli();
        Assert.assertTrue(dateTimeInMs1 == dateTimeInMs2 && dateTimeInMs2 == dateTimeInMs3);
    }

    /**
     * Use DateTimeFormatter.ofPattern() - to load date and time in custom format
     */
    @Test
    public void fromOneZoneToOtherTest() {
        ZonedDateTime londonTime = ZonedDateTime.of(LocalDateTime.parse("2018 03 14, 13 43 53",
                DateTimeFormatter.ofPattern("yyyy MM dd, HH mm ss")), ZoneId.of("Europe/London"));
        ZonedDateTime kolkataTime = londonTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));  //+05:30
        Assert.assertEquals("19:13", kolkataTime.format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    /**
     * Tests behaviour of plus method of
     */
    @Test
    public void addingTimeUnitToDateTime() {
        LocalDateTime dateTime = LocalDateTime.parse("2004-03-14T20:34:18");
        Assert.assertEquals("2004-03-18T20:34:18",
                dateTime.plus(4, ChronoUnit.DAYS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        Assert.assertEquals("2004-04-17T20:34:18",
                dateTime.plus(34, ChronoUnit.DAYS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        Assert.assertEquals("2004-03-14T22:49:18",
                dateTime.plusMinutes(135).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    /**
     * Tests behaviour of Period
     */
    @Test
    public void periodBetweenDefinedDatesTest() {
        LocalDate endDate1 = LocalDate.parse("2015-10-12");
        LocalDate startDate2 = LocalDate.parse("2014-11-10");
        Period betweenDates = Period.between(startDate2, endDate1);

        Assert.assertEquals(0, betweenDates.getYears());
        Assert.assertEquals(11, betweenDates.get(ChronoUnit.MONTHS));
        Assert.assertEquals(2, betweenDates.getDays());
    }

    /**
     * Test behaviour of Duration
     */
    @Test
    public void durationBetweenDefinedTimesTest() {
        LocalTime startTime = LocalTime.parse("14:43:59");
        LocalTime endTime = LocalTime.parse("15:48:00");
        Duration duration = Duration.between(startTime, endTime);

        Assert.assertEquals(3841, duration.get(ChronoUnit.SECONDS));
        Assert.assertEquals(endTime, startTime.plus(duration));
    }

    @Test
    public void temporalAdjustersTest() {
        LocalDate date = LocalDate.of(2019, 10, 15);
        Assert.assertEquals("2019-10-01",
                date.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ISO_DATE));

        //first Saturday in october
        Assert.assertEquals("2019-10-05",
                date.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.SATURDAY)).format(DateTimeFormatter.ISO_DATE));

        //second Saturday in october
        Assert.assertEquals("2019-10-12",
                date.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.SATURDAY)).format(DateTimeFormatter.ISO_DATE));

        Assert.assertEquals("2019-11-01", date.with(TemporalAdjusters.firstDayOfNextMonth()).format(DateTimeFormatter.ISO_DATE));
        Assert.assertEquals("2020-01-01", date.with(TemporalAdjusters.firstDayOfNextYear()).format(DateTimeFormatter.ISO_DATE));
        Assert.assertEquals("2019-01-01", date.with(TemporalAdjusters.firstDayOfYear()).format(DateTimeFormatter.ISO_DATE));

        //first Tuesday in month
        Assert.assertEquals("2019-10-01", date.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY.TUESDAY)).format(DateTimeFormatter.ISO_DATE));

        //first Wednesday in month
        Assert.assertEquals("2019-10-02", date.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY.WEDNESDAY)).format(DateTimeFormatter.ISO_DATE));

        Assert.assertEquals("2019-10-19", date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).format(DateTimeFormatter.ISO_DATE));

        Assert.assertEquals("2019-10-29",
                date.with(TemporalAdjusters.ofDateAdjuster(dt -> dt.plusWeeks(2))).format(DateTimeFormatter.ISO_DATE));

    }

}
