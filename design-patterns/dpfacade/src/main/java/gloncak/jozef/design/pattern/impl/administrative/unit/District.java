package gloncak.jozef.design.pattern.impl.administrative.unit;

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

    public void setSettlements(Set<Integer> settlements) {
        this.settlements = settlements;
    }

}
