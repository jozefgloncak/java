package gloncak.jozef.jsp.integratespringjsp2.service;

import org.junit.Test;

public class PersonGeneratorImplTest {

    @Test
    public void test() {
        PersonGeneratorServiceImpl personGenerator = new PersonGeneratorServiceImpl();
        personGenerator.generatePersons(50);
    }

    @Test
    public void dataAccess() {

    }
}