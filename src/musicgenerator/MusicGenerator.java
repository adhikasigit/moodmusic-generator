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
import java.util.Random;
/**
 *
 * @author user
 */
public class MusicGenerator {

    static double[][] transisi = { { 0.142, 0.142, 0.142, 0.142, 0.142, 0.142, 0.142},
                                { 0.142, 0.142, 0.142, 0.142, 0.142, 0.142, 0.142},
                                { 0.142, 0.142, 0.142, 0.142, 0.142, 0.142, 0.142},
                                { 0.142, 0.142, 0.142, 0.142, 0.142, 0.142, 0.142},
                                { 0.142, 0.142, 0.142, 0.142, 0.142, 0.142, 0.142},
                                { 0.142, 0.142, 0.142, 0.142, 0.142, 0.142, 0.142},
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
        
        Random randnum =  new Random();
        MarkovChain NoteMC = new MarkovChain();
        MarkovChain OctMC = new MarkovChain();
        MarkovChain ValMC = new MarkovChain();
        MarkovChain ChordMC = new MarkovChain();
        int transposer = randnum.nextInt(12) + 1;
        
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
                n.setPitch(map1[NoteMC.curState - 1]);
                //System.out.println(n.getPitch());
                Melody.phrase.addNote(n);
            }
        }
        Melody.part.addPhrase(Melody.phrase);
        scr.addPart(Melody.part);
        scr.setTempo(60.0);
        
        Play.midi(scr);
        
        
        
    }
    
}
