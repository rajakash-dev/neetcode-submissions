class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        Queue<int[]> pacific = new ArrayDeque<>();
        Queue<int[]> atlantic = new ArrayDeque<>();
        List<List<Integer>> list = new ArrayList<>();
        int rows = heights.length;
        int cols = heights[0].length;

        for(int r=0;r<rows;r++){
            pacific.offer(new int[]{r,0});
            atlantic.offer(new int[]{r,cols-1});
        }

        for(int c=0;c<cols;c++){
            pacific.offer(new int[]{0,c});
            atlantic.offer(new int[]{rows-1,c});

        }

        boolean[][] visitedP = this.bfs(pacific,heights,rows,cols);
        boolean[][] visitedA = this.bfs(atlantic,heights,rows,cols);

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(visitedA[i][j] && visitedP[i][j]){
                    list.add(List.of(i,j));
                }
            }
        }
        return list;
    }

    public boolean[][] bfs(Queue<int[]> queue, int[][] heights, int rows, int columns) {
        int[] dx = new int[] { -1, 0, 0, 1 };
        int[] dy = new int[] { 0, 1, -1, 0 };
        boolean[][] visited = new boolean[rows][columns];
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int x = node[0];
            int y = node[1];
            visited[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX >= 0 && newY >= 0 && newX < rows && newY < columns && heights[newX][newY] >= heights[x][y]
                        && !visited[newX][newY]) {
                    queue.offer(new int[] { newX, newY });
                }
            }
        }
        return visited;
    }
}
