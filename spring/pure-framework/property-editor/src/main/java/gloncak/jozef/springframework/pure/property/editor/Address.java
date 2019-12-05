package gloncak.jozef.springframework.pure.property.editor;

public class Address {
    private String street;
    private int number;
    private String zip;

    public Address(String street, int number, String zip) {
        this.street = street;
        this.number = number;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" + "street='" + street + '\'' + ", number=" + number + ", zip='" + zip + '\'' + '}';
    }
}
