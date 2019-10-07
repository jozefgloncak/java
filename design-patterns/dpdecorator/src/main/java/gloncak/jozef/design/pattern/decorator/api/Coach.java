package gloncak.jozef.design.pattern.decorator.api;

/**
 * Contains list of method which should be available in railway coach
 */
public interface Coach {

    /**
     * Checks state of coach whether it is|isn't moving
     * @return
     */
    boolean isMoving();

    /**
     * Pull break to make it sure that coach will stay on place
     */
    void pullBreak();

    /**
     * Release break to make it possible to manipulate with coach.
     */
    void releaseOfBreak();
}
