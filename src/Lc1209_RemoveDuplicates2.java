class Lc1209_RemoveDuplicates2 {
    public String removeDuplicates(String s, int k) {
        if (s.length() < k) return s;

        int[] count = new int[s.length()];
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            sb.append(ch);
            int last = sb.length() - 1;
            count[last] = 1 + (last > 0 && sb.charAt(last) == sb.charAt(last - 1) ? count[last - 1] : 0);
            if (count[last] >= k) {
                sb.delete(last - k + 1, sb.length());
            }
        }

        return sb.toString();
    }
}