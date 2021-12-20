package solutions_2021.day2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2DiveTest {

    @Test
    public void calculatePositionTest() {
        assertEquals(900, Day2Dive.calculatePosition(List.of("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")));
    }

}
