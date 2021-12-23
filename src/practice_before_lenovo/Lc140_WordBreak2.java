package practice_before_lenovo;

import java.util.*;

class Lc140_WordBreak2 {
    protected Set<String> wordSet;
    protected HashMap<String, List<List<String>>> memo;

    public List<String> wordBreak(String s, List<String> wordDict) {
        wordSet = new HashSet<>();
        for (String word : wordDict) {
            wordSet.add(word);
        }
        memo = new HashMap<String, List<List<String>>>();

        // perform DP to fill out the memo map, to get memo.get(s) later
        _wordBreak_topdown(s);

        // chain up words together
        List<String> ret = new ArrayList<String>();
        for (List<String> words : memo.get(s)) {
            // if thread safety is needed, then StringBuffer is better than StringBuilder
            StringBuffer sentence = new StringBuffer();
            for (String word : words) {
                sentence.insert(0, word);
                sentence.insert(0, " ");
            }
            ret.add(sentence.toString().strip());
        }

        return ret;
    }

    protected List<List<String>> _wordBreak_topdown(String s) {
        if (s.equals("")) {
            // return a list of List<String> : a list of possible solutions
            List<List<String>> solutions = new ArrayList<List<String>>();
            solutions.add(new ArrayList<String>());
            return solutions;
        }

        if (memo.containsKey(s)) {
            return memo.get(s);
        } else {
            List<List<String>> solutions = new ArrayList<List<String>>();
            memo.put(s, solutions);
        }

        for (int endIndex = 1; endIndex <= s.length(); ++endIndex) {
            String word = s.substring(0, endIndex);
            // if it's a valid word (prefix)
            if (wordSet.contains(word)) {
                // store the solutions (a list of List<String>) of the rest part
                List<List<String>> subsentences = _wordBreak_topdown(s.substring(endIndex));
                // for each possible solution
                for (List<String> subsentence : subsentences) {
                    // deep copy of this solution (List<String>)
                    List<String> newSentence = new ArrayList<String>(subsentence);
                    // add the word (the prefix at the beginning) to this solution
                    // to make up the solution for the whole string
                    newSentence.add(word);
                    // then add this solution to memo.get(s) list
                    memo.get(s).add(newSentence);
                }
            }
        }
        return memo.get(s);
    }
}
