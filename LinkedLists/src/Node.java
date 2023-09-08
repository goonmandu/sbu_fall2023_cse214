public class Node<E> {
    private E data;
    private Node<E> next;

    public Node(E e, Node<E> next) {
        this.data = e;
        this.next = next;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public E getValue() {
        return data;
    }
}
