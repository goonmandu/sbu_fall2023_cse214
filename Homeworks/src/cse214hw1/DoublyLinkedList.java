package cse214hw1;

public class DoublyLinkedList<E> implements ListAbstractType<E> {
    static class Node<E> {
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
    static class Iterator<E> implements TwoWayListIterator<E> {
        private Node<E> prev;
        private Node<E> next;
        private int index;
        private int lastOp;  // +1 next, -1 prev, 0 invalid

        public Iterator(Node<E> prev, Node<E> next) {
            this.prev = prev;
            this.next = next;
            index = 0;
            lastOp = 0;
        }

        public boolean hasPrevious() {
            return this.prev != null;
        }

        public boolean hasNext() {
            return this.next != null && (this.next.data != null && this.next.next != null);
        }

        public E previous() {
            E ret = this.prev.getValue();
            this.next = this.prev;
            this.prev = this.prev.getPrev();
            this.index--;
            this.lastOp = -1;
            return ret;
        }

        public E next() {
            E ret = this.next.getValue();
            this.prev = this.next;
            this.next = this.next.getNext();
            this.index++;
            this.lastOp = +1;
            return ret;
        }

        public void add(E e) {
            this.lastOp = 0;
            this.index++;
            Node<E> origPrev = this.prev;
            if (origPrev == null) {
                this.prev = new Node<>(null, e, null);
                return;
            }
            Node<E> toInsert = new Node<>(origPrev, e, this.next);
            origPrev.setNext(toInsert);
            this.prev = toInsert;
        }

        public void set(E e) {
            if (this.lastOp == +1) {
                this.prev.setValue(e);
            } else if (this.lastOp == -1) {
                this.next.setValue(e);
            } else {
                throw new IllegalStateException("Last method called by iterator must be either next or previous!");
            }
        }

        public void remove() {
            // ?
            this.lastOp = 0;
        }

        public int getIndex() {
            return index;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private Iterator<E> it;

    public DoublyLinkedList() {
        head = new Node<>();
        tail = new Node<>();
        it = new Iterator<>(null, tail);
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

    public void add(int idx, E e) {
        if (idx < it.getIndex()) {
            while (it.getIndex() != idx) {
                it.previous();
            }
            it.set(e);
        } else {
            while (it.getIndex() != idx) {
                it.next();
            }
            it.set(e);
        }
    }

    public boolean add(E e) {
        while (it.hasNext()) {
            it.next();
        }
        it.add(e);
        return true;
    }

    public boolean remove(E e) {
        return true;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return this.head.next == null;
    }

    public boolean contains(E e) {
        return false;
    }

    public DoublyLinkedList.Iterator<E> iterator() {
        return this.it;
    }

    @Override
    public String toString() {
        var it = this.iterator();
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
