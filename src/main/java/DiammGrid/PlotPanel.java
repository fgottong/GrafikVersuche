package DiammGrid;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlotPanel extends JPanel {

    private final List<double[][]> datas = new ArrayList<>();

    int originX = 30;
    int originY = 30;

    GridPanel gp;
    private Rectangle gpBounds = null;

    public PlotPanel() {
        gp = new GridPanel(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setLayout(null);

        removeAll(); // Remove all "old" Datas and grids.

        gp.setBounds(100, 0, 650, 650);

//        if (gpBounds != null) {
//            gp.setBounds(0, 0, 650, 650);
//            gpBounds = gp.getBounds();
//        } else {
//            gp.setBounds(gpBounds);
//        }

        gp.setOpaque(true);

        for (double[][] data : datas) {
            DataLayer dl = new DataLayer(data, this);
            dl.setOpaque(false);
            dl.setBounds(0, 0, 650, 650);
            this.add(dl);
        }

        //adjustBounds(gp.ylabelwidth);

        this.add(gp);

    }

    public void plot(double[][] data) {
        datas.add(data);
        repaint();
    }


    public int getOriginX() {
        return originX;
    }

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
    }

    public GridPanel getGrid() {
        return gp;
    }

    public void adjustBounds(int xShift) {

        if (gp.getBounds() != null) {
            Rectangle oldBound = gp.getBounds();
            gp.setBounds(oldBound.x + xShift, oldBound.y, oldBound.width, oldBound.height);
        } else {
            gp.setBounds(0,0, 650, 650);
        }


    }

}
