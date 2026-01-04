package generalproblem;

/*Problem statement
You are given a string STR representing the column title in an Excel Sheet. You need to find its corresponding column number.

For example: A corresponds to 1, B to 2, C to 3, … , Z to 26, AA to 27, .. and so on.

Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 ≤ T ≤ 50
1 ≤ |STR| ≤ 12

where 'T' denotes number of testcases, and |STR| denotes the length of the string.

Time Limit : 1 sec 
Sample Input 1:
3
A
AB
F
Sample Output 1:
1
28
6
Explanation of Input 1:
The first test case, STR = “A”. This corresponds to column number 1.

The second test case, STR = “AB”. This corresponds to column number 28.

The third test case, STR = “F”. This corresponds to column number 6.
Sample Input 2:
3
AZ
COD
ZZZ
Sample Output 2
52
2422
18278*/

public class ExcelColumnToNumber {

    public static int titleToNumber(String columnTitle) {
        int result = 0;
        
        for (int i = 0; i < columnTitle.length(); i++) {
            char ch = columnTitle.charAt(i);
            int value = ch - 'A' + 1; // Convert A-Z to 1-26
           // System.out.println(value); 1 to 26 
            result = result * 26 + value;
        }
        
        return result;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(titleToNumber("A"));   // 1
        System.out.println(titleToNumber("Z"));   // 26
        System.out.println(titleToNumber("AA"));  // 27
        System.out.println(titleToNumber("AB"));  // 28
        System.out.println(titleToNumber("ZY"));  // 701
        System.out.println(titleToNumber("FXSHRXW")); // 2147483647 (max int)
    }
}

