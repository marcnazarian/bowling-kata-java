import java.util.List;

public class BowlingGame {

    public static int computeScore(List<Integer> rolls) {
        int gameScore = 0;
        int frameIndex = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(frameIndex, rolls)) {
                gameScore += 10 + computeStrikeBonus(rolls, frameIndex);
                frameIndex++;
            } else if (isSpare(frameIndex, rolls)) {
                gameScore += 10 + computeSpareBonus(rolls, frameIndex);
                frameIndex += 2;
            } else {
                gameScore += rolls.get(frameIndex) + rolls.get(frameIndex + 1);
                frameIndex += 2;
            }
        }
        return gameScore;
    }

    private static boolean isSpare(int frameIndex, List<Integer> rolls) {
        return frameIndex + 1 <= rolls.size() && rolls.get(frameIndex) + rolls.get(frameIndex + 1) == 10;
    }

    private static int computeSpareBonus(List<Integer> rolls, int frameIndex) {
        return rolls.get(frameIndex + 2);
    }

    private static boolean isStrike(int frameIndex, List<Integer> rolls) {
        return rolls.get(frameIndex) == 10;
    }

    private static int computeStrikeBonus(List<Integer> rolls, int frameIndex) {
        return rolls.get(frameIndex + 1) + rolls.get(frameIndex + 2);
    }
}
