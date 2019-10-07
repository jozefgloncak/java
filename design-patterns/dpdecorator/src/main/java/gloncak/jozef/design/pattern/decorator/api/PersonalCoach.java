package gloncak.jozef.design.pattern.decorator.api;

public interface PersonalCoach extends Coach {
    int getSeatsNumber();

    boolean isToiletVacant();
}
