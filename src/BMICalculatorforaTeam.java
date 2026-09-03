import java.util.*;
public class BMICalculatorforaTeam {
    public static String getBMIStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }
    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("Person | Height (m) | Weight (kg) | BMI    | Status");
        System.out.println("-------|------------|------------|--------|------------");
        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            System.out.printf("%6d | %10.2f | %10.2f | %6.2f | %s%n",
                    i + 1, heights[i], weights[i], bmi, getBMIStatus(bmi));
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();
        double[] heights = new double[n];
        double[] weights = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.println("\nEmployee " + (i + 1) + ":");
            System.out.print("  Height (in meters): ");
            heights[i] = sc.nextDouble();
            System.out.print("  Weight (in kg): ");
            weights[i] = sc.nextDouble();
        }
        System.out.println("\n=== Wellness Report ===");
        printWellnessReport(heights, weights);
    }
}
