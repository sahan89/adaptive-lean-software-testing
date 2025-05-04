package com.lean;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WeightHelper {
    private final static String UNDER_WEIGHT = "Underweight";
    private final static String NORMAL_WEIGHT = "Normal weight";
    private final static String OVER_WEIGHT = "Overweight";
    private final static String OBESE = "Obese";


    public static void main(String[] args) {
        WeightHelper weightHelper = new WeightHelper();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter weight in kilograms: ");
            double weight = scanner.nextDouble();

            System.out.print("Enter Height in centimeters: ");
            double height = scanner.nextDouble();

            double bmi = weightHelper.calculateBMI(weight, height);
            String category = weightHelper.getBMICategory(weight, height);

            System.out.println("BMI     : " + bmi);
            System.out.println("Category: " + category);
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Error: Invalid input. Please enter numeric values for weight and height.");
        }
    }


    public double calculateBMI(double weight, double height) {
        validateInput(weight, height);
        double heightInMeters = height / 100.0;
        double bmi = weight / (heightInMeters * heightInMeters);
        return Math.round(bmi * (100.0)) / 100.0;
    }

    public String getBMICategory(double weight, double height) {
        double bmi = calculateBMI(weight, height);

        if (bmi < 18.5) {
            return UNDER_WEIGHT;
        } else if (bmi >= 18.5 && bmi < 25) {
            return NORMAL_WEIGHT;
        } else if (bmi >= 25 && bmi < 30) {
            return OVER_WEIGHT;
        } else {
            return OBESE;
        }
    }

    private static void validateInput(double weight, double height) {
        if (weight <= 0 || height <= 0) {
            throw new IllegalArgumentException("Error: Weight and height must be greater than 0.");
        }
    }
}
