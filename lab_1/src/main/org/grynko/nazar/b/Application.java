package main.org.grynko.nazar.b;

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
        MyThread minThread = new MyThread("-", Thread.MIN_PRIORITY, slider);
        MyThread maxThread = new MyThread("+", Thread.MAX_PRIORITY, slider);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));

        // Panel for buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBorder(new EmptyBorder(70, 0, 0, 0));

        buttonsPanel.add(createButtonsPanel(minThread));
        buttonsPanel.add(createButtonsPanel(maxThread));

        sliderPanel.add(slider);
        sliderPanel.add(buttonsPanel);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.add(sliderPanel);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public static JPanel createButtonsPanel(MyThread thread) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton startButton = new JButton("Start: " + thread.getOperation());
        JButton endButton = new JButton("Stop: " + thread.getOperation());
        endButton.setEnabled(false);

        startButton.addActionListener(e -> {
            if(!thread.isAlive()) thread.start();
            else thread.startSlider();

            startButton.setEnabled(false);
            endButton.setEnabled(true);
        });

        endButton.addActionListener(e -> {
            thread.stopSlider();

            startButton.setEnabled(true);
            endButton.setEnabled(false);
        });

        panel.add(startButton, BorderLayout.WEST);
        panel.add(endButton, BorderLayout.EAST);

        panel.setBorder(new EmptyBorder(50, 50, 0, 50));
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
