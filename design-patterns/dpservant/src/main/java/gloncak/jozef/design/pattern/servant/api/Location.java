package gloncak.jozef.design.pattern.servant.api;

public enum Location {

    CAPITAL(4), //capital city
    COUNTY(3),  //main town of higher administration unit
    DISTRICT(2), //main town of lower administration unit
    TOWN(1.5),    //ordinary town
    VILLAGE(1);

    /**
     * Defines coeficient used for calculation of insurance
     */
    private final double coeficient;

    Location(double coeficient) {
        this.coeficient = coeficient;
    }

    public double getCoeficient() {
        return this.coeficient;
    }
}
