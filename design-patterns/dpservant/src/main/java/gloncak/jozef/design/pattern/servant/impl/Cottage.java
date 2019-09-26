package gloncak.jozef.design.pattern.servant.impl;

import gloncak.jozef.design.pattern.servant.api.Location;
import gloncak.jozef.design.pattern.servant.api.Property;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

public class Cottage implements Property {

    private int numberOfFloors;
    private int areaOfFloor;
    private int buildingYear;
    private Location location;

    public Cottage(int numberOfFloors, int areaOfFloor, int buildingYear, Location location) {
        this.numberOfFloors = numberOfFloors;
        this.areaOfFloor = areaOfFloor;
        this.buildingYear = buildingYear;
        this.location = location;
    }

    @Override
    public int getArea() {
        return numberOfFloors * areaOfFloor;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getAge() {
        return ZonedDateTime.now().get(ChronoField.YEAR) - buildingYear;
    }
}
