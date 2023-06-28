/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Object;

import JeuxDame.Object.ObjPiece.Evolution;
import JeuxDame.Object.ObjPiece.IPiece;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Utilisateur
 */
public class Piece extends IPiece{
    
     /**
     * Constructs a Piece with the specified image, name, evolution, team, list of movements,
     * and list of placements.
     *
     * @param img             the image of the piece
     * @param name            the name of the piece
     * @param pointEvol       the evolution points of the piece
     * @param team            the team of the piece
     * @param evolution       the evolution of the piece
     * @param listDeplacement the list of movements for the piece
     * @param listPlacement   the list of placements for the piece
     */
    public Piece(ImageIcon Img,String Name, int PointEvol,String Team,Evolution Evol,ArrayList listDeplacement,ArrayList ListPlacement) {
        setImg(Img);
        setName(Name);
        setTeam(Team);
        setEvolution(Evol);
        setDeplacementPiece(listDeplacement);
        setPlacementPiece(ListPlacement);
    }
     /**
     * Constructs an empty Piece.
     */
    public Piece() {
    }
    
}
