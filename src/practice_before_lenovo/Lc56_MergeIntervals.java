package practice_before_lenovo;

import java.util.*;

class Lc56_MergeIntervals {
    public int[][] merge(int[][] intervals) {

        List<int[]> list = new ArrayList<>();

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
                intervals.length,
                new Comparator<int[]>() {
                    public int compare(int[] a, int[] b) {
                        return a[0] - b[0];
                    }
                }
        );

        for (int[] item : intervals) {
            pq.add(item);
        }

        // check the top 2 early meetings
        // if connected, merger and push back to PQ
        // else, add the unique interval to result list
        while (! pq.isEmpty()) {
            int[] first = pq.poll();
            if (! pq.isEmpty()) {
                int[] second = pq.peek();
                if (first[1] >= second[0]) {
                    second = pq.poll();
                    int[] merge = new int[]{first[0], Math.max(first[1], second[1])};
                    pq.add(merge);
                } else {
                    list.add(first);
                }
            } else {
                list.add(first);
            }
        }

        int[][] results = new int[list.size()][];

        for (int i = 0; i < list.size(); i ++) {
            results[i] = list.get(i);
        }

        return results;
    }
}
