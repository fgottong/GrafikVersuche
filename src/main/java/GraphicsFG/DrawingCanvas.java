package GraphicsFG;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
public class DrawingCanvas extends JComponent{
    private int width;
    private int height;
    public DrawingCanvas(int w,int h){
        width=w;
        height=h;
    }

    protected void paintComponent(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double ellipse;
        int radius = 15;

//        ellipse = new Ellipse2D.Double(100,100,radius,radius);
//
//        g2d.setColor(new Color(100,149,237));
//
//        g2d.fill(ellipse);
        Punkt p = new Punkt(300,300);
        p.drawPunkt(g2d);

        for(int i = 0;i<10;i++){
            p.move();
            System.out.println(p);
            p.drawPunkt(g2d);
        }
    }

    private void irgendwas(){

    }
}
