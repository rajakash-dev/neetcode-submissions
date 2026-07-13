class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];

        for (int[] p : prerequisites) {
            int from = p[1];
            int to = p[0];
            adjList.get(from).add(to);
            indegree[to]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i=0;i<indegree.length;i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int[] result = new int[numCourses];
        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result[count] = node;
            count++;
            for (int n : adjList.get(node)) {
                indegree[n]--;
                if (indegree[n] == 0) {
                    queue.add(n);
                }
            }
        }
        for (int degree : indegree) {
            if (degree != 0) {
                return false;
            }
        }
        return true;
    }
}
