/**
 STEP1: traverse the tree by DFS, generate a list of triplet (3 dimention values)
 STEP2: sort the list (compare first -> second -> third)
 STEP3: extract the value from sorted list, and group them by collumn index
 */

import java.util.*;

class Lc987_TreeVerticalTraversal2 {

    List<Triplet<Integer, Integer, Integer>> nodeList = new ArrayList<>();

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // implement the function
        List<List<Integer>> results = new ArrayList<>();

        if (root == null) return results;

        // step 1). DFS traversal to fill out the node list
        DFS(root, 0, 0);

        // step 2). sort the list by <column, row, value>
        Collections.sort(this.nodeList, new Comparator<Triplet<Integer, Integer, Integer>>() {
            @Override
            public int compare(Triplet<Integer, Integer, Integer> t1,
                               Triplet<Integer, Integer, Integer> t2) {
                if (t1.first.equals(t2.first)) {
                    if (t1.second.equals(t2.second)) {
                        return t1.third - t2.third;
                    } else {
                        return t1.second - t2.second;
                    }
                } else {
                    return t1.first - t2.first;
                }
            }
        });

        // step 3). extract the values, grouped by the column index.
        List<Integer> curr_collum = new ArrayList<>();
        Integer collum_idx = nodeList.get(0).first;

        for (Triplet<Integer, Integer, Integer> tri : nodeList) {
            Integer collum = tri.first, value = tri.third;
            if (collum.equals(collum_idx)) {
                curr_collum.add(value);
            } else {
                results.add(curr_collum);
                curr_collum = new ArrayList<>();
                collum_idx = collum;
                curr_collum.add(value);
            }
        }

        results.add(curr_collum);  // add the last group (column) of value to results

        return results;
    }

    // @todo: create a DFS function
    private void DFS(TreeNode node, Integer row, Integer column) {
        if (node == null) return;

        nodeList.add(new Triplet(column, row, node.val));

        this.DFS(node.left, row + 1, column -1);
        this.DFS(node.right, row + 1, column + 1);
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){}
    TreeNode(int val) {this.val = val;}
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// @todo: implement a triplet class
class Triplet<F, S, T> {
    public final F first;
    public final S second;
    public final T third;

    public Triplet(F first, S second, T third) {
        this.first = first; // column
        this.second = second;  // row
        this.third = third;  // value
    }
}
