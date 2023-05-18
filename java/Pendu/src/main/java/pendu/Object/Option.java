/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendu.Object;

/**
 *
 * @author ceule
 */
public class Option {

    private String SenseDuText;
    private String Police;
    private String Langue;
    private int taillePolice;
    private boolean sound;
    private String StyleImg;

    public String getStyleImg() {
        return StyleImg;
    }

    public void setStyleImg(String StyleImg) {
        this.StyleImg = StyleImg;
    }
  
    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }
    


    public String getSenseDuText() {
        return SenseDuText;
    }

    public void setSenseDuText(String SenseDuText) {
        this.SenseDuText = SenseDuText;
    }

    public String getPolice() {
        return Police;
    }

    public void setPolice(String Police) {
        this.Police = Police;
    }

    public int getTaillePolice() {
        return taillePolice;
    }

    public void setTaillePolice(int taillePolice) {
        this.taillePolice = taillePolice;
    }

    public String getLangue() {
        return Langue;
    }

    public void setLangue(String Langue) {
        this.Langue = Langue;
    }
    
    
}
