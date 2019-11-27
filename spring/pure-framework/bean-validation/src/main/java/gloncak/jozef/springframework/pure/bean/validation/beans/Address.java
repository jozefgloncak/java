package gloncak.jozef.springframework.pure.bean.validation.beans;

public class Address {
    private String street;
    private int number;
    private String zip;

    public Address(String street, int number, String zip) {
        this.street = street;
        this.number = number;
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
