import java.util.*;

class Lc797_AllPaths {

    private List<List<Integer>> results;
    private int[][] graph;
    private int target;

    /**
     * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1,
     * find all possible paths from node 0 to node n - 1 and return them in any order
     * @param graph: a 2d array
     * @return a list of paths
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        this.target = graph.length - 1;
        this.results = new ArrayList<List<Integer>>();
        // adopt the LinkedList for fast access to the tail element.
        LinkedList<Integer> path = new LinkedList<Integer>();
        path.addLast(0);
        // kick of the backtracking, starting from the source (node 0)
        this.backTracking(0, path);

        return results;
    }

    // Path: a list with currNode as the last element
    public void backTracking(int currNode, LinkedList<Integer> path) {

        if (currNode == this.target) {
            // Note: one should make a deep copy (new another path) of the path, Otherwise:
            //  when you perform path.removeLast, the path stored in the result will also change
            //  because it's a shallow copy without new, so just store the reference
            this.results.add(new ArrayList<Integer>(path));
            return;
        }

        // explore the neighbor nodes one after another.
        for (int nextNode : this.graph[currNode]) {
            // mark the choice, before backtracking.
            path.addLast(nextNode);
            backTracking(nextNode, path);
            // remove the previous choice, to try the next choice
            path.removeLast();
        }
    }
}





