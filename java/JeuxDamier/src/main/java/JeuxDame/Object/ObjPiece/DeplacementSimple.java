/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Object.ObjPiece;

/**
 *
 * @author ceule
 */
public class DeplacementSimple extends IDeplacement {

    private int NbDeplacement;

    public DeplacementSimple(int DeplacementY,int DeplacementX ,int NbDeplacement,String id) {
        setNbDeplacement(NbDeplacement);
        setDeplacementY(DeplacementY);
        setDeplacementX(DeplacementX);
        setIDDeplacement(id);

    }
    
    

    public int getNbDeplacement() {
        return NbDeplacement;
    }

    private void setNbDeplacement(int NbDeplacement) {
        this.NbDeplacement = NbDeplacement;
    }

}
