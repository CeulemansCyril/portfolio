/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package JeuxDame.Listener;

/**
 *
 * @author ceule
 */
public interface ListenerDeplacementComplexe {
    
    public void PlacementPieceComplexe(String txt,boolean clear);
    public void ColorDeplacement(int Row,int Col,boolean full,String placement);
    
}
