package DiammGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class DataLayer extends JPanel {
    public int originX = 3;
    public int originY = 3;

    public float lineWidth = 1f;
    public Color lineColor = new Color(200, 10, 80);

    public double pointSize = 15;
    public float edgeWidth = 1f;
    public Color edgeColor = new Color(255, 0, 0);
    public Color fillColor = new Color(0, 0, 125);

    private double[][] data;
    private PlotPanel parentPlot;

    public DataLayer(double[][] input) {
        this(input, null);
    }

    public DataLayer(double[][] input, PlotPanel plot) {
        this.data = input;
        this.originX = plot.getOriginX();
        this.originY = plot.getOriginY();
        parentPlot = plot;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        removeAll();

        g2d.translate(originX, originY);

        double[] point;

        for (double[] datum : data) {
            // Zeiche Punkte
            point = new double[]{datum[0], invertYcoordinate(datum[1])};
            drawPoint(point, g2d);
        }

    }

    private void drawPoint(double[] datum, Graphics2D g2d) {

        Ellipse2D.Double p = new Ellipse2D.Double(datum[0] - pointSize / 2, datum[1] - pointSize / 2, pointSize, pointSize);
//          Ellipse2D.Double p = new Ellipse2D.Double(datum[0] , datum[1] , pointSize, pointSize);

        g2d.setColor(fillColor);
        g2d.fill(p);

        g2d.setColor(edgeColor);
        g2d.setStroke(new BasicStroke(edgeWidth));
        g2d.draw(p);


    }

    private double invertYcoordinate(double input) {

        double heiht = 480;

        return heiht - input;

    }

    public void resetSettings() {
        if (parentPlot != null) {
            originX = parentPlot.getOriginX();
            originY = parentPlot.getOriginY();
        } else {
            originX = 3;
            originY = 3;
        }

        lineWidth = 1f;
        lineColor = new Color(200, 10, 80);

        pointSize = 15;
        edgeWidth = 1f;
        edgeColor = new Color(255, 0, 0);
        fillColor = new Color(0, 0, 125);
    }

}
