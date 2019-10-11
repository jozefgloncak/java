package gloncak.jozef.springframework.pure.di.beans;

public class Address {
    private String street;
    private String town;
    private String zip;

    public Address(String street, String town, String zip) {
        this.street = street;
        this.town = town;
        this.zip = zip;
    }

    public Address() {
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                " street='" + street + '\'' +
                ",  town='" + town + '\'' +
                ",  zip='" + zip + '\'' +
                "}";
    }
}
