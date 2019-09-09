package gloncak.jozef.design.pattern.observer.impl.dp;

import gloncak.jozef.design.pattern.observer.api.dp.Observable;
import gloncak.jozef.design.pattern.observer.writer.CustomizedBufferedWriter;

public class Number3DivisibleNumberObserverImpl extends ObserverAdapter {

    public Number3DivisibleNumberObserverImpl(CustomizedBufferedWriter fw) {
        super(fw);
    }

    @Override
    public void update(Observable observable, Object data) {
        fw.write(String.format(" 3NumberDivisibleNumberObserver %d: generated %d.", id, data));
    }

    @Override
    public void updateWithNoData(Observable observable) {

    }
}
