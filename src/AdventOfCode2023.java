import Days.Day;
import Days.Day1.Day1;
import Days.Day2.Day2;
import Days.Day3.Day3;
import Days.Day4.Day4;
import Days.Day5.Day5;
import Days.Day6.Day6;

import java.util.Scanner;

/**
 * @author Carlos
 */
public class AdventOfCode2023 {

    public static void main(String[] args) {
        System.out.println("Welcome to Advent of Code 2022! Please choose a day between 1 and 25:");
        int dayNumber;
        do {
            //dayNumber = ReadDay();
            dayNumber = 6; //DELETE
            Day day = null;
            switch (dayNumber) {
                case 1:
                    day = new Day1();
                    break;
                case 2:
                    day = new Day2();
                    break;
                case 3:
                    day = new Day3();
                    break;
                case 4:
                    day = new Day4();
                    break;
                case 5:
                    day = new Day5();
                    break;
                case 6:
                    day = new Day6();
                    break;
                default:
                    day = null;
                    System.out.println("ups, this day isn't avaliable yet! Try again");
            }
            if (day != null) {
                day.exec();
            }
        } while (dayNumber < 0 && dayNumber > 25);
    }

    public static int ReadDay() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

}