package gloncak.jozef.design.pattern.observer.api;

import gloncak.jozef.design.pattern.observer.api.dp.Observable;
import gloncak.jozef.design.pattern.observer.api.dp.Observer;

/**
 * Is responsible for starting and stopping of generation of number and
 * for registering and unregistering of observers.
 */
public interface NumberGenerator extends Observable {

    void startGenerate();

    void stopGenerate();

    void registerEvenNumberObserver(Observer evenNumObserver);

    void registerOddNumberObserver(Observer oddNumObserver);

    void register3DivisibleNumberObserver(Observer oddNumObserver);

    void unregisterObserver(int observerID);
}
