/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicgenerator;

import jm.JMC;
import jm.music.data.*;
import jm.midi.*;
import jm.util.*;
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

     
    public static void main(String[] args) {
        
        //initialize MIDI
        //ask for mood input
        //read mood input
        //initialize Markov Chains according to mood
        //fill constraints
        //feed values of MC + Constraint to MIDI
        //Play
        MarkovChain mc = new MarkovChain();
        mc.transition = transisi;
        mc.states = 6;
        mc.curState = 5;
                
        int map[] = {60,62,64,65,67,69,71,72};
        
        Score scr = new Score();
        Part par = new Part();
        Phrase phr = new Phrase();
        Note not = new Note();
        not.setPitch(map[mc.curState - 1]);
        phr.addNote(not);
        
        for(int i=0;i<30;i++){
            mc.nextState();
            Note n = new Note();
            n.setPitch(map[mc.curState - 1]);
            System.out.println(n.getPitch());
            phr.addNote(n);
        }
        par.addPhrase(phr);
        scr.addPart(par);
        Play.midi(scr);
        
        
        // TODO code application logic here
    }
    
}
