package solutions_2021.day3;

import util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day3BinaryDiagnostic {

    public static void main(String[] args) {
        List<String> adventInput = Util.adventInput("2021", "3");

        System.out.println("First part solution: " + getPowerConsumption(adventInput));
        System.out.println("Second part solution: " + getLifeSupportRating(adventInput));
    }

    public static Integer getPowerConsumption(List<String> diagnosticList) {
        String gamaRateBinary = getGamaOccurrenceRateBinary(diagnosticList);
        String epsilonRateBinary = getEpsilonOccurrenceRateBinary(gamaRateBinary);

        Integer gamaRateInt = Integer.parseInt(gamaRateBinary, 2);
        Integer epislonRateInt = Integer.parseInt(epsilonRateBinary, 2);

        return gamaRateInt * epislonRateInt;
    }

    public static Integer getLifeSupportRating(List<String> diagnosticList) {
        String oxygenRatingBinary = getDiagnosticByOccurrencesInEachIndex(diagnosticList, true);
        String co2RatingBinary = getDiagnosticByOccurrencesInEachIndex(diagnosticList, false);

        Integer oxygenRatingInt = Integer.parseInt(oxygenRatingBinary, 2);
        Integer co2RatingInt = Integer.parseInt(co2RatingBinary, 2);

        return oxygenRatingInt * co2RatingInt;
    }

    public static String getGamaOccurrenceRateBinary(List<String> diagnosticList) {
        int[] zeroOccurrenceInIndexArray = new int[diagnosticList.get(0).length()];
        int[] oneOccurrenceInIndexArray = new int[diagnosticList.get(0).length()];

        for (String diagnostic : diagnosticList) {
            for (int i = 0; i < diagnostic.length(); i++) {
                if (String.valueOf(diagnostic.charAt(i)).equals("0")) {
                    zeroOccurrenceInIndexArray[i]++;
                } else {
                    oneOccurrenceInIndexArray[i]++;
                }
            }
        }

        return getBinaryByMostOccurrences(zeroOccurrenceInIndexArray, oneOccurrenceInIndexArray);
    }

    public static String getEpsilonOccurrenceRateBinary(String gamaRate) {
        char[] gamaRateParts = gamaRate.toCharArray();
        StringBuilder epsilonRate = new StringBuilder();

        for (char gamaRatePart : gamaRateParts) {
            if (gamaRatePart == '0') {
                epsilonRate.append("1");
            } else {
                epsilonRate.append("0");
            }
        }

        return epsilonRate.toString();
    }

    private static String getDiagnosticByOccurrencesInEachIndex(List<String> diagnosticList, boolean oxygenGenerator) {
        String occurrenceRate = getBinaryDiagnosticIndexOccurrenceRate(diagnosticList, oxygenGenerator);
        List<String> diagnosticByOccurrencesOfIndex = new ArrayList<>(diagnosticList);

        for (int i = 0; i < occurrenceRate.length(); i++) {
            if (diagnosticByOccurrencesOfIndex.size() > 1) {
                List<String> diagnosticByMostOccurrencesTemp = new ArrayList<>(diagnosticByOccurrencesOfIndex);
                occurrenceRate = getBinaryDiagnosticIndexOccurrenceRate(diagnosticByOccurrencesOfIndex, oxygenGenerator);
                diagnosticByOccurrencesOfIndex.clear();
                for (String diagnostic : diagnosticByMostOccurrencesTemp) {
                    if (diagnostic.charAt(i) == occurrenceRate.charAt(i)) {
                        diagnosticByOccurrencesOfIndex.add(diagnostic);
                    }
                }
            } else {
                break;
            }
        }

        return diagnosticByOccurrencesOfIndex.get(0);
    }

    private static String getBinaryByMostOccurrences(int[] zeroOccurrenceInIndexArray, int[] oneOccurrenceInIndexArray) {
        StringBuilder binary = new StringBuilder();

        for (int i = 0; i < zeroOccurrenceInIndexArray.length; i++) {
            if (zeroOccurrenceInIndexArray[i] > oneOccurrenceInIndexArray[i]) {
                binary.append("0");
            } else {
                binary.append("1");
            }
        }

        return binary.toString();
    }

    private static String getBinaryDiagnosticIndexOccurrenceRate(List<String> diagnosticList, boolean oxygenGenerator) {
        String occurrenceRate;
        if (oxygenGenerator) {
            occurrenceRate = getGamaOccurrenceRateBinary(diagnosticList);
        } else {
            occurrenceRate = getEpsilonOccurrenceRateBinary(getGamaOccurrenceRateBinary(diagnosticList));
        }

        return occurrenceRate;
    }

}
