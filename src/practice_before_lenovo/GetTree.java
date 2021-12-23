package practice_before_lenovo;

import java.util.*;


public class GetTree {

    public static String getSExpress(String s) {
        boolean[][] graph = new boolean[26][26];
        Set<Character> set = new HashSet<>();

        boolean E2 = false;  // E2: duplicate edge

        // construct the graph and check E2
        for (int i = 1; i < s.length(); i += 6) {
            int x = s.charAt(i) - 'A', y = s.charAt(i + 2) - 'A';
            if (graph[x][y]) {
                E2 = true;
            }
            graph[x][y] = true;
            set.add(s.charAt(i));
            set.add(s.charAt(i + 2));
        }

        boolean E1 = false;  // E1: more than 2 children

        for (int i = 0; i < 26; i ++) {
            int count = 0;
            for (int j = 0; j < 26; j ++) {
                if (graph[i][j]) count ++;
            }
            if (count > 2) return "E1";
        }

        if (E2) return "E2";

        // check E3: cycle present and E4 : multiple roots
        int root_num = 0;
        char root = ' ';
//        if (root == ' ') {
//            return "E5";
//        }
        for (char node : set) {
            for (int i = 0; i < 26; i ++) {
//                System.out.println(node);
//                break;
                if (graph[i][node - 'A']) {
                    break;
                }
                if (i == 25) {
                    root_num ++;
                    root = node;
                    boolean[] visited = new boolean[26];
                    if (isCycle(node, graph, visited)) return "E3";
                }
            }
        }

        if (root_num == 0) return "E3";  // if no root, must be a cycle
        if (root_num > 1) return "E4";  // if more than one roots
        if (root == ' ') {
            return "E5";
        }

        return getHelper(root, graph);
    }

    private static boolean isCycle(char node, boolean[][] graph, boolean[] visited) {

        if (visited[node - 'A']) return true;

        visited[node - 'A'] = true;
        for (int i = 0; i < 26; i ++) {
            if (graph[node - 'A'][i]) {
                if (isCycle((char)(i + 'A'), graph, visited)) return true;
            }
        }
        return false;
    }

    private static String getHelper(char root, boolean[][] graph) {
        String left = "", right = "";
        for (int i = 0; i < 26; i ++) {
            if (graph[root - 'A'][i]) {
                left = getHelper((char)(i + 'A'), graph);
                for (int j = 0; j < 26; j ++) {
                    if (graph[root - 'A'][j]) {
                        right = getHelper((char)(i + 'A'), graph);
                        break;
                    }
                }
                break;
            }
        }

        return "(" + root + left + right + ")";
    }


    public static void main(String[] args) {

        String str = " ";
//        System.out.println('B' - 'A');
        System.out.println(getSExpress(str));
    }
}
