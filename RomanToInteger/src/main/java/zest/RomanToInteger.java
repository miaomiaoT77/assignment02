package zest;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    // Map to store Roman numerals and their corresponding integer values
    private static final Map<Character, Integer> romanMap = new HashMap<>();

    static {
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
    }

    /**
     * convert a string representing a Roman numeral into its integer equivalent
     *
     * @param roman input has to be string (I,V,X,L,C,D,M), non-empty.
     * @return The integer is  a positive number between 1 and 3999.
     */

    public void checkInvariant() {
        assert invariant() : "Roman numeral mapping errored";
    }

    public boolean invariant() {
        return romanMap.size() == 7;
    }

    public int romanToInt(String s) {
        //invariant at the start public method
        checkInvariant();
        // precondition
        assert s != null && !s.isEmpty() : "String cannot be null or empty";
        assert s.matches("[IVXLCDM]+") : "Invalid Roman numeral characters";


        int result = romanMap.get(s.charAt(s.length() - 1));

        for (int i = s.length() - 2; i >= 0; i--) {
            if (romanMap.get(s.charAt(i)) < romanMap.get(s.charAt(i + 1))) {
                result -= romanMap.get(s.charAt(i));
            } else {
                result += romanMap.get(s.charAt(i));
            }
        }
        // post-condition
        assert result >= 1 && result <= 3999 : "Result out of range";
        assert !InvalidRomanFormat(s) : "Invalid Roman numeral format (e.g., IIII, VV, XXXX)";
        //invariant at the end public method
        checkInvariant();
        return result;
    }
    // checking the invalid format method
    private boolean InvalidRomanFormat(String s) {
        return s.matches(".*I{4}.*|.*X{4}.*|.*C{4}.*|.*M{4}.*") ||  //consecutive 4 numbers
                s.matches(".*V{2}.*|.*L{2}.*|.*D{2}.*");           // repetitive numbers
    }
}