package GraphicsFG;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Punkt {

    private double inkrement = 20;
    private int radius = 10;
    private double angle;
    private double[] position = new double[2];
    private double[] direction= new double[2];;
    Ellipse2D.Double ellipse;

    Random rand ;

    public Punkt(){
       this(0,0);
    }

    public Punkt(double x, double y){
        position[0]=x;
        position[1]=y;



        rand=new Random();
        ellipse = new Ellipse2D.Double(position[0],position[1], radius,radius);

        // Zufällige startrichtung
        direction[0]=rand.nextInt(-1,2);
        direction[1]=rand.nextInt(-1,2);

        //zufällige bewegungsinkrement
        inkrement = rand.nextDouble(20,50);
    }

    public double[] move(){

        //Ändere die Richtung
        if(rand.nextBoolean()){

            double[] newDirection = new double[2];

            // Ja richtungsänderung
            angle = rand.nextDouble(-30,30)*( Math.PI / 180); //winkel in Radian
            //angle = -30 *( Math.PI / 180); //winkel in Radian


            System.out.println("Winkel:" + angle);

//            newDirection[0] = inkrement * Math.cos(angle) * direction[0] - inkrement * Math.sin(angle) * direction[1];
//            newDirection[1] = inkrement * Math.sin(angle) * direction[0] + inkrement * Math.cos(angle) * direction[1];
            newDirection[0] = Math.cos(angle) * direction[0] - Math.sin(angle) * direction[1];
            newDirection[1] = Math.sin(angle) * direction[0] + Math.cos(angle) * direction[1];

            direction[0] = newDirection[0];
            direction[1] = newDirection[1];

            System.out.printf("Direktion x=%3.1f  y=%3.1f",direction[0],direction[1]);
        } // Nein, die Direktion bleibt, wie sie war



        //Berechne die Neue Position;
        position[0] += direction[0]*inkrement;
        position[1] += direction[1]*inkrement;

        // Gebe dem Graphic Element die Neue Position
        ellipse.x=position[0];
        ellipse.y=position[1];

        return position;
    }



    @Override
    public String toString() {
        return String.format("[%4.1f | %4.1f]",position[0],position[1]);
    }

    public void drawPunkt(Graphics2D g2d) {

        g2d.setColor(new Color(100,149,237));
        g2d.fill(ellipse);

    }
}
