package gloncak.jozef.design.pattern.observer.api.dp;

/**
 * Own implementation of interface for Observer
 */
public interface Observer {

    /**
     * Used to notify Observer about change with changed data - data push.
     *
     * @param observable is reference to observable which this Observer is observing
     * @param data
     */
    void update(Observable observable, Object data);

    /**
     * Used to notify Observer about change without data push
     *
     * @param observable is reference to observable which this Observer is observing
     */
    void updateWithNoData(Observable observable);

    /**
     * Provide ID of observer
     *
     * @return integer with ID of observer
     */
    int getID();
}
