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
public class EmotionHandler {
    //use to simplify case for Main
    public double[][] ChordMC;
    public double[][] NoteMC; 
    public double[][] OctMC;
    public double[][] ValMC; // ini semua keluaran dari EmotionHandler
    public int emoMap[];
    public double emoTempo;
    
    //int map[] = {60,62,64,65,67,69,71};
    
    public void setLowNegativeAffect(){ // Barat

        emoTempo = 120.0;
        int map[] = {60,62,64,65,67,69,71};
        emoMap = map;
        
        //double[][] CT1;
        //ChordMC = CT1;
        //double[][] NT1;
        //NoteMC = NT1;
        //double[][] OT1;
        //OctMC = OT1;
        //double[][] = VT1;
        //ValMC = VT1; 
        
    }
    public void setPleasantness(){
        emoTempo = 160.0;
        int map[] = {96,98,100,101,103,105,107};
        emoMap = map;
    }
    
    public void setHighPositiveAffect(){
        emoTempo = 180.0;
        int map[] = {96,98,100,101,103,105,107};
        emoMap = map;
    }
    public void setStrongEngagement(){
        emoTempo = 220.0;
        int map[] = {48,50,52,53,55,57,59};
        emoMap = map;
    }
    public void setHighNegativeAffect(){
        emoTempo = 120.0;
        int map[] = {36,38,40,41,43,45,47};
        emoMap = map;
    }
    public void setUnpleasantness(){
        emoTempo = 90.0;
        int map[] = {24,26,28,29,31,33,35};
        emoMap = map;
    }
    public void setLowPositiveAffect(){
        emoTempo = 70.0;
        int map[] = {36,38,40,41,43,45,47};
        emoMap =  map; 
    }
    public void setDisengagement(){
        emoTempo = 60.0;
        int map[] = {48,50,52,53,55,57,59};
        emoMap = map;
    }
    //do the same for all emotion
}
