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

    static double[][] transisi = { { 0.142, 0.142, 0.142, 0.142, 0.142, 0.142, 0.142},
                                { 0.107, 0.267, 0.227, 0.120, 0.207, 0.052, 0.020},
                                { 0.035, 0.101, 0.188, 0.191, 0.357, 0.067, 0.061},
                                { 0.021, 0.039, 0.112, 0.212, 0.431, 0.124, 0.061},
                                { 0.009, 0.024, 0.075, 0.123, 0.473, 0.171, 0.125},
                                { 0.000, 0.103, 0.041, 0.088, 0.391, 0.312, 0.155},
                                { 0.142, 0.142, 0.142, 0.142, 0.142, 0.142, 0.142}
                              };

     
    public static void main(String[] args) {
        
        //initialize MIDI
        //ask for mood input
        //read mood input
        //initialize Markov Chains according to mood
        //fill constraints
        //feed values of MC + Constraint to MIDI
        //Play
        MarkovChain NoteMC = new MarkovChain();
        MarkovChain OctMC = new MarkovChain();
        MarkovChain ValMC = new MarkovChain();
        
        NoteMC.transition = transisi;
        NoteMC.states = 7;
        NoteMC.curState = 7;
                
        int map[] = {60,62,64,65,67,69,71};
        int map1[] = {24,26,28,29,31,33,35};
        int map2[] = {96,98,100,101,103,105,107};
        
        
        MidiHandler Melody = new MidiHandler();
        Score scr = new Score();
        
        
        for(int i=0;i<20;i++){
            NoteMC.nextState();
            if (NoteMC.curState == 8){
                //Rest
            }
            else{
                Note n = new Note();
                n.setPitch(map2[NoteMC.curState - 1]);
                //System.out.println(n.getPitch());
                Melody.phrase.addNote(n);
            }
        }
        Melody.part.addPhrase(Melody.phrase);
        scr.addPart(Melody.part);
        scr.setTempo(300.0);
        
        Play.midi(scr);
        
        
        // TODO code application logic here
    }
    
}
