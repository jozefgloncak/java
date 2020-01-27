package gloncak.jozef.jsp.integratespringjsp2.service;

import gloncak.jozef.jsp.integratespringjsp2.api.DayOfWeekResolverService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class DayOfWeekResolverServiceImpl implements DayOfWeekResolverService {

    @Override
    public String determineDayOfWeek(int year, int month, int dayOfMonth) {
        LocalDate resolvedLocalDate = LocalDate.of(year, month, dayOfMonth);
        return resolvedLocalDate.getDayOfWeek().getDisplayName(TextStyle.FULL,
                Locale.getDefault());
    }

}
