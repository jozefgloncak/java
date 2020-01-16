package gloncak.jozef.springboot.restfulwebservice;

public class HelloWorldBean {

    private String message;

    public HelloWorldBean(String text) {
        this.message = text;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
