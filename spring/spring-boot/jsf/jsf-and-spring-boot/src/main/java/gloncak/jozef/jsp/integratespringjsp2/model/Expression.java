package gloncak.jozef.jsp.integratespringjsp2.model;

import gloncak.jozef.jsp.integratespringjsp2.api.service.MathematicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.LocaleResolver;

//@Component
//@SessionScope
/**
 * This bean is configured through xml - beans.xml to demonstrate this posssibility
 */
public class Expression {
    private boolean displayLabels;

    private int firstValue;
    private int secondValue;
    private Integer result = 0;
    private boolean prime;

    //    wiring is now done through beans.xml file
//    @Autowired
    MathematicalService mathService;

    public MathematicalService getMathService() {
        return mathService;
    }

    public void setMathService(MathematicalService mathService) {
        this.mathService = mathService;
    }

    public int getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(int firstValue) {
        this.firstValue = firstValue;
    }

    public int getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(int secondValue) {
        this.secondValue = secondValue;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String calculate() {
        result = firstValue + secondValue;
        this.analyzePrime();
        return "";
    }

    public String analyzeEven() {
        this.calculate();
        return result % 2 == 0 ? "even" : "odd";
    }

    public String analyzePrime() {
        prime = mathService.isPrime(result);
        return prime ? "prime" : "not-prime";
    }

    public boolean isDisplayLabels() {
        return displayLabels;
    }

    public void setDisplayLabels(boolean displayLabels) {
        this.displayLabels = displayLabels;
    }

    public boolean isPrime() {
        return prime;
    }

    public void setPrime(boolean prime) {
        this.prime = prime;
    }
}
