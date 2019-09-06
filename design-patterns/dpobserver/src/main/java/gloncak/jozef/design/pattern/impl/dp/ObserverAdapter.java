package gloncak.jozef.design.pattern.impl.dp;

import gloncak.jozef.design.pattern.api.dp.Observable;
import gloncak.jozef.design.pattern.api.dp.Observer;
import gloncak.jozef.design.pattern.writer.CustomizedBufferedWriter;

public abstract class ObserverAdapter implements Observer {

    protected final CustomizedBufferedWriter fw;
    protected final int id;
    protected static int currentId = 1;

    public ObserverAdapter(CustomizedBufferedWriter fw) {
        this.fw = fw;
        this.id = currentId++;
    }

    public abstract void update(Observable observable, Object data);

    public abstract void updateWithNoData(Observable observable);

    public int getID() {
        return id;
    }
}
