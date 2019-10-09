package gloncak.jozef.java8.optional.dto;

public class Student {
    private Address address;

    public Student(Address address) {
        this.address = address;
    }

    public Student() {
    }

    public Address getAddress() {
        return address;
    }
}
