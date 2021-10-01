/**
 [BFS]
 1. traverse from the start, push all the possible next substring end_point into queue
 (these work as the the second level in a tree)
 2. for all the end_points in queue. pop and push all it's next possible end_points
 3. once we get the end == s.length(), means we reach to the end, return true;

 Time complexity : O(n^3).
 For every starting index, the search can continue till the end of the given string.
 Space Complexity: O(n).
 Queue of at most n size is needed
 */

import java.util.*;

class Solution1 {
    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> set = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();

        if (s == null || s.length() == 0) return true;

        // use a boolean array to mark if this 'level' has been visited (traversed)
        queue.offer(0);
        boolean[] visited  = new boolean[s.length()];

        while (! queue.isEmpty()) {
            int start = queue.poll();

            // if visited, skip and go next
            if (visited[start] == true) {
                continue;
            }
            for (int end = start + 1; end <= s.length() ; end ++) {
                // check if it is a valid substring
                if (set.contains(s.substring(start, end))) {
                    if (end == s.length()) {
                        return true;
                    }
                    queue.offer(end);
                }
            }
            visited[start] = true;
        }
        return false;
    }
}



/**
 [Dynamic Programming (DP)]
 when 0 <= j < i,
 if "dp[j] == true" && "set.contains(s.substring(j, i))",
 then dp[i] = true

 Time Complexity: O(n^3)
 two nested loops(O(n^2)) + substring(O(n))
 Space Complextiy: O(n)
 length of DP array is n+1
 */
class Solution2 {
    public boolean wordBreak(String s, List<String> wordDict) {

        if (s == null || s.length() == 0) return true;

        // don't forget to pass in the origin list
        Set<String> set = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];

        // base condition
        dp[0] = true;

        for (int i = 1; i <= s.length(); i ++) {
            for (int j = 0; j < i; j ++) {
                // DP equation
                if (dp[j] == true && set.contains(s.substring(j, i))) {
                    // there exits an expected j for current i
                    // break and go for next i
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}

