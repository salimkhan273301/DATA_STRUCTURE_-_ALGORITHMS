package generalproblem;

public class ExcelSheetColumnTitle {

    // Approach 1: Iterative with Modulo and Division
    public String convertToTitle1(int columnNumber) {
        StringBuilder result = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            char c = (char) ('A' + columnNumber % 26);
            result.append(c);
            columnNumber /= 26;
        }
        return result.reverse().toString();
    }

    // Approach 2: Recursive Solution
    public String convertToTitle2(int columnNumber) {
        if (columnNumber == 0) return "";
        columnNumber--;
        return convertToTitle2(columnNumber / 26) + (char) ('A' + columnNumber % 26);
    }

    // Approach 3: Using a Loop Without Reversing
    public String convertToTitle3(int columnNumber) {
        StringBuilder result = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            result.insert(0, (char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }
        return result.toString();
    }

    // Approach 4: Using a Predefined Character Array
    public String convertToTitle4(int columnNumber) {
        char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder result = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            result.append(letters[columnNumber % 26]);
            columnNumber /= 26;
        }
        return result.reverse().toString();
    }

    // Approach 5: Using Mathematical Calculation
    public String convertToTitle5(int columnNumber) {
        int length = 0;
        int temp = columnNumber;
        while (temp > 0) {
            temp = (temp - 1) / 26;
            length++;
        }
        char[] result = new char[length];
        for (int i = length - 1; i >= 0; i--) {
            columnNumber--;
            result[i] = (char) ('A' + columnNumber % 26);
            columnNumber /= 26;
        }
        return new String(result);
    }

    // Test Cases
    public static void main(String[] args) {
        ExcelSheetColumnTitle excel = new ExcelSheetColumnTitle();
        int[] testCases = {1, 28, 701, 52, 26, 27, 703, 18278, 731, 1000};
        String[] expected = {"A", "AB", "ZY", "AZ", "Z", "AA", "AAA", "ZZZ", "ABC", "ALL"};

        for (int i = 0; i < testCases.length; i++) {
            int columnNumber = testCases[i];
            String result1 = excel.convertToTitle1(columnNumber);
            String result2 = excel.convertToTitle2(columnNumber);
            String result3 = excel.convertToTitle3(columnNumber);
            String result4 = excel.convertToTitle4(columnNumber);
            String result5 = excel.convertToTitle5(columnNumber);

            System.out.println("Test Case " + (i + 1) + ": Column Number = " + columnNumber);
            System.out.println("Expected: " + expected[i]);
            System.out.println("Approach 1: " + result1 + " " + (result1.equals(expected[i]) ? "✓" : "✗"));
            System.out.println("Approach 2: " + result2 + " " + (result2.equals(expected[i]) ? "✓" : "✗"));
            System.out.println("Approach 3: " + result3 + " " + (result3.equals(expected[i]) ? "✓" : "✗"));
            System.out.println("Approach 4: " + result4 + " " + (result4.equals(expected[i]) ? "✓" : "✗"));
            System.out.println("Approach 5: " + result5 + " " + (result5.equals(expected[i]) ? "✓" : "✗"));
            System.out.println();
        }
    }
}