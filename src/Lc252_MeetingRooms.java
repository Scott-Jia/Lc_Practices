import java.util.*;

class Lc252_MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {

        // sort the array by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // to compare the first end time and second start time one by one
        for (int i = 0; i < intervals.length - 1; i ++) {
            if (intervals[i][1] > intervals[i+1][0]) return false;
        }

        return true;
    }
}
