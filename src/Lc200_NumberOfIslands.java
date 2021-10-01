/**
 [DFS]  Linear scan the 2d grid map, if a node contains a '1', then it is a root node that triggers a Depth First Search.
 During DFS, every visited node should be set as '0' to mark as visited node.
 Count the number of root nodes that trigger DFS, this number would be the number of islands since each DFS starting at some root identifies an island.
 */

import java.util.*;

class Lc200_1 {
    public int numIslands(char[][] grid) {
        // corner case
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length, columns = grid[0].length;
        int count = 0;

        // traverse the 2D array, find how many 'root' trigger a dfs
        for (int i = 0; i < rows; i ++) {
            for (int j = 0; j < columns; j ++) {
                if (grid[i][j] == '1') {
                    count ++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    void dfs(char[][] grid, int r, int c) {
        int rows = grid.length, columns = grid[0].length;

        // beyond the boarder or not '1', return
        if (r < 0 || r >= rows || c < 0 || c >= columns || grid[r][c] == '0') return;

        // if valid island, convert it to '0' (marked it as visited)
        // if should be marked first, to avoid the path comes back
        grid[r][c] = '0';

        // then dfs() its top, right, bottom, left in order
        dfs(grid, r-1, c);  // top
        dfs(grid, r, c+1);  // right
        dfs(grid, r+1, c);  // bottom
        dfs(grid, r, c-1);  // left
    }
}



/**
 [BFS] Linear scan the 2d grid map, if a node contains a '1', then it is a root node that triggers a Breadth First Search.
 */
class Lc200_2 {
    public int numIslands(char[][] grid) {

        // corner case
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length, columns = grid[0].length;

        int count = 0;

        // traverse the 2D array, start BFS if we meet '1'
        // count how many BFS we got in total
        for (int i = 0; i < rows; i ++) {
            for (int j = 0; j < columns; j ++) {
                if (grid[i][j] == '1') {
                    count ++;
                    // convert it to '0' (mark it as visited)
                    // initialize a Queue, push the INDEX (of 2D array) in
                    grid[i][j] = '0';
                    Queue<Integer> queue = new LinkedList<>();
                    queue.offer(i * columns + j);

                    // perform BFS and change all the '1' to '0' meanwhile
                    while (! queue.isEmpty()) {
                        // poll the first element
                        // get the location from index number, push it neighbours in queue in order
                        int index = queue.poll();
                        int r = index / columns;
                        int c = index % columns;

                        // top
                        if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                            grid[r - 1][c] = '0';
                            queue.offer((r - 1) * columns + c);
                            // grid[r - 1][c] = '0';
                        }
                        // right
                        if (c + 1 < columns && grid[r][c + 1] == '1') {
                            grid[r][c + 1] = '0';
                            queue.offer(r * columns + c + 1);
                        }
                        // bottom
                        if (r + 1 < rows && grid[r + 1][c] == '1') {
                            grid[r + 1][c] = '0';
                            queue.offer((r + 1) * columns + c);
                        }
                        // left
                        if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                            grid[r][c - 1] = '0';
                            queue.offer(r * columns + c - 1);
                        }
                    }

                }
            }

        }

        return count;
    }
}

