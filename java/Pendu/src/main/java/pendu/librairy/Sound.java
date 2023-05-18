/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendu.librairy;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author ceule
 */
public class Sound {

    public Sound() {
    }
    //joue un sond demander
    public void SoundPlay(String txt){
        String path = new String();
        Clip clip = null;
        AudioInputStream audioStream = null;
        //recher le path du fichier demander
        switch(txt){
            case "Good":
                path="./Ressource/Sound/DingSound.aiff";
                break;
            case "Bad":
                path="./Ressource/Sound/BuzzerWrong.aiff";
                break;
            case"Win":
                path="./Ressource/Sound/YouWin.aiff";
                break;
            case"Lose":
                path="./Ressource/Sound/Lose.aiff";
                break;
            case "AmongUs":
                path="./Ressource/Sound/AmongUs.aiff";
                break;
        }
        
        //transforme le fichier en audioStream
        try {
             audioStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
        } catch (UnsupportedAudioFileException ex) {
        } catch (IOException ex) {}
            //transforme l'audioStream en clip
        try {
             clip = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
           
        }
        try {
            //ouvre le clip
            clip.open(audioStream);
        } catch (LineUnavailableException ex) {
        } catch (IOException ex) {
        }
        //joue le fichier
        clip.start();
    }
}
