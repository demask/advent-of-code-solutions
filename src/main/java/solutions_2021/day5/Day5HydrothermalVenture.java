package solutions_2021.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import util.Util;

public class Day5HydrothermalVenture {
    
    public static void main(String[] args) {
        List<String> adventInput = Util.adventInput("2021", "5");

        System.out.println(getOverlappingCount(adventInput, false));
    }

    public static Integer getOverlappingCount(List<String> adventInput, boolean firstPart){
        List<LineCoordinate> lineCoordinateList = getLineCoordinateList(adventInput);
        Integer greatestCoordintePoint = getGreatesLineCoordinatePoint(lineCoordinateList);
        Integer[][] integerArray = createAndPopulateIntegerArrayWithZeros(greatestCoordintePoint);

        for (LineCoordinate lineCoordinate : lineCoordinateList) {
            List<Coordinate> coordinateList = new ArrayList<>();
            if(firstPart) {
                coordinateList = getAllCoordinatesForStraightLineList(lineCoordinate); 
            } else {
                coordinateList = getAllCoordinatesForComplexLineList(lineCoordinate);
            }
            addLineCoordinateToIntegerArray(coordinateList, integerArray);
        }
        int overlapping = findInHowManyPointsDoLinesOverlap(integerArray);

        return overlapping;
    }

    private static Integer findInHowManyPointsDoLinesOverlap(Integer[][] integerArray) {
        int countOverlapping = 0;
        for (int i=0; i<(integerArray.length); i++ ) {
            for (int j=0; j<integerArray[i].length; j++) {
                if(integerArray[i][j] > 1) {
                    countOverlapping++;
                }
            }   
        }

        return countOverlapping;
    }

    private static Integer[][] createAndPopulateIntegerArrayWithZeros(Integer greatestCoordintePoint) {
        Integer[][] integerArray = new Integer[greatestCoordintePoint][greatestCoordintePoint];
        for (int i=0; i<(integerArray.length); i++ ) {
            for (int j=0; j<integerArray[i].length; j++) {
                integerArray[i][j] = 0;
            }   
        }

        return integerArray;
    }

    private static void addLineCoordinateToIntegerArray(List<Coordinate> coordinateList, Integer[][] integerArray) {
        for (Coordinate coordinate : coordinateList) {
            int currentCoordinateValue = integerArray[coordinate.getX()][coordinate.getY()];
            integerArray[coordinate.getX()][coordinate.getY()] = ++currentCoordinateValue;
        }
    }

    private static List<Coordinate> getAllCoordinatesForStraightLineList(LineCoordinate lineCoordinate) {
        List<Coordinate> fullLineCoordinateList = new ArrayList<>();
        
        if(lineCoordinate.getX1() == lineCoordinate.getX2()) {
            List<Integer> yList = Arrays.asList(lineCoordinate.getY1(), lineCoordinate.getY2());
            Collections.sort(yList);
            for (int i = yList.get(0); i<=yList.get(1); i++) {
                fullLineCoordinateList.add(new Coordinate(lineCoordinate.getX1(), i));
            }
        } else if(lineCoordinate.getY1() == lineCoordinate.getY2()) {
            List<Integer> xList = Arrays.asList(lineCoordinate.getX1(), lineCoordinate.getX2());
            Collections.sort(xList);
            for (int i = xList.get(0); i<=xList.get(1); i++) {
                fullLineCoordinateList.add(new Coordinate(i, lineCoordinate.getY1()));
            }
        }

        return fullLineCoordinateList;
    }

    private static List<Coordinate> getAllCoordinatesForComplexLineList(LineCoordinate lineCoordinate) {
        List<Coordinate> fullLineCoordinateList = new ArrayList<>();
        
        if(lineCoordinate.getX1() == lineCoordinate.getX2()) {
            fullLineCoordinateList = getAllCoordinatesForStraightLineList(lineCoordinate);
        } else if(lineCoordinate.getY1() == lineCoordinate.getY2()) {
            fullLineCoordinateList = getAllCoordinatesForStraightLineList(lineCoordinate);
        } else {
            List<Integer> xCoordinates = new ArrayList<>();
            int startingXCoordinate = 0;
            int endingXCoordinate = 0;
            int slope = 0;

            List<Integer> yCoordinates = new ArrayList<>();
            int startingYCoordinate = 0;
            int endingYCoordinate = 0;

            if(lineCoordinate.getX1() < lineCoordinate.getX2()) {
                startingXCoordinate = lineCoordinate.getX1();
                endingXCoordinate = lineCoordinate.getX2();
                startingYCoordinate = lineCoordinate.getY1();
                endingYCoordinate = lineCoordinate.getY2();
                slope = lineCoordinate.getY1() - lineCoordinate.getY2();
            } else {
                startingXCoordinate = lineCoordinate.getX2();
                endingXCoordinate = lineCoordinate.getX1();
                startingYCoordinate = lineCoordinate.getY2();
                endingYCoordinate = lineCoordinate.getY1();
                slope = lineCoordinate.getY2() - lineCoordinate.getY1();
            }
            for (int i = startingXCoordinate; i<=endingXCoordinate; i++) {
                xCoordinates.add(i);
            }

            if(slope > 0) {
                for (int i = startingYCoordinate; i>=endingYCoordinate; i--) {
                    yCoordinates.add(i);
                }
            } else {
                for (int i = startingYCoordinate; i<=endingYCoordinate; i++) {
                    yCoordinates.add(i);
                }
            }

            for(int i = 0; i < yCoordinates.size(); i++) {
                fullLineCoordinateList.add(new Coordinate(xCoordinates.get(i), yCoordinates.get(i)));
            }
        }

        return fullLineCoordinateList;
    }

    private static List<LineCoordinate> getLineCoordinateList(List<String> adventInput) {
        List<LineCoordinate> lineCoordinateList = new ArrayList<>();
        for (String string : adventInput) {
            String[] coordinates = string.split(" -> ");
            String[] coordinatesSplittedFirstPart = coordinates[0].split(",");
            String[] coordinatesSplittedSecondPart = coordinates[1].split(",");
            int x1 = Integer.parseInt(coordinatesSplittedFirstPart[0]);
            int y1 = Integer.parseInt(coordinatesSplittedFirstPart[1]);
            int x2 = Integer.parseInt(coordinatesSplittedSecondPart[0]);
            int y2 = Integer.parseInt(coordinatesSplittedSecondPart[1]);

            LineCoordinate lineCoordinate = new LineCoordinate(x1, y1, x2, y2);
            lineCoordinateList.add(lineCoordinate);
        }

        return lineCoordinateList;
    }

    private static Integer getGreatesLineCoordinatePoint(List<LineCoordinate> coordinateList) {
        List<LineCoordinate> sortedCoordinate = coordinateList.stream()
                    .sorted(compareByCoordinates.reversed())
                    .collect(Collectors.toList());
                LineCoordinate coordinate = sortedCoordinate.get(0);

        List<Integer> oneCoordinateList = List.of(coordinate.getX1(), coordinate.getX2(), coordinate.getY1(), coordinate.getY2());
        List<Integer> oneCoordinateListSorted = oneCoordinateList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        Integer greatestLineCoordinate = oneCoordinateListSorted.get(0);
        return ++greatestLineCoordinate;
    }

    static Comparator<LineCoordinate> compareByCoordinates = Comparator
                        .comparing(LineCoordinate::getX1)
                        .thenComparing(LineCoordinate::getX2)
                        .thenComparing(LineCoordinate::getY1)
                        .thenComparing(LineCoordinate::getY2);

}