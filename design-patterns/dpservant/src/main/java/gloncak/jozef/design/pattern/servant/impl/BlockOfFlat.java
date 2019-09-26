package gloncak.jozef.design.pattern.servant.impl;

import gloncak.jozef.design.pattern.servant.api.Location;
import gloncak.jozef.design.pattern.servant.api.Property;

public class BlockOfFlat implements Property {

    private int area;
    private Location location;
    private int age;

    public void setArea(int area) {
        this.area = area;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setAge(int age) {
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
