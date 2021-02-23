package gloncak.jozef.jsp.integratespringjsp2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class KorDnyOpakovaniConverterTest {

    @Test
    public void asObjectTest() {
        KorDnyOpakovaniConverter korDnyOpakovaniConverter = new KorDnyOpakovaniConverter();
        List<Integer> lst1 = korDnyOpakovaniConverter.getAsObject("1000000");
        System.out.println(lst1);
        lst1 = korDnyOpakovaniConverter.getAsObject("1100000");
        System.out.println(lst1);
        lst1 = korDnyOpakovaniConverter.getAsObject("0000001");
        System.out.println(lst1);
        lst1 = korDnyOpakovaniConverter.getAsObject("1000001");
        System.out.println(lst1);
        lst1 = korDnyOpakovaniConverter.getAsObject("10004011");
        System.out.println(lst1);
        lst1 = korDnyOpakovaniConverter.getAsObject("10000011b");
        System.out.println(lst1);
        lst1 = korDnyOpakovaniConverter.getAsObject("1000");
        System.out.println(lst1);
        lst1 = korDnyOpakovaniConverter.getAsObject("1111111");
        System.out.println(lst1);
    }

    @Test
    public void asStringTest() {
        KorDnyOpakovaniConverter korDnyOpakovaniConverter = new KorDnyOpakovaniConverter();
        System.out.println(korDnyOpakovaniConverter.getAsString(Arrays.asList(10, 100, 10000)));
        System.out.println(korDnyOpakovaniConverter.getAsString(Arrays.asList("45", 14.3, 10000, "100", "1", "32")));
        System.out.println(korDnyOpakovaniConverter.getAsString(Arrays.asList(1000000000, 435, 1001, 1, 0, -5, -10,
                -100, 1000,
                -1)));
        System.out.println(korDnyOpakovaniConverter.getAsString(Arrays.asList()));

    }
}