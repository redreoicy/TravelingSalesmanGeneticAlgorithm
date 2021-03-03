/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tspgeneticalg;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
class Chromosome {
    ArrayList<Integer> sequence;
    int length;
    Chromosome(int n){    //given a size n, randomly generate a chromosome.
        length = n;
        sequence = new ArrayList<>();
        for (int i = 0; i < length; i++) { // points start at 0
            sequence.add(i);
        }
        Collections.shuffle(sequence);      // random chromosome
    }
    Chromosome(ArrayList sequence){         //create a Chromosome given a sequence
        this.length = sequence.size();
        this.sequence = sequence;
    }
    Chromosome[] reproduceWith(Chromosome chr){ //returns a 2 element array of the children
        //first, interleave the chromosomes (1,2,3) + (3,2,1) = (1,3,2,2,3,1).
        //Then, randomly add one from every pair of same integers to child a,
        // and the other to child b.
        //If two parents are identical, the offspring will also be identical.
        //Assumes parents have same length
        
        ArrayList<Integer> combinedSequence = new ArrayList<>();
        //interleave the chromosomes
        for (int i = 0; i < this.length; i++) {
            combinedSequence.add(this.sequence.get(i));
            combinedSequence.add(chr.sequence.get(i));
        }
        //randomly choose which to use
        boolean[] whichChild = new boolean[this.length];
        Random rng = new Random();
        for(int i = 0; i < this.length; i++){
            whichChild[i] = rng.nextBoolean();
        }
        
        ArrayList<Integer> childSequence1 = new ArrayList<>();
        ArrayList<Integer> childSequence2 = new ArrayList<>();
        
        for(int n : combinedSequence){
            if(whichChild[n]){
                childSequence1.add(n);
            }else{
                childSequence2.add(n);
            }
            whichChild[n] = !whichChild[n];  //flip it so the next occurence of n goes to the other child
        }
        
        //random chance for a mutation
        Random rng2 = new Random();
        /*while(rng.nextDouble()<0.05){//5% chance to mutate the children, swap 2 values - can happen more than once, hmm, move should be better than swap
            int index1 = rng.nextInt(length);
            int index2 = rng.nextInt(length);
            
            int temp = childSequence1.get(index1); //swap in child 1
            childSequence1.set(index1, childSequence1.get(index2));
            childSequence1.set(index2, temp);
            
            temp = childSequence2.get(index1);    //swap in child 2
            childSequence2.set(index1, childSequence2.get(index2));
            childSequence2.set(index2, temp);
        }*/
        while(rng.nextDouble()<0.01){//1% chance to mutate the children, swap 2 values - can happen more than once, hmm, move should be better than swap
            int index1 = rng.nextInt(length);
            int index2 = rng.nextInt(length);
            
            int temp = childSequence1.get(index1); //swap in child 1
            childSequence1.add(index2, temp);
            if(index1<index2){//index2 was greater, so index 1 wasn't shifted
                childSequence1.remove(index1);
            }else{//otherwise, index 1 got shifted up one
                childSequence1.remove(index1+1);
            }
            
            temp = childSequence2.get(index1); //swap in child 2
            childSequence2.add(index2, temp);
            if(index1<index2){//index2 was greater, so index 1 wasn't shifted
                childSequence2.remove(index1);
            }else{//otherwise, index 1 got shifted up one
                childSequence2.remove(index1+1);
            }
        }
        
        
        Chromosome child1 = new Chromosome(childSequence1);
        Chromosome child2 = new Chromosome(childSequence2);
        Chromosome[] result = {child1, child2};
        return result;
    }
    void print(){
        this.sequence.stream().forEach((i) -> {
            System.out.print(" " + (i+1));
        });
        System.out.println("");
    }
}
