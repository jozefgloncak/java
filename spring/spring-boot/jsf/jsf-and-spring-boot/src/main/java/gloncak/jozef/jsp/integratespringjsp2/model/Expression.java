package gloncak.jozef.jsp.integratespringjsp2.model;

import gloncak.jozef.jsp.integratespringjsp2.api.service.MathematicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Expression {
    private int firstValue;
    private int secondValue;
    private int result;

    @Autowired
    MathematicalService mathService;

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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String analyzeEven() {
        result = firstValue + secondValue;
        return result % 2 == 0 ? "even":"odd";
    }

    public String analyzePrime() {
        return mathService.isPrime(result) ? "prime":"not-prime";
    }
}
