import java.util.ArrayList;
import java.util.List;

public class Scratch {
    public static void main(String[] args) {
        // Create an ArrayList
        List<String> originalList = new ArrayList<>();
        originalList.add("Apple");
        originalList.add("Banana");
        originalList.add("Cherry");
        originalList.add("Date");

        // Obtain a sublist
        List<String> sublist = originalList.subList(1, 3);

        // Modify the sublist
        sublist.set(0, "Grape");
        sublist.add("Fig");

        // Print the original list to see the changes
        System.out.println("Original List: " + originalList);
    }
}
