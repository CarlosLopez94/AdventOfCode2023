package Days;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Carlos
 */
public class Util {

    public static String readFileOneLine(Integer day) {
        final String path = "src/Days/Day%d/input.txt".formatted(day);
        return readFileOneLine(path);
    }

    public static String readFileOneLine(String path) {
        String fileContent = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String line = bf.readLine();
            while (line != null) {
                fileContent += line;
                line = bf.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileContent;
    }

    public static List<String> readFile(Integer day) {
        final String path = "src/Days/Day%d/input.txt".formatted(day);
        List<String> fileContent = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String line = bf.readLine();
            while (line != null) {
                fileContent.add(line);
                line = bf.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileContent;
    }

    public static Long gcd(Long a, Long b) {
        while (b > 0) {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    public static Long gcd(List<Long> input) {
        Long result = input.get(0);
        for (int i = 1; i < input.size(); i++) result = gcd(result, input.get(i));
        return result;
    }

    public static Long lcm(Long a, Long b) {
        return a * (b / gcd(a, b));
    }

    public static long lcm(List<Long> input) {
        Long result = input.get(0);
        for (int i = 1; i < input.size(); i++) result = lcm(result, input.get(i));
        return result;
    }

}