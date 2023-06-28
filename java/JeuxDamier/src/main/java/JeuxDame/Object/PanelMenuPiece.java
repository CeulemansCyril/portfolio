/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package JeuxDame.Object;

import JeuxDame.Object.ObjPiece.IPiece;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public abstract class PanelMenuPiece extends JPanel{
    
    public abstract boolean isEmpty(); 
    public abstract IPiece getPieceData(IPiece p); 
    public abstract void UpdatePieceData(IPiece p); 
    public abstract void LoadPieceData(IPiece p); 
    public abstract void Clear(); 
}
