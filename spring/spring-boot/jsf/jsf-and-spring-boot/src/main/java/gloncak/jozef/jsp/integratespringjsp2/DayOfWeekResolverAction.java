package gloncak.jozef.jsp.integratespringjsp2;

import gloncak.jozef.jsp.integratespringjsp2.api.DayOfWeekResolverService;
import gloncak.jozef.jsp.integratespringjsp2.model.DayOfWeekResolverForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.io.Serializable;

@Component
@RequestScope
public class DayOfWeekResolverAction implements Serializable {
    private final DayOfWeekResolverForm dayOfWeekResolverForm;
    private final DayOfWeekResolverService dayOfWeekResolverService;

    public DayOfWeekResolverAction(@Autowired DayOfWeekResolverForm dayOfWeekResolverForm,
                                   @Autowired DayOfWeekResolverService dayOfWeekResolverService) {
        this.dayOfWeekResolverForm = dayOfWeekResolverForm;
        this.dayOfWeekResolverService = dayOfWeekResolverService;
    }

    public void determineDayOfWeek() {
        int year = dayOfWeekResolverForm.getYear();
        int month = dayOfWeekResolverForm.getMonth();
        int dayOfMonth = dayOfWeekResolverForm.getDayOfMonth();

        String dayOfWeekName = dayOfWeekResolverService.determineDayOfWeek(year, month, dayOfMonth);
        dayOfWeekResolverForm.setDayOfWeek(dayOfWeekName);
    }
}