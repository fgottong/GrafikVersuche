package DiammGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyBoundsListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class GridPanel extends JPanel {


    public int originX = 30 ;
    public int originY = 30 ;

    public int ylabelwidth = 100 ;


    public double outerWidth = 600;
    public double outerHeight = 480;
    public boolean printFrame = true;
    public boolean printArrowAxis = false;
    public float outerFrameLineWidth = 2f;
    public Color frameColor = new Color(0, 0, 0);

    public boolean grid = true;
    public boolean drawMajorGrids = true;
    public boolean drawMinorGrids = true;
    public boolean xGrid = true;
    public boolean yGrid = true;

    public float xGridLineWidth = 0.5f;
    public float yGridLineWidth = 0.5f;
    public Color gridColor = new Color(120, 120, 120);
    public double xTicksSep = 10;
    public double xTicksStart = 10;
    public double yTicksSep = 10;
    public double yTicksStart = 10;
    public float majorGridLineWidth = 1.5f;
    public double xMajorTicksSep = 100;
    public double yMajorTicksSep = 100;
    public double xMajorTicksStart = 100;
    public double yMajorTicksStart = 100;

    private PlotPanel parentPlot = null;

    public GridPanel() {
        this(null);
    }

    public GridPanel(PlotPanel plot) {
        if (plot != null) {
            this.originX = plot.getOriginX();
            this.originY = plot.getOriginY();
            parentPlot = plot;
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        removeAll();

        g2d.translate(originX, originY);

        if (grid) {
            if (drawMinorGrids) {
                drawMinorGrids(g2d);
            }
            if (drawMajorGrids) {
                drawMajorGrids(g2d);
            }

        }

        // Outerframe Zeichen
        if (printFrame) {
            drawFrame(g2d);
        }

        // Pfeil-Achsen Zeichnen
        if(printArrowAxis){
            _drawArrowAxis(g2d);
        }

        printTicks(g2d);

    }

    private void printTicks(Graphics2D g2d) {
        JLabel currentLabel ;
        Font labelFont;
        FontMetrics fontMetrics;
        int width=10,height;
        if (xGrid) {

            for (double w = xMajorTicksStart; w < outerWidth; w += xMajorTicksSep) {
                currentLabel = new JLabel( String.format("%#1.0f", w));
                labelFont   = currentLabel.getFont();
                fontMetrics = g2d.getFontMetrics(labelFont);
                width       = fontMetrics.stringWidth(currentLabel.getText());
                height      = fontMetrics.getHeight();

                currentLabel.setBounds((int)w-width/2,
                        (int)outerHeight+5,
                        width,
                        height);

                this.add(currentLabel);
            }

        }

        // Raster für die y-ticks
        if (yGrid) {

            for (double h = yMajorTicksStart; h < outerHeight; h += yMajorTicksSep) {
                currentLabel = new JLabel( String.format("%#1.0f", h));
                labelFont   = currentLabel.getFont();
                fontMetrics = g2d.getFontMetrics(labelFont);
                width       = fontMetrics.stringWidth(currentLabel.getText());
                height      = fontMetrics.getHeight();

                currentLabel.setBounds(-width-10 ,
                        (int)h-height/2,
                        width,
                        height);

                this.add(currentLabel);
            }
        }

        ylabelwidth = 200;

        //parentPlot.adjustBounds(200);

    }

    private void _drawArrowAxis(Graphics2D g2d) {

        double extension = 0.05;

        g2d.setColor(frameColor);
        g2d.setStroke(new BasicStroke(outerFrameLineWidth));




        Path2D.Double xAxis = new Path2D.Double();
        xAxis.moveTo(0,outerHeight);
        xAxis.lineTo(outerWidth*(1+extension),outerHeight);
        g2d.draw(xAxis);

        _drawArrowHead(outerWidth*(1+extension)-10,outerHeight,0,g2d);


        Path2D.Double yAxis = new Path2D.Double();
        yAxis.moveTo(0,0);
        yAxis.lineTo(0,outerHeight);
        g2d.draw(yAxis);
        _drawArrowHead(0,10,270,g2d);


    }

    private void drawFrame(Graphics2D g2d) {
        g2d.setColor(frameColor);
        g2d.setStroke(new BasicStroke(outerFrameLineWidth));
        Rectangle2D.Double outerFrame = new Rectangle2D.Double(0, 0, outerWidth, outerHeight);
        g2d.draw(outerFrame);
    }

    private void drawMajorGrids(Graphics2D g2d) {
        g2d.setColor(gridColor);

        // Raster für die x-ticks
        if (xGrid) {
            g2d.setStroke(new BasicStroke(majorGridLineWidth));
            for (double w = xMajorTicksStart; w < outerWidth; w += xMajorTicksSep) {
                Path2D.Double p = new Path2D.Double();
                p.moveTo(w, 0);
                p.lineTo(w, outerHeight);
                g2d.draw(p);
            }
        }

        // Raster für die y-ticks
        if (yGrid) {
            g2d.setStroke(new BasicStroke(majorGridLineWidth));

            for (double h = yMajorTicksStart; h < outerHeight; h += yMajorTicksSep) {
                Path2D.Double p = new Path2D.Double();
                p.moveTo(0, h);
                p.lineTo(outerWidth, h);
                g2d.draw(p);
            }
        }
    }

    private void drawMinorGrids(Graphics2D g2d) {

        g2d.setColor(gridColor);

        // Raster für die x-ticks
        if (xGrid) {
            g2d.setStroke(new BasicStroke(xGridLineWidth));
            for (double w = xTicksStart; w < outerWidth; w += xTicksSep) {
                Path2D.Double p = new Path2D.Double();
                p.moveTo(w, 0);
                p.lineTo(w, outerHeight);
                g2d.draw(p);
            }
        }

        // Raster für die y-ticks
        if (yGrid) {
            g2d.setStroke(new BasicStroke(yGridLineWidth));

            for (double h = yTicksStart; h < outerHeight; h += yTicksSep) {
                Path2D.Double p = new Path2D.Double();
                p.moveTo(0, h);
                p.lineTo(outerWidth, h);
                g2d.draw(p);
            }
        }


    }

    public void resetSettings() {

        if (parentPlot != null) {
            originX = parentPlot.getOriginX();
            originY = parentPlot.getOriginY();
        } else {
            originX = 3;
            originY = 3;
        }

        outerWidth = 600;
        outerHeight = 480;

        printFrame = true;
        printArrowAxis = false;
        outerFrameLineWidth = 2f;
        frameColor = new Color(0, 0, 0);

        grid = true;
        drawMajorGrids = true;
        drawMinorGrids = true;
        xGrid = true;
        yGrid = true;

        xGridLineWidth = 0.5f;
        yGridLineWidth = 0.5f;
        gridColor = new Color(120, 120, 120);
        xTicksSep = 10;
        xTicksStart = 10;
        yTicksSep = 10;
        yTicksStart = 10;
        majorGridLineWidth = 1.5f;
        xMajorTicksSep = 100;
        yMajorTicksSep = 100;
        xMajorTicksStart = 100;
        yMajorTicksStart = 100;

    }

    /**
     * Draws Arrows instead of box-frame.
     */
    public void drawArrowAxis(){
        printFrame=false;
        printArrowAxis = true;
        repaint();
    }

    private void _drawArrowHead(double x , double y , double rot,Graphics2D g2d){

        double headLenght = 30;

        double openAngle = 0.25;

        double retraction = 0.2;

        Path2D.Double xHead = new Path2D.Double();
        xHead.moveTo(x+ headLenght,0+y);
        xHead.lineTo(0+x,Math.tan(openAngle)*headLenght+y);
        xHead.lineTo(x+ retraction*headLenght,0+y);
        xHead.lineTo(x+ 0,-Math.tan(openAngle)*headLenght+y);
        xHead.closePath();

        AffineTransform reset = g2d.getTransform();
        g2d.rotate(Math.toRadians(rot),x,y);

        g2d.fill(xHead);
        g2d.setTransform(reset);
    }

}
