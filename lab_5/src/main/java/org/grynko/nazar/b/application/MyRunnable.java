package org.grynko.nazar.b.application;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;

import static java.lang.Thread.interrupted;

@Component
@Scope("prototype")
public class MyRunnable implements Runnable {

    private final Character[] string;
    private final CyclicBarrier barrier;
    private final Controller controller;

    private Map<Character, Integer> characterCounter;

    public MyRunnable(@Value("${string.length}") int length, CyclicBarrier barrier, Controller controller) {
        this.string = generateString(length);
        this.barrier = barrier;
        this.controller = controller;
    }

    @SneakyThrows
    @Override
    public void run() {
        while(!interrupted()) {
            System.out.printf("%s: %s", Thread.currentThread().getName(), getString());

            modify();
            randomSleep();

            controller.send(characterCounter);

            barrier.await();

            if(controller.isFinished()) {
                System.out.printf("%s is finished\n", Thread.currentThread().getName());
                return;
            }
        }
    }

    private void modify() {
        Random random = new Random();

        int cell = random.nextInt(100) % string.length;
        Character oldCharacter = string[cell];
        Character newCharacter = null;

        switch (oldCharacter) {
            case 'A': newCharacter = 'C'; break;
            case 'B': newCharacter = 'D'; break;
            case 'C': newCharacter = 'A'; break;
            case 'D': newCharacter = 'B'; break;
        }

        string[cell] = newCharacter;

        System.out.printf("%s: old - %s, new - %s\n", Thread.currentThread().getName(), oldCharacter, newCharacter);

        characterCounter.put(oldCharacter, characterCounter.get(oldCharacter) - 1);
        characterCounter.put(newCharacter, characterCounter.get(newCharacter) + 1);
    }

    private Character[] generateString(int length) {
        Character[] result = new Character[length];
        Random random = new Random();

        characterCounter = new HashMap<>();
        characterCounter.put('A', 0);
        characterCounter.put('B', 0);
        characterCounter.put('C', 0);
        characterCounter.put('D', 0);


        for(int i = 0; i < length; ++i) {
            int module = random.nextInt(100) % 4;
            Character character = null;

            switch (module) {
                case 0: character = 'A'; break;
                case 1: character = 'B'; break;
                case 2: character = 'C'; break;
                case 3: character = 'D'; break;
            }

            result[i] = character;

            characterCounter.merge(character, 1, Integer::sum);
        }

        return result;
    }

    public String getString() {
        StringBuilder sb = new StringBuilder(
                String.format("A = %d, B = %d, C = %d, D = %d",
                        characterCounter.get('A'), characterCounter.get('B'),
                        characterCounter.get('C'), characterCounter.get('D'))
        );

        sb.append("\t");
        String resultString = Arrays.stream(string).map(Object::toString).collect(Collectors.joining());
        sb.append(resultString).append("\n");

        return sb.toString();
    }

    private void randomSleep() throws InterruptedException {
        Random random = new Random();
        Thread.sleep(random.nextInt(1000) + 300);
    }

}
