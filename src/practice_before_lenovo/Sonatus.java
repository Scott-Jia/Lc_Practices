package practice_before_lenovo;

import java.util.*;

public class Sonatus {
    public static List<Integer> twoSum(int[] arr) {
        // code goes here
        int target = arr[0];
        // int[] elements = new int[arr.length - 1];
        // Array.sorts(elements);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < arr.length - 1; i ++) {
            for (int j = i + 1; j < arr.length; j ++) {
                if (arr[i] + arr[j] == target) {
                    list.add(arr[i]);
                    list.add(arr[j]);
                }
            }
        }

        return list;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {17, 2, 2, 19, 8, 10, 7};
        System.out.println(twoSum(arr));
    }
}
