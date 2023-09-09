public class Main {
    public static void main(String[] args) {
        String[] strs = {"0", "1", "2", "3", "4", "5", "6", "7"};
        LinkedList<String> strll = new LinkedList<>(strs);

        strll.remove(4);
        strll.add(4, "A");
        strll.add("Z");

        System.out.printf("%d\n", strll.indexOf("A"));
        printLinkedList(strll);
        printLinkedListWithIterator(strll);

        var subll = strll.subList(2, 5);
        subll.set(1, "R");
        printLinkedList(subll);
        printLinkedListWithIterator(subll);
        printLinkedList(strll);
        printLinkedListWithIterator(strll);
    }

    public static <T> void printLinkedList(LinkedList<T> list) {
        var tarr = list.toArray();
        for (var t : tarr) {
            System.out.printf("%s ", t);
        }
        System.out.println();
    }

    public static <T> void printLinkedListWithIterator(LinkedList<T> list) {
        LinkedListIterator<T> iter = new LinkedListIterator<>(list);
        while (iter.hasNext()) {
            System.out.printf("%s ", iter.next());
        }
        System.out.println();
    }
}
