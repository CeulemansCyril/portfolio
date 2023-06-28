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
import JeuxDame.Panel.PanelJTextField;
import JeuxDame.PanelMenu.PanelTextFielSizeTerrain;
import JeuxDame.PopUp.PopUpErreur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class MenuStartCreatGame extends JFrame implements ActionListener,WindowListener {

    private ListernerWindowsClose ecoute;
    private PanelJTextField NameTeam1 = new PanelJTextField("Equipe 1", "", "Team1");
    private PanelJTextField NameTeam2 = new PanelJTextField("Equipe 2", "", "Team2");
    private PanelJTextField NameGame = new PanelJTextField("Nom du jeu", "", "Game");
    private PanelTextFielSizeTerrain Terr = new PanelTextFielSizeTerrain(1, 1);
    private PanelButon butOk = new PanelButon(this, "OK", "OK", 20, 50);

    public MenuStartCreatGame(ListernerWindowsClose ecoute) {
        this.ecoute = ecoute;
        initComponent();
        this.setVisible(true);
    }

    private void initComponent() {
        JPanel content = (JPanel) this.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

        NameGame.setAlignmentX(CENTER_ALIGNMENT);
        NameTeam1.setAlignmentX(CENTER_ALIGNMENT);
        NameTeam2.setAlignmentX(CENTER_ALIGNMENT);
        Terr.setAlignmentX(CENTER_ALIGNMENT);
        butOk.setAlignmentX(CENTER_ALIGNMENT);

        content.add(NameGame);
        content.add(NameTeam1);
        content.add(NameTeam2);
        content.add(Terr);
        content.add(butOk);
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Creation");
        this.setSize(250, 250);
        this.addWindowListener(this);
        this.setResizable(false);

    }

    public boolean CheckListGameName(String Name) {
        ArrayList<TemplateGame> listGame = DataControlleur.getInstance().CallListFileGame();
        if (listGame != null) {
            int size = listGame.size();
            for (int i = 0; i < size; i++) {
                if(listGame.get(i).getNameFile().equalsIgnoreCase(Name)) return true;
            }
        }

        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String NameG = NameGame.getText();
        String NameT1 = NameTeam1.getText();
        String NameT2 = NameTeam2.getText();
        int Row = Terr.getRow();
        int Col = Terr.getCol();

        if (!NameG.equalsIgnoreCase("")  && !NameT1.equalsIgnoreCase("") && !NameT2.equalsIgnoreCase("")) {

            if (!CheckListGameName(NameG)) {
                if(!NameT1.equalsIgnoreCase(NameT2)){
                    ArrayList listTeam = new ArrayList();
                    listTeam.add(NameT1);
                    listTeam.add(NameT2);
                    this.setVisible(false);
                    ViewControlleur.getInstance().CallMenuCreatGame(Row, Col, listTeam, ecoute, NameG);
                    this.dispose();
                }else{
                    PopUpErreur err = new PopUpErreur("Erreur les Equipe ne peuve pas avoir le meme nom");
                }
            } else {
                PopUpErreur err = new PopUpErreur("Erreur nom déjà utiliser");
            }
        } else {
            PopUpErreur err = new PopUpErreur("Erreur veuilliez remplit tout les champs");
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ecoute.Closing();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
