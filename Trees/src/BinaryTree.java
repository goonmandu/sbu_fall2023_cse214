import java.util.LinkedList;
import java.util.List;

public class BinaryTree<E> {
    public E e;
    public BinaryTree<E> left;
    public BinaryTree<E> right;

    private void preorderInternal(List<E> in) {
        in.add(e);
        if (this.left != null) this.left.preorderInternal(in);
        if (this.right != null) this.right.preorderInternal(in);
    }

    public List<E> preorder() {
        List<E> ret = new LinkedList<>();
        preorderInternal(ret);
        return ret;
    }

    public static void main(String[] args) {
        BinaryTree<Integer> inttree = new BinaryTree<>();
        inttree.e = 1;
        inttree.left = new BinaryTree<>();
        inttree.right = new BinaryTree<>();
        inttree.left.e = 2;
        inttree.left.left = new BinaryTree<>();
        inttree.left.right = new BinaryTree<>();
        inttree.left.left.e = 4;
        inttree.left.right.e = 5;
        inttree.right.e = 3;
        inttree.right.left = new BinaryTree<>();
        inttree.right.right = new BinaryTree<>();
        inttree.right.left.e = 6;
        inttree.right.right.e = 7;
        /*
                    1
                  /   \
                2       5
               / \     / \
              3   4   6   7
         */

        for (var e : inttree.preorder()) {
            System.out.printf("%d, ", e);
        }
    }


}
