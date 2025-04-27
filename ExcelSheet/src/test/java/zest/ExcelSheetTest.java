package zest;


import net.jqwik.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExcelSheetTest {
    ExcelSheet excelSheet = new ExcelSheet();

    // Structural Testing
    @Test
    void testExcelSheet() {
        assertEquals(1, excelSheet.titleToNumber("A"));
        assertEquals(26, excelSheet.titleToNumber("Z"));
        assertEquals(28, excelSheet.titleToNumber("AB"));
        assertEquals(52, excelSheet.titleToNumber("AZ"));
        assertEquals(701, excelSheet.titleToNumber("ZY"));
        assertEquals(1850407191, excelSheet.titleToNumber("EYSFHSI"));

        assertEquals(27, excelSheet.titleToNumber("AA"));
        assertEquals(1406, excelSheet.titleToNumber("BBB"));
        assertEquals(109674, excelSheet.titleToNumber("FFFF"));
        assertEquals(4277295, excelSheet.titleToNumber("IIIII"));
        assertEquals(321272406, excelSheet.titleToNumber("ZZZZZZ"));
        assertEquals(2130480844, excelSheet.titleToNumber("TTTTTTT"));
    }

    // Testing Contracts
    @Test
    void testPreconditions() {
        assertThrows(IllegalArgumentException.class, () -> excelSheet.titleToNumber(null));
        assertThrows(IllegalArgumentException.class, () -> excelSheet.titleToNumber(""));
        assertThrows(IllegalArgumentException.class, () -> excelSheet.titleToNumber("aB"));
        assertThrows(IllegalArgumentException.class, () -> excelSheet.titleToNumber("A1"));
        assertThrows(IllegalArgumentException.class, () -> excelSheet.titleToNumber("B$"));
    }

    @Test
    void testPostconditions() {
        assertEquals(1, excelSheet.titleToNumber("A"));
        assertEquals(28, excelSheet.titleToNumber("AB"));
        assertEquals(702, excelSheet.titleToNumber("ZZ"));
        assertEquals(2147483647, excelSheet.titleToNumber("FXSHRXW"));
        assertTrue(excelSheet.titleToNumber("TY") >= 1);
    }

    // Property-based Testing
    @Provide
    Arbitrary<String> generateColumnTitles() {
        return Arbitraries.strings()
                .withCharRange('A', 'Z')
                .ofMinLength(1)
                .ofMaxLength(7)
                .filter(s -> {
                    try {
                        int result = excelSheet.titleToNumber(s);
                        return result > 0;
                    } catch (Exception e) {
                        return false;
                    }
                });
    }

    @Property
    boolean testValidResult(@ForAll("generateColumnTitles") String title) {
        int result = excelSheet.titleToNumber(title);
        return result >= 1;
    }
}
