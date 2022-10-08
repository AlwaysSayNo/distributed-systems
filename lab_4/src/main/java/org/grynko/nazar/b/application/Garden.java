package org.grynko.nazar.b.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class Garden {

    @Value("${garden.width}")
    private int WIDTH;
    @Value("${garden.height}")
    private int HEIGHT;

    private List<List<PlantState>> cells;

    @PostConstruct
    private void init() {
        Random random = new Random();
        cells = new ArrayList<>(WIDTH);

        for(int i = 0; i < WIDTH; ++i) {
            List<PlantState> row = new ArrayList<>(HEIGHT);

            for(int j = 0; j < HEIGHT; ++j) {
                PlantState state = null;
                if(random.nextInt() % 2 == 0) state = PlantState.DRY;
                else state = PlantState.WATERED;

                row.add(state);
            }

            cells.add(row);
        }
    }

    public PlantState getCell(int width, int height) {
        if(width < 0 || height < 0 || width > WIDTH || height > HEIGHT)
            throw new IllegalArgumentException();
        return cells.get(width).get(height);
    }

    public void setCell(int width, int height, PlantState state) {
        if(width < 0 || height < 0 || width > WIDTH || height > HEIGHT)
            throw new IllegalArgumentException();
        cells.get(width).set(height, state);
    }

    public List<List<PlantState>> getCells() {
        return Collections.unmodifiableList(cells);
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < WIDTH; ++i) {
            List<PlantState> row = cells.get(i);
            sb.append(
                    row.stream().map(PlantState::toString).collect(Collectors.joining(", "))
            );

            if(i != WIDTH - 1) sb.append("\n");
        }

        return sb.toString();
    }

}
