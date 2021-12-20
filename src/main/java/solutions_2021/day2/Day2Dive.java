package solutions_2021.day2;

import util.Util;

import java.util.List;

public class Day2Dive {

    public static void main(String[] args) {
        List<String> adventInput = Util.adventInput("2021", "2");

        System.out.println("First part solution: " + calculatePosition(adventInput));
    }

    public static Integer calculatePosition(List<String> commandsList) {
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;

        for (String course : commandsList) {
            String[] courseParts = course.split(" ");
            String courseDirection = courseParts[0];
            int courseDirectionValue = Integer.parseInt(courseParts[1]);

            switch (courseDirection) {
                case "forward":
                    horizontalPosition = horizontalPosition + courseDirectionValue;
                    depth = depth + (courseDirectionValue * aim);
                    break;

                case "down":
                    aim = aim + courseDirectionValue;
                    break;

                case "up":
                    aim = aim - courseDirectionValue;
                    break;
            }
        }

        return horizontalPosition * depth;
    }

}
