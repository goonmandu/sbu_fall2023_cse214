public class LinkedList<E> {
    private Node<E> head;
    private int size;

    public LinkedList() {
        head = new Node<>(null, null);
        size = 0;
    }

    public LinkedList(E[] src) {
        head = new Node<>(null, null);
        for (E e : src) {
            this.add(e);
        }
        size = src.length;
    }

    public Node<E> getHead() {
        return head;
    }

    public boolean add(E e) {
        Node<E> dummy = this.head;
        while (dummy.getNext() != null) {
            dummy = dummy.getNext();
        }
        dummy.setNext(new Node<>(e, null));
        this.size++;
        return true;
    }

    public boolean add(int idx, E e) {
        Node<E> dummy = this.head;
        for (int i = 0; i < idx; ++i) {
            dummy = dummy.getNext();
        }
        Node<E> origNext = dummy.getNext();
        dummy.setNext(new Node<>(e, null));
        dummy.getNext().setNext(origNext);
        this.size++;
        return true;
    }

    public E remove(int idx) {
        Node<E> oneBefore = this.head;
        for (int i = 0; i < idx; i++) {
            oneBefore = oneBefore.getNext();
        }

        E ret = oneBefore.getNext().getValue();
        if (oneBefore.getNext().getNext() == null) {  // If element is last
            oneBefore.setNext(null);
        } else {                                      // Else
            oneBefore.setNext(oneBefore.getNext().getNext());
        }
        this.size--;
        return ret;
    }

    public boolean remove(E e) {
        // O(n^2)
        Node<E> oneBefore = this.head;
        while (oneBefore.getNext() != null) {
            oneBefore = oneBefore.getNext();
            if (oneBefore.getValue().equals(e)) {
                this.remove(this.indexOf(e));
                return true;
            }
        }
        this.size--;
        return true;
    }

    public int indexOf(E e) {
        Node<E> dummy = this.head;
        int idx = 0;
        while (dummy.getNext() != null) {
            dummy = dummy.getNext();
            if (dummy.getValue().equals(e)) {
                return idx;
            }
            ++idx;
        }
        return -1;
    }

    public int size() {
        return this.size;
    }

    public E get(int idx) {
        // O(n)
        Node<E> dummy = this.head;
        for (int i = 0; i < idx + 1; ++i) {
            dummy = dummy.getNext();
        }
        return dummy.getValue();
    }

    public LinkedList<E> subList(int fromIndex, int toIndex) {
        // O(n)
        LinkedList<E> ret = new LinkedList<>();
        Node<E> targetTemp = ret.head;
        LinkedListIterator<E> srcIter = new LinkedListIterator<>(this);
        for (int i = 0; i < fromIndex; ++i) {
            srcIter.next();
        }
        for (int i = fromIndex; i < toIndex; ++i) {
            targetTemp.setNext(srcIter.nextRawNode());
            targetTemp = targetTemp.getNext();
            ret.size++;
        }
        return ret;
    }

    public Object[] toArray() {
        // O(n^2)
        Object[] ret = new Object[this.size()];
        for (int i = 0; i < this.size(); ++i) {
            ret[i] = this.get(i);
        }
        return ret;
    }

    public void clear() {
        this.head.setNext(null);
    }

    public boolean isEmpty() {
        return this.head.getNext() == null;
    }

    public E set(int idx, E e) {
        E ret = this.remove(idx);
        this.add(idx, e);
        return ret;
    }
}
