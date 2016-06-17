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

    
    static double[][][] map = {MidiHandler.Maj1,MidiHandler.Min2,MidiHandler.Min3,MidiHandler.Maj4,MidiHandler.Maj5,MidiHandler.Min6,MidiHandler.Dim7};
     
    EmotionHandler Emotion = new EmotionHandler();
    Score scr = new Score();
    
    boolean stopPlay = false;
     
    static double[] ValMap = {JMC.QUAVER, JMC.CROTCHET, JMC.MINIM ,JMC.SEMIBREVE};
    static int[] OctMap = {0,12};
     
    public void Generate() {
        
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
        int chordRandomizer = 1;
        
        
        NoteMC.transition = map[randnum.nextInt(6)];
        NoteMC.states = 7;
        NoteMC.curState = 1;
        
        OctMC.transition = Emotion.OctMC;
        OctMC.states = 2;
        OctMC.curState = 1;
       
        ValMC.transition = Emotion.ValueMC;
        ValMC.states = 4;
        ValMC.curState = 1;
        
        ChordMC.transition = Emotion.ChordMC;
        ChordMC.states = 7;
        ChordMC.curState = 1;
        
        MidiHandler Melody = new MidiHandler();
        MidiHandler Chord =  new MidiHandler();
        MidiHandler chordRoot = new MidiHandler();
        MidiHandler chordMid = new MidiHandler();
        MidiHandler chordDom = new MidiHandler();
        
        int crCounter = 0;
        int cmCounter = 0;
        int cdCounter = 0;
        int mCounter = 0;
        
            for(int i=0;i<1200;i++){
                if (i % 8 == 0){
                    ChordMC.nextState();
                //System.out.println(map[ChordMC.curState - 1]);
                NoteMC.transition = map[ChordMC.curState - 1];
                System.out.println("Chord State = " + ChordMC.curState);
                OctMC.nextState();
                }
                //initialize chord notes
                Note cRoot = new Note();
                Note cMid = new Note();
                Note cDom = new Note();
                //setting the note pitch for each chord note
                if(crCounter == 0){
                    cRoot.setPitch(Emotion.emoMap[ChordMC.curState - 1] - 12);
                    ValMC.nextState();
                    cRoot.setLength(ValMap[ValMC.curState - 1]);
                    switch (ValMC.curState) {
                        case 1 : crCounter = 1;
                                 break;
                        case 2 : crCounter = 2;
                                 break;
                        case 3 : crCounter = 4;
                                 break;
                        case 4 : crCounter = 8;
                                 break;
                    }
                    //System.out.println(cRoot.getPitch());
                    chordRoot.phrase.addNote(cRoot);
                }
                if (cmCounter == 0){
                    cMid.setPitch(cRoot.getPitch() + 4);
                    ValMC.nextState();
                    cMid.setLength(ValMap[ValMC.curState - 1]);
                    switch (ValMC.curState) {
                        case 1 : cmCounter = 1;
                                 break;
                        case 2 : cmCounter = 2;
                                 break;
                        case 3 : cmCounter = 4;
                                 break;
                        case 4 : cmCounter = 8;
                                 break;
                    }
                    chordMid.phrase.addNote(cMid);
                }
                if (cmCounter == 0){
                    cDom.setPitch(cRoot.getPitch() + 7);
                    ValMC.nextState();
                    cDom.setLength(ValMap[ValMC.curState - 1]);
                    switch (ValMC.curState) {
                        case 1 : cmCounter = 1;
                                 break;
                        case 2 : cmCounter = 2;
                                 break;
                        case 3 : cmCounter = 4;
                                 break;
                        case 4 : cmCounter = 8;
                                 break;
                    }
                    chordDom.phrase.addNote(cMid);
                }
                //putting the chord notes in a note array
                //Note[] noteArray = {cRoot, cMid, cDom};
                //using cphrase to contain the chord
                //CPhrase ChordN = new CPhrase();
                //ChordN.addChord(noteArray);
                //adding the chord to the part
               // Chord.part.addCPhrase(ChordN);
               if (mCounter == 0){
                    NoteMC.nextState();
                    System.out.println("Note state = " + NoteMC.curState);
                    ValMC.nextState();
                    Note n = new Note();
                    n.setPitch(Emotion.emoMap[NoteMC.curState - 1] + OctMap[OctMC.curState - 1]);
                    n.setLength(ValMap[ValMC.curState - 1]);
                    switch (ValMC.curState) {
                        case 1 : mCounter = 1;
                                 break;
                        case 2 : mCounter = 2;
                                 break;
                        case 3 : mCounter = 4;
                                 break;
                        case 4 : mCounter = 8;
                                 break;
                    }
                    //System.out.println(n.getPitch());
                    Melody.phrase.addNote(n);
                    }
                crCounter--;
                cmCounter--;
                cdCounter--;
                mCounter--;
            }
            Melody.part.addPhrase(Melody.phrase);
            chordRoot.part.addPhrase(chordRoot.phrase);
            chordMid.part.addPhrase(chordMid.phrase);
            chordDom.part.addPhrase (chordDom.phrase);
            scr.addPart(Melody.part);
            scr.addPart(chordRoot.part);
            scr.addPart(chordMid.part);
            scr.addPart(chordDom.part);
            scr.setTempo(Emotion.emoTempo);
            Mod.transpose(scr, transposer);
            
       }
}
