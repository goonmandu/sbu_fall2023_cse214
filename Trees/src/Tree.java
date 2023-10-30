public class Tree<E> {
    private static class Node<E> {
        E val;
        Node<E> leftChild;
        Node<E> rightSibling;

        private E getValue() {
            return this.val;
        }
    }

}
