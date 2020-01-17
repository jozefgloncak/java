package gloncak.jozef.springboot.restfulwebservice.versioning;

public class PersonA {
    private String name;

    public PersonA(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
