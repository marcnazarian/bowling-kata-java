import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BowlingGameTest {

    @Test
    public void zero_for_each_roll_should_give_0() {
        List<Integer> rolls = Arrays.asList(0,0,0,0,0,0,0,0,0,0);

        int gameScore = BowlingGame.computeScore(rolls);
        
        assertEquals(0, gameScore);
    }

    @Test
    public void one_for_each_roll_should_give_10() {
        List<Integer> rolls = Arrays.asList(1,1,1,1,1,1,1,1,1,1);

        int gameScore = BowlingGame.computeScore(rolls);

        assertEquals(10, gameScore);
    }

    @Test
    public void rolls_without_special_bonus_should_give_sum_of_each_roll() {
        List<Integer> rolls = Arrays.asList(4,3,6,1,2,2,8,1,0,9);

        int gameScore = BowlingGame.computeScore(rolls);

        assertEquals(36, gameScore);
    }

    @Test
    public void spare_should_double_next_roll() {
        List<Integer> rolls = Arrays.asList(4,3,6,4,2,2,8,1,0,9);

        int gameScore = BowlingGame.computeScore(rolls);

        assertEquals(41, gameScore);
    }

    @Test
    public void spares_should_double_next_roll() {
        List<Integer> rolls = Arrays.asList(4,3,6,4,2,2,8,2,6,3);

        int gameScore = BowlingGame.computeScore(rolls);

        assertEquals(48, gameScore);
    }

    @Test
    public void strike_should_double_next_two_rolls() {
        List<Integer> rolls = Arrays.asList(4,3,10,4,5,2,7,2,6);

        int gameScore = BowlingGame.computeScore(rolls);

        assertEquals(52, gameScore);
    }

    @Test
    public void strike_and_spare_case() {
        List<Integer> rolls = Arrays.asList(4,3,10,4,5,2,8,2,6);

        int gameScore = BowlingGame.computeScore(rolls);

        assertEquals(55, gameScore);
    }

    private static class BowlingGame {
        private static List<Integer> rolls;

        public static int computeScore(List<Integer> rolls) {
            int gameScore = 0;
            int frameIndex = 0;
            while (frameIndex < rolls.size()) {
                if (isStrike(frameIndex, rolls)) {
                    gameScore += 10 + rolls.get(frameIndex + 1) + rolls.get(frameIndex + 2);
                    frameIndex ++;
                }
                else if (isSpare(frameIndex, rolls)) {
                    gameScore += 10 + rolls.get(frameIndex + 2);
                    frameIndex += 2;
                } else {
                    gameScore += rolls.get(frameIndex) + rolls.get(frameIndex +1);
                    frameIndex += 2;
                }
            }
            return gameScore;
        }

        private static boolean isSpare(int frameIndex, List<Integer> rolls) {
            return rolls.get(frameIndex) + rolls.get(frameIndex + 1) == 10;
        }

        private static boolean isStrike(int frameIndex, List<Integer> rolls) {
            return rolls.get(frameIndex) == 10;
        }
    }
}