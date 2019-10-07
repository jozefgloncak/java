package gloncak.jozef.design.pattern.decorator;

import gloncak.jozef.design.pattern.decorator.api.Coach;
import gloncak.jozef.design.pattern.decorator.api.PersonalCoach;
import gloncak.jozef.design.pattern.decorator.impl.PersonalCoachImpl;
import gloncak.jozef.design.pattern.decorator.impl.decorator.ACCoach;
import gloncak.jozef.design.pattern.decorator.impl.decorator.ElectricCoach;
import gloncak.jozef.design.pattern.decorator.impl.CoachImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App
{
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) {
        //create new instance of railway Coach
        final Coach coach = new CoachImpl();

        //decorate existing coach
        ElectricCoach electricCoach = new ElectricCoach(coach);
        //call of first 3 methods is delegated to inner instance of coach. it is evident from logger (CoachImpl)
        LOG.info("Methods for electric coach");
        electricCoach.isMoving();
        electricCoach.pullBreak();
        electricCoach.releaseOfBreak();
        //following 2 method are implemented directly in ElectricCoach
        electricCoach.turnElectricityOn();
        electricCoach.turnElectricityOff();

        LOG.info("");
        final PersonalCoach personalCoach = new PersonalCoachImpl();
        ACCoach acCoach = new ACCoach(personalCoach);
        LOG.info("Methods for AC coach");
        acCoach.turnACOff();
        acCoach.turnACOn();
        acCoach.getSeatsNumber();
        acCoach.isToiletVacant();
        acCoach.isMoving();
        acCoach.pullBreak();
        acCoach.releaseOfBreak();



    }
}
