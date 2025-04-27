package zest;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        if (numCourses < 1 || numCourses > 64) {
            throw new IllegalArgumentException("numCourses must be between 1 and 64.");
        }

        if (prerequisites == null) {
            throw new IllegalArgumentException("prerequisites cannot be null.");
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            if (prerequisite.length != 2) {
                throw new IllegalArgumentException("Each prerequisite must contain exactly 2 elements.");
            }

            if(prerequisite[0] >= numCourses || prerequisite[1] >= numCourses) {
                throw new IllegalArgumentException("Each prerequisite must be less than numCourses-1.");
            }

            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        int[] visited = new int[numCourses];

        // Check for cycles
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (hasCycle(graph, visited, i)) {
                    return false;
                }
            }
        }

        return true; 
    }

    private boolean hasCycle(List<List<Integer>> graph, int[] visited, int course) {
        if (visited[course] == 1) {
            return true;
        }
        if (visited[course] == 2) {
            return false;
        }

        visited[course] = 1;

        for (int adjCourse : graph.get(course)) {
            if (hasCycle(graph, visited, adjCourse)) {
                return true;
            }
        }

        visited[course] = 2;
        return false;
    }
}
