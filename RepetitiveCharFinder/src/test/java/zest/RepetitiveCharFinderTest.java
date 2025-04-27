package zest;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import net.jqwik.api.*;

class RepetitiveCharFinderTest {
    RepetitiveCharFinder finder = new RepetitiveCharFinder();

    // Structural Testing
    @Test
    void testRepetitiveCharFinder() {
        assertEquals(List.of('l'), finder.findNonUniqueCharacters("Hello"));
        assertEquals(List.of('a', 'b', 'c'), finder.findNonUniqueCharacters("aabbcc"));
        assertEquals(List.of(), finder.findNonUniqueCharacters("abcde"));
        assertEquals(List.of('a', 'A'), finder.findNonUniqueCharacters("aAaA"));
        assertEquals(List.of('x', '#', '9'), finder.findNonUniqueCharacters("xx##99"));
        assertEquals(List.of(), finder.findNonUniqueCharacters(""));
    }

    // Testing Contracts
    @Test
    void testPreconditions() {
        assertThrows(IllegalArgumentException.class, () -> finder.findNonUniqueCharacters(null));
    }

    @Test
    void testPostconditions() {
        assertNotNull(finder.findNonUniqueCharacters(""));
        assertNotNull(finder.findNonUniqueCharacters("Hello"));

        List<Character> result = finder.findNonUniqueCharacters("acfsfca");
        Set<Character> set = new HashSet<>(result);
        assertEquals(set.size(), result.size());

        assertEquals(List.of('a', 'c', 'f'), result);
    }

    // Property-based Testing
    @Provide
    Arbitrary<String> generateInput() {
        return Arbitraries.strings()
                .ascii()
                .ofMinLength(0).ofMaxLength(10);
    }

    @Property
    void testNotNullResult(@ForAll("generateInput") String input) {
        Assume.that(input != null);

        List<Character> result = finder.findNonUniqueCharacters(input);
        assertNotNull(result);
    }

    @Property
    void testUniqueResult(@ForAll("generateInput") String input) {
        Assume.that(input != null);

        List<Character> result = finder.findNonUniqueCharacters(input);

        Set<Character> set = new HashSet<>(result);
        assertEquals(set.size(), result.size());
    }

    @Property
    void testCorrectOrder(@ForAll("generateInput") String input) {
        Assume.that(input != null);

        List<Character> result = finder.findNonUniqueCharacters(input);

        Set<Character> seen = new HashSet<>();
        int lastIndex = -1;

        for (char c : result) {
            int index = input.indexOf(c);
            assertFalse(seen.contains(c));
            assertTrue(index > lastIndex);
            seen.add(c);
            lastIndex = index;
        }
    }
}
