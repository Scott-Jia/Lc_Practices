package practice_before_lenovo;

import java.util.*;

class Lc253_MeetingRooms3 {
    /**
     * Given an array of meeting time intervals, return the minimum number of conference room required
     * @param intervals: the array of interval's array
     * @return the number of rooms needed
     */
    public int minMeetingRooms(int[][] intervals) {

        if (intervals == null || intervals.length == 0) return 0;

        // sort the element in array by their start time
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });

        // initialize a min-heap (priority queue)
        // to serve as an ongoing meeting poll (store ending time in PQ)
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                intervals.length,
                new Comparator<Integer>() {
                    public int compare(Integer a, Integer b) {
                        return a - b;
                    }
                });

        // for each meeting:
        //  compare it's start time and earliest ending time in the 'on-going pool'
        //  if there's conflict, add that meeting's ending time to pool (Priority Queue)
        //  if there's not, poll the top one from heap, and push the new ending time to heap
        for (int[] meeting : intervals) {
            if (! pq.isEmpty()) {
                int early_end = pq.peek();
                if (meeting[0] >= early_end) {
                    pq.poll();
                }
                pq.offer(meeting[1]);
            } else {
                pq.offer(meeting[1]);
            }

        }

        // the size of heap is rooms needed
        return pq.size();

    }
}
