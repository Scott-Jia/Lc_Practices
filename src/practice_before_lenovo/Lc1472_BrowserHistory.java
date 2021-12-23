package practice_before_lenovo;

import java.util.*;

class Lc1472_BrowserHistory {

    // max: the max length we can go further
    // current: the current page we are in
    int max = 0;
    int current = 0;

    // map: mapping the page index and url
    Map<Integer, String> map = new HashMap<>();

    public void BrowserHistory(String homepage) {
        map.put(current, homepage);
    }

    public void visit(String url) {
        // update the current, max, map
        current ++;
        map.put(current, url);
        max = current;
    }

    // check the leftmost/rightmost we can reach and update the current
    // return the url mapping the current index
    public String back(int steps) {
        current = Math.max(0, current - steps);
        return map.get(current);
    }

    public String forward(int steps) {
        current = Math.min(max, current + steps);
        return map.get(current);
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
