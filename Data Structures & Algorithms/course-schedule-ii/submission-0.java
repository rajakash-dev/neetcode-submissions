class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] node : prerequisites) {
            int from = node[1];
            int to = node[0];
            adjList.get(from).add(to);
            indegree[to]++;
        }

        Queue<Integer> zeroIndegree = new ArrayDeque<>();
        int[] result = new int[numCourses];
        int idx = 0;
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                zeroIndegree.add(i);
            }
        }
        while (!zeroIndegree.isEmpty()) {
            int node = zeroIndegree.poll();
            result[idx] = node;
            idx++;
            for (Integer connectedNodes : adjList.get(node)) {
                indegree[connectedNodes]--;
                if (indegree[connectedNodes] == 0) {
                    zeroIndegree.add(connectedNodes);
                }
            }
        }

        for (int node : indegree) {
            if (node != 0) {
                return new int[0];
            }
        }
        return result;
    }
}