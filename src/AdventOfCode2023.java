import Days.Day;
import Days.Day1.Day1;
import Days.Day2.Day2;
import Days.Day3.Day3;
import Days.Day4.Day4;
import Days.Day5.Day5;
import Days.Day6.Day6;
import Days.Day7.Day7;
import Days.Day8.Day8;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Carlos
 */
public class AdventOfCode2023 {

    public static void main(String[] args) {
        System.out.println("Welcome to Advent of Code 2022! Please choose a day between 1 and 25:");
        int dayNumber;
        do {
            //dayNumber = ReadDay();
            dayNumber = 8; //DELETE

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
                case 7:
                    day = new Day7();
                    break;
                case 8:
                    day = new Day8();
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