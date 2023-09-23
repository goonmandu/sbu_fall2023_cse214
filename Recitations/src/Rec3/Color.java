package Rec3;

import java.util.LinkedList;
public class Color {
    private final int r, g, b;

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public String toString() {
        return String.format("%3dR, %3dG, %3dB", this.r, this.g, this.b);
    }

    public static void main(String[] args) {
        LinkedList<Color> colors = new LinkedList<>();
        for (int i = 0; i < 8; ++i) {
            int r = (int) (Math.random() * 255);
            int g = (int) (Math.random() * 255);
            int b = (int) (Math.random() * 255);
            colors.push(new Color(r, g, b));
        }
        colors.push(new Color(91, 206, 250));
        colors.push(new Color(245, 169, 184));

        colors.forEach(System.out::println);
    }
}
