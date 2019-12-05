package gloncak.jozef.springframework.pure.util.xml.schema;

import java.util.List;

public class CollectorBean {
    private String constValue;
    private String valueOfInnerBean;
    private List<String> firstNames;

    public void setConstValue(String constValue) {
        this.constValue = constValue;
    }

    public String getConstValue() {
        return constValue;
    }

    public String getValueOfInnerBean() {
        return valueOfInnerBean;
    }

    public void setValueOfInnerBean(String valueOfInnerBean) {
        this.valueOfInnerBean = valueOfInnerBean;
    }

    public List<String> getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(List<String> firstNames) {
        this.firstNames = firstNames;
    }
}
