package gloncak.jozef.design.pattern.observer.impl.dp;

import gloncak.jozef.design.pattern.observer.api.dp.Observable;
import gloncak.jozef.design.pattern.observer.api.dp.Observer;
import gloncak.jozef.design.pattern.observer.writer.CustomizedBufferedWriter;

/**
 * Common ancestor for all observers.
 *
 * Contains common code for generating ID of observer. IDs are unique across all observers.
 */
public abstract class ObserverCommon implements Observer {

    protected final CustomizedBufferedWriter fw;

    /**
     * ID of observer
     */
    protected final int id;

    /**
     * Value used for creating next instance of descendant of this class.
     */
    protected static int currentId = 1;

    public ObserverCommon(CustomizedBufferedWriter fw) {
        this.fw = fw;
        this.id = currentId++;
    }

    /**
     * Common code for all observers.
     */
    public void update(Observable observable, Object data) {
        fw.write(String.format(" %d: generated %d.%n", id, data));
    }

    public abstract void updateWithNoData(Observable observable);

    public int getId() {
        return id;
    }
}
