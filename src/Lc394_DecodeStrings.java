import java.util.*;

class Lc394_DecodeStrings {
    public static String decodeString(String s) {
        if (s == null || s.length() == 0) return s;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i ++) {
            char ch = s.charAt(i);
            // when we hit ']', we should do some operations
            if (ch == ']') {
                // store all chars within a pair of '[',']' to a current list
                List<Character> curr_ls = new ArrayList<>();
                while (! stack.isEmpty() && stack.peek() != '[') {
                    curr_ls.add(stack.pop());
                }
                stack.pop();
                // calculate the numbers of repeat for the current substring
                int k = 0, base = 1;
                while (! stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + base * (stack.pop() - '0');
                    base *= 10;
                }
                // push the substring back to stack by calculated times
                int size = curr_ls.size();
                while (k > 0) {
                    for (int j = size-1; j >= 0; j --) {
                        stack.push(curr_ls.get(j));
                    }
                    k --;
                }
            } else {
                stack.push(ch);
            }
        }

        List<Character> list = new ArrayList<>();

        while (! stack.isEmpty()) {
            list.add(stack.pop());
        }

        StringBuilder sb = new StringBuilder();

        for (int m = list.size() - 1; m >= 0; m --) {
            sb.append(list.get(m));
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        String str = "2[ab3[cd]e]";
        System.out.println(decodeString(str));
    }
}

