/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Menu;

import JeuxDame.Listener.ListenerNewRule;
import JeuxDame.Listener.ListernerWindowsClose;
import JeuxDame.Object.ObjGameConfig.GameRule;
import JeuxDame.Object.ObjGameConfig.KillOne;
import JeuxDame.Object.Piece;
import JeuxDame.Panel.PanelButon;
import JeuxDame.Panel.PanelCheckBoxWhitComboBox;
import JeuxDame.Panel.PanelCheckBoxWhitGroup;
import JeuxDame.Panel.PanelComboBox;
import JeuxDame.PopUp.PopUpErreur;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class MenuRule extends JFrame implements WindowListener, ActionListener {

    private PanelComboBox comboPieceTeam1;
    private PanelComboBox comboPieceTeam2;

    private PanelCheckBoxWhitComboBox CheckKillAllPiece = new PanelCheckBoxWhitComboBox("Un joueur na plus de piece", "KillAll", true);
    private PanelCheckBoxWhitComboBox CheckKillOnePiece = new PanelCheckBoxWhitComboBox("Un joueur perte une piece précise", "KillOne", false);
   
    private PanelCheckBoxWhitGroup CheckPrise = new PanelCheckBoxWhitGroup("Prise de piece direct (jeux d'echec)", "PriseDirect", true);
  private PanelCheckBoxWhitGroup CheckContinue = new PanelCheckBoxWhitGroup("Oui", "Continue", true);
    
    private PanelButon But = new PanelButon(this, "Sauvegarde", "Save", 20, 50);

    private ListernerWindowsClose ecouteClose;
    private ListenerNewRule ecoute;
    
    private JPanel contentPan = (JPanel) this.getContentPane();

    public MenuRule(ListernerWindowsClose ecouteClose, ArrayList<Piece> listeP, String Team1,String Team2, GameRule rule, ListenerNewRule ecoute) {
        this.ecoute = ecoute;
        this.ecouteClose = ecouteClose;
        initComponent(listeP, Team1,Team2);
        //Load rule
        if (rule.getKillone() != null) {
            CheckKillAllPiece.setSelect(rule.isKillAll());
            CheckKillOnePiece.setSelect(rule.getKillone().isSelect());
            if (rule.isPriseDirect()) {
                CheckPrise.setSelect("PriseDirect");
            } else {
                CheckPrise.setSelect("PriseInDirect");
            }
            if(rule.isContinue()){
                CheckContinue.setSelect("Continue");
            }else{
                 CheckContinue.setSelect("NonContinue");
            }
        }
        
        this.setVisible(true);
    }

    private void initComponent(ArrayList<Piece> listeP, String Team1,String Team2) {

        ArrayList ListTeam1 = new ArrayList();
        ArrayList ListTeam2 = new ArrayList();

        int sizeP = listeP.size();

        for (int i = 0; i < sizeP; i++) {
            String team=listeP.get(i).getTeam();
            if (team.equalsIgnoreCase(Team1)) {
                ListTeam1.add(listeP.get(i).getName());
            } else {
                ListTeam2.add(listeP.get(i).getName());
            }

        }

        comboPieceTeam1 = new PanelComboBox(Team1, ListTeam1, "");
        comboPieceTeam2 = new PanelComboBox(Team2, ListTeam2, "");

        JPanel PanelComboTeam = new JPanel();
        PanelComboTeam.add(comboPieceTeam1);
        PanelComboTeam.add(comboPieceTeam2);

        //Panel Kill Piece-----------------------------------------------------------------------------------------------
        JPanel PanelKillOne = new JPanel();
        PanelKillOne.setLayout(new BoxLayout(PanelKillOne, BoxLayout.PAGE_AXIS));
        CheckKillOnePiece.setAlignmentX(CENTER_ALIGNMENT);
        PanelComboTeam.setAlignmentX(CENTER_ALIGNMENT);
        PanelKillOne.add(CheckKillOnePiece);
        PanelKillOne.add(PanelComboTeam);

        JPanel PanelLose = new JPanel();
        PanelLose.setLayout(new BoxLayout(PanelLose, BoxLayout.PAGE_AXIS));
        CheckKillAllPiece.setAlignmentX(CENTER_ALIGNMENT);
        PanelKillOne.setAlignmentX(CENTER_ALIGNMENT);

        PanelLose.add(CheckKillAllPiece);
        PanelLose.add(PanelKillOne);

        PanelLose.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Defaite"));

        //Panel Prise-------------------------------------------------------------------------------------------------
        CheckPrise.addCheckBox("Prise de piece indirect (jeux de dame)", "PriseInDirect");

        JPanel PanelPrise = new JPanel();
        PanelPrise.setLayout(new BoxLayout(PanelPrise, BoxLayout.PAGE_AXIS));

        PanelPrise.add(CheckPrise);

        PanelPrise.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Prise"));
        //Panel Continue-------------------------------------------------------------------------------------------------
        CheckContinue.addCheckBox("Non", "NonContinue");

        JPanel PanelContinue = new JPanel();
        PanelContinue.setLayout(new BoxLayout(PanelContinue, BoxLayout.PAGE_AXIS));

        PanelContinue.add(CheckContinue);

        PanelContinue.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Apres une prise le joueur continue"));
        //------------------------------------------------------------------------------------------------------------
        contentPan.setLayout(new BoxLayout(contentPan, BoxLayout.PAGE_AXIS));
        PanelLose.setAlignmentX(CENTER_ALIGNMENT);
        PanelPrise.setAlignmentX(CENTER_ALIGNMENT);
        PanelContinue.setAlignmentX(CENTER_ALIGNMENT);
        But.setAlignmentX(CENTER_ALIGNMENT);

        contentPan.add(PanelLose);
        contentPan.add(PanelPrise);
        contentPan.add(PanelContinue);
        contentPan.add(But);

        this.setTitle("Regle");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500, 400);
        this.addWindowListener(this);

    }

    private boolean isEmpty() {
        if (!(CheckKillAllPiece.isSelected()) && !(CheckKillOnePiece.isSelected())) {
            PopUpErreur err = new PopUpErreur("Erreur vous devez choisir minimun une defaite");
            return true;
        }

        return false;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
        if(isEmpty()){
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }else{
            ecouteClose.Closing();
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        
        
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "Save":
                if (!isEmpty()) {
                    boolean flag = true;
                    GameRule rule = new GameRule();
                    ArrayList<Checkbox> Check = CheckPrise.getAllBox();
                    rule.setKillAll(CheckKillAllPiece.isCheckedCheckBox("KillAll"));
                    if (CheckKillOnePiece.isCheckedCheckBox("KillOne")) {
                        if(comboPieceTeam1.getSelectItem()!=null && comboPieceTeam2!=null)rule.setKillone(new KillOne(CheckKillOnePiece.isCheckedCheckBox("KillOne"), (String) comboPieceTeam1.getSelectItem(), (String) comboPieceTeam2.getSelectItem()));
                        else{
                            PopUpErreur err = new PopUpErreur("Erreu veullier selectionner une piece pour chaque equipe");
                            flag=false;
                        }
                    } else {
                        rule.setKillone(new KillOne(CheckKillOnePiece.isCheckedCheckBox("KillOne")));
                    }
                    rule.setPriseDirect(Check.get(0).getState());
                     ArrayList<Checkbox> ListCheckContinue = CheckContinue.getAllBox();
                    rule.setContinue(ListCheckContinue.get(0).getState());
                    ecoute.NewRule(rule);
                    if(flag){
                        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        ecouteClose.Closing();
                        this.dispose();
                    }
                }
                break;

        }
    }

}
