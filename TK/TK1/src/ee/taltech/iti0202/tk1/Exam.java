package ee.taltech.iti0202.tk1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Exam {


    /**
     * Return the "centered" average of an array of ints, which we'll say is the mean average of the values,
     * except ignoring the largest and smallest values in the array. If there are multiple copies of the
     * smallest value, ignore just one copy, and likewise for the largest value. Use int division to produce
     * the final average. You may assume that the array is length 3 or more.
     * <p>
     * centeredAverage([1, 2, 3, 4, 100]) → 3
     * centeredAverage([1, 1, 5, 5, 10, 8, 7]) → 5
     * centeredAverage([-10, -4, -2, -4, -2, 0]) → -3
     */
    public static int centeredAverage(List<Integer> nums) {
        int sum = 0;
        List<Integer> numbers = nums.stream().sorted().collect(Collectors.toList());
        numbers.remove(numbers.get(0));
        numbers.remove(numbers.get(numbers.size() - 1));
        System.out.println(numbers);
        for (Integer n : numbers) {
            sum += n;
        }
        return sum / numbers.size();
    }


    /**
     * Given 2 int values greater than 0, return whichever value is nearest to 21 without going over.
     * Return 0 if they both go over.
     * <p>
     * blackjack(19, 21) → 21
     * blackjack(21, 19) → 21
     * blackjack(19, 22) → 19
     */
    public static int blackjack(int a, int b) {
        if (a > 21 && b > 21) {
            return 0;
        } else if (a == 21 || b == 21) {
            return 21;
        } else if (a > 21) {
            return b;
        } else if (b > 21) {
            return a;
        } else if (21 - a >= 21 - b) {
            return b;
        } else {
            return a;
        }
    }


    /**

     Given a string and an int n, return a string made of n repetitions of the last n characters
     of the string. You may assume that n is between 0 and the length of the string, inclusive.

     repeatEnd("Hello", 3) → "llollollo"
     repeatEnd("Hello", 2) → "lolo"
     repeatEnd("Hello", 1) → "o"
     */
    public static String repeatEnd(String str, int n) {
        String answer = "";
        String substring = str.substring(str.length() - n);
        for (int i = 0; i < n; i++) {
            answer += substring;
        }
        return answer;
    }

    /**

     Modify and return the given map as follows: if the keys "a" and "b" are both in the map
     and have equal values, remove them both.

     mapAB2({"a": "aaa", "b": "aaa", "c": "cake"}) → {"c": "cake"}
     mapAB2({"a": "aaa", "b": "bbb"}) → {"a": "aaa", "b": "bbb"}
     mapAB2({"a": "aaa", "b": "bbb", "c": "aaa"}) → {"a": "aaa", "b": "bbb", "c": "aaa"}
     */
    public static Map<String, String> mapAB(Map<String, String> map) {
        if (map.get("a").equals(map.get("b"))) {
            map.remove("a");
            map.remove("b");
        }
        return null;
    }

}
