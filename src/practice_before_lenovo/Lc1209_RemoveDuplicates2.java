package practice_before_lenovo;

class Lc1209_RemoveDuplicates2 {
    /**
     * @param s: a string need to be processed
     * @param k: the number of adjacent char
     * @return a string that after removal process
     */
    public static String removeDuplicates(String s, int k) {
        if (s.length() < k) return s;

        // use an array to to keep how many continuous char there is till this index
        int[] count = new int[s.length()];
        // use a StringBuilder to easily add and delete
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            sb.append(ch);
            // the last index of current string (builder)
            int last = sb.length() - 1;
            // if not the beginning and the same with previous one, add one more
            // else, set this as 1
            count[last] = 1 + (last > 0 && sb.charAt(last) == sb.charAt(last - 1) ? count[last - 1] : 0);
            if (count[last] >= k) {
                sb.delete(last - k + 1, sb.length());
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "deeedbbcccbdaa";
        System.out.println(removeDuplicates(s, 3));
    }
}