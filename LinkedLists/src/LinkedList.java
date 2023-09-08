public class LinkedList<E> {
    private Node<E> head;

    public LinkedList() {
        head = new Node<>(null, null);
    }

    public LinkedList(E[] src) {
        head = new Node<>(null, null);
        for (E e : src) {
            this.append(e);
        }
    }

    public void append(E e) {
        Node<E> dummy = this.head;
        while (dummy.getNext() != null) {
            dummy = dummy.getNext();
        }
        dummy.setNext(new Node<>(e, null));
    }

    public void insert(E e, int idx) {
        Node<E> dummy = this.head;
        for (int i = 0; i < idx; ++i) {
            dummy = dummy.getNext();
        }
        Node<E> origNext = dummy.getNext();
        dummy.setNext(new Node<>(e, null));
        dummy.getNext().setNext(origNext);
    }

    public void remove(int idx) {
        Node<E> oneBefore = this.head;
        for (int i = 0; i < idx; i++) {
            oneBefore = oneBefore.getNext();
        }

        if (oneBefore.getNext().getNext() == null) {  // If element is last
            oneBefore.setNext(null);
        } else {                                      // Else
            oneBefore.setNext(oneBefore.getNext().getNext());
        }
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

    public int length() {
        // O(n)
        int ret = 0;
        Node<E> dummy = this.head;
        while (dummy.getNext() != null) {
            ++ret;
            dummy = dummy.getNext();
        }
        return ret;
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
        // O(k*n)
        LinkedList<E> ret = new LinkedList<>();
        for (int i = fromIndex; i < toIndex; ++i) {
            ret.append(this.get(i));
        }
        return ret;
    }

    public Object[] toArray() {
        // O(n^2)
        Object[] ret = new Object[this.length()];
        for (int i = 0; i < this.length(); ++i) {
            ret[i] = this.get(i);
        }
        return ret;
    }
}
