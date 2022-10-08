package main.org.grynko.nazar.a;

import java.util.ArrayList;
import java.util.List;

public class Forest {

    private final List<List<Boolean>> hectares;
    private WinnieThePooh winnieThePooh = null;

    public Forest(int width, int height) {
        if(width < 1 || height < 1) throw new IllegalArgumentException();

        hectares = new ArrayList<>(width);
        for(int i = 0; i < width; ++i) {
            List<Boolean> column = new ArrayList<>(height);
            for(int j = 0; j < height; ++j) column.add(false);

            hectares.add(column);
        }
    }

    public void setWinnieThePooh(int x, int y) {
        if(x < 0 || y < 0 || x > hectares.size() || y > hectares.get(0).size())
            throw new IllegalArgumentException();

        this.winnieThePooh = new WinnieThePooh(x, y);
        hectares.get(x).set(y, true);
    }

    public List<List<Boolean>> getHectares() {
        return hectares;
    }

    private class WinnieThePooh {

        private final int x;
        private final int y;

        public WinnieThePooh(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
