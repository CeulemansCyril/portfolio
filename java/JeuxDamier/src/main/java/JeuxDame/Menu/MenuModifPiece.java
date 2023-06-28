/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Menu;

import JeuxDame.Controlleur.DataControlleur;
import JeuxDame.Listener.ListenerModifPiece;
import JeuxDame.Listener.ListenerResize;
import JeuxDame.Listener.ListernerWindowsClose;
import JeuxDame.Object.ObjPiece.Evolution;
import JeuxDame.Object.PanelMenuPiece;
import JeuxDame.Object.Piece;
import JeuxDame.Panel.PanelButon;
import JeuxDame.PanelMenu.CreatGame.PanelCreationPiece;
import JeuxDame.PanelMenu.CreatGame.PanelDeplacementPiece;
import JeuxDame.PanelMenu.CreatGame.PanelEvolutionPlacementPiece;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author ceule
 */
public class MenuModifPiece extends JFrame implements ChangeListener, ActionListener, ComponentListener, WindowListener {

    private PanelCreationPiece PanelCreationP;
    private PanelEvolutionPlacementPiece PanelEvolPlace;
    private PanelDeplacementPiece PanelDeplace;
    private PanelButon panSave;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private ArrayList<PanelMenuPiece> ListPan = new ArrayList();
    private ArrayList<ListenerResize> ListeCallResize = new ArrayList<>();
    private int CurrentPage = 0;
    private int MaxPage;
    private Piece NewPiece = new Piece(null, "", 0, "", new Evolution(), new ArrayList(), new ArrayList());
    private Piece OldPiece;
    private ListenerModifPiece ecouter;
    private ListernerWindowsClose ecouteClose;
    private String NameGame;

    public MenuModifPiece(Piece p, ListenerModifPiece ecout, int col, int row, ArrayList ListePiece, ArrayList ListTeam, Color one, Color two, Color Depl, ListernerWindowsClose close, String gameName) {
        this.OldPiece = p;
        this.ecouter = ecout;
        this.ecouteClose = close;
        this.NameGame = gameName;
        PanelEvolPlace = new PanelEvolutionPlacementPiece(col, row, ListePiece, (String) ListTeam.get(0), OldPiece, 500, 300, one, two, Depl);
        PanelCreationP = new PanelCreationPiece(ListTeam, ListePiece);
        PanelDeplace = new PanelDeplacementPiece(col, row, OldPiece, 500, 300, one, two, Depl);

        initComponent();
        this.setVisible(true);
    }

    private void initComponent() {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(1000, 700);

        panSave = new PanelButon(this, "Sauvegarder", "Save", 40, 100);

        tabbedPane.setSize(100, 100);

        tabbedPane.addTab("Piece", PanelCreationP);
        tabbedPane.addTab("Placement", PanelEvolPlace);
        tabbedPane.addTab("Deplacement", PanelDeplace);
        tabbedPane.addTab("Sauvegarder", panSave);

        ListPan.add(PanelCreationP);
        ListPan.add(PanelEvolPlace);
        ListPan.add(PanelDeplace);

        PanelCreationP.LoadPieceData(OldPiece);
        PanelDeplace.LoadPieceData(OldPiece);
        PanelEvolPlace.LoadPieceData(OldPiece);

        ListeCallResize.add(PanelEvolPlace);
        ListeCallResize.add(PanelDeplace);

        tabbedPane.addChangeListener(this);

        MaxPage = tabbedPane.getTabCount() - 1;

        this.add(tabbedPane);
        this.addWindowListener(this);
        this.setTitle("Modification");
    }

    //verifie si tout les panel sont completer 
    public boolean CheckAllPan() {

        for (int i = 0; i < MaxPage; i++) {
            if (ListPan.get(i).isEmpty()) {
                tabbedPane.setSelectedIndex(i);
                return false;
            }
        }

        return true;
    }

    private void getAllData() {
        int size = ListPan.size();

        for (int i = 0; i < size; i++) {
            ListPan.get(i).getPieceData(NewPiece);
        }
    }

    //return false si un panel est vide
    private boolean CheckIfPaneIsEmpty() {
        boolean flag = true;
        int page = tabbedPane.getSelectedIndex();
        int tempCurentPage = CurrentPage;
        //si le pannel est vide bloc l'utilisateur sur le pannel vide
        if (page != MaxPage) {
            if ((page != CurrentPage) && (ListPan.get(CurrentPage).isEmpty())) {
                tabbedPane.setSelectedIndex(CurrentPage);
                flag = false;
            } else if ((page == CurrentPage)  && (ListPan.get(CurrentPage).isEmpty())) {
                flag = false;
            } else {
                CurrentPage = page;
            }
        } else {
            if ((CurrentPage == 0) && (ListPan.get(CurrentPage).isEmpty())) {
                tabbedPane.setSelectedIndex(CurrentPage);
                flag = false;
            } else if ((CurrentPage != 0) && (ListPan.get(CurrentPage - 1).isEmpty())) {
                tabbedPane.setSelectedIndex(CurrentPage - 1);
                flag = false;
            }
        }

        
        return flag;
    }

    private void Save() {
        if (CheckIfPaneIsEmpty()) {
            getAllData();
            DataControlleur.getInstance().SavePiece(NewPiece, NameGame);
            ArrayList<Piece> list = new ArrayList();
            list.add(OldPiece);
            list.add(NewPiece);
            ecouter.ModifPiece(list);
            JOptionPane.showConfirmDialog(null, "Piece modifier", "Information", JOptionPane.YES_OPTION);
            ecouteClose.Closing();
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.dispose();
        }else{
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        CheckIfPaneIsEmpty();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        switch (action) {
            case "Save":
                if (CheckAllPan()) {
                    Save();
                }
                break;
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int Size = ListeCallResize.size();

        for (int i = 0; i < Size; i++) {
            ListeCallResize.get(i).Resize(this.getWidth(), this.getHeight());
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {

        if (CheckAllPan()) {
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ecouteClose.Closing();
            Save();
        } else {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
}
