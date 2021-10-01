import java.util.*;

class Lc1047_RemoveAdjacentDuplicates {
    /**
     * You are given a string s consisting of lowercase English letters.
     * A duplicate removal consists of choosing two adjacent and equal letters and removing them.
     * We repeatedly make duplicate removals on s until we no longer can.
     * Return the final string after all such duplicate removals have been made.
     *
     * @param s: a string to be processed
     * @return a stable string
     */
    public String removeDuplicates(String s) {
        if (s == null || s.length() < 2) return s;

        Stack<Character> stack = new Stack<>();

        stack.push(s.charAt(0));

        for (int i = 1; i < s.length(); i ++) {
            char ch = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(ch);
            } else {
                if (stack.peek() == ch) {
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }
        }

        List<Character> ls = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while (! stack.isEmpty()) {
            ls.add(stack.pop());
        }

        for (int i = ls.size() - 1; i >= 0; i --) {
            sb.append(ls.get(i));
        }

        String res = sb.toString();

        return res;
    }
}
