class GasStation {
    /**
     1. Initiate total_tank and curr_tank as zero, and choose station 0 as a starting station.
     2. Iterate over all stations :
     - Update total_tank and curr_tank at each step, by adding gas[i] and subtracting cost[i].
     - If curr_tank < 0 at i + 1 station, make i + 1 station a new starting point and reset curr_tank = 0 to start with              an empty tank.
     3. Return -1 if total_tank < 0 and starting station otherwise.
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int candidate = 0;
        int curr_gain = 0;
        int total_gain = 0;

        for (int i = 0; i < gas.length; i ++) {
            curr_gain += gas[i] - cost[i];
            total_gain += gas[i] - cost[i];

            // means this station is false
            // move to the next start by i + 1;
            if (curr_gain < 0) {
                candidate = i + 1;
                curr_gain = 0;
            }
        }

        return total_gain >= 0 ? candidate : -1;
    }
}
