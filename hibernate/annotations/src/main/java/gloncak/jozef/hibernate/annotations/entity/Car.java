package gloncak.jozef.hibernate.annotations.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "CREATION")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", plate='" + plate + '\'' + ", color='" + color + '\'' + ", engineVolume=" + engineVolume + ", created=" + created + ", lastUpdated=" + lastUpdated + '}';
    }
}
