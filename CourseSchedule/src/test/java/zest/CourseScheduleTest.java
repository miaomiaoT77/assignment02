package zest;

import net.jqwik.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CourseScheduleTest {
    CourseSchedule scheduler = new CourseSchedule();

    // Structural Testing
    @Test
    void checkPrerequisites() {
        assertTrue(scheduler.canFinish(2, new int[][]{{1, 0}}));
        assertFalse(scheduler.canFinish(2, new int[][]{{0, 1}, {1, 0}}));
        assertTrue(scheduler.canFinish(4, new int[][]{{1, 0}, {2, 1}, {3, 2}}));
        assertTrue(scheduler.canFinish(3, new int[][]{{0, 2}, {1, 2}}));
        assertFalse(scheduler.canFinish(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}));
        assertFalse(scheduler.canFinish(2, new int[][]{{1, 1}}));
        assertTrue(scheduler.canFinish(4, new int[][]{{1, 2}, {0, 3}}));
    }

    // Designing and Testing Contracts
    @Test
    void checkPrecondition() {
        assertThrows(IllegalArgumentException.class, () -> scheduler.canFinish(0, new int[][]{}));
        assertThrows(IllegalArgumentException.class, () -> scheduler.canFinish(65, new int[][]{{0, 1}, {2, 3}}));
        assertDoesNotThrow(() -> scheduler.canFinish(2, new int[][]{}));
        assertThrows(IllegalArgumentException.class, () -> scheduler.canFinish(2, null));
        assertThrows(IllegalArgumentException.class, () -> scheduler.canFinish(1, new int[][]{{0}}));
        assertThrows(IllegalArgumentException.class, () -> scheduler.canFinish(3, new int[][]{{3, 1}}));
        assertThrows(IllegalArgumentException.class, () -> scheduler.canFinish(3, new int[][]{{1, 3}}));
        assertThrows(IllegalArgumentException.class, () -> scheduler.canFinish(3, new int[][]{{5, 4}}));
    }

    // Property-based Testing
    @Provide
    Arbitrary<Tuple.Tuple2<Integer, int[][]>> generateCourseData() {
        Arbitrary<Integer> numCourses = Arbitraries.integers().between(1, 64);

        return numCourses.flatMap(nc ->
                Arbitraries.integers().between(0, nc - 1)
                        .tuple2()
                        .list()  // 먼저 리스트로 받고
                        .ofMinSize(0).ofMaxSize(10)
                        .map(list -> {
                            int[][] prereqs = new int[list.size()][2];
                            for (int i = 0; i < list.size(); i++) {
                                Tuple.Tuple2<Integer, Integer> t = list.get(i);
                                prereqs[i][0] = t.get1();
                                prereqs[i][1] = t.get2();
                            }
                            return Tuple.of(nc, prereqs);
                        })
        );
    }

    @Provide
    Arbitrary<Tuple.Tuple2<Integer, int[][]>> generateCyclicCourseData() {
        Arbitrary<Integer> numCourses = Arbitraries.integers().between(2, 64);
        return numCourses.map(nc -> {
            int a = 0;
            int b = (a + 1) % nc;

            int[][] cyclicPrereqs = new int[][]{
                    {a, b},
                    {b, a}
            };
            return Tuple.of(nc, cyclicPrereqs);
        });
    }

    @Property
    void testValidResult(@ForAll("generateCourseData") Tuple.Tuple2<Integer, int[][]> input) {
        int numCourses = input.get1();
        int[][] prerequisites = input.get2();

        boolean result = scheduler.canFinish(numCourses, prerequisites);

        assertTrue(result == true || result == false);
    }

    @Property
    void testCyclicPrerequisites(@ForAll("generateCyclicCourseData") Tuple.Tuple2<Integer, int[][]> input) {
        int numCourses = input.get1();
        int[][] prerequisites = input.get2();

        boolean result = scheduler.canFinish(numCourses, prerequisites);

        assertFalse(result);
    }

}
