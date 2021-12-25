package solutions_2021.day4;

import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day4GiantSquid {

    public static void main(String[] args) {
        List<String> adventInput = Util.adventInput("2021", "4");

        System.out.println(getFinalScorePartOne(adventInput));
        System.out.println(getFinalScorePartTwo(adventInput));
    }

    public static Integer getFinalScorePartOne(List<String> adventInput) {
        List<Integer> drawnNumberList = getDrawnNumberList(adventInput);
        List<Integer[][]> bingoBoardList = getBingoBoardList(adventInput);
        List<Integer[][]> shadowBingoBoardList = createShadowBingoBoard(bingoBoardList);

        int bingoBoardHit = -1;
        int drawnNumber = -1;

        for (int i = 0; i < drawnNumberList.size(); i++) {
            drawnNumber = drawnNumberList.get(i);
            checkBingoBoard(drawnNumber, bingoBoardList, shadowBingoBoardList);
            bingoBoardHit = checkBingoBoardsForHit(shadowBingoBoardList);

            if (bingoBoardHit != -1) {
                break;
            }
        }

        Integer sumOfUnmarkedNumbers = getSumOfUnmarkedNumbers(bingoBoardList.get(bingoBoardHit), shadowBingoBoardList.get(bingoBoardHit));

        return sumOfUnmarkedNumbers * drawnNumber;
    }

    public static Integer getFinalScorePartTwo(List<String> adventInput) {
        List<Integer> drawnNumberList = getDrawnNumberList(adventInput);
        List<Integer[][]> bingoBoardList = getBingoBoardList(adventInput);
        List<Integer[][]> shadowBingoBoardList = createShadowBingoBoard(bingoBoardList);

        int bingoBoardHit = -1;
        int drawnNumber = -1;

        outherloop:
        for (int i = 0; i < drawnNumberList.size(); i++) {
            drawnNumber = drawnNumberList.get(i);
            checkBingoBoard(drawnNumber, bingoBoardList, shadowBingoBoardList);
            bingoBoardHit = checkBingoBoardsForHit(shadowBingoBoardList);

            while (bingoBoardHit != -1) {
                if (bingoBoardList.size() > 1) {
                    bingoBoardList.remove(bingoBoardHit);
                    shadowBingoBoardList.remove(bingoBoardHit);
                } else if (bingoBoardList.size() == 1) {
                    break outherloop;
                }
                bingoBoardHit = checkBingoBoardsForHit(shadowBingoBoardList);
            }
        }

        Integer sumOfUnmarkedNumbers = getSumOfUnmarkedNumbers(bingoBoardList.get(bingoBoardHit), shadowBingoBoardList.get(bingoBoardHit));

        return sumOfUnmarkedNumbers * drawnNumber;
    }

    private static Integer getSumOfUnmarkedNumbers(Integer[][] bingoBoard, Integer[][] shadowBingoBoard) {
        Integer sumOfUnmarkedNumbers = 0;
        for (int i = 0; i < bingoBoard.length; i++) {
            Integer[] bingoBoardLine = bingoBoard[i];
            Integer[] shadowBingoBoardLine = shadowBingoBoard[i];

            for (int j = 0; j < bingoBoardLine.length; j++) {
                if (shadowBingoBoardLine[j] == null) {
                    sumOfUnmarkedNumbers = sumOfUnmarkedNumbers + bingoBoardLine[j];
                }
            }
        }

        return sumOfUnmarkedNumbers;
    }

    private static void checkBingoBoard(Integer drawnNumber, List<Integer[][]> bingoBoardList, List<Integer[][]> shadowBingoBoardList) {
        for (int i = 0; i < bingoBoardList.size(); i++) {
            Integer[][] bingoBoard = bingoBoardList.get(i);
            Integer[][] shadowBingoBoard = shadowBingoBoardList.get(i);

            for (int j = 0; j < bingoBoard.length; j++) {
                Integer[] bingoBoardLine = bingoBoard[j];
                Integer[] shadowBingoBoardLine = shadowBingoBoard[j];

                for (int n = 0; n < bingoBoardLine.length; n++) {
                    if (Objects.equals(bingoBoardLine[n], drawnNumber)) {
                        shadowBingoBoardLine[n] = 1;
                    }
                }
            }
        }
    }

    private static List<Integer[][]> createShadowBingoBoard(List<Integer[][]> bingoBoardList) {
        List<Integer[][]> shadowBingoBoardList = new ArrayList<>();
        for (int i = 0; i < bingoBoardList.size(); i++) {
            shadowBingoBoardList.add(new Integer[5][5]);
        }

        return shadowBingoBoardList;
    }

    private static Integer checkBingoBoardsForHit(List<Integer[][]> shadowBingoBoardList) {
        for (int i = 0; i < shadowBingoBoardList.size(); i++) {
            Integer[][] shadowBingoBoard = shadowBingoBoardList.get(i);

            for (int j = 0; j < shadowBingoBoard.length; j++) {
                Integer[] shadowBingoBoardRow = shadowBingoBoard[j];

                int rowHits = 0;
                for (int n = 0; n < shadowBingoBoardRow.length; n++) {
                    if (shadowBingoBoardRow[n] != null) {
                        rowHits++;
                    } else {
                        break;
                    }
                }
                if (rowHits == 5) {
                    return i;
                }
            }

            for (int j = 0; j < shadowBingoBoard.length; j++) {
                Integer[] shadowBingoBoardColumn = getColumn(shadowBingoBoard, j);

                int columnHits = 0;
                for (int n = 0; n < shadowBingoBoardColumn.length; n++) {
                    if (shadowBingoBoardColumn[n] != null) {
                        columnHits++;
                    } else {
                        break;
                    }
                }
                if (columnHits == 5) {
                    return i;
                }
            }
        }

        return -1;
    }

    public static Integer[] getColumn(Integer[][] array, int index) {
        Integer[] column = new Integer[array[0].length];
        for (int i = 0; i < column.length; i++) {
            column[i] = array[i][index];
        }

        return column;
    }

    public static List<Integer[][]> getBingoBoardList(List<String> adventInput) {
        List<Integer[][]> bingoBoardList = new ArrayList<>();

        for (int i = 2; i < adventInput.size(); i++) {
            bingoBoardList.add(getBingoBoardArray(adventInput, i));
            i = i + 5;
        }

        return bingoBoardList;
    }

    public static List<Integer> getDrawnNumberList(List<String> adventInput) {
        return Arrays.stream(adventInput.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static Integer[][] getBingoBoardArray(List<String> adventInput, int adventInputLineIndex) {
        Integer[][] bingoBoardArray = new Integer[5][5];
        for (int j = 0; j < 5; j++) {
            int index = adventInputLineIndex + j;

            int[] bingoBoardLineArray = getBingoBoardLineArray(adventInput, index);

            for (int x = 0; x < bingoBoardLineArray.length; x++) {
                bingoBoardArray[j][x] = bingoBoardLineArray[x];
            }
        }

        return bingoBoardArray;
    }

    private static int[] getBingoBoardLineArray(List<String> adventInput, int index) {
        return Arrays.stream(adventInput.get(index).trim().replaceAll("  ", " ").split(" ")).mapToInt(Integer::parseInt).toArray();
    }

}
