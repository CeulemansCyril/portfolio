/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeux.Controleur;

import BlackJack.BlackJack;
import Dame.Dames2;
import OXO.oxo;
import Pendu.Pendu;

/**
 *
 * @author Utilisateur
 */
public class GuiControleur {
    
    private GuiControleur() {
    }
    
    public static GuiControleur getInstance() {
        return GuiControleurHolder.INSTANCE;
    }
    
    private static class GuiControleurHolder {

        private static final GuiControleur INSTANCE = new GuiControleur();
    }
    public void CallPendu(String Mot){
        Pendu CallPendu = new Pendu(Mot);
        CallPendu.setVisible(true);
    }
    public void CallOXO(String id){
        oxo Call = new oxo(id);
        Call.setVisible(true);
    }
    public void CallBlackJack(){
        BlackJack call = new BlackJack();
        call.setVisible(true);
    }
    public void CallDames(){
        Dames2 call = new Dames2();
    }
        
}
