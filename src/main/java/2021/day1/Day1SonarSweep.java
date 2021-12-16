package day1;

import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day1SonarSweep {

    public static void main(String[] args) {
        List<String> adventInput = Util.adventInput("2021", "1");

        List<Integer> measurementIntegerList = adventInput.stream().map(Integer::parseInt).collect(Collectors.toList());

        System.out.println("First part solution: " + numberOfMeasurementsLargerThanPrevious(measurementIntegerList));
        System.out.println("Second part solution: " + numberOfMeasurementsLargerThanPrevious(makeThreeMeasurementSlidingWindowList(measurementIntegerList)));
    }

    public static Integer numberOfMeasurementsLargerThanPrevious(List<Integer> measurementList) {
        Integer inMemoryMeasurement = null;
        Integer numberOfMeasurementsLargerThanPrevious = 0;
        for (Integer measurement : measurementList) {
            if (inMemoryMeasurement != null && measurement > inMemoryMeasurement) {
                numberOfMeasurementsLargerThanPrevious++;
            }
            inMemoryMeasurement = measurement;
        }

        return numberOfMeasurementsLargerThanPrevious;
    }

    public static List<Integer> makeThreeMeasurementSlidingWindowList(List<Integer> measurementList) {
        int slidingIndex = 0;
        List<Integer> threeMeasurementSlidingWindowList = new ArrayList<>();

        for (Integer measurement : measurementList) {
            int slidingMeasurementSum = 0;

            if (slidingIndex + 2 < measurementList.size()) {
                for (int i = 3, localSlidingIndex = slidingIndex; i > 0; i--, localSlidingIndex++) {
                    slidingMeasurementSum = slidingMeasurementSum + measurementList.get(localSlidingIndex);
                }

                threeMeasurementSlidingWindowList.add(slidingMeasurementSum);
                slidingIndex++;
            }
        }

        return threeMeasurementSlidingWindowList;
    }

}
