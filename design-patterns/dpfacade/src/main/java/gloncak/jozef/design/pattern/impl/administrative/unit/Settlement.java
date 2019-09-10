package gloncak.jozef.design.pattern.impl.administrative.unit;

/**
 * Model for settlement (village or town)
 */
public class Settlement extends AdministrativeUnit {
    private static int idGenerator = 1;

    private int belongsToDistrict;
    private SettlementType settlementType;

    public Settlement() {
        this.id = idGenerator++;
    }

    public int getBelongsToDistrict() {
        return belongsToDistrict;
    }

    public void setBelongsToDistrict(int belongsToDistrict) {
        this.belongsToDistrict = belongsToDistrict;
    }

    public SettlementType getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(SettlementType settlementType) {
        this.settlementType = settlementType;
    }
}


