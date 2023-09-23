package Rec3;

public class MyStack<E> {
    static class Node<E> {
        private Node<E> prev;
        private E data;
        private Node<E> next;


        public Node(E e) {
            prev = null;
            data = e;
            next = null;
        }

        public E getData() {
            return data;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setData(E e) {
            this.data = e;
        }

        public void setPrev(Node<E> p) {
            this.prev = p;
        }

        public void setNext(Node<E> n) {
            this.next = n;
        }
    }
    private Node<E> data;

    public MyStack() {
        data = new Node<>(null);
    }

    public E pop() {
        if (this.data.getData() == null) {
            throw new IllegalStateException("Stack is empty!");
        }
        var ret = data.getData();
        data = data.getPrev();
        data.setNext(null);
        return ret;
    }

    public boolean isEmpty() {
        return this.data.getData() == null;
    }

    public void push(E e) {
        if (false) {  // Stack size is dynamic, therefore push cannot fail
            throw new IllegalStateException("Stack is full!");
        }
        data.setNext(new Node<>(e));
        data.getNext().setPrev(data);
        data = data.getNext();
    }

    public static <E> int sizeOfStack(MyStack<E> s) {
        MyStack<E> aux = new MyStack<>();
        int ret = 0;
        while (!s.isEmpty()) {
            aux.push(s.pop());
            ret++;
        }
        while (!aux.isEmpty()) {
            s.push(aux.pop());
        }
        return ret;
    }

    public static <E> boolean search(MyStack<E> s, E e) {
        MyStack<E> aux = new MyStack<>();
        boolean found = false;
        while (!s.isEmpty()) {
            var popped = s.pop();
            if (popped.equals(e)) {
                found = true;
            }
            aux.push(popped);
        }
        while (!aux.isEmpty()) {
            s.push(aux.pop());
        }
        return found;
    }

    public static <E> void printMyStack(MyStack<E> s) {
        MyStack<E> aux = new MyStack<>();
        while (!s.isEmpty()) {
            var popped = s.pop();
            System.out.printf("%s ", popped);
            aux.push(popped);
        }
        System.out.println();
        while (!aux.isEmpty()) {
            s.push(aux.pop());
        }
    }

    public static void main(String[] args) {
        MyStack<String> strstack = new MyStack<>();
        System.out.printf("Is Stack right after instantiation empty: %s\n", strstack.isEmpty());
        for (int i = 0; i < 16; ++i) {
            strstack.push(Integer.toString(i));
        }
        System.out.printf("Is Stack after pushing stuff in it empty: %s\n", strstack.isEmpty());
        System.out.print("Original Stack: "); printMyStack(strstack);
        strstack.pop();
        System.out.print("Size after pop: "); System.out.println(sizeOfStack(strstack));
        strstack.push("69");
        System.out.print("Size after pushing \"69\": "); System.out.println(sizeOfStack(strstack));
        System.out.print("Does Stack contain \"69\": "); System.out.println(search(strstack, "69"));
        System.out.print("Stack after push: "); printMyStack(strstack);
    }
}
