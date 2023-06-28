/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Menu;

import JeuxDame.Controlleur.DataControlleur;
import JeuxDame.Listener.ListenerNewPiece;
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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author ceule
 */
public class MenuCreatPiece extends JFrame implements ChangeListener, ActionListener,ComponentListener,WindowListener {

    private ArrayList ListePiece = new ArrayList();
    private PanelCreationPiece PanelCreationP;
    private PanelEvolutionPlacementPiece PanelEvolPlace;
    private PanelDeplacementPiece PanelDeplace;
    private PanelButon panSave;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private ArrayList<PanelMenuPiece> ListPan = new ArrayList();
    private ArrayList<ListenerNewPiece>ListeCallNewPiece = new ArrayList<>();
    private ArrayList<ListenerResize> ListeCallResize = new ArrayList<>();
    private int CurrentPage = 0;
    private int MaxPage;
    private Piece piece;
    private ListenerNewPiece ecoute;
    private ListernerWindowsClose ecouteClose;
    private String GameName;

    public MenuCreatPiece(int col, int row,ListenerNewPiece PEcoute,ArrayList listTeam,ArrayList listPiece,Color one,Color two,Color Depl,ListernerWindowsClose close,String GameName) {
        this.ListePiece=listPiece;
        this.ecouteClose = close;
        this.GameName=GameName;
        PanelEvolPlace = new PanelEvolutionPlacementPiece(col, row, ListePiece, (String) listTeam.get(0),piece,500,300,one,two,Depl);
        PanelCreationP = new PanelCreationPiece(listTeam,ListePiece);
        PanelDeplace = new PanelDeplacementPiece(col, row,piece,500,300,one,two,Depl);
        
        this.ecoute=PEcoute;
        initComponent();
        this.setVisible(true);
    }

    private void initComponent() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        
        ListeCallNewPiece.add(PanelEvolPlace);
        ListeCallNewPiece.add(ecoute);
        
        ListeCallResize.add(PanelEvolPlace);
        ListeCallResize.add(PanelDeplace);

        tabbedPane.addChangeListener(this);
        
        ImageIcon icon = new ImageIcon("./src/img/Default.png");


        piece = new Piece(icon, "", 0, " ", new Evolution(new ArrayList<String>(), 0), new ArrayList(), new ArrayList());

        MaxPage = tabbedPane.getTabCount()-1;

        this.addWindowListener(this);
        this.add(tabbedPane);
        this.setTitle("Creation");

    }
    //verifie si tout les panel sont completer 
    public boolean CheckAllPan(){
        
        for (int i = 0; i < MaxPage-1; i++) {
            if(ListPan.get(i).isEmpty()){
                tabbedPane.setSelectedIndex(i);
                return false;
            }
        }
        
        return true;
    }
    
    private void ClearAllPan(){
        tabbedPane.setSelectedIndex(0);
        int SizePan = ListPan.size();
        for (int i = 0; i < SizePan; i++) {
            ListPan.get(i).Clear();
        }
  
    }
    
    private void CallAllNewPiece(Piece p){
        int SizeCall = ListeCallNewPiece.size();
        for (int i = 0; i < SizeCall; i++) {
            ListeCallNewPiece.get(i).NewPiece(p);
        }
    }
    
    
    @Override
    public void stateChanged(ChangeEvent e) {
        boolean flag = true;
        int page = tabbedPane.getSelectedIndex();
        int tempCurentPage = CurrentPage;
        //si le pannel est vide bloc l'utilisateur sur le pannel vide
        if (page != MaxPage) {
            if ((page != CurrentPage)&&(ListPan.get(CurrentPage).isEmpty())){
                tabbedPane.setSelectedIndex(CurrentPage);
                flag=false;
            }
            else if(page==CurrentPage)flag=false;
            else CurrentPage = page;        
        }else{
             if ((ListPan.get(CurrentPage-1).isEmpty())){
                tabbedPane.setSelectedIndex(CurrentPage-1);
                flag=false;
            }
        }
        
        if(flag){
            piece = (Piece) ListPan.get(tempCurentPage).getPieceData(piece);
            for (int i = 1; i < MaxPage; i++) {
                if(i!=tempCurentPage)ListPan.get(i).UpdatePieceData(piece);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        switch (action) {
            case "Save":
                if(CheckAllPan()){
                    DataControlleur.getInstance().SavePiece(piece,GameName);
                    CallAllNewPiece(piece);
                    ClearAllPan();
                    piece = new Piece(null, "", 0, "", new Evolution(), new ArrayList(), new ArrayList());
                }
                break;
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int Size = ListeCallResize.size();
        
        for (int i = 0; i < Size; i++) {
            ListeCallResize.get(i).Resize(this.getWidth(),this.getHeight());
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

}
