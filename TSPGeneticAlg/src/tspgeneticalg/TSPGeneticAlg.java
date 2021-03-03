/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tspgeneticalg;

import java.util.ArrayList;

/**
 *
 * @author redre
 */
public class TSPGeneticAlg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { //currently the sample version is commented.
        //uncomment the other fun loop, and comment the sample for loop, then also uncomment the display lines in
        //genePool.advanceGeneration() to see the algorithm at work on a larger sample.

        for (int j = 0; j<10;j++) {//10 trials
            System.out.println();
            System.out.println("Trial: " + (j+1));
            Map sample = new Map();//call map without arguments to get the sample.
            GenePool overkill = new GenePool(50, sample);
            for (int i = 0; i < 501; i++) {
                overkill.advanceAGeneration();
            }
            overkill.printCurrentGeneration(1);
        }



        /*
        Map m = new Map(50, 1000.0);
        for (int j = 0; j<1;j++) {//only 1 trial
            System.out.println();
            System.out.println("Trial: " + (j+1));

            GenePool rabbits = new GenePool(5000, m);
            for (int i = 0; i < 501; i++) {
                rabbits.advanceAGeneration();
            }
        }
        //rabbits.printCurrentGeneration(50); //print 50 chromosomes for testing purposes

         */
    }
    
}
