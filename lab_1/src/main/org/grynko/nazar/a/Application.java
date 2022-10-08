package main.org.grynko.nazar.a;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Application {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JSlider slider = createSlider();

        // threads
        MyThread thread1 = new MyThread("-", 1, slider);
        MyThread thread2 = new MyThread("+", 1, slider);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));

        // Panel for spinners
        JPanel spinnerPanel = new JPanel();
        spinnerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
        spinnerPanel.setBorder(new EmptyBorder(70, 0, 0, 0));

        spinnerPanel.add(createSpinnerPanel(thread1));
        spinnerPanel.add(createSpinnerPanel(thread2));

        sliderPanel.add(slider);
        sliderPanel.add(spinnerPanel);

        // Buttons
        JButton startBtn = new JButton("Start");

        JButton endBtn = new JButton("End");
        endBtn.setEnabled(false);

        startBtn.addActionListener(e -> {
            thread1.start();
            thread2.start();
            startBtn.setEnabled(false);
            endBtn.setEnabled(true);
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
        btnPanel.add(startBtn);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.add(sliderPanel);
        mainPanel.add(btnPanel);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public static JPanel createSpinnerPanel(MyThread thread) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Thread: " + thread.getOperation());

        SpinnerModel sModel = new SpinnerNumberModel(thread.getPriority(), Thread.MIN_PRIORITY, Thread.MAX_PRIORITY, 1);
        JSpinner spinner = new JSpinner(sModel);
        spinner.addChangeListener(e -> thread.setPriority((Integer) spinner.getValue()));

        panel.add(label);
        panel.add(spinner);

        return panel;
    }

    public static JSlider createSlider() {
        JSlider slider = new JSlider(0, 100, 50);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        return slider;
    }

}
