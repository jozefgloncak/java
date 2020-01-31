package gloncak.jozef.jsp.integratespringjsp2.model.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@SessionScope
public class FilterForm {

    private List<String> numbers = new ArrayList<>(Arrays.asList("0", "0"));
    private String newLine = "<br />";
    private Integer sum;

    public void addNumber() {
        this.numbers.add(null);
    }

    public void removeNumber(int index) {
        this.numbers.remove(index);
    }

    public String calculateSum() {
//        sum = numbers.stream().collect(Collectors.summingInt(Integer::parseInt));
        sum = 0;
        for (String number : numbers) {
            try {
                sum = sum+Integer.parseInt(number);
            } catch (NumberFormatException e) {

            }
        }
        return "";
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    public String getNewLine() {
        return newLine;
    }

    public void setNewLine(String newLine) {
        this.newLine = newLine;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
