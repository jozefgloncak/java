package gloncak.jozef.springframework.pure.collections.beans;

public class CarType {
    private String company;
    private int year;
    private String commercialName;

    public CarType(String company, int year, String commercialName) {
        this.company = company;
        this.year = year;
        this.commercialName = commercialName;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    @Override
    public String toString() {
        return "CarType{" + "company='" + company + '\'' + ", year=" + year + ", commercialName='" + commercialName + '\'' + '}';
    }
}
