package gloncak.jozef.jsp.integratespringjsp2.menu;

public class Gender {
    private String name;
    private String code;

    public Gender() {
    }

    public Gender(String name, String value) {
        this.name = name;
        this.code = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
