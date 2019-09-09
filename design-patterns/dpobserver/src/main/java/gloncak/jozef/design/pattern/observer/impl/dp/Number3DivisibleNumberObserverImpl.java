package gloncak.jozef.design.pattern.observer.impl.dp;

import gloncak.jozef.design.pattern.observer.api.dp.Observable;
import gloncak.jozef.design.pattern.observer.writer.CustomizedBufferedWriter;

/**
 * Observer for numbers which are divisible by number 3
 */
public class Number3DivisibleNumberObserverImpl extends ObserverCommon {

    public Number3DivisibleNumberObserverImpl(CustomizedBufferedWriter fw) {
        super(fw);
    }

    @Override
    public void update(Observable observable, Object data) {
        fw.write(" 3NumberDivisibleNumberObserver");
        super.update(observable, data);
    }

    @Override
    public void updateWithNoData(Observable observable) {

    }
}
