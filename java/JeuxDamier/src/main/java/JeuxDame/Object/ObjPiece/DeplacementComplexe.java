/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Object.ObjPiece;

/**
 *
 * @author ceule
 */
public class DeplacementComplexe extends IDeplacement{
    
    private boolean ColorAll;

    public DeplacementComplexe(int DeplacementY,int DeplacementX,String id,boolean ColorAll) {
        setDeplacementY(DeplacementY);
        setDeplacementX(DeplacementX);
        setIDDeplacement(id);
        setColorAll(ColorAll);
    }

    public boolean isColorAll() {
        return ColorAll;
    }

    public void setColorAll(boolean ColorAll) {
        this.ColorAll = ColorAll;
    }
    
    
    
    
    
}
