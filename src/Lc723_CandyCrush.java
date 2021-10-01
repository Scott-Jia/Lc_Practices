class Lc723_CandyCrush {
    public int[][] candyCrush(int[][] board) {
        int row = board.length, column = board[0].length;
        boolean toDo = false;

        // crush the candies horizontally
        // if 3 adjacent cells have same values and not 0, change them as negative (mark crushed)
        // mark "to do"= true to set the status to unstable
        for (int r = 0; r < row; r ++) {
            for (int c = 0; c < column - 2; c ++) {
                int v = Math.abs(board[r][c]);
                // use Math.abs to check if they have same value
                // use positive/negative to mark if they have crushed [to avoid later drop down]
                if (v != 0 && v == Math.abs(board[r][c+1]) && v == Math.abs(board[r][c+2])) {
                    board[r][c] = board[r][c+1] = board[r][c+2] = -v;
                    toDo = true;
                }
            }
        }

        // crush the candies vertically
        for (int r = 0; r < row -2; r ++) {
            for (int c = 0; c < column; c ++) {
                int p = Math.abs(board[r][c]);
                if (p != 0 && p == Math.abs(board[r+1][c]) && p == Math.abs(board[r+2][c])) {
                    board[r][c] = board[r+1][c] = board[r+2][c] = -p;
                    toDo = true;
                }
            }
        }

        // gravity operation:
        //      for each column, from bottom to up, if the value > 0, set the value to bottom one by one
        //      then fill the other cells by 0
        for (int c = 0; c < column; c ++) {
            int valid_row = row - 1;
            for (int r = row - 1; r >= 0; r --) {
                if (board[r][c] > 0) {
                    board[valid_row --][c] = board[r][c];
                }
            }

            while (valid_row >= 0) {
                board[valid_row --][c] = 0;
            }
        }

        // if stable -> return; else -> recursively call this function
        return toDo ? candyCrush(board) : board;

    }
}
