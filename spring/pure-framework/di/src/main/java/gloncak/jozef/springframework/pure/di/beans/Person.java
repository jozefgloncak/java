package gloncak.jozef.springframework.pure.di.beans;

public class Person {
    private Address address;
    private Name name;

    public Person(Address address, Name name) {
        this.address = address;
        this.name = name;
    }

    public Person() {
    }

    public void setAddress(Address address) {
        this.address = address;
    }

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
