package solutions_2021.day5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class Day5HydrothermalVentureTest {
    
    @Test
    public void getFinalScorePartOneTest() {
        List<String> adventInput = List.of(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2");

        assertEquals(5, Day5HydrothermalVenture.getOverlappingCount(adventInput, true));
    }

    @Test
    public void getFinalScorePartTwoTest() {
        List<String> adventInput = List.of(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2");

        assertEquals(12, Day5HydrothermalVenture.getOverlappingCount(adventInput, false));
    }
}
