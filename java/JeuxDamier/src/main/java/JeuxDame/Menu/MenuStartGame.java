/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Menu;

import JeuxDame.Controlleur.DataControlleur;
import JeuxDame.Controlleur.ViewControlleur;
import JeuxDame.Listener.ListernerWindowsClose;
import JeuxDame.Object.ObjTemplate.TemplateGame;
import JeuxDame.Panel.PanelButon;
import JeuxDame.Panel.PanelComboBoxTemplate.PanelComboBoxTemplate;
import JeuxDame.PopUp.PopUpErreur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class MenuStartGame extends JFrame implements ActionListener, ListernerWindowsClose {

    private JPanel pan = new JPanel();
    private PanelButon butGame = new PanelButon(this, "Jeu", "Game", 20, 50);
    private PanelButon butCreatGame = new PanelButon(this, "Cree un nouveaux jeu", "Create", 20, 50);
    private PanelButon butModifGame = new PanelButon(this, "Modifier un jeu", "Modif", 20, 50);
    private PanelButon butDeletGame = new PanelButon(this, "Supprimer un jeu", "Del", 20, 50);
    private PanelButon butCompresseGame = new PanelButon(this, "Compresser un jeu", "Comp", 20, 50);

    public MenuStartGame() {
        initComponent();
        this.setVisible(true);
    }

    private void initComponent() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
        this.setSize(250, 250);

        butCreatGame.setAlignmentX(CENTER_ALIGNMENT);
        butGame.setAlignmentX(CENTER_ALIGNMENT);
        butModifGame.setAlignmentX(CENTER_ALIGNMENT);
        butDeletGame.setAlignmentX(CENTER_ALIGNMENT);
        butCompresseGame.setAlignmentX(CENTER_ALIGNMENT);

        pan.add(butGame);
        pan.add(butCreatGame);
        pan.add(butModifGame);
        pan.add(butDeletGame);
        pan.add(butCompresseGame);

        this.add(pan);
        this.setTitle("Home");
    }

    private void Enable(boolean flag) {
        butGame.SetButEnable(flag);
        butCreatGame.SetButEnable(flag);
        butModifGame.SetButEnable(flag);
        butDeletGame.SetButEnable(flag);
        butCompresseGame.SetButEnable(flag);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag =true;
        String action = e.getActionCommand();
        switch (action) {
            case "Game":
                ArrayList listGamePlay = DataControlleur.getInstance().CallListFileGame();
                if (listGamePlay != null && !listGamePlay.isEmpty()) {
                    PanelComboBoxTemplate combo = new PanelComboBoxTemplate("Choisissez un jeux à modifier", listGamePlay, action);
                    int choix = JOptionPane.showConfirmDialog(null, combo, "Choisissez un template", JOptionPane.OK_CANCEL_OPTION);
                    if (choix == JOptionPane.YES_OPTION) {
                        TemplateGame game = (TemplateGame) combo.getSelectItem();
                        ViewControlleur.getInstance().CallGame(DataControlleur.getInstance().ReadGame(game.getNameFile()), this);
                    }else   flag = false;
                } else {
                    PopUpErreur err = new PopUpErreur("Erreur aucun jeux disponible");
                    flag = false;
                }
                break;
            case "Create":
                ViewControlleur.getInstance().CallMenuStratCreationGame(this);
                break;
            case "Modif":
                ArrayList listGame = DataControlleur.getInstance().CallListFileGame();
                if (listGame != null && !listGame.isEmpty()) {
                    PanelComboBoxTemplate combo = new PanelComboBoxTemplate("Choisissez un jeux à modifier", listGame, action);
                    int choix = JOptionPane.showConfirmDialog(null, combo, "Choisissez un template", JOptionPane.OK_CANCEL_OPTION);
                    if (choix == JOptionPane.YES_OPTION) {
                        TemplateGame game = (TemplateGame) combo.getSelectItem();
                        ViewControlleur.getInstance().CallModifGame(DataControlleur.getInstance().ReadGame(game.getNameFile()), this);
                    }
                } else {
                    PopUpErreur err = new PopUpErreur("Erreur aucun jeux disponible");
                    flag = false;
                }
                break;
            case "Del":
                ArrayList listDelGame = DataControlleur.getInstance().CallListFileGame();
                if (listDelGame != null && !listDelGame.isEmpty()) {
                    PanelComboBoxTemplate combo = new PanelComboBoxTemplate("Choisissez un jeux à Suprimer", listDelGame, action);
                    int choix = JOptionPane.showConfirmDialog(null, combo, "Choisissez un template", JOptionPane.OK_CANCEL_OPTION);
                    if (choix == JOptionPane.YES_OPTION) {
                        int choix2 = JOptionPane.showConfirmDialog(null, "Demande de confirmation", "confirmation", JOptionPane.OK_CANCEL_OPTION);
                        if (choix2 == JOptionPane.YES_OPTION) {
                            TemplateGame game = (TemplateGame) combo.getSelectItem();
                            DataControlleur.getInstance().RemoveTemplateGame(game);
                            PopUpErreur err = new PopUpErreur("Jeux supprimer");
                        }
                    }else   flag = false;
                } else {
                    PopUpErreur err = new PopUpErreur("Erreur aucun jeux disponible");
                    flag= false;
                }
                break;
            case "Comp":
                ArrayList listGameComp = DataControlleur.getInstance().CallListFileGame();
                if (listGameComp != null && !listGameComp.isEmpty()) {
                    PanelComboBoxTemplate combo = new PanelComboBoxTemplate("Choisissez un jeux à compresser (.RAR)", listGameComp, action);
                    int choix = JOptionPane.showConfirmDialog(null, combo, "Choisissez un template", JOptionPane.OK_CANCEL_OPTION);
                    if (choix == JOptionPane.YES_OPTION) {
                        TemplateGame game = (TemplateGame) combo.getSelectItem();
                        boolean flagComp = DataControlleur.getInstance().CompressGame(game.getNameFile());
                        if(flagComp){
                            PopUpErreur err = new PopUpErreur("Jeux Compresser");
                        }else{
                            PopUpErreur err = new PopUpErreur("Erreur echec de la compression");
                        }
                    }
                } else {
                    PopUpErreur err = new PopUpErreur("Erreur aucun jeux disponible");
                }
                flag = false;
                break;
        }
        if(flag) Enable(false);
    }

    public static void main(String[] args) {
        new MenuStartGame();
    }

    @Override
    public void Closing() {
        Enable(true);
    }
}
