package org.grynko.nazar.a.application;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

@Component
public class RecruitersShire {

    private Direction[] shire;
    private int cursor = 0;

    public RecruitersShire(int soldiersAmount) {
        this.shire = generateShire(soldiersAmount);
    }

    private Direction[] generateShire(int soldiersAmount) {
        Direction[] result = new Direction[soldiersAmount];
        Random random = new Random();

        for(int i = 0; i < soldiersAmount; ++i) {
            result[i] = random.nextInt() % 2 == 0 ? Direction.RIGHT : Direction.LEFT;
        }

        return result;
    }

    public boolean unfold() {
        int i = cursor;
        while(i < shire.length - 1) {
            Direction current = shire[i];
            Direction next = shire[i + 1];

            if(isOpposite(current, next)) {
                shire[i] = Direction.unfold(current);
                shire[i + 1] = Direction.unfold(next);

                if(i != 0) {
                    cursor = --i;
                    return true;
                }
            }
            else {
                i++;
            }
        }

        cursor = shire.length - 1;
        return false;
    }

    private boolean isOpposite(Direction lhs, Direction rhs) {
        return lhs == Direction.RIGHT && rhs == Direction.LEFT;
    }

    @Override
    public String toString() {
        return Arrays.toString(shire);
    }

}
