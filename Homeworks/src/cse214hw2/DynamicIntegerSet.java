package cse214hw2;

public class DynamicIntegerSet implements DynamicSet {
    public static class Node implements PrintableNode {
        Integer data;
        Node left, right;
        Node(int x) { this(x, null, null); }
        Node(int x, Node left, Node right) {
            this.data = x;
            this.left = left;
            this.right = right;
        }
        @Override
        public String getValueAsString() { return data.toString(); }
        @Override
        public PrintableNode getLeft() { return left; }
        @Override
        public PrintableNode getRight() { return right; }
    }

    private Node root;

    // this method must be there exactly in this form
    public Node root() { return this.root; }

    public int size() {
        return size(root);
    }

    private int size(Node n) {
        if (n == null) {
            return 0;
        } else {
            return (size(n.left) + 1 + size(n.right));
        }
    }

    private boolean contains(Node node, Integer x) {
        if (node == null) {
            return false;
        }

        if (x.equals(node.data)) {
            return true;
        } else if (x < node.data) {
            return contains(node.left, x);
        } else {
            return contains(node.right, x);
        }
    }

    public boolean contains(Integer x) {
        return contains(root, x);
    }

    public boolean add(Integer x) {
        if (contains(x)) {
            return false;
        } else {
            root = add(root, x);
            return true;
        }
    }

    private Node add(Node node, Integer x) {
        if (node == null) {
            return new Node(x);
        }

        if (x < node.data) {
            node.left = add(node.left, x);
        } else {
            node.right = add(node.right, x);
        }

        return node;
    }

    public boolean remove(Integer x) {
        if (!contains(x)) {
            return false;
        } else {
            root = remove(root, x);
            return true;
        }
    }

    private Node remove(Node node, Integer x) {
        if (node == null) {
            return null;
        }

        if (x.equals(node.data)) {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.data = findMin(node.right);
            node.right = remove(node.right, node.data);
        } else if (x < node.data) {
            node.left = remove(node.left, x);
        } else {
            node.right = remove(node.right, x);
        }

        return node;
    }

    private Integer findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }
}
