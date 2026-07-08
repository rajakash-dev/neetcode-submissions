class Solution {
    public void solve(char[][] board) {
        int rows = board.length;
        int columns = board[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i == 0 || j == 0 || i == rows - 1 || j == columns - 1) && board[i][j] == 'O') {
                    queue.add(new int[] { i, j });
                    board[i][j] = '#';
                }
            }
        }
        this.bfs(board, queue, rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(board[i][j]=='O'){
                    board[i][j]='X';
                }
                 if(board[i][j]=='#'){
                    board[i][j]='O';
                }
            }
        }
    }

    public void bfs(char[][] board, Queue<int[]> queue, int rows, int columns) {
        int[] dx = { 0, 1, -1, 0 };
        int[] dy = { 1, 0, 0, -1 };

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int x = node[0];
            int y = node[1];

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX >= 0 && newY >= 0 && newX < rows && newY < columns && board[newX][newY] == 'O') {
                    board[newX][newY] = '#';
                    queue.add(new int[] { newX, newY });
                }
            }
        }
    }
}
