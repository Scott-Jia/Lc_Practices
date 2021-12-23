package practice_before_lenovo;

import java.util.*;

public class GG_Rod {

    public static int cntRings(String str) {
        HashMap<Integer, HashSet<Character>> map = new HashMap<>();

        for (int i = 0; i < str.length() - 1; i ++) {
            char letter = str.charAt(i);
            int rod = str.charAt(++ i) - '0';
            if (!map.containsKey(rod)) {
                map.put(rod, new HashSet<>());
            }
            map.get(rod).add(letter);
        }

        int res = 0;

        for (int rod: map.keySet()) {
            HashSet<Character> set = map.get(rod);
            if (set.contains('R') && set.contains('G') && set.contains('B'))
                res += 1;
        }

        return res;
    }

    public static void main(String[] args) {
        String str1 = "R1G1B1";
        String str2 = "R0G1B2";
        String str3 = "R8R0B5G1B8G8";
        String str4 = "R1G1B1R2G2B2";

        System.out.println(cntRings(str1));  //1
        System.out.println(cntRings(str2));  //0
        System.out.println(cntRings(str3));  //1
        System.out.println(cntRings(str4));  //2
    }
}
