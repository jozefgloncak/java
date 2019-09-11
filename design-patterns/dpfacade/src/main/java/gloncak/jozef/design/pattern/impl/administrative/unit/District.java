package gloncak.jozef.design.pattern.impl.administrative.unit;

import java.util.HashSet;
import java.util.Set;

/**
 * Model district which consists of setlements.
 */
public class District extends AdministrativeUnit {
    private static int idGenerator = 1;

    private int belongsToState;
    private Set<Integer> settlements;

    public District(String districtName) {
        this.id = idGenerator++;
        this.settlements = new HashSet<>();
        setName(districtName);
    }

    public int getBelongsToState() {
        return belongsToState;
    }

    public void setBelongsToState(int belongsToState) {
        this.belongsToState = belongsToState;
    }

    public Set<Integer> getSettlements() {
        return settlements;
    }

    public void addSettlement(int settlementId) {
        this.settlements.add(settlementId);
    }

}
