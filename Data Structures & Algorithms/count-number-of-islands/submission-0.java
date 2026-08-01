class Solution {
    public int numIslands(char[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int count=0;
        boolean[][] visited = new boolean[r][c];
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (!visited[x][y] && grid[x][y] == '1') {
                    visited[x][y] = true;
                    count++;
                    dfs(x, y, r, c, grid, visited);
                }
            }
        }
        return count;
    }

    public void dfs(int x, int y, int r, int c, char[][] grid, boolean[][] visited) {
        int[] dx = new int[] {0, -1, 0, 1};
        int[] dy = new int[] {1, 0, -1, 0};

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX >= 0 && newY >= 0 && newX < r && newY < c && !visited[newX][newY] && grid[newX][newY] == '1'){
                visited[newX][newY]=true;
                dfs(newX, newY, r, c, grid, visited);
            }
        }
    }
}
