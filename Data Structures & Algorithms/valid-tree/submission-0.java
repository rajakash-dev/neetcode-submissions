class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length > n - 1) {
            return false;
        }


        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0,-1);

        while(!stack.isEmpty()){
            int node = stack.pop();
            for(int neighbour : adjList.get(node)){
                if(parent.get(node)==neighbour){
                    continue;
                }
                if(parent.containsKey(neighbour)){
                    return false;
                }
                stack.add(neighbour);
                parent.put(neighbour, node);
            }

        }
        return parent.size() == n;
    }
}