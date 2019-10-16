package gloncak.jozef.springframework.pure.collections.beans;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CarManager {
    private Set<CarType> carTypes;
    private Map<String, Car> cars;
    private List<String> driversID;

    public Set<CarType> getCarTypes() {
        return carTypes;
    }

    public void setCarTypes(Set<CarType> carTypes) {
        this.carTypes = carTypes;
    }

    public Map<String, Car> getCars() {
        return cars;
    }

    public void setCars(Map<String, Car> cars) {
        this.cars = cars;
    }

    public List<String> getDriversID() {
        return driversID;
    }

    public void setDriversID(List<String> driversID) {
        this.driversID = driversID;
    }
}
