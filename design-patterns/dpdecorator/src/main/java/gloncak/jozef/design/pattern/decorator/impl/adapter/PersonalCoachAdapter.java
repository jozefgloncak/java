package gloncak.jozef.design.pattern.decorator.impl.adapter;

import gloncak.jozef.design.pattern.decorator.api.PersonalCoach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonalCoachAdapter extends CoachAdapter implements PersonalCoach {

    private PersonalCoach personalCoach;

    public PersonalCoachAdapter(PersonalCoach personalCoach) {
        super(personalCoach);
        this.personalCoach = personalCoach;
    }

    @Override
    public int getSeatsNumber() {
        return personalCoach.getSeatsNumber();
    }

    @Override
    public boolean isToiletVacant() {
        return personalCoach.isToiletVacant();
    }
}
