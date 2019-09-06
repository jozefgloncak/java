package gloncak.jozef.design.pattern.impl.dp;

import gloncak.jozef.design.pattern.api.dp.Observable;
import gloncak.jozef.design.pattern.api.dp.Observer;
import gloncak.jozef.design.pattern.writer.CustomizedBufferedWriter;

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
