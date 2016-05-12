/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicgenerator;

/**
 *
 * @author user
 */
public class MusicGenerator {

     static double[][] transisi = { { 0.386, 0.147, 0.202, 0.062, 0.140, 0.047, 0.016},
                                { 0.107, 0.267, 0.227, 0.120, 0.207, 0.052, 0.020},
                                { 0.035, 0.101, 0.188, 0.191, 0.357, 0.067, 0.061},
                                { 0.021, 0.039, 0.112, 0.212, 0.431, 0.124, 0.061},
                                { 0.009, 0.024, 0.075, 0.123, 0.473, 0.171, 0.125},
                                { 0.000, 0.103, 0.041, 0.088, 0.391, 0.312, 0.155},
                                { 0.000, 0.008, 0.036, 0.083, 0.364, 0.235, 0.274}
                              };
     static double[][] CT1;
     static double[][] CT2;
     static double[][] CT3;
     static double[][] CT4;
     static double[][] CT5;
     static double[][] CT6;
     static double[][] CT7;
     static double[][] CT8;
     static double[][] NT1;
     static double[][] NT2;
     static double[][] NT3;
     static double[][] NT4;
     static double[][] NT5;
     static double[][] NT6;
     static double[][] NT7;
     static double[][] OkT;
     static double[][] VT1;
     static double[][] VT2;
     
    public static void main(String[] args) {
        
        //initialize MIDI
        //ask for mood input
        //read mood input
        //initialize Markov Chains according to mood
        //fill constraints
        //feed values of MC + Constraint to MIDI
        //Play
        
        MarkovChain Mc = new MarkovChain();
        
        Mc.transition = transisi;
        Mc.states = 7;
        Mc.curState = 1;
        
        Mc.nextState();
        
        // TODO code application logic here
    }
    
}
