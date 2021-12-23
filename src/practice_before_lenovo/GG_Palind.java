package practice_before_lenovo;

import java.util.*;

public class GG_Palind {

    public static int getLen(String[] arr) {
        int[][] map = new int[26][26];
        int res = 0;

        // fill out the pair
        // once form a pair, update the res and map;
        // otherwise update the map only
        for (String pair: arr) {
            int m = pair.charAt(0) - 'a';
            int n = pair.charAt(1) - 'a';
            if (map[n][m] > 0) {
                map[n][m] -= 1;
                res += 4;
            } else {
                map[m][n] += 1;
            }
        }

        // check if there's pair with same char
        for (int i = 0; i < 26; i ++) {
            for (int j = 0; j < 26; j ++) {
                if (i == j && map[i][j] > 0) {
                    res += 2;
                    return res;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String[] str1 = new String[]{"oo", "ss", "pp"};
        String[] str2 = new String[]{"so", "os", "kk"};
        String[] str3 = new String[]{"so", "os", "kc", "ck"};
        String[] str4 = new String[]{"ck", "kc", "ho", "kc"};
        String[] str5 = new String[]{"ab", "hu", "ba", "nn"};
        String[] str6 = new String[]{"so", "oo", "kk", "od"};

        System.out.println(getLen(str1));  //2
        System.out.println(getLen(str2));  //6
        System.out.println(getLen(str3));  //8
        System.out.println(getLen(str4));  //4
        System.out.println(getLen(str5));  //6
        System.out.println(getLen(str6));  //2

    }
}
