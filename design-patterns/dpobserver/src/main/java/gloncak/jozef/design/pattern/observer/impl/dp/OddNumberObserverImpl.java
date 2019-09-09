package gloncak.jozef.design.pattern.observer.impl.dp;

import gloncak.jozef.design.pattern.observer.api.dp.Observable;
import gloncak.jozef.design.pattern.observer.writer.CustomizedBufferedWriter;

/**
 * Observer for odd numbers - 1, 3, 5....
 */
public class OddNumberObserverImpl extends ObserverCommon {
    public OddNumberObserverImpl(CustomizedBufferedWriter fw) {
        super(fw);
    }

    @Override
    public void update(Observable observable, Object data) {
        fw.write(" OddNumberObserver");
        super.update(observable, data);
    }

    @Override
    public void updateWithNoData(Observable observable) {

    }
}
