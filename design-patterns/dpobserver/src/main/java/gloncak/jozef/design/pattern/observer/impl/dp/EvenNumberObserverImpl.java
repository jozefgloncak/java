package gloncak.jozef.design.pattern.observer.impl.dp;

import gloncak.jozef.design.pattern.observer.api.dp.Observable;
import gloncak.jozef.design.pattern.observer.writer.CustomizedBufferedWriter;

/**
 * Observer for even number 0, 2, 4, 6....
 */
public class EvenNumberObserverImpl extends ObserverAdapter {

    public EvenNumberObserverImpl(CustomizedBufferedWriter fw) {
        super(fw);
    }

    @Override
    public void update(Observable observable, Object data) {
        fw.write(String.format(" EvenNumberObserver %d: generated %d.", id, data));
    }

    @Override
    public void updateWithNoData(Observable observable) {

    }
}