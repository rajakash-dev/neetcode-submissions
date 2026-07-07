class Solution {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        Queue<int[]> pacific = new ArrayDeque<>();
        Queue<int[]> atlantic = new ArrayDeque<>();

        int rows = heights.length;
        int columns = heights[0].length;

        List<List<Integer>> result = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            pacific.add(new int[]{r,0});
            atlantic.add(new int[]{r, columns - 1});
        }
        for (int c = 0; c < columns; c++) {
            pacific.add(new int[]{0, c});
            atlantic.add(new int[]{rows - 1, c});
        }

        boolean[][] visitedFromPacific = this.iterativeBfs(heights, pacific, rows, columns);
        boolean[][] visitedFromAtlantic = this.iterativeBfs(heights, atlantic, rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (visitedFromAtlantic[i][j] && visitedFromPacific[i][j]) {
                    result.add(List.of(i, j));
                }
            }
        }
        return result;
    }

    public boolean[][] iterativeBfs(int[][] data, Queue<int[]> queue, int rows, int columns) {
        boolean[][] visited = new boolean[rows][columns];
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int row = node[0];
            int col = node[1];
            visited[row][col] = true;
            // U , D , L , R - all directions
            int[] dx = new int[]{0, 0, 1, -1};
            int[] dy = new int[]{-1, 1, 0, 0};

            for (int i = 0; i < 4; i++) {
                int nRow = row + dx[i];
                int nCol = col + dy[i];
                if (nRow >= rows || nCol >= columns || nRow < 0 || nCol < 0 || visited[nRow][nCol]
                        || data[nRow][nCol] < data[row][col]) {
                    continue;
                }
                queue.offer(new int[]{nRow, nCol});
            }
        }
        return visited;
    }
}
