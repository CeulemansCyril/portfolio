/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Object.ObjGameConfig;

/**
 *
 * @author ceule
 */
public class KillOne {
    private boolean isSelect;
    private String  PieceNameTeam1;
    private String  PieceNameTeam2;

    public KillOne(boolean isSelect, String PieceNameTeam1, String PieceNameTeam2) {
        setIsSelect(isSelect);
        setPieceNameTeam1(PieceNameTeam1);
        setPieceNameTeam2(PieceNameTeam2);
    }

    public KillOne() {
    }

    public KillOne(boolean isSelect) {
         setIsSelect(isSelect);
    }
    
    

    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public String getPieceNameTeam1() {
        return PieceNameTeam1;
    }

    public void setPieceNameTeam1(String PieceNameTeam1) {
        this.PieceNameTeam1 = PieceNameTeam1;
    }

    public String getPieceNameTeam2() {
        return PieceNameTeam2;
    }

    public void setPieceNameTeam2(String PieceNameTeam2) {
        this.PieceNameTeam2 = PieceNameTeam2;
    }
    
    
}
