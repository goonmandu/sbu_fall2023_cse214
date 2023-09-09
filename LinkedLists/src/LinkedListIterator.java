import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<E> implements Iterator<E> {
    private Node<E> current;
    private int currIndex;
    private final int MAXINDEX;

    public LinkedListIterator(LinkedList<E> ll) {
        this.current = ll.getHead();
        this.currIndex = 0;
        this.MAXINDEX = ll.size();
    }

    public boolean hasNext() {
        return (current.getNext() != null && currIndex != MAXINDEX);
    }

    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        var ret = current.getNext().getValue();
        current = current.getNext();
        currIndex++;
        return ret;
    }

    public Node<E> nextRawNode() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        var ret = current.getNext();
        current = current.getNext();
        currIndex++;
        return ret;
    }
}
