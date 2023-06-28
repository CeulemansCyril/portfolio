/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Object.ObjGameConfig;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author ceule
 */
public class GameConfig {
    private ArrayList<String> ListPieceName ;
    private String GameName;
    private GameRule Rule;
    private String Team1;
    private String Team2;
    private Color One;
    private Color Two;
    private Color Depl;
    private int Row;
    private int Col;

    public GameConfig(ArrayList<String> ListPieceName, String GameName, GameRule Rule, String Team1, String Team2, Color One, Color Two, Color Depl,int Row,int Col) {
        setDepl(Depl);
        setGameName(GameName);
        setListPieceName(ListPieceName);
        setOne(One);
        setRule(Rule);
        setTeam1(Team1);
        setTeam1(Team1);
        setTeam2(Team2);
        setTwo(Two);
        setCol(Col);
        setRow(Row);
    }

    public GameConfig() {
    }
    
    

    public ArrayList<String> getListPieceName() {
        return ListPieceName;
    }

    public void setListPieceName(ArrayList<String> ListPieceName) {
        this.ListPieceName = ListPieceName;
    }

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String GameName) {
        this.GameName = GameName;
    }

    public GameRule getRule() {
        return Rule;
    }

    public void setRule(GameRule Rule) {
        this.Rule = Rule;
    }

    public String getTeam1() {
        return Team1;
    }

    public void setTeam1(String Team1) {
        this.Team1 = Team1;
    }

    public String getTeam2() {
        return Team2;
    }

    public void setTeam2(String Team2) {
        this.Team2 = Team2;
    }

    public Color getOne() {
        return One;
    }

    public void setOne(Color One) {
        this.One = One;
    }

    public Color getTwo() {
        return Two;
    }

    public void setTwo(Color Two) {
        this.Two = Two;
    }

    public Color getDepl() {
        return Depl;
    }

    public void setDepl(Color Depl) {
        this.Depl = Depl;
    }

    public int getRow() {
        return Row;
    }

    public void setRow(int Row) {
        this.Row = Row;
    }

    public int getCol() {
        return Col;
    }

    public void setCol(int Col) {
        this.Col = Col;
    }
    
    
    
}
