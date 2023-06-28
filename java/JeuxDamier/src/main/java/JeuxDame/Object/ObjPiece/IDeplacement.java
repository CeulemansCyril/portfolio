/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package JeuxDame.Object.ObjPiece;

/**
 *
 * @author ceule
 */
public abstract class IDeplacement {

    private int DeplacementX;
    private int DeplacementY;

    private String IDDeplacement;

    public int getDeplacementX() {
        return DeplacementX;
    }

    public void setDeplacementX(int DeplacementX) {
        this.DeplacementX = DeplacementX;
    }

    public int getDeplacementY() {
        return DeplacementY;
    }

    public void setDeplacementY(int DeplacementY) {
        this.DeplacementY = DeplacementY;
    }

    public String getIDDeplacement() {
        return IDDeplacement;
    }

    public void setIDDeplacement(String IDDeplacement) {
        this.IDDeplacement = IDDeplacement;
    }

    
}
