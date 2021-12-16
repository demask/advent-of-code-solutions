package day1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1SonarSweepTest {

    @Test
    public void numberOfMeasurementsLargerThanPreviousTest() {
        assertEquals(7, day1.Day1SonarSweep.numberOfMeasurementsLargerThanPrevious(List.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)));
    }

    @Test
    public void numberOfMeasurementsLargerThanPreviousTest2() {
        assertEquals(5, day1.Day1SonarSweep.numberOfMeasurementsLargerThanPrevious(List.of(607, 618, 618, 617, 647, 716, 769, 792)));
    }

    @Test
    public void makeThreeMeasurementSlidingWindowListTest() {
        assertEquals(List.of(607, 618, 618, 617, 647, 716, 769, 792),
            day1.Day1SonarSweep.makeThreeMeasurementSlidingWindowList(List.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)));
    }

}
