import com.googlecode.zohhak.api.Coercion;
import com.googlecode.zohhak.api.Configure;
import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(ZohhakRunner.class)
@Configure(separator="->")
public class BowlingGameTest {

    @TestWith({
            "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 -> 0",
            "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 -> 20",
            "2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 -> 40",
            "9,0,9,0,9,0,9,0,9,0,9,0,9,0,9,0,9,0,9,0 -> 90",
            "4,3,6,1,2,2,8,1,0,9,2,1,4,5,2,7,1,8,0,9 -> 75"
    })
    public void game_without_special_bonus_should_give_sum_of_each_roll(List<Integer> rolls, int expectedGameScore) {
        int gameScore = BowlingGame.computeScore(rolls);

        assertEquals(expectedGameScore, gameScore);
    }

    @TestWith({
            "0,0,7,3,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 -> 26",
            "4,3,6,4,2,2,8,1,0,9,0,0,0,0,0,0,0,0,0,0 -> 41",
            "4,3,6,4,2,2,8,2,6,3,0,0,0,0,0,0,0,0,0,0 -> 48",
            "5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5 -> 150"
    })
    public void spare_should_double_next_roll(List<Integer> rolls, int expectedGameScore) {
        int gameScore = BowlingGame.computeScore(rolls);

        assertEquals(expectedGameScore, gameScore);
    }

    @TestWith({
            "0,0,10,4,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0 -> 24",
            "4,3,10,4,5,2,7,2,6,1,0,0,0,0,0,0,0,0,0 -> 53",
            "4,3,10,4,5,2,8,2,6,1,0,0,0,0,0,0,0,0,0 -> 56",
            "10,10,10,10,10,10,10,10,10,10,10,10 -> 300",
    })
    public void strike_should_double_next_two_rolls(List<Integer> rolls, int expectedGameScore) {
        int gameScore = BowlingGame.computeScore(rolls);

        assertEquals(expectedGameScore, gameScore);
    }

    @Coercion
    public List<Integer> CoerceStringIntoListOfInteger(String rolls) {
        return Stream.of(rolls.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

}