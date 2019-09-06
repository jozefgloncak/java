package gloncak.jozef.design.pattern.impl;

import gloncak.jozef.design.pattern.api.NumberGenerator;
import gloncak.jozef.design.pattern.api.dp.Observable;
import gloncak.jozef.design.pattern.api.dp.Observer;
import gloncak.jozef.design.pattern.impl.dp.ObserverAdapter;
import gloncak.jozef.design.pattern.writer.CustomizedBufferedWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGeneratorImpl implements NumberGenerator {
    private CustomizedBufferedWriter fw;
    private List<Observer> observersForOddNumberGenerated = new ArrayList<>();
    private List<Observer> observersForEvenNumberGenerated = new ArrayList<>();
    private List<Observer> observersFor3DivisibleNumberGenerated = new ArrayList<>();

    Observable thisNumberGenerator = this;
    Observer numberGeneratedObserver;
    Thread threadForNumGenerator;

    public NumberGeneratorImpl(CustomizedBufferedWriter fw) {
        this.fw = fw;
        this.numberGeneratedObserver = new CommonObserverAdapter(fw);
    }

    @Override
    public void startGenerate() {
        threadForNumGenerator = new Thread(new NumGenerator(numberGeneratedObserver));
        threadForNumGenerator.start();
    }

    @Override
    public void stopGenerate() {
        threadForNumGenerator.interrupt();
    }

    @Override
    public void registerEvenNumberObserver(Observer evenNumObserver) {
        this.observersForEvenNumberGenerated.add(evenNumObserver);
    }

    @Override
    public void unregisterEvenNumberObserver(Observer evenNumObserver) {
        this.observersForEvenNumberGenerated.remove(evenNumObserver);
    }

    @Override
    public void registerOddNumberObserver(Observer oddNumObserver) {
        this.observersForOddNumberGenerated.add(oddNumObserver);
    }

    @Override
    public void unregisterOddNumberObserver(Observer evenNumObserver) {
        this.observersForOddNumberGenerated.remove(evenNumObserver);
    }

    @Override
    public void register3DivisibleNumberObserver(Observer divisible3NumObserver) {
        this.observersFor3DivisibleNumberGenerated.add(divisible3NumObserver);
    }

    @Override
    public void unregister3DivisibleNumberObserver(Observer divisible3NumObserver) {
        this.observersFor3DivisibleNumberGenerated.remove(divisible3NumObserver);
    }

    @Override
    public void unregisterObserver(int observerID) {
        this.observersFor3DivisibleNumberGenerated
                .removeIf(observer -> observer.getID() == observerID);
        this.observersForEvenNumberGenerated
                .removeIf(observer -> observer.getID() == observerID);
        this.observersForOddNumberGenerated
                .removeIf(observer -> observer.getID() == observerID);
    }

    private class NumGenerator implements Runnable, Observable {
        Random randNumberGenerator = new Random();
        Random randTimeGenerator = new Random();
        Observer numberGeneratedObserver;
        boolean isRunning = true;
        public NumGenerator(Observer numberGeneratedObserver) {
            this.numberGeneratedObserver = numberGeneratedObserver;
        }

        @Override
        public void run() {
            while (isRunning) {
                int newGeneratedNumber = randNumberGenerator.nextInt();
                numberGeneratedObserver.update(this, newGeneratedNumber);
                try {
                    Thread.currentThread().sleep(Math.abs(randTimeGenerator.nextInt()) % 3000);
                } catch (InterruptedException e) {
                    isRunning = false;
                }
            }

        }
    }

    private class CommonObserverAdapter extends ObserverAdapter {

        public CommonObserverAdapter(CustomizedBufferedWriter fw) {
            super(fw);
        }

        @Override
        public void update(Observable observable, Object data) {
            if (data instanceof Integer) {
                final int newData = Math.abs((int) data) % 1000;
                fw.write(String.format("Notif in NumberGenerator. New generated number %d.", newData));

                if ((Integer)newData % 2 == 0) {
                    observersForEvenNumberGenerated.forEach(
                            observer -> observer.update(thisNumberGenerator, newData));
                } else {
                    observersForOddNumberGenerated.forEach(
                            observer -> observer.update(thisNumberGenerator, newData));
                }
                if ((Integer)newData % 3 == 0 ) {
                    observersFor3DivisibleNumberGenerated.forEach(
                            observer -> observer.update(thisNumberGenerator, newData));
                }
            }
        }

        @Override
        public void updateWithNoData(Observable observable) {
            fw.write("Notification accepted in NumberGenerator about new generated number");
        }
    };

    public String provideListOfObservers() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nOdd observers:\n");
        observersForOddNumberGenerated.forEach(observer -> sb.append(String.format(" %s ",observer.getID())));
        sb.append("\nEven observers:\n");
        observersForEvenNumberGenerated.forEach(observer -> sb.append(String.format(" %s ",observer.getID())));
        sb.append("\nDivisible by 3 observers:\n");
        observersFor3DivisibleNumberGenerated.forEach(observer -> sb.append(String.format(" %s ",observer.getID())));
        return sb.toString();
    }
}
