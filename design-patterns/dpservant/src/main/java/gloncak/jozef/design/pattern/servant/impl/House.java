package gloncak.jozef.design.pattern.servant.impl;

import gloncak.jozef.design.pattern.servant.api.Location;
import gloncak.jozef.design.pattern.servant.api.Property;

public class House implements Property {

    private int area;
    private Location location;
    private int age;

    public House(int area, Location location, int age) {
        this.area = area;
        this.location = location;
        this.age = age;
    }

    @Override
    public int getArea() {
        return area;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getAge() {
        return age;
    }
}
