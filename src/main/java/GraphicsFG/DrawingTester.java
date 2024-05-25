package GraphicsFG;

import javax.swing.*;

public class DrawingTester {

    public static void main(String[] args) {
        int w = 600;
        int h = 600;
        JFrame f = new JFrame();
        f.setSize(w,h);
        f.setTitle("Mein erster Punkt");
        DrawingCanvas dc = new DrawingCanvas(w,h);
        f.add(dc);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

}
