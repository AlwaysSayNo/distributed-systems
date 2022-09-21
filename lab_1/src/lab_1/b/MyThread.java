package lab_1.b;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyThread extends Thread{
    private static final AtomicBoolean SEMAPHORE = new AtomicBoolean(true);

    private final String operation;
    private final JSlider slider;
    private final int BORDER;
    private boolean isStopped;

    public MyThread(String operation, int priority, JSlider slider) {
        this.operation = operation;
        this.slider = slider;
        this.isStopped = false;

        this.setPriority(priority);
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
        while (true) {
            if(!SEMAPHORE.get() || isStopped) continue;
            SEMAPHORE.getAndSet(false);

            while(!interrupted()){
                doOperation();
            }

            SEMAPHORE.getAndSet(true);
        }

    }

    private void doOperation() {
        int newValue = slider.getValue();
        switch (operation) {
            case "-": {
                if(newValue > BORDER) newValue--;
                break;
            }

            case "+": {
                if(newValue < BORDER) newValue++;
                break;
            }

            default: {
                throw new IllegalStateException();
            }
        }

        slider.setValue(newValue);
    }

    public void stopSlider() {
        interrupt();
        isStopped = true;
    }

    public void startSlider() {
        isStopped = false;
    }

    public String getOperation() {
        return operation;
    }

}
