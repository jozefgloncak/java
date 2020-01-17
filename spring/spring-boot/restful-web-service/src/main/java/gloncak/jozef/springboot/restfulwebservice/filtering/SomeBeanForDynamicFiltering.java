package gloncak.jozef.springboot.restfulwebservice.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * Example of dynamic filtering. See corresponding code in {@link FilteringController#retrieveSomeBeanDynamicFiltering()}
 */
@JsonFilter(value = "someBeanFilter")
public class SomeBeanForDynamicFiltering {
    private String value1;
    private String value2;
    private String password;
    private String value4;

    public SomeBeanForDynamicFiltering(String value1, String value2, String password, String value4) {
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
