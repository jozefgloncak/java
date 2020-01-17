package gloncak.jozef.springboot.restfulwebservice.versioning;

public class PersonB {
    private Name name;

    public PersonB(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
