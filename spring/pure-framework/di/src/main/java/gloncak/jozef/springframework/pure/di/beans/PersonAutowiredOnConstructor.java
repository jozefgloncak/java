package gloncak.jozef.springframework.pure.di.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("personAnnotOnConstructor")
public class PersonAutowiredOnConstructor {
    private Address address;
    private Name name;


    public PersonAutowiredOnConstructor(@Autowired@Qualifier("address2") Address address,
                                        @Autowired@Qualifier("name2")Name name) {
        this.address = address;
        this.name = name;
    }

    @Override
    public String toString() {
        return "- Person{" +
                "\n   address=" + address + "," +
                "\n   name=" + name +
                "\n }";
    }
}
