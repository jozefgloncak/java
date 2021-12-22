package gloncak.jozef.jasper;

public class DataBean {
    private String name;
    private String coutr;

    public DataBean(String name, String coutr) {
        this.name = name;
        this.coutr = coutr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoutr() {
        return coutr;
    }

    public void setCoutr(String coutr) {
        this.coutr = coutr;
    }
}
