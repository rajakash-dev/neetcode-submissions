class Solution {
       public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. Build graph: adjList[u] = list of nodes that depend on u (u -> v)
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            adjList.add(new ArrayList<>());

        int[] indegree = new int[numCourses];

        for (int[] e : prerequisites) {
            int u = e[0], v = e[1]; // define direction clearly: u -> v
            adjList.get(u).add(v);
            indegree[v]++;
        }

        // 2. Seed queue with all zero-indegree nodes (no dependencies)
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                queue.add(i);
        }

        // 3. Process nodes, peeling off edges as we go
        // int[] order = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            idx++;
            // order[idx++] = node;

            for (int next : adjList.get(node)) {
                if (--indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        // 4. If we processed fewer than n nodes, there's a cycle
        return idx == numCourses;
    }
}
