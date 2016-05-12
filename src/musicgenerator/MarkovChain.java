/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicgenerator;
import java.lang.Math;
import java.util.*;
/**
 *
 * @author user
 */
public class MarkovChain {
    
    double[][] transition;
    int states;
    int curState;
    boolean done = false;
    
    
    public void runMarkov(){
    //double r = (int) Math.ceil(Math.random() * 100);
    System.out.println(curState);
    while (curState > 0){
        double r = Math.random();
       // System.out.println("random = " + r);
        double sum = 0.0;
        for (int j = 0; j < states ; j++){
            sum += transition[curState][j];
            //System.out.println("Sum = " + sum);
            if (sum >= r){
                curState = j;
                break;
            }
        }
    }
    
    }
    

}
