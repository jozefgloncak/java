package gloncak.jozef.jsp.integratespringjsp2.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Color {

    @Id
    @GeneratedValue
    private Integer id;

    private String color;

    private String code;

    public Color() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color1 = (Color) o;
        return id.equals(color1.id) && color.equals(color1.color) && code.equals(color1.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, code);
    }
}
