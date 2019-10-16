package gloncak.jozef.springframework.pure.collections.beans;

public class Car {

    private String vin;
    private String ecv;

    public Car(String vin) {
        this.vin = vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getEcv() {
        return ecv;
    }

    public void setEcv(String ecv) {
        this.ecv = ecv;
    }

    @Override
    public String toString() {
        return "Car{" + "vin='" + vin + '\'' + ", ecv='" + ecv + '\'' + '}';
    }
}
