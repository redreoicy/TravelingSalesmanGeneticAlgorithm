/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tspgeneticalg;
import java.util.ArrayList;
import java.util.Random;
public class GenePool {
    ArrayList<Chromosome> chromosomeList;
    int generation;
    int population;
    Map map;
    GenePool(int population, Map map){ //assumes an even population
        this.population = population;
        this.generation = 0;
        this.map = map;
        chromosomeList = new ArrayList<>();
        for (int i = 0; i < population; i++) {
            chromosomeList.add(new Chromosome(Map.amount));
        }
    }
    public void advanceAGeneration(){
        double totalFitness = 0.0;
        double bestFitness = 999999999999999990.0; //keep best fitness to record
        double worstFitness = 0.0;                   //keep worst fitness to normalize scores later
        Chromosome bestChr = chromosomeList.get(0);
        for(Chromosome chr : chromosomeList){
            totalFitness += map.findTotalDistance(chr);
            if(map.findTotalDistance(chr)<bestFitness){
                bestFitness = map.findTotalDistance(chr);
                bestChr = chr;
            }
            if(map.findTotalDistance(chr)>worstFitness){
                worstFitness = map.findTotalDistance(chr);
            }
        }
        if(generation % 500 == 0){
            //System.out.println(generation); //used for tracking
        }
        if(generation %500 == 0){
            System.out.println("Generation " + generation + " best fitness: " + bestFitness + "\nWorst fitness is: " + worstFitness +"\nTotal fitness is: " + totalFitness);
            //map.displayLines(bestChr);//Remove these comments to view graphs.
        }
        
        
        double calculatedTotal = (worstFitness + 5)*population - totalFitness; //this is the adusted value for parents selection.
        //positive fitness is determined by how much better a chromosome is than the worst. 5 + the difference
        Random rng = new Random();
        ArrayList<Chromosome> nextGeneration = new ArrayList<>();
        double[] normedSumArray = new double[population];
        double sum = 0.0;
        for(int i = 0; i < population; i++){ //later we can use binary search on the array
                sum += (5 + worstFitness - map.findTotalDistance(chromosomeList.get(i)));
                normedSumArray[i] = sum;
            }
        for (int i = 0; i < population/2; i++) {
            double parent1Val = rng.nextDouble()*calculatedTotal;
            double parent2Val = rng.nextDouble()*calculatedTotal;
            Chromosome parent1; 
            Chromosome parent2;
            
            int low = 0; 
            int high = population-1;
            while(low<high){
                int middle = (low+high)/2;
                if(parent1Val<normedSumArray[middle]){
                    high = middle;
                }else{
                    low = middle+1;
                }
            }
            parent1 = chromosomeList.get(low);
            
            low = 0; 
            high = population-1;
            while(low<high){
                int middle = (low+high)/2;
                if(parent2Val<normedSumArray[middle]){
                    high = middle;
                }else{
                    low = middle+1;
                }
            }
            parent2 = chromosomeList.get(low);

            Chromosome[] children;
            children = parent1.reproduceWith(parent2);
            nextGeneration.add(children[0]);//add the children to the next gen
            nextGeneration.add(children[1]);
            
            
        }
        chromosomeList = nextGeneration;
        generation++;
    }
    public void printCurrentGeneration(){
        chromosomeList.stream().forEach((chr) -> {
            chr.print();
        });
    }
    public void printCurrentGeneration(int n){
        for (int i = 0; i < n; i++) {
            chromosomeList.get(i).print();
        }
    }
}
