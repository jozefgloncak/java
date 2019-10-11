package gloncak.jozef.springframework.pure.di.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("personAnnotOnSetter")
public class PersonAutowiredOnSetter {
    private Address address;
    private Name name;

    public PersonAutowiredOnSetter() {
    }

    @Autowired
    @Qualifier("address1")
    public void setAddress(Address address) {
        this.address = address;
    }

    @Autowired
    @Qualifier("name1")
    public void setName(Name name) {
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
