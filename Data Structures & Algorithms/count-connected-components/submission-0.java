class Solution {
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            adjList.get(to).add(from);
            adjList.get(from).add(to);
        }

        int result = 0;
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                result++;
                stack.add(i);
                visited[i] = true;
                dfs(stack, visited, adjList);
            }
        }
        return result;

    }

    public void dfs(Stack<Integer> stack, boolean[] visited, List<List<Integer>> adjList) {
        while (!stack.isEmpty()) {
            int node = stack.pop();
            for (int n : adjList.get(node)) {
                if (!visited[n]) {
                    stack.add(n);
                    visited[n] = true;
                }
            }
        }
    }
}