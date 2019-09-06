package gloncak.jozef.design.pattern.impl.dp;

import gloncak.jozef.design.pattern.api.dp.Observable;
import gloncak.jozef.design.pattern.api.dp.Observer;
import gloncak.jozef.design.pattern.writer.CustomizedBufferedWriter;

/**
 * Observer for odd numbers - 1, 3, 5....
 */
public class OddNumberObserverImpl extends ObserverAdapter {
    public OddNumberObserverImpl(CustomizedBufferedWriter fw) {
        super(fw);
    }

    @Override
    public void update(Observable observable, Object data) {
        fw.write(String.format(" OddNumberObserver %d: generated %d.", id, data));
    }

    @Override
    public void updateWithNoData(Observable observable) {

    }
}
