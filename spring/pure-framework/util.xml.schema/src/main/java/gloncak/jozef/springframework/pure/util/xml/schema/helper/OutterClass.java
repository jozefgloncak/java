package gloncak.jozef.springframework.pure.util.xml.schema.helper;

import java.util.List;

public class OutterClass {
    private InnerClass inner;
    private List<String> lst;

    public InnerClass getInner() {
        return inner;
    }

    public void setInner(InnerClass inner) {
        this.inner = inner;
    }

    public List<String> getLst() {
        return lst;
    }

    public void setLst(List<String> lst) {
        this.lst = lst;
    }
}
