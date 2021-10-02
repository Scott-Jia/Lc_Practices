import java.util.*;


class UndergroundSystem {
    /**
     there are two HashMap:
     checkInData = {'id': ['station', 'time']}   --> map1
     journeyData = ('startStation->endStation': ['totalTime', 'totalTrips']) --> map2

     checkIn() : put info to map1
     checkOut(): get startStation/time by id from map1
     use helper function to form a new string as route
     search the time/cnt from map2 by route as the key
     update the totalTime/totalTrip and put back to map2
     remove this check-in info from map1
     getAverage():
     use helper function to form a route string
     uee the 'route' as key to find time and trips
     ave = time / trip
     */
    private Map<String, Pair<Double, Double>> journeyData = new HashMap<>();
    // a hashmap to store the check-in info, {'id': ['station':'time']}
    private Map<Integer, Pair<String, Integer>> checkInData = new HashMap<>();

    public UndergroundSystem() {

    }

    public void checkIn(int id, String stationName, int t) {
        checkInData.put(id, new Pair<>(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> checkInDataForId = checkInData.get(id);
        String startStation = checkInDataForId.getKey();
        Integer checkInTime = checkInDataForId.getValue();

        String routeKey = stationsKey(startStation, stationName);
        Pair<Double, Double> routeStats = journeyData.getOrDefault(routeKey, new Pair<>(0.0, 0.0));
        Double totalTripTime = routeStats.getKey();
        Double totalTrips = routeStats.getValue();

        double tripTime = t - checkInTime;
        journeyData.put(routeKey, new Pair<>(totalTripTime + tripTime, totalTrips + 1));

        checkInData.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {

        String routeKey = stationsKey(startStation, endStation);
        Double totalTime = journeyData.get(routeKey).getKey();
        Double totalTrips = journeyData.get(routeKey).getValue();

        return totalTime / totalTrips;
    }

    private String stationsKey(String startStation, String endStation) {
        return startStation + "->" + endStation;
    }
}

class Pair<K, V> {
    public K key;
    public V value;
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey() { return this.key; }

    public V getValue() { return this.value; }
}
/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
