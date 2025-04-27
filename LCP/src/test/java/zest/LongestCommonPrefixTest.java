package zest;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import net.jqwik.api.*;

class LongestCommonPrefixTest {
    LongestCommonPrefix lcp = new LongestCommonPrefix();

    // Structural Testing
    @Test
    void testLongestCommonPrefix() {
        assertEquals("apple", lcp.lcp(new String[]{"apple"}));
        assertEquals("banana", lcp.lcp(new String[]{"banana", "banana", "banana"}));
        assertEquals("fl", lcp.lcp(new String[]{"flower", "flow", "flight"}));
        assertEquals("", lcp.lcp(new String[]{"apple", "banana", "tomato"}));
        assertEquals("a", lcp.lcp(new String[]{"a", "ab", "abc"}));
        assertEquals("", lcp.lcp(new String[]{"abc", "bcd", "cde"}));
    }

    // Testing Contracts
    @Test
    void testPreconditions() {
        assertThrows(IllegalArgumentException.class, () -> lcp.lcp(null));
        assertThrows(IllegalArgumentException.class, () -> lcp.lcp(new String[]{}));
        assertThrows(IllegalArgumentException.class, () -> lcp.lcp(new String[]{"apple", null, "banana"}));
        assertThrows(IllegalArgumentException.class, () -> lcp.lcp(new String[]{"apple", "banana", ""}));
    }

    @Test
    void testPostconditions() {
        assertEquals("xyz", lcp.lcp(new String[]{"xyzab", "xyzqwe", "xyzmno"}));
        assertNotNull(lcp.lcp(new String[]{"apple", "orange"}));
    }

    // Property-based Testing
    @Provide
    Arbitrary<String[]> generateCommonPrefixStrings() {
        Arbitrary<String> commonPrefix = Arbitraries.strings()
                .withCharRange('a', 'z')
                .ofMinLength(1).ofMaxLength(5);

        Arbitrary<String> string = Arbitraries.strings()
                .withCharRange('a', 'z')
                .ofMinLength(0).ofMaxLength(5);

        return Combinators.combine(
                commonPrefix,
                string.list().ofMinSize(1).ofMaxSize(10)
        ).as((prefix, strings) -> {
            String[] arr = new String[strings.size()];
            for (int i = 0; i < strings.size(); i++) {
                arr[i] = prefix + strings.get(i);
            }
            return arr;
        });
    }

    @Property
    boolean testNotNullResult(@ForAll("generateCommonPrefixStrings") String[] strs) {
        String result = lcp.lcp(strs);
        assertNotNull(result);

        for (String str : strs) {
            if (!str.startsWith(result)) return false;
        }

        return true;
    }

    @Property
    boolean testValidResult(@ForAll("generateCommonPrefixStrings") String[] strs) {
        String result = lcp.lcp(strs);

        for (String str : strs) {
            assertTrue(str.startsWith(result));
        }

        return true;
    }

}
