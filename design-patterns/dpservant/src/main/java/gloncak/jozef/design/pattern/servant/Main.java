package gloncak.jozef.design.pattern.servant;

import gloncak.jozef.design.pattern.servant.api.Location;
import gloncak.jozef.design.pattern.servant.impl.BlockOfFlat;
import gloncak.jozef.design.pattern.servant.impl.Cottage;
import gloncak.jozef.design.pattern.servant.impl.House;
import gloncak.jozef.design.pattern.servant.pattern.InsuranceCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class Main
{
    public static final Logger LOG = LoggerFactory.getLogger(Main.class);
    public static void main( String[] args )
    {
        //create instance of servant {@link InsuranceCounter}
        InsuranceCounter insuranceCounter = new InsuranceCounter();

        BlockOfFlat blockOfFlat = new BlockOfFlat();
        blockOfFlat.setAge(14);
        blockOfFlat.setArea(540);
        blockOfFlat.setLocation(Location.CAPITAL);
        LOG.info("Block of flat insurance: {}", insuranceCounter.countInsurance(blockOfFlat));

        Cottage cottage = new Cottage(4, 155, 1985, Location.VILLAGE);
        LOG.info("Cottage insurance: {}", insuranceCounter.countInsurance(cottage));

        House house = new House(300, Location.CAPITAL, 14);
        LOG.info("House insurance: {}", insuranceCounter.countInsurance(house));
    }
}
