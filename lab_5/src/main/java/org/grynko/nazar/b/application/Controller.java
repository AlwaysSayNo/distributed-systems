package org.grynko.nazar.b.application;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Controller {

    private final Integer[] counts;
    private final int threadAmount;
    private final int minMismatches;

    private int cursor = 0;
    private boolean isFinished = false;

    public Controller(@Value("${thread.amount}") int threadAmount,
                      @Value("${min.mismatches}") int minMismatches) {
        this.threadAmount = threadAmount;
        this.minMismatches = minMismatches;
        this.counts = new Integer[this.threadAmount];
    }

    @SneakyThrows
    public void send(Map<Character, Integer> charactersToCount) {
        synchronized (counts) {

            int amount = charactersToCount.get('A') + charactersToCount.get('B');

            System.out.printf("%s: cursor = %d, amount = %d\n", Thread.currentThread().getName(), cursor, amount);

            counts[cursor] = amount;

            cursor++;

            if(check()) {
                isFinished = true;
            }

            cursor %= threadAmount;
        }
    }

    private boolean check() {
        if(cursor < threadAmount) return false;

        Map<Integer, Integer> countTimes = new HashMap<>();
        for(int i = 0; i < threadAmount; ++i) {
            int count = counts[i];
            countTimes.merge(count, 1, Integer::sum);
        }

        int totalOptions = countTimes.size();
        System.out.printf("Total options: %d\n", totalOptions);

        return totalOptions - 1 <= minMismatches;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public int getThreadAmount() {
        return threadAmount;
    }
}
