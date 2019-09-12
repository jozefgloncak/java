package gloncak.jozef.design.pattern.impl.facade;

/**
 * Represents DTO for complex information about citizen.
 */
public class CitizenInfoDTO {
    private String name;
    private String permanentStay;
    private String district;
    private String state;

    public void setName(String name) {
        this.name = name;
    }

    public void setPermanentStay(String permanentStay) {
        this.permanentStay = permanentStay;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("Name: %s%nPermanent stay: %s%nDistrict: %s%nState: %s%n", name,
                permanentStay, district, state);
    }

}
