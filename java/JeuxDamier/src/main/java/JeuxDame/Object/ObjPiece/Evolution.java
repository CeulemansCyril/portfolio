/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Object.ObjPiece;

import java.util.ArrayList;

/**
 *
 * @author ceule
 */
public class Evolution {
    private ArrayList <String> NamePieceEvoltuion;
    private int PoineEvolutionX;

    public Evolution() {
    }

    public Evolution(ArrayList<String> NamePieceEvoltuion, int PoineEvolutionX) {
        this.NamePieceEvoltuion= NamePieceEvoltuion;
        setPoineEvolutionX(PoineEvolutionX);
    }
    
    

    public ArrayList<String> getNamePieceEvoltuion() {
        return NamePieceEvoltuion;
    }

    public void addNamePieceEvoltuion(String NamePieceEvoltuion) {
        this.NamePieceEvoltuion.add(NamePieceEvoltuion);
    }
    public void addMultiNamePieceEvoltuion(ArrayList<String> NamePieceEvoltuion) {
        this.NamePieceEvoltuion.addAll(NamePieceEvoltuion);
    }

    public int getPoineEvolutionX() {
        return PoineEvolutionX;
    }

    public void setPoineEvolutionX(int PoineEvolutionX) {
        this.PoineEvolutionX = PoineEvolutionX;
    }


    
    
}
