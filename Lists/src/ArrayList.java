// Example self-implementation of ArrayList

import java.util.List;

public class ArrayList /* implements List */ {
    private String[] data;
    int appendidx = 0;

    public ArrayList(int len) {
        data = new String[len];
    }

    public boolean contains(Object o) throws IllegalArgumentException {
        if (!(o instanceof String)) {
            throw new IllegalArgumentException(String.format("Expected String, received %s", o.getClass().getName()));
        }
        if (this.isEmpty()) {
            return false;
        }

        String s = (String) o;
        for (var str : this.data) {
            if (str.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public String get(int idx) {
        return this.data[idx];
    }

    public boolean add(String s) {
        /**
         * Assumes object does not track append position

        int idx = 0;
        while (this.data[idx] != null) {
            ++idx;
        }
        this.data[idx] = s;
        return true;

         */
        if (this.appendidx >= this.data.length) {
            String[] sneaky;
            if (this.data.length == 0) {
                sneaky = new String[1];
            } else {
                sneaky = new String[this.data.length * 2];
            }
            System.arraycopy(this.data, 0, sneaky, 0, this.data.length);
            this.data = sneaky;
        }
        this.data[this.appendidx++] = s;
        return true;
    }

    public int size() {
        return this.appendidx;
    }

    public boolean isEmpty() {
        boolean everythingNull = true;
        for (var str : this.data) {
            if (str != null) {
                everythingNull = false;
                break;
            }
        }
        return this.size() == 0 || everythingNull;
    }

    public static void main(String[] args) {
        ArrayList strarrl = new ArrayList(0);
        strarrl.add("Hi");
        strarrl.add("Java is a dogshit language");
        strarrl.add("Nevermind");
        for (int i = 0; i < 3; ++i) {
            System.out.printf("%s\n", strarrl.get(i));
        }
    }
}
