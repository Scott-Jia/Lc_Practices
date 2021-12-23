package practice_before_lenovo;

import java.util.*;

class Lc1583_CountUnhappyFriends {
    /**
     The Key is how to check "x prefers u over y" for each x from 0 to n-1;

     I need know y, so I need a hashmap; (x ,y);
     I need know for x, If u is before y in preferences[x],
     so I need a matrix which rank[x][u] means index of u in preferences[x];
     then, x prefers u over y ---> rank[x][u]< rank[x][y];
     */
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        // initialize a map to easily get person's pair
        Map<Integer, Integer> map = new HashMap<>();
        // initialize a 2D array to compare rank[x][u] < rand[x][y]
        int[][] ranks = new int[n][n];
        // loop the pairs array
        for (int[] p : pairs) {
            int person1 = p[0];
            int person2 = p[1];
            // for each person in pair
            // fill out the 'rank' matrix by search from 'preferences' matrix
            for (int i = 0; i < n -1; i ++) {
                int fri1 = preferences[person1][i];
                int fri2 = preferences[person2][i];
                ranks[person1][fri1] = i;
                ranks[person2][fri2] = i;
            }
            // put this pair into hashmap
            map.put(person1, person2);
            map.put(person2, person1);
        }
        // count the unhappy people
        int ans = 0;
        for (int j = 0; j < n; j ++) {
            if (isUnhappy(ranks, preferences, j, map)) {
                ans ++;
            }
        }
        return ans;
    }

    public boolean isUnhappy(int[][] ranks, int[][] preferences, int x, Map<Integer, Integer> map) {
        // get person x's pair
        int x_fri = map.get(x);

        // loop x's friends list in preference
        for (int k : preferences[x]) {
            // if they're pair, means happy, return
            if (k == x_fri) {
                break;
            } else {
                // according to definition
                // if there exists a k meets the following, return true;
                int k_fri = map.get(k);
                if (ranks[x][k] < ranks[x][x_fri] && ranks[k][x] < ranks[k][k_fri]) {
                    return true;
                }
            }
        }
        return false;
    }

}
