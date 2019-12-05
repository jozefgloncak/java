package gloncak.jozef.springframework.pure.property.editor.beans;

import gloncak.jozef.springframework.pure.property.editor.Address;
import org.springframework.beans.factory.annotation.Value;

public class Person {
    @Value("Moskovska|644|97335")
    private Address address;

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" + "address=" + address + '}';
    }
}
