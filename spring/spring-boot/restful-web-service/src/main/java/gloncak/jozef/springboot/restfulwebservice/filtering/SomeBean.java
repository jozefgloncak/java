package gloncak.jozef.springboot.restfulwebservice.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * There is several attitudes to filtering
 * * static filtering
 * ** specify through annotation @JsonIgnore which field should be ignored
 * ** specify through annotation @JsonIgnoreProperties which fields should be ignored (is specified list of property
 * names)
 */
@JsonIgnoreProperties(value = {"value1", "value2"})
public class SomeBean {

    private String value1;
    private String value2;

    @JsonIgnore
    private String password;

    private String value4;

    public SomeBean(String value1, String value2, String password, String value4) {
        this.value1 = value1;
        this.value2 = value2;
        this.password = password;
        this.value4 = value4;
    }

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

    public String getPassword() {
        return password;
    }

    public String getValue4() {
        return value4;
    }
}
