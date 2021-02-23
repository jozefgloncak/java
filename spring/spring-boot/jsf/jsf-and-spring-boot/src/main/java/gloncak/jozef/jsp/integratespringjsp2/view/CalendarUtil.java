package gloncak.jozef.jsp.integratespringjsp2.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class CalendarUtil {
    static final List<Integer> roky = Arrays.asList(2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029,
            2030, 2031, 2032, 2033, 2034, 2035, 2036, 2037, 2038, 2039, 2040);

    static final List<Integer> mesiace = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    static final List<Integer> dni28 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 25, 26, 27, 28);
    static final List<Integer> dni29;
    static final List<Integer> dni30;
    static final List<Integer> dni31;

    private static final Integer APRIL = 4;
    private static final Integer JUN = 6;
    private static final Integer SEPTEMBER = 9;
    private static final Integer NOVEMBER = 11;
    private static final Integer FEBRUAR = 2;

    static {
        dni29 = new ArrayList<>(dni28);
        dni29.add(29);

        dni30 = new ArrayList<>(dni29);
        dni30.add(30);

        dni31 = new ArrayList<>(dni30);
        dni31.add(31);
    }

    static List<Integer> vratListSDnamiPreMesiac(Integer mesiac, Integer rok) {
        if (mesiac == null) {
            return Collections.EMPTY_LIST;
        }
        if (isMesiac30Dni(mesiac)) {
            return CalendarUtil.dni30;
        }
        if (isFebruar(mesiac)) {
            if (isPriestupnyRok(rok)) {
                return CalendarUtil.dni29;
            }
            return CalendarUtil.dni28;
        }
        return CalendarUtil.dni31;
    }

    private static boolean isPriestupnyRok(Integer rok) {
        return rok % 4 == 0;
    }

    private static boolean isFebruar(Integer mesiac) {
        return mesiac == FEBRUAR;
    }

    private static boolean isMesiac30Dni(Integer mesiac) {
        return mesiac == APRIL || mesiac == JUN || mesiac == SEPTEMBER || mesiac == NOVEMBER;
    }

}
