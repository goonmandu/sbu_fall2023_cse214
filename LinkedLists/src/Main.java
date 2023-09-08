public class Main {
    public static void main(String[] args) {
        String[] strs = {"0", "1", "2", "3", "4", "5", "6", "7"};
        LinkedList<String> strll = new LinkedList<>(strs);

        strll.remove(4);
        strll.insert("A", 4);
        strll.append("Z");

        System.out.printf("%d\n", strll.indexOf("A"));

        var strarr = strll.toArray();
        for (var e : strarr) {
            System.out.printf("%s ", e);
        }
        System.out.println();
    }
}
