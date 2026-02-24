package basic_110_question;

import java.util.*;
import java.util.stream.Collectors;

public class VowelConsonantAllMethods {

    // 1️⃣ Basic Loop Method
    public static void countMethod1(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println("Empty String");
            return;
        }

        int vowels = 0, consonants = 0;
        str = str.toLowerCase();

        for (char ch : str.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                if ("aeiou".indexOf(ch) != -1) {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        System.out.println("Method1 -> Vowels: " + vowels + ", Consonants: " + consonants);
    }

    // 2️⃣ Using Switch Case
    public static void countMethod2(String str) {
        int vowels = 0, consonants = 0;

        for (char ch : str.toLowerCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                switch (ch) {
                    case 'a': case 'e': case 'i': case 'o': case 'u':
                        vowels++;
                        break;
                    default:
                        consonants++;
                }
            }
        }

        System.out.println("Method2 -> Vowels: " + vowels + ", Consonants: " + consonants);
    }

    // 3️⃣ Using Regex
    public static void countMethod3(String str) {
        String onlyLetters = str.replaceAll("[^a-zA-Z]", "");
        String vowelsStr = onlyLetters.replaceAll("(?i)[^aeiou]", "");

        int vowels = vowelsStr.length();
        int consonants = onlyLetters.length() - vowels;

        System.out.println("Method3 -> Vowels: " + vowels + ", Consonants: " + consonants);
    }

    // 4️⃣ Using Java 8 Stream
    public static void countMethod4(String str) {
        long vowels = str.toLowerCase().chars()
                .filter(c -> "aeiou".indexOf(c) != -1)
                .count();

        long consonants = str.toLowerCase().chars()
                .filter(c -> c >= 'a' && c <= 'z')
                .filter(c -> "aeiou".indexOf(c) == -1)
                .count();

        System.out.println("Method4 -> Vowels: " + vowels + ", Consonants: " + consonants);
    }

    // 5️⃣ Using Set (Clean & Professional)
    public static void countMethod5(String str) {
        Set<Character> vowelSet = new HashSet<>(Arrays.asList('a','e','i','o','u'));

        int vowels = 0, consonants = 0;

        for (char ch : str.toLowerCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                if (vowelSet.contains(ch)) {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        System.out.println("Method5 -> Vowels: " + vowels + ", Consonants: " + consonants);
    }

    // 6️⃣ Count Everything (Vowels, Consonants, Digits, Spaces)
    public static void countAll(String str) {
        int vowels = 0, consonants = 0, digits = 0, spaces = 0;

        for (char ch : str.toLowerCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                if ("aeiou".indexOf(ch) != -1)
                    vowels++;
                else
                    consonants++;
            } else if (Character.isDigit(ch)) {
                digits++;
            } else if (Character.isWhitespace(ch)) {
                spaces++;
            }
        }

        System.out.println("Method6 -> Vowels: " + vowels +
                ", Consonants: " + consonants +
                ", Digits: " + digits +
                ", Spaces: " + spaces);
    }

    public static void main(String[] args) {

        String text = "Hello World 123";

        System.out.println("Input: " + text);
        System.out.println("--------------------------");

        countMethod1(text);
        countMethod2(text);
        countMethod3(text);
        countMethod4(text);
        countMethod5(text);
        countAll(text);
    }
}