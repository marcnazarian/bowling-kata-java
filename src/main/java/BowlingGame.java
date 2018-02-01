import java.util.List;

public class BowlingGame {

    private static final int NUMBER_OF_FRAMES = 10;
    private static final int TOTAL_NUMBER_OF_PINS = 10;

    public static int computeScore(List<Integer> rolls) {
        int gameScore = 0;
        int frameIndex = 0;
        for (int frame = 0; frame < NUMBER_OF_FRAMES; frame++) {
            if (isStrike(frameIndex, rolls)) {
                gameScore += TOTAL_NUMBER_OF_PINS + computeStrikeBonus(rolls, frameIndex);
                frameIndex++;
            } else if (isSpare(frameIndex, rolls)) {
                gameScore += TOTAL_NUMBER_OF_PINS + computeSpareBonus(rolls, frameIndex);
                frameIndex += 2;
            } else {
                gameScore += rolls.get(frameIndex) + rolls.get(frameIndex + 1);
                frameIndex += 2;
            }
        }
        return gameScore;
    }

    private static boolean isSpare(int frameIndex, List<Integer> rolls) {
        return rolls.get(frameIndex) + rolls.get(frameIndex + 1) == TOTAL_NUMBER_OF_PINS;
    }

    private static int computeSpareBonus(List<Integer> rolls, int frameIndex) {
        return rolls.get(frameIndex + 2);
    }

    private static boolean isStrike(int frameIndex, List<Integer> rolls) {
        return rolls.get(frameIndex) == TOTAL_NUMBER_OF_PINS;
    }

    private static int computeStrikeBonus(List<Integer> rolls, int frameIndex) {
        return rolls.get(frameIndex + 1) + rolls.get(frameIndex + 2);
    }
}
