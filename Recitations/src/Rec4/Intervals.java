package Rec4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Intervals {

    public static List<Interval> mergeIntervals(List<Interval> intervals) {
        // Sort intervals based on their starting values
        Collections.sort(intervals, Comparator.comparingInt(i -> i.start));

        Stack<Interval> stack = new Stack<>();
        stack.push(intervals.get(0));

        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            Interval top = stack.peek();

            if (top.end < current.start) {
                stack.push(current);
            } else if (top.end < current.end) {
                top.end = current.end;
                stack.pop();
                stack.push(top);
            }
        }

        return new ArrayList<>(stack);
    }

    public static void printIntervals(List<Interval> intervals) {
        for (Interval interval : intervals) {
            System.out.println("{" + interval.start + ", " + interval.end + "}");
        }
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 5));
        intervals.add(new Interval(2, 3));
        intervals.add(new Interval(4, 6));
        intervals.add(new Interval(7, 8));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(12, 15));

        List<Interval> mergedIntervals = mergeIntervals(intervals);
        printIntervals(mergedIntervals);
    }
}
