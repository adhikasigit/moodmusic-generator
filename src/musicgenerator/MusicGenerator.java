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
import jm.music.tools.Mod;
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
    
    
     
     
     
    static double[] ValMap = {JMC.QUAVER, JMC.CROTCHET, JMC.MINIM ,JMC.SEMIBREVE};
    static int[] OctMap = {-12,0,12};
     
    public static void main(String[] args) {
        
        //initialize MIDI
        //ask for mood input
        //read mood input
        //initialize Markov Chains according to mood
        //fill constraints
        //feed values of MC + Constraint to MIDI
        //Play
        EmotionHandler Emotion = new EmotionHandler();
        Random randnum =  new Random();
        MarkovChain NoteMC = new MarkovChain();
        MarkovChain OctMC = new MarkovChain();
        MarkovChain ValMC = new MarkovChain();
        MarkovChain ChordMC = new MarkovChain();
        int transposer = randnum.nextInt(12) + 1;
        
        Emotion.setLowNegativeAffect();
        
        NoteMC.transition = transisi;
        NoteMC.states = 7;
        NoteMC.curState = 7;
        
        OctMC.transition = Emotion.OctMC;
        OctMC.states = 3;
        OctMC.curState = 2;
       
        ValMC.transition = Emotion.ValueMC;
        ValMC.states = 4;
        ValMC.curState = 1;
        
        MidiHandler Melody = new MidiHandler();
        Score scr = new Score();
        
        System.out.println(ValMC.transition[0][0]);
        
        //while(true){
            for(int i=0;i<100;i++){
                for(int j=0;i<4;i++){
                    
                    NoteMC.nextState();
                    ValMC.nextState();
                    OctMC.nextState();
                    if (NoteMC.curState == 8){
                        //Rest
                    }
                    else{
                        Note n = new Note();
                        n.setPitch(Emotion.emoMap[NoteMC.curState - 1] + OctMap[OctMC.curState - 1]);
                        n.setLength(ValMap[ValMC.curState - 1]);
                        //System.out.println(n.getPitch());
                        Melody.phrase.addNote(n);
                    }
                }
            Melody.part.addPhrase(Melody.phrase);
            scr.addPart(Melody.part);
            scr.setTempo(Emotion.emoTempo);
                                          
            Play.midi(scr); 
        }
    }
    
}
