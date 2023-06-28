/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Object.ObjPiece;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Utilisateur
 */
public abstract class IPiece {
    private String Team;
    private String Name;
    private ArrayList <IDeplacement> DeplacementPiece;
    private Evolution Evolution;
    private ImageIcon Img;
    private ArrayList <Placement> PlacementPiece;
        private boolean SautPiece;

    public String getTeam() {
        return Team;
    }

    public void setTeam(String Team) {
        this.Team = Team;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public ArrayList getDeplacementPiece(){
        return this.DeplacementPiece;
    }
    public void addDeplacementPiece(IDeplacement deplace){
        this.DeplacementPiece.add(deplace);
    }
    public void addMutiDeplacementPiece(ArrayList deplace){
        this.DeplacementPiece.addAll(deplace);
    }

    public ImageIcon getImg() {
        return Img;
    }

    public void setImg(ImageIcon Img) {
        this.Img = Img;
    }

    public void setDeplacementPiece(ArrayList<IDeplacement> DeplacementPiece) {
        this.DeplacementPiece = DeplacementPiece;
    }

    public void setEvolution(Evolution Evolution) {
        this.Evolution = Evolution;
    }

    public void setPlacementPiece(ArrayList<Placement> PlacementPiece) {
        this.PlacementPiece = PlacementPiece;
    }

    
    
    public void addEvolution(String evol){
        this.Evolution.addNamePieceEvoltuion(evol);
    }
    
    public int getPointEvolution(){
        return this.Evolution.getPoineEvolutionX();
    }
    
    public ArrayList getNamePieceEvolution(){
        return this.Evolution.getNamePieceEvoltuion();
    }
    
    public void addOnePlacement(Placement e){
        PlacementPiece.add(e);
    }
    
    public void addMultyPlacement(ArrayList<Placement> e){
        PlacementPiece.addAll(e);
    }
    public void addMultyPEvolution(ArrayList<String> e){
        Evolution.addMultiNamePieceEvoltuion(e);
    }
    
    public ArrayList getAllPlacement(){
        return PlacementPiece;
    }

    public boolean isSautPiece() {
        return SautPiece;
    }

    public void setSautPiece(boolean SautPiece) {
        this.SautPiece = SautPiece;
    }
    
    
    
}
