package generalproblem;

import java.util.*;

public class TotalSalary {

    public static int calculateTotalSalary(int basic, char grade) {
        double hra = 0.2 * basic;
        double da = 0.5 * basic;
        double pf = 0.11 * basic;

        int allowance;

        switch (grade) {
            case 'A':
                allowance = 1700;
                break;
            case 'B':
                allowance = 1500;
                break;
            default:
                allowance = 1300;
        }

        double total = basic + hra + da + allowance - pf;
        return (int) Math.round(total);  // round() returns long, cast to int
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // number of test cases
        for (int i = 0; i < T; i++) {
            int basic = sc.nextInt();
            char grade = sc.next().charAt(0);
            int totalSalary = calculateTotalSalary(basic, grade);
            System.out.println(totalSalary);
            
          
			/*
			 * 5114 B 6436 C 3960 C 9590 B 488 B
			 */
            
			/*
			 * 9631 11533 7596 16748 2276  output
			 */
        }
        sc.close();
    }
}
