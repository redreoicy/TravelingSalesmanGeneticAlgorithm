package tspgeneticalg;

import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Map {
    Point[] pointList;
    double[][] distanceLookUp;
    static double bound;
     static int amount;
    Map(int amount, double bound){
        this.bound = bound;
        this.amount = amount;
        Random rng = new Random();
        pointList = new Point[amount];
        for (int i = 0; i < amount; i++) {
            pointList[i] = new Point(rng.nextDouble() * bound, rng.nextDouble() * bound);
        }
        distanceLookUp = new double[amount][amount];
        for (int i = 0; i < amount-1; i++) { //create an adjacency table
            for (int j = i+1; j < amount; j++) {
                distanceLookUp[i][j] = pointList[i].distanceTo(pointList[j]);
                distanceLookUp[j][i] = distanceLookUp[i][j];
            }
        }
    }
    Map(){ //call map with no argument to create the sample map.
        this.bound = 1000.0;
        this.amount = 5;
        Point p1 = new Point(400,200);
        Point p2 = new Point(400,600);
        Point p3 = new Point(200,400);
        Point p4 = new Point(800,200);
        Point p5 = new Point(800,600);
        pointList = new Point[5];
        pointList[0] = p1;
        pointList[1] = p2;
        pointList[2] = p3;
        pointList[3] = p4;
        pointList[4] = p5;
        distanceLookUp = new double[5][5];
        distanceLookUp[0][0] = 0.0;
        distanceLookUp[0][1] = 14.0;
        distanceLookUp[0][2] = 4.0;
        distanceLookUp[0][3] = 10.0;
        distanceLookUp[0][4] = 20.0;
        distanceLookUp[1][0] = 14.0;
        distanceLookUp[1][1] = 0.0;
        distanceLookUp[1][2] = 7.0;
        distanceLookUp[1][3] = 8.0;
        distanceLookUp[1][4] = 7.0;
        distanceLookUp[2][0] = 4.0;
        distanceLookUp[2][1] = 5.0;
        distanceLookUp[2][2] = 0.0;
        distanceLookUp[2][3] = 7.0;
        distanceLookUp[2][4] = 16.0;
        distanceLookUp[3][0] = 11.0;
        distanceLookUp[3][1] = 7.0;
        distanceLookUp[3][2] = 9.0;
        distanceLookUp[3][3] = 0.0;
        distanceLookUp[3][4] = 2.0;
        distanceLookUp[4][0] = 18.0;
        distanceLookUp[4][1] = 7.0;
        distanceLookUp[4][2] = 17.0;
        distanceLookUp[4][3] = 4.0;
        distanceLookUp[4][4] = 0.0;



    }
    
    double findTotalDistance(Chromosome chr){
        double sum = 0.0;
        for (int i = 0; i < chr.length-1; i++) {
            sum += distanceLookUp[chr.sequence.get(i)][chr.sequence.get(i+1)];
        }
        return sum;
    }
    
    void displayLines(Chromosome chr){
        Runnable r = new Runnable() {
            public void run() {
                LinesComponent lineComponent = new LinesComponent((int)bound, pointList);
                    for (int i = 0; i < amount-1; i++) {
                        lineComponent.addLine(  (int)pointList[chr.sequence.get(i)].x,
                                                (int)pointList[chr.sequence.get(i)].y,
                                                (int)pointList[chr.sequence.get(i+1)].x,
                                                (int)pointList[chr.sequence.get(i+1)].y
                                                );
                    }

                JOptionPane.showMessageDialog(null, lineComponent);
            }
        };
        SwingUtilities.invokeLater(r);
    }
    
}
