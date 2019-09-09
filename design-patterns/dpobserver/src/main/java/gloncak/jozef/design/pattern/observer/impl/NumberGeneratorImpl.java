package gloncak.jozef.design.pattern.observer.impl;

import gloncak.jozef.design.pattern.observer.api.NumberGenerator;
import gloncak.jozef.design.pattern.observer.api.dp.Observable;
import gloncak.jozef.design.pattern.observer.api.dp.Observer;
import gloncak.jozef.design.pattern.observer.impl.dp.ObserverCommon;
import gloncak.jozef.design.pattern.observer.writer.CustomizedBufferedWriter;

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
        this.numberGeneratedObserver = new CustomObserver(fw);
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
    public void registerOddNumberObserver(Observer oddNumObserver) {
        this.observersForOddNumberGenerated.add(oddNumObserver);
    }

    @Override
    public void register3DivisibleNumberObserver(Observer divisible3NumObserver) {
        this.observersFor3DivisibleNumberGenerated.add(divisible3NumObserver);
    }

    @Override
    public void unregisterObserver(int observerID) {
        this.observersFor3DivisibleNumberGenerated
                .removeIf(observer -> observer.getId() == observerID);
        this.observersForEvenNumberGenerated
                .removeIf(observer -> observer.getId() == observerID);
        this.observersForOddNumberGenerated
                .removeIf(observer -> observer.getId() == observerID);
    }

    public String provideListOfObservers() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nOdd observers:\n");
        observersForOddNumberGenerated.forEach(observer -> sb.append(String.format(" %s ",observer.getId())));
        sb.append("\nEven observers:\n");
        observersForEvenNumberGenerated.forEach(observer -> sb.append(String.format(" %s ",observer.getId())));
        sb.append("\nDivisible by 3 observers:\n");
        observersFor3DivisibleNumberGenerated.forEach(observer -> sb.append(String.format(" %s ",observer.getId())));
        return sb.toString();
    }

    /**
     * Generate number.
     *
     * This code is run in standalone thread.
     */
    private class NumGenerator implements Runnable, Observable {
        /**
         * Generator for random numbers
         */
        private Random randNumberGenerator = new Random();

        /**
         * Generator for time intervals (ms) between two following numbers
         */
        private Random randTimeGenerator = new Random();

        /**
         * Observer for generating new number.
         */
        private Observer generatedNumberObserver;
        private boolean isRunning = true;

        private NumGenerator(Observer numberGeneratedObserver) {
            this.generatedNumberObserver = numberGeneratedObserver;
        }

        @Override
        public void run() {
            while (isRunning) {
                int newGeneratedNumber = randNumberGenerator.nextInt();
                generatedNumberObserver.update(this, newGeneratedNumber);
                try {
                    Thread.currentThread().sleep(Math.abs(randTimeGenerator.nextInt()) % 3000);
                } catch (InterruptedException e) {
                    isRunning = false;
                }
            }
        }
    }

    /**
     * Interconnect thread which generates numbers with {@link NumberGeneratorImpl}.
     *
     * It is dispatcher. It is observer which receive new generated numbers and dispatch
     * them to proper observer.
     */
    private class CustomObserver extends ObserverCommon {

        public CustomObserver(CustomizedBufferedWriter fw) {
            super(fw);
        }

        @Override
        public void update(Observable observable, Object data) {
            if (data instanceof Integer) {
                final int newData = Math.abs((int) data) % 1000;
                fw.writeLn(String.format("Notif in NumberGenerator. New generated number %d.", newData));

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
            fw.writeLn("Notification accepted in NumberGenerator about new generated number");
        }
    };

}
