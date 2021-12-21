package solutions_2021.day3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3BinaryDiagnosticTest {

    @Test
    public void getGamaRateTest() {
        assertEquals("10110", Day3BinaryDiagnostic.getGamaOccurrenceRateBinary(List.of("00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010")));
    }

    @Test
    public void getEpislonRateTest() {
        assertEquals("01001", Day3BinaryDiagnostic.getEpsilonOccurrenceRateBinary("10110"));
    }

    @Test
    public void getPowerConsumptionTest() {
        assertEquals(198, Day3BinaryDiagnostic.getPowerConsumption("10110", "01001"));
    }

    @Test
    public void getLifeSupportRatingTest() {
        assertEquals(230, Day3BinaryDiagnostic.getLifeSupportRating(List.of("00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010")));
    }

}
