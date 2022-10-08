package main.org.grynko.nazar.a;

import javax.swing.*;

public class MyThread extends Thread{
    private final String operation;
    private final int value;
    private final JSlider slider;

    private final static int BOUNDARY = 1_500_000;
    private final int BORDER;
    private int count;

    public MyThread(String operation, int incValue, JSlider slider) {
        this.operation = operation;
        this.value = incValue;
        this.slider = slider;
        this.count = 0;

        BORDER = evaluateBorder(operation);
    }

    private int evaluateBorder(String operation) {
        switch (operation) {
            case "-": {
                return 10;
            }

            case "+": {
                return 90;
            }

            default: {
                throw new IllegalStateException();
            }
        }
    }

    @Override
    public void run() {
        while (!interrupted()) {
            count++;

            if(count > BOUNDARY) {
                System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
                doOperation();
                count = 0;
            }
        }

    }

    synchronized private void doOperation() {
        int newValue = slider.getValue();

        switch (operation) {
            case "-": {
                if(newValue > BORDER) newValue -= value;
                else newValue = BORDER;
                break;
            }

            case "+": {
                if(newValue < BORDER) newValue += value;
                else newValue = BORDER;
                break;
            }

            default: {
                throw new IllegalStateException();
            }
        }

        slider.setValue(newValue);
    }

    public String getOperation() {
        return operation;
    }
}
