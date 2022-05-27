/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendu.Controleur;

import java.util.ArrayList;
import pendu.Game.Pendu;
import pendu.Listener.ListenerOption;
import pendu.Menu.MenuChoixGame;
import pendu.Menu.MenuChoixImg;
import pendu.Menu.MenuChooseList;
import pendu.Menu.MenuList;
import pendu.Menu.MenuOption;

/**
 *
 * @author Utilisateur
 */
public class ViewControleur {

    public ViewControleur() {
    }
    public static ViewControleur getInstance(){
        return ViewHolder.INSTANCE ;
    } 

    private static class ViewHolder{
          private static final ViewControleur INSTANCE = new ViewControleur();
    }
    public void PenduGame(String mot,String Langue,Boolean twoP){
        Pendu pan = new Pendu(mot,Langue,twoP,DataControleur.getInstance().getOption());
    }
    public void CallMenuChoixGame(){
        MenuChoixGame pan = new MenuChoixGame();
    }
    public void CallMenuChooseList(){
        MenuChooseList pan = new MenuChooseList();
    }
    public void CallMenuList(String nom,ArrayList li,String langue,boolean Save){
        MenuList pan = new MenuList(nom, li,langue,Save);
    }
    public void CallOptionMenu(ListenerOption ecoute){
        MenuOption pan = new MenuOption(ecoute);
    }
    public void CallImgMenu(ListenerOption ecoute){
        MenuChoixImg pan = new MenuChoixImg(ecoute);
    }
    
}
