/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Menu;

import JeuxDame.Controlleur.DataControlleur;
import JeuxDame.Controlleur.ViewControlleur;
import JeuxDame.Listener.ListenerColorChange;
import JeuxDame.Listener.ListenerColorDeplacement;
import JeuxDame.Listener.ListenerModifPiece;
import JeuxDame.Listener.ListenerNewPiece;
import JeuxDame.Listener.ListenerNewRule;
import JeuxDame.Listener.ListenerNewSizeTerrain;
import JeuxDame.Listener.ListenerRenameGame;
import JeuxDame.Listener.ListernerWindowsClose;
import JeuxDame.Object.ObjGameConfig.GameConfig;
import JeuxDame.Object.ObjGameConfig.GameRule;
import JeuxDame.Object.ObjGameConfig.KillOne;
import JeuxDame.Object.ObjPiece.Placement;
import JeuxDame.Object.ObjTemplate.TemplatePiece;
import JeuxDame.Object.Piece;
import JeuxDame.Panel.PanelButon;
import JeuxDame.Panel.PanelComboBox;
import JeuxDame.Panel.PanelComboBoxTemplate.PanelComboBoxTemplate;
import JeuxDame.Panel.PanelDamier;
import JeuxDame.PopUp.PopUpErreur;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class MenuCreatGame extends JFrame implements ActionListener, ListenerNewPiece, ListenerModifPiece, ListenerColorChange, ListenerColorDeplacement, ListernerWindowsClose, WindowListener, ListenerNewRule, ListenerNewSizeTerrain, ListenerRenameGame {

    private PanelDamier Dam;
    private PanelButon butCreatPiece = new PanelButon(this, "Cree une piece", "New", 50, 100);
    private PanelButon butModifPiece = new PanelButon(this, "Modifier une piece", "Modif", 50, 100);
    private PanelButon butDeletePiece = new PanelButon(this, "Supprimer une piece", "Del", 50, 100);
    private PanelButon butAddTemplatePiece = new PanelButon(this, "Ajouter un Template d'une piece", "Temp", 50, 100);
    private PanelButon butModifTerrain = new PanelButon(this, "Modifier le terrain", "Terr", 50, 100);
    private PanelButon butRule = new PanelButon(this, "Regle du jeu ", "Rule", 50, 100);
    private PanelButon butSave = new PanelButon(this, "Sauvegarder", "Save", 50, 100);
    private PanelButon butRen = new PanelButon(this, "Renommer", "Rename", 50, 100);

    private ArrayList<String> listPieceName = new ArrayList();
    private ArrayList<Piece> listePiece = new ArrayList();
    private ArrayList listTeam = new ArrayList();
    private int Row;
    private int Col;
    private Color colorOne = Color.BLACK;
    private Color colorTwo = Color.WHITE;
    private Color colorDepl = Color.BLUE;
    private ListernerWindowsClose ecouteClose;
    private GameRule rule = new GameRule(new KillOne(false), true, false,true);
    private String Team1 = new String();
    private String Team2 = new String();
    private String GameName;

    public MenuCreatGame(int Row, int Col, ArrayList<String> listTeam, ListernerWindowsClose close, String GameName) {
        Dam = new PanelDamier(Col, Row, colorOne, colorTwo, colorDepl);
        this.GameName = GameName;
        this.setTitle(GameName);
        this.Row = Row;
        this.Col = Col;
        this.listTeam = listTeam;
        this.ecouteClose = close;
        this.Team1 = (String) listTeam.get(0);
        this.Team2 = (String) listTeam.get(1);
        initComponent();
        this.setVisible(true);
    }

    public MenuCreatGame(GameConfig config, ListernerWindowsClose close) {       
        this.GameName = config.getGameName();
        this.setTitle(GameName);
        this.Row = config.getRow();
        this.Col = config.getCol();
        this.ecouteClose = close;
        this.Team1 = config.getTeam1();
        this.Team2 = config.getTeam2();
        listTeam.add(Team1);
        listTeam.add(Team2);
        this.rule = config.getRule();
        this.colorDepl=config.getDepl();
        this.colorOne = config.getOne();
        this.colorTwo= config.getTwo();
        this.listPieceName=config.getListPieceName();
        Dam = new PanelDamier(Col, Row, colorOne, colorTwo, colorDepl);
        loadPiece();
        initComponent();       
        this.setVisible(true);
    }

    private void initComponent() {
        this.setLayout(new GridLayout(1, 2));
        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));

        butCreatPiece.setAlignmentX(CENTER_ALIGNMENT);
        butModifPiece.setAlignmentX(CENTER_ALIGNMENT);
        butDeletePiece.setAlignmentX(CENTER_ALIGNMENT);
        butAddTemplatePiece.setAlignmentX(CENTER_ALIGNMENT);
        butModifTerrain.setAlignmentX(CENTER_ALIGNMENT);
        butRen.setAlignmentX(CENTER_ALIGNMENT);
        butSave.setAlignmentX(CENTER_ALIGNMENT);

        pan.add(butCreatPiece);
        pan.add(butModifPiece);
        pan.add(butDeletePiece);
        pan.add(butAddTemplatePiece);
        pan.add(butModifTerrain);
        pan.add(butRule);
        pan.add(butRen);
        pan.add(butSave);

        this.addWindowListener(this);

        this.add(Dam);
        this.add(pan);
        this.setSize(500, 350);
    }
    
    private void loadPiece(){
        int size =  listPieceName.size();
        
        for (int i = 0; i < size; i++) {
            Piece p = DataControlleur.getInstance().ReadPiece(listPieceName.get(i)+".json");
            listePiece.add(p);
            if(!p.getAllPlacement().isEmpty())Dam.PlaceOnePieceInMultyTuile(p, 500, 350);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "New":
                Enable(false);
                ViewControlleur.getInstance().CallMenuCreatPiece(Col, Row, this, listTeam, listePiece, colorOne, colorTwo, colorDepl, this, GameName);
                break;
            case "Modif":
                Enable(false);
                if ((listPieceName != null && listePiece != null) && (!listPieceName.isEmpty() && !listePiece.isEmpty())) {
                    PanelComboBox box = new PanelComboBox("Choisissez une piece", listPieceName, (String) listPieceName.get(0));
                    int choix = JOptionPane.showConfirmDialog(null, box, "Modifier une piece", JOptionPane.OK_CANCEL_OPTION);
                    if (choix == JOptionPane.YES_OPTION) {
                        String nameP = (String) box.getSelectItem();
                        Piece p = new Piece();
                        boolean flag = true;
                        int i = 0;
                        int Size = listePiece.size();
                        while (flag && i <= Size) {
                            String name = listePiece.get(i).getName()+"-"+GameName;
                            if (name.equalsIgnoreCase(nameP)) {
                                flag = false;
                                p = (Piece) listePiece.get(i);
                            }
                            i++;
                        }
                        ViewControlleur.getInstance().CallMenuModifPiece(Col, Row, this, p, listePiece, listTeam, colorOne, colorTwo, colorDepl, this, GameName);
                    }else Enable(true);
                } else {
                    PopUpErreur err = new PopUpErreur("Erreur aucune piece disponible pour être modiflier");
                    Enable(true);
                }
                break;
            //suppression
            case "Del":
                Enable(false);
                if ((listPieceName != null && listePiece != null) && (!listPieceName.isEmpty() && !listePiece.isEmpty())) {
                    PanelComboBox box = new PanelComboBox("Choisissez une piece", listPieceName, (String) listPieceName.get(0));
                    int choix = JOptionPane.showConfirmDialog(null, box, "Supprimer une piece", JOptionPane.OK_CANCEL_OPTION);
                    if (choix == JOptionPane.YES_OPTION) {
                        int validation = JOptionPane.showConfirmDialog(null, "Confirmer la suppression", "Validation", JOptionPane.OK_CANCEL_OPTION);
                        if (validation == JOptionPane.YES_OPTION) {
                            String nameP = (String) box.getSelectItem();
                            //suppression de la liste des piece
                            boolean flag = true;
                            int i = 0;
                            int Size = listePiece.size();
                            Piece p = new Piece();
                            while (flag && i <= Size) {
                                String name = listePiece.get(i).getName();
                                if (name.equalsIgnoreCase(nameP)) {
                                    flag = false;
                                    p = (Piece) listePiece.get(i);
                                    listePiece.remove(i);
                                }
                                i++;
                            }
                            listPieceName.remove(nameP);
                            Dam.RemoveOnePieceInAllTuile(p);
                        }
                    }else Enable(true);
                } else {
                    PopUpErreur err = new PopUpErreur("Erreur aucune piece disponible pour être suprimer");
                    Enable(true);
                }
                break;
            //add one template
            case "Temp":
                ArrayList<TemplatePiece> list = DataControlleur.getInstance().CallListFileTemplate();

                int sizeName = listPieceName.size();
                for (int i = 0; i < sizeName; i++) {
                    String name = listPieceName.get(i);
                    int SizeList = list.size();
                    int j=0;
                    while((!list.isEmpty())&&(j<SizeList)) {
                        if (name.equalsIgnoreCase(list.get(j).getNameFile())) {
                            list.remove(j);
                            SizeList--;
                            j--;        
                        }
                        j++;
                    }
                }

                if (!list.isEmpty()) {
                    PanelComboBoxTemplate box = new PanelComboBoxTemplate("Choisissez un template", list, "");
                    int choix = JOptionPane.showConfirmDialog(null, box, "Choisissez un template", JOptionPane.OK_CANCEL_OPTION);
                    if (choix == JOptionPane.YES_OPTION) {
                        TemplatePiece nameP = (TemplatePiece) box.getSelectItem();
                        Piece p = DataControlleur.getInstance().ReadPiece(nameP.getPath());
                        if (p != null) {
                            if (Dam.CheckAllPlacement(p)) {
                                listPieceName.add(nameP.getNameFile());

                                if (p.getAllPlacement() != null && !p.getAllPlacement().isEmpty()) {
                                    RePlacementPieceInTerrain();
                                }
                                Enable(false);
                                ViewControlleur.getInstance().CallMenuModifPiece(Col, Row, this, p, listePiece, listTeam, colorOne, colorTwo, colorDepl, this, GameName);
                                
                            } else {
                                PopUpErreur err = new PopUpErreur("Erreur tout les placement du template son déjà occuper");
                                ViewControlleur.getInstance().CallMenuModifPiece(Col, Row, this, p, listePiece, listTeam, colorOne, colorTwo, colorDepl, this, GameName);
                            }
                        } else {
                            PopUpErreur err = new PopUpErreur("Erreur template corrompu");
                            DataControlleur.getInstance().RemoveTemplatePiece(nameP);
                        }

                    }else Enable(true);
                } else {
                    PopUpErreur err = new PopUpErreur("Erreur aucun template disponible");
                }
                break;
            //Terrain
            case "Terr":
                Enable(false);
                ViewControlleur.getInstance().CallMenuColorTerrain(this, colorOne, colorTwo, colorDepl, this, this, this, Row, Col);
                break;
            //Rule
            case "Rule":
                Enable(false);
                ViewControlleur.getInstance().CallMenuRule(this, listePiece, Team1, Team2, rule, this);
                break;
            case "Rename":
                Enable(false);
                ViewControlleur.getInstance().CallMenuRename(this, GameName, this);
                break;
            case "Save":
                int Size = listePiece.size();
                for (int i = 0; i < Size; i++) {
                    if(!listePiece.get(i).getAllPlacement().isEmpty())CheckPlacementTerrainPiece(listePiece.get(i), i);
                }
                GameConfig config = new GameConfig(listPieceName, GameName, rule, Team1, Team2, colorOne, colorTwo, colorDepl, Row, Col);
                DataControlleur.getInstance().SaveGame(config);
                JOptionPane.showConfirmDialog(null, "jeux sauvegarder", "sauvegarde", JOptionPane.OK_OPTION);
                ecouteClose.Closing();
                this.dispose();
                break;
        }

    }
    //permet d'activer/desactiver les bouton
    private void Enable(boolean Flag) {
        butCreatPiece.SetButEnable(Flag);
        butModifPiece.SetButEnable(Flag);
        butDeletePiece.SetButEnable(Flag);
        butAddTemplatePiece.SetButEnable(Flag);
        butModifTerrain.SetButEnable(Flag);
        butRule.SetButEnable(Flag);
        butSave.SetButEnable(Flag);
        butRen.SetButEnable(Flag);
    }
    //peremet defacer les élement parasite
    private void CheckArrayListePiece() {
        int size = listePiece.size();

        for (int i = 0; i < size; i++) {
            try {
                Piece p = listePiece.get(i);
            } catch (ClassCastException e) {
                listePiece.remove(i);
            }
        }
    }
    //verrifie les placement des piece quand le terrain est modifier
    public void CheckPlacementTerrainPiece(Piece p, int index) {

        ArrayList<Placement> placement = p.getAllPlacement();
        int sizePlacement = placement.size();
        boolean flag = false;
        for (int j = 0; j < sizePlacement; j++) {
            if (((placement.get(j).getPlacementY() > Row) || (placement.get(j).getPlacementX() > Col))) {
                placement.remove(j);
                flag = true;
            }
        }
        if (placement.isEmpty()) {
            PopUpErreur err = new PopUpErreur("Erreur le terrain à changer et votre piece à perdu tout ses placement");
            ViewControlleur.getInstance().CallMenuModifPiece(Col, Row, this, p, listePiece, listTeam, colorOne, colorTwo, colorDepl, ecouteClose, GameName);
        } else {
            p.setPlacementPiece(placement);
            DataControlleur.getInstance().SavePiece(p, GameName);
            listePiece.add(index, p);
        }

    }
    //replace les piece sur le terrain
    public void RePlacementPieceInTerrain() {
        int size = listePiece.size();

        for (int i = 0; i < size; i++) {

            Piece p = new Piece();
            p = listePiece.get(i);
            ArrayList<Placement> placement = p.getAllPlacement();
            int sizePlacement = placement.size();
            boolean flag = false;
            for (int j = 0; j < sizePlacement; j++) {
                if (((placement.get(j).getPlacementY() > Row) && (placement.get(j).getPlacementX() > Col))) {
                    placement.remove(j);
                    sizePlacement = placement.size();
                    flag = true;
                }
            }
            if (flag) {
                p.setPlacementPiece(placement);
            }
            if (!placement.isEmpty()) {
                Dam.PlaceOnePieceInMultyTuile(p, this.getWidth(), this.getHeight());
            }
        }
    }

    public void PanelUpdateUi() {
        JPanel pan = (JPanel) this.getContentPane();
        pan.updateUI();
    }

    @Override
    public void NewPiece(Piece name) {
        listPieceName.add(name.getName()+"-"+GameName);
        Piece p = name;
        listePiece.add(p);
        CheckArrayListePiece();
        ArrayList<Placement> list = p.getAllPlacement();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Dam.PlaceOnePiece(p, list.get(i).getPlacementY(), list.get(i).getPlacementX(), this.getWidth(), this.getHeight());
        }

    }

    @Override
    public void ModifPiece(ArrayList<Piece> list) {
        Piece OldPiece = list.get(0);
        Piece NewPiece = list.get(1);
        
        int size = listePiece.size();
        


        listePiece.remove(OldPiece);
        listePiece.add(NewPiece);

        listPieceName.remove(OldPiece.getName()+"-"+GameName);
        listPieceName.add(NewPiece.getName()+"-"+GameName);

        Dam.RemoveOnePieceInAllTuile(OldPiece);
        Dam.PlaceOnePieceInMultyTuile(NewPiece, this.getWidth(), this.getHeight());

    }

    @Override
    public void ColorChange(Color tuileColor, String tuileCible) {
        switch (tuileCible) {
            case "One":
                colorOne = tuileColor;
                break;
            case "Two":
                colorTwo = tuileColor;
                break;
            case "Depl":
                colorDepl = tuileColor;
                break;
        }
        Dam.ReColor(colorOne, colorTwo, colorDepl);
        Dam.ColorRow(Row / 2);
    }

    @Override
    public void NewColorDepl(Color depl) {
        colorDepl = depl;
        Dam.ColorRow(Row / 2);
    }

    @Override
    public void ClearColor() {
        Dam.ClearColor();
    }

    @Override
    public void Closing() {
        Enable(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ecouteClose.Closing();
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
    public void NewRule(GameRule rule) {
        this.rule = rule;
    }

    @Override
    public void NewSizeTerrain(int SizeRow, int SizeCol) {
        this.Row = SizeRow;
        this.Col = SizeCol;
        this.remove(Dam);
        Dam = new PanelDamier(SizeCol, SizeRow, colorOne, colorTwo, colorDepl);
        this.add(Dam, 0);
        //if there are pieces on the ground, we replace them
        if (!listePiece.isEmpty()) {
            RePlacementPieceInTerrain();
        }
        Dam.ColorRow(Row / 2);
        PanelUpdateUi();
    }

    @Override
    public void RenameGame(String name) {
        this.GameName = name;
        this.setTitle(name);
    }

}
