package ee.taltech.iti0202.kt1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exam {
    /**
     * Given an array of integers,
     * every element appears twice except for one. Find that single one.
     * If there are not such (and in any other case) return 0.
     *
     * singleNumber([2,2,1]) => 1
     * singleNumber([4,1,2,1,2]) => 4
     */
    public static int singleNumber(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int n : nums) {
            if (list.contains(n)) {
                list.remove(Integer.valueOf(n));
            } else {
                list.add(n);
            }
        }
        if (list.isEmpty()) {
            return 0;
        }
        return list.get(0);
    }

    /**
     * Given a string, consider the prefix string made of the first N chars of the string.
     * Does that prefix string appear somewhere else in the string.
     * Assume that the string is not empty and that N is in the range 1 .. str.length().
     *
     * prefixExistsAgain("abXXabc", 1) => true
     * prefixExistsAgain("abXXabc", 2) => true
     * prefixExistsAgain("abXXabc", 3) => false
     * prefixExistsAgain("ababa", 3) => true
     */
    public static boolean prefixExistsAgain(String str, int n) {
        if (str.isEmpty() || n < 0 || n > str.length()) {
            return false;
        }
        String substring = str.substring(0, n);
        CharSequence sequence = new String(substring);
        if (str.substring(1).contains(sequence)) {
            return true;
        }
        return false;
    }

    /**public static void main(String[] args) {
        int[] arr = {3, 1, 3, 2, 2};
        System.out.println(singleNumber(arr));
        System.out.println(prefixExistsAgain("aaacddab", 3));
    }*/
}
