package solutions_2021.day4;

import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4GiantSquid {

    public static void main(String[] args) {
        List<String> adventInput = Util.adventInput("2021", "4");
        List<Integer> drawnNumberList = getDrawnNumberList(adventInput);
        List<Integer[][]> bingoBoardList = getBingoBoardList(adventInput);

        System.out.println(drawnNumberList);
        System.out.println(bingoBoardList);
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
        return Arrays.stream(
                adventInput.get(index)
                    .trim().replaceAll("  ", " ")
                    .split(" "))
            .mapToInt(Integer::parseInt).toArray();
    }

}
