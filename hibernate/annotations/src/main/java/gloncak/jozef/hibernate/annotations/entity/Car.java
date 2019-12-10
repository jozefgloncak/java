package gloncak.jozef.hibernate.annotations.entity;

import javax.persistence.*;

@Entity
@Table(name = "VEHICLE")
public class Car {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "PLATE")
    private String plate;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "VOLUME")
    private int engineVolume;

    public Car() {
    }

    public Car(String plate, String color, int engineVolume) {
        this.plate = plate;
        this.color = color;
        this.engineVolume = engineVolume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(int engineVolume) {
        this.engineVolume = engineVolume;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", plate='" + plate + '\'' + ", color='" + color + '\'' + ", engineVolume=" + engineVolume + '}';
    }
}
