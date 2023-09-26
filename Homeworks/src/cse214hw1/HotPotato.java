package cse214hw1;

import java.util.LinkedList;

public class HotPotato {
    public static void main(String[] args) {
        System.out.println(playWithDoublyLinkedList(5, 0));
        System.out.println(playWithDoublyLinkedList(5, 1));
        System.out.println(playWithLinkedList(5, 0));
        System.out.println(playWithLinkedList(5, 1));
    }

    public static DoublyLinkedList<Integer> playWithDoublyLinkedList(int numOfPlayers, int lengthOfPass) {
        DoublyLinkedList<Integer> hotPotato = new DoublyLinkedList<>();
        DoublyLinkedList<Integer> ret = new DoublyLinkedList<>();
        for (int i = 1; i < numOfPlayers + 1; ++i) {
            hotPotato.add(i);
        }
        var it = hotPotato.iterator();
        while (hotPotato.size() > 0) {
            int skips = lengthOfPass;
            Integer poppedValue;
            do {
                if (!it.hasNext()) {
                    it = hotPotato.iterator();
                }
                poppedValue = it.next();
                skips--;
            }  while (skips >= 0);
            ret.add(poppedValue);
            it.remove();
        }
        return ret;
    }

    public static LinkedList<Integer> playWithLinkedList(int numOfPlayers, int lengthOfPass) {
        LinkedList<Integer> hotPotato = new LinkedList<>();
        LinkedList<Integer> ret = new LinkedList<>();
        for (int i = 1; i < numOfPlayers + 1; ++i) {
            hotPotato.add(i);
        }
        var it = hotPotato.iterator();
        while (hotPotato.size() > 0) {
            int skips = lengthOfPass;
            Integer poppedValue;
            do {
                if (!it.hasNext()) {
                    it = hotPotato.iterator();
                }
                poppedValue = it.next();
                skips--;
            }  while (skips >= 0);
            ret.add(poppedValue);
            it.remove();
        }
        return ret;
    }
}
