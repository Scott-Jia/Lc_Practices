import java.util.*;

class Leaderboard {
    /** Complexity
     addScore() --> O(1)
     reset() --> O(1)
     top() --> O(NlogN)
     space: O(N)
     */
    // use a private map to mapping playerId/Score
    private Map<Integer, Integer> map;

    public Leaderboard() {
        this.map = new HashMap<Integer, Integer>();
    }

    public void addScore(int playerId, int score) {
        if (! map.containsKey(playerId)) {
            this.map.put(playerId, score);
        } else {
            this.map.put(playerId, map.getOrDefault(playerId, 0) + score);
        }
    }

    // create a arraylist to store all the values in map
    // use collections.sort() to sort the list
    // get the top k elements and return sum
    public int top(int K) {
        List<Integer> ls = new ArrayList<>(this.map.values());
        Collections.sort(ls, Collections.reverseOrder());
        int sum = 0;
        for (int i = 0; i < K; i ++) {
            sum += ls.get(i);
        }
        return sum;
    }

    public void reset(int playerId) {
        map.put(playerId, 0);
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */
