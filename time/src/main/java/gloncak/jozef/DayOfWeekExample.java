package gloncak.jozef;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

public class DayOfWeekExample {

    public DayOfWeekExample() {
        String head = "DayOfWeek enumeration";
        System.out.format("%s%n",head);
        underline(head);
    }

    public static void underline(final String str) {
        for(int i=0; i<str.length(); i++) {
            System.out.format("-");
        }
        System.out.format("%n");
    }

    public void exTextStyle() {
        System.out.format("\t* enum to string conversion%n");

        System.out.format("\t\t text style narrow:\t%s%n", DayOfWeek.FRIDAY.getDisplayName(TextStyle.NARROW,
                Locale.getDefault()));
        System.out.format("\t\t text style full:\t%s%n", DayOfWeek.FRIDAY.getDisplayName(TextStyle.FULL, Locale
                .getDefault()));
    }
}
