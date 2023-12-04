import Days.Day;
import Days.Day1.Day1;

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
            dayNumber = 1; //DELETE
            Day day = null;
            switch (dayNumber) {
                case 1:
                    day = new Day1();
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