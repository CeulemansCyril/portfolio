/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package JeuxDame.Controlleur;

import JeuxDame.Game.Game;
import JeuxDame.Listener.ListenerColorChange;
import JeuxDame.Listener.ListenerColorDeplacement;
import JeuxDame.Listener.ListenerModifPiece;
import JeuxDame.Listener.ListenerNewPiece;
import JeuxDame.Listener.ListenerNewRule;
import JeuxDame.Listener.ListenerNewSizeTerrain;
import JeuxDame.Listener.ListenerRenameGame;
import JeuxDame.Listener.ListernerWindowsClose;
import JeuxDame.Menu.MenuCreatGame;
import JeuxDame.Menu.MenuCreatPiece;
import JeuxDame.Menu.MenuModifPiece;
import JeuxDame.Menu.MenuPersoTerrain;
import JeuxDame.Menu.MenuRenameGame;
import JeuxDame.Menu.MenuRule;
import JeuxDame.Menu.MenuStartCreatGame;
import JeuxDame.Object.ObjGameConfig.GameConfig;
import JeuxDame.Object.ObjGameConfig.GameRule;
import JeuxDame.Object.Piece;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author ceule
 */
public class ViewControlleur {
    
    private ViewControlleur() {
    }
    
    public static ViewControlleur getInstance() {
        return ViewControlleurHolder.INSTANCE;
    }
    
    private static class ViewControlleurHolder {

        private static final ViewControlleur INSTANCE = new ViewControlleur();
    }
    
    public void CallMenuCreatGame(int row,int col,ArrayList<String> listTeam,ListernerWindowsClose close,String GameName){
        MenuCreatGame c = new MenuCreatGame(row,col,listTeam,close,GameName);
    }
    public void CallMenuCreatPiece(int Col,int Row,ListenerNewPiece ecoute,ArrayList listTeam,ArrayList listPiece,Color one,Color two,Color Depl,ListernerWindowsClose close,String GameName){
        MenuCreatPiece p = new MenuCreatPiece(Col, Row, ecoute,listTeam,listPiece,one,two,Depl,close,GameName);
    }
    
    public void CallMenuModifPiece(int Col,int Row,ListenerModifPiece ecoute,Piece p,ArrayList listPiece,ArrayList listTeam,Color one,Color two,Color Depl,ListernerWindowsClose close,String GameName){
        MenuModifPiece m = new MenuModifPiece(p, ecoute, Col, Row, listPiece, listTeam,one,two,Depl,close,GameName);
    }
    
    public void CallMenuColorTerrain(ListenerColorChange ecou,Color one,Color two, Color depl,ListenerColorDeplacement ec,ListernerWindowsClose close,ListenerNewSizeTerrain ecoutTer,int Row,int Col){
        MenuPersoTerrain t = new MenuPersoTerrain(ecou, one, two, depl,ec,close,ecoutTer, Row, Col);
    }
    
    public void CallMenuRename(ListenerRenameGame ecout,String name,ListernerWindowsClose ecouteClose){
        MenuRenameGame r = new MenuRenameGame(ecout, name,ecouteClose);
    }
    
    public void CallMenuRule(ListernerWindowsClose close,ArrayList listPiece,String Team1,String Team2,GameRule rule, ListenerNewRule ecoute){
        MenuRule r = new MenuRule(close, listPiece, Team1,Team2, rule, ecoute);
    }
    
    public void CallModifGame(GameConfig config, ListernerWindowsClose close){
        MenuCreatGame m = new MenuCreatGame(config, close);
    }
    
    public void CallMenuStratCreationGame(ListernerWindowsClose close){
        MenuStartCreatGame s = new MenuStartCreatGame(close);
    }
    
    public void CallGame(GameConfig config,ListernerWindowsClose close){
        Game g = new Game(close, config);
    }
 
}
