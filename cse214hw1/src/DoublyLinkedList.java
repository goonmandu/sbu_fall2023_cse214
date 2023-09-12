public class DoublyLinkedList<E> implements ListAbstractType<E> {
    class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node() {
            data = null;
            next = null;
            prev = null;
        }

        public Node(E e) {
            data = e;
            next = null;
            prev = null;
        }

        public Node(Node<E> prev, E e, Node<E> next) {
            data = e;
            this.prev = prev;
            this.next = next;
        }

        public E getValue() {
            return this.data;
        }

        public Node<E> getNext() {
            return this.next;
        }

        public Node<E> getPrev() {
            return this.prev;
        }

        public void setValue(E e) {
            this.data = e;
        }

        public void setNext(Node<E> newNext) {
            this.next = newNext;
        }

        public void setPrev(Node<E> newPrev) {
            this.prev = newPrev;
        }
    }
    class Iterator<E> implements TwoWayListIterator<E> {
        private Node<E> prev;
        private Node<E> next;
        private int index;

        public Iterator() {
            prev = null;
            next = null;
            index = 0;
        }

        public boolean hasPrevious() {
            return this.prev != null;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public E previous() {
            E ret = this.prev.getValue();
            this.next = this.prev;
            this.prev = this.prev.getPrev();
            this.index--;
            return ret;
        }

        public E next() {
            E ret = this.next.getValue();
            this.prev = this.next;
            this.next = this.next.getNext();
            this.index++;
            return ret;
        }

        public void add(E e) {
            Node<E> origPrev = this.prev;
            Node<E> toInsert = new Node<>(origPrev, e, this.next);
            origPrev.setNext(toInsert);
            this.prev = toInsert;
            this.index++;
        }

        public void set(E e) {
            // ?
        }

        public void remove() {
            // ?
        }

        public int getIndex() {
            return index;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private Iterator<E> it;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        it = new Iterator<>();
    }

    public E get(int idx) {
        if (idx < it.getIndex()) {
            while (it.getIndex() != idx) {
                it.previous();
            }
            return it.previous();
        } else {
            while (it.getIndex() != idx) {
                it.next();
            }
            return it.next();
        }
    }

    public E set(int idx, E e) {
        E ret;
        if (idx < it.getIndex()) {
            while (it.getIndex() != idx) {
                it.previous();
            }
            ret = it.previous();
            it.set(e);
        } else {
            while (it.getIndex() != idx) {
                it.next();
            }
            ret = it.next();
            it.set(e);
        }
        return ret;
    }

    public void remove(int idx) {
        if (idx < it.getIndex()) {
            while (it.getIndex() != idx) {
                it.previous();
            }
        } else {
            while (it.getIndex() != idx) {
                it.next();
            }
        }
    }

    @Override
    public String toString() {
        Iterator<E> it = this.iterator();  // ?
        if (!it.hasNext())
            return "[]";
        StringBuilder builder = new StringBuilder("[");
        while (it.hasNext()) {
            E e = it.next();
            builder.append(e.toString());
            if (!it.hasNext())
                return builder.append("]").toString();
            builder.append(", ");
        }
        return null;
    }
}