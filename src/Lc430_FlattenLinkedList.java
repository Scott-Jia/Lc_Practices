
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};

class Lc430_FlattenLinkedList {
    /**
     for every current node:
     1. curr --> curr.child --> child's tail --> curr.next;
     2. curr = curr.next;
     */
    public Node flatten(Node head) {
        Node curr = head;
        while (curr != null) {
            // if there is a child for this current node
            if (curr.child != null) {
                // find the tail for this 'child group'
                // in order to connect to the curr.next at the end of this 'child group'
                Node tail = findTail(curr.child);
                // if there's next node, point it's prev to tail
                if (curr.next != null) {
                    curr.next.prev = tail;
                }

                // tail --> curr.next;
                tail.next = curr.next;
                // curr --> curr.child
                curr.next = curr.child;
                curr.child.prev = curr;
                // set curr's child param to null
                curr.child = null;
            }
            curr = curr.next;
        }

        return head;
    }

    // go next till the end
    // return the last node as the tail of this 'group'
    public Node findTail(Node child) {
        while (child.next != null) {
            child = child.next;
        }
        return child;
    }
}
