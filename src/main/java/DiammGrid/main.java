package DiammGrid;



import javax.swing.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class main {

    public static void main(String[] args) {
        int w = 700;
        int h = 700;
        JFrame f = new JFrame();
        f.setSize(w, h);
        f.setTitle("Mein erster Punkt");


        double[][] data = createDate(2) ;
        double[][] newData = createDate(1) ;

        String oa = Arrays.stream(data).map(x -> String.format("[%s,%s]", x[0], x[1])).collect(Collectors.joining(","));
        System.out.printf("Data ist: %s", oa);

        PlotPanel plot = new PlotPanel();

        GridPanel gp =  plot.getGrid();

        gp.drawMinorGrids=false;
        gp.drawArrowAxis();

        plot.plot(data);
        plot.plot(newData);


        f.add(plot);

//        GridPanel gp = new GridPanel();
//        gp.drawArrowAxis();
//        f.add(gp);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }


    public static double[][] createDate(int methode) {

        double[][] returnData;
        int c = 0;
        switch (methode) {
            case 1:
                returnData = new double[8][2];
                for (int i = 0; i < returnData.length; i++) {
                    double[] pair = new double[2];
                    pair[0] = i * 50;
                    pair[1] = pair[0] * i * 0.2;
                    returnData[c++] = pair;
                }
                break;
            case 2:
                returnData = new double[5][2];
                for (int i = 0; i < 5; i++) {
                    double[] pair = new double[2];
                    pair[0] = i*25 ;
                    pair[1] = 0;
                    returnData[c++] = pair;
                }
                break;
            default:
                returnData = new double[5][2];
                for (int i = 1; i < 6; i++) {
                    double[] pair = new double[2];
                    pair[0] = i * 105;
                    pair[1] = i * 75;
                    returnData[c++] = pair;
                }
                ;
        }

        return returnData;


    }

}
