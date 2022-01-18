package acmr.javacore.basic.lambda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * @author guohz
 * @version 1.0
 */
public class LambdaTest {
    public static void main(String[] args) {
        String[] planets = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        System.out.println("self toString : " + planets.toString());
        System.out.println("Arrays toString : " + Arrays.toString(planets));
        Arrays.sort(planets);
        System.out.println("Sorted in dictionary order : " + Arrays.toString(planets));
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
        System.out.println("Sorted in length order : " + Arrays.toString(planets));
        Timer timer = new Timer(1000, e -> System.out.println("The time is " + new Date()));
        timer.start();

        JOptionPane.showMessageDialog(null, "Exit?");
        System.exit(0);
    }

}
