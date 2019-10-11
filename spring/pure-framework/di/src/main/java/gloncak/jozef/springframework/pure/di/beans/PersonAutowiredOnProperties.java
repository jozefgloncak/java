package gloncak.jozef.springframework.pure.di.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("personAnnotOnProperties")
public class PersonAutowiredOnProperties {
    @Autowired@Qualifier("address3")
    private Address address;

    @Autowired@Qualifier("name3")
    private Name name;

    @Override
    public String toString() {
        return "- Person{" +
                "\n   address=" + address + "," +
                "\n   name=" + name +
                "\n }";
    }
}
