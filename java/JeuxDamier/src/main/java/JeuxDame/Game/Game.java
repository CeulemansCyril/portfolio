/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Game;

import JeuxDame.Controlleur.DataControlleur;
import JeuxDame.Listener.ListenerEvolution;
import JeuxDame.Listener.ListernerWindowsClose;
import JeuxDame.Object.ObjGameConfig.GameConfig;
import JeuxDame.Object.ObjGameConfig.GameRule;
import JeuxDame.Object.ObjGameConfig.KillOne;
import JeuxDame.Object.ObjPiece.DeplacementComplexe;
import JeuxDame.Object.ObjPiece.DeplacementSimple;
import JeuxDame.Object.ObjPiece.IDeplacement;
import JeuxDame.Object.ObjPiece.IPiece;
import JeuxDame.Object.ObjPiece.Placement;
import JeuxDame.Object.Piece;
import JeuxDame.Object.Tuile;
import JeuxDame.Panel.PanelDamier;
import JeuxDame.PopUp.PopUpEvolution;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class Game extends JFrame implements MouseListener, WindowListener, ListenerEvolution, ComponentListener {

    private ArrayList<IPiece> ListePiece = new ArrayList<>();
    private ArrayList<IPiece> ListePieceTeam1 = new ArrayList<>();
    private ArrayList<IPiece> ListePieceTeam2 = new ArrayList<>();
    private GameRule rule = new GameRule();
    private KillOne killOne = new KillOne();
    private int SizeRow = 0;
    private int SizeCol = 0;
    private String team1 = "";
    private String team2 = "";
    private String GameName = "";
    private ListernerWindowsClose ecoteClose;
    private PanelDamier Dam;
    private Color colorDepl;
    private String teamPlay = "";
    private Tuile TuilSelect = new Tuile();
    private boolean prise = false;
    private Piece BeforEvol = new Piece();

    public Game(ListernerWindowsClose ecoteClose, GameConfig config) {
        this.ecoteClose = ecoteClose;
        initComponent(config);
        this.setVisible(true);
    }

    private void initComponent(GameConfig config) {
        rule = config.getRule();
        killOne = rule.getKillone();
        SizeCol = config.getCol();
        SizeRow = config.getRow();
        team1 = config.getTeam1();
        team2 = config.getTeam2();
        teamPlay = team1;
        colorDepl = config.getDepl();
        this.GameName = config.getGameName();
        Dam = new PanelDamier(SizeCol, SizeRow, config.getOne(), config.getTwo(), colorDepl);

        ArrayList<String> listName = config.getListPieceName();
        int size = listName.size();

        for (int i = 0; i < size; i++) {
            Piece p = new Piece();
            p = DataControlleur.getInstance().ReadPiece(listName.get(i) + ".json");
            ListePiece.add(p);
            ArrayList<Placement> listPlac = new ArrayList<>();
            listPlac = p.getAllPlacement();
            int SizePlacement = listPlac.size();
            for (int j = 0; j < SizePlacement; j++) {
                Piece p2 = new Piece();
                p2 = p;
                Dam.PlaceOnePiece(p2, listPlac.get(j).getPlacementY(), listPlac.get(j).getPlacementX(), 500, 350);
                if (p2.getTeam().equalsIgnoreCase(team1)) {
                    ListePieceTeam1.add(p2);
                } else {
                    ListePieceTeam2.add(p2);
                }
            }
        }

        JPanel pan = (JPanel) this.getContentPane();
        pan.setLayout(new GridLayout(1, 1));
        pan.add(Dam);

        Dam.AddMouseListenerAllTuile(this);
        this.addWindowListener(this);

        this.setTitle(config.getGameName());
        this.setSize(500, 350);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addComponentListener(this);
    }

    //Permet de trouver le bon deplacemnt de la piece par rapport à la tuile selectionner 
    private boolean checkDeplacement(IDeplacement deplacement, int RowStart, int ColStart, Tuile t) {

        int x = 0;
        int y = 0;
        int nbDepl = 1;

        if (deplacement.getIDDeplacement().equalsIgnoreCase("DC")) {
            DeplacementComplexe depl = (DeplacementComplexe) deplacement;
            x = depl.getDeplacementX();
            y = depl.getDeplacementY();

        } else {
            DeplacementSimple depl = (DeplacementSimple) deplacement;
            x = depl.getDeplacementX();
            y = depl.getDeplacementY();
            nbDepl = depl.getNbDeplacement();
        }

        if (!rule.isPriseDirect() && !deplacement.getIDDeplacement().equalsIgnoreCase("DC")) {
            nbDepl++;
        }

        int xCible = t.getPointX();
        int yCible = t.getPointY();

        for (int i = 0; i < nbDepl; i++) {

            ColStart = ColStart + x;
            RowStart = RowStart + y;

            if (RowStart == yCible && ColStart == xCible) {
                return true;
            }

        }
        //verifie si il y a une prise quand on est in mode pise inderect
        if (!rule.isPriseDirect() && deplacement.getIDDeplacement().equalsIgnoreCase("DC")) {
            if (x < 0) {
                x = x / -(x);
            } else {
                x = x / x;
            }
            if (y < 0) {
                y = y / -(y);
            } else {
                y = y / y;
            }

            ColStart = ColStart + x;
            RowStart = RowStart + y;

            if (RowStart == yCible && ColStart == xCible) {
                return true;
            }
        }

        return false;

    }

    //execute les mouvements de deplacement de la piece
    private void PrintDeplacement(IDeplacement deplacement, int RowStart, int ColStart, Tuile Cible) {
        int x = 0;
        int y = 0;
        int nbDepl = 1;

        if (deplacement.getIDDeplacement().equalsIgnoreCase("DC")) {
            DeplacementComplexe depl = (DeplacementComplexe) deplacement;
            x = depl.getDeplacementX();
            y = depl.getDeplacementY();

        } else {
            DeplacementSimple depl = (DeplacementSimple) deplacement;
            x = depl.getDeplacementX();
            y = depl.getDeplacementY();
            nbDepl = depl.getNbDeplacement();
        }

        int xCible = TuilSelect.getPointX();
        int yCible = TuilSelect.getPointY();
        int i = 0;
        boolean flag = true;
        while (i < nbDepl && flag) {
            ColStart = ColStart + x;
            RowStart = RowStart + y;
            Threadinit time = new Threadinit();
            if (((ColStart >= 1 && ColStart <= SizeCol)) && (RowStart >= 1 && RowStart <= SizeRow)) {
                Piece p = (Piece) TuilSelect.getPieceTuile();
                TuilSelect.RemovePieceTuile();
                Dam.PlaceOnePiece(p, RowStart, ColStart, this.getWidth(), this.getHeight());
                TuilSelect = Dam.getTuile(RowStart, ColStart);
                Dam.UpdateViewDamier();
                time.run();

                i++;
            } else {
                flag = false;
            }
            if (prise && !rule.isPriseDirect()) {

                if (deplacement.getIDDeplacement().equalsIgnoreCase("DC")) {
                    if (deplacement.getDeplacementY() < 0) {
                        y = deplacement.getDeplacementY() / -(deplacement.getDeplacementY());
                    } else {
                        y = deplacement.getDeplacementY() / deplacement.getDeplacementY();
                    }
                    if (deplacement.getDeplacementX() < 0) {
                        x = deplacement.getDeplacementX() / -(deplacement.getDeplacementX());
                    } else {
                        x = deplacement.getDeplacementX() / deplacement.getDeplacementX();
                    }
                }
                ColStart = ColStart + x;
                RowStart = RowStart + y;
                if (((ColStart >= 1 && ColStart <= SizeCol)) && (RowStart >= 1 && RowStart <= SizeRow)) {
                    Piece p = (Piece) TuilSelect.getPieceTuile();
                    TuilSelect.RemovePieceTuile();
                    Dam.PlaceOnePiece(p, RowStart, ColStart, this.getWidth(), this.getHeight());
                    TuilSelect = Dam.getTuile(RowStart, ColStart);
                }

            }
            if (ColStart == Cible.getPointX() && RowStart == Cible.getPointY()) {
                flag = false;
            }

        }

        CheckEvolution();
    }
   //verifie si la piece peut evoluer
    private void CheckEvolution() {

        Piece p = (Piece) TuilSelect.getPieceTuile();
        int Row = TuilSelect.getPointY();

        if (p.getPointEvolution() == Row) {
            BeforEvol = p;
            PopUpEvolution evo = new PopUpEvolution(this, TuilSelect, this.getWidth(), this.getHeight(), GameName);
        }

    }
    // met en couleur l'echiquier
    private boolean ColorDeplSimple(DeplacementSimple Simp, int Row, int Col, Piece p) {
        if (p.isSautPiece()) {
            Tuile t = Dam.getTuile(Row, Col);
            if (t != null) {
                Piece pcible = (Piece) t.getPieceTuile();
                if (pcible == null) {
                    Dam.ColorSimpleDeplacementGame(Simp, Row, Col, pcible.isSautPiece(), teamPlay, !rule.isPriseDirect());
                }
            }
        } else {

            Tuile t = Dam.getTuile(Row, Col);
            if (t != null) {
                Piece pcible = (Piece) t.getPieceTuile();
                if (pcible == null) {
                    return false;
                } else {
                    Dam.ColorSimpleDeplacementGame(Simp, Row, Col, pcible.isSautPiece(), teamPlay, !rule.isPriseDirect());
                }
            }
        }

        return true;
    }
    // met en couleur l'echiquier
    private boolean ColorDeplComplexe(DeplacementComplexe Simp, int Row, int Col, Piece p) {
        //prise direct 
        if (p.isSautPiece()) {
            Tuile t = Dam.getTuile(Row, Col);
            if (t != null) {
                Piece pcible = (Piece) t.getPieceTuile();
                if (pcible != null) {
                    Dam.ColorComplexeDeplacementGame(Simp, Row, Col, !rule.isPriseDirect(), teamPlay);
                }
            }
        } else {
            Tuile t = Dam.getTuile(Row, Col);
            if (t != null) {
                Piece pcible = (Piece) t.getPieceTuile();
                if (pcible == null) {
                    return false;
                } else {
                    Dam.ColorComplexeDeplacementGame(Simp, Row, Col, !rule.isPriseDirect(), teamPlay);
                }
            }
        }

        return true;
    }
   //gére les pris indirect
    private void PriseDirect(Tuile tui) {
        Piece p = (Piece) tui.getPieceTuile();
        tui.RemovePieceTuile();
        if (p.getTeam().equalsIgnoreCase(team1)) {
            ListePieceTeam1.remove(p);
        } else {
            ListePieceTeam2.remove(p);
        }
    }
    //gére les pris direct
    private boolean PriseInDirect(Tuile tui) {

        Piece pSelect = (Piece) TuilSelect.getPieceTuile();
        ArrayList<IDeplacement> delp = pSelect.getDeplacementPiece();
        int size = delp.size();
        int i = 0;
        boolean flag = true;
        while (flag && i < size) {
            if (checkDeplacement(delp.get(i), TuilSelect.getPointY(), TuilSelect.getPointX(), tui)) {
                flag = false;
            }
            i++;
        }
        int x = 0;
        int y = 0;
        IDeplacement dep = delp.get(i - 1);

        if (dep.getIDDeplacement().equalsIgnoreCase("DC") && !rule.isPriseDirect()) {
            if (dep.getDeplacementY() < 0) {
                y = dep.getDeplacementY() / -(dep.getDeplacementY());
            } else {
                y = dep.getDeplacementY() / dep.getDeplacementY();
            }
            if (dep.getDeplacementX() < 0) {
                x = dep.getDeplacementX() / -(dep.getDeplacementX());
            } else {
                x = dep.getDeplacementX() / dep.getDeplacementX();
            }

        } else {
            y = dep.getDeplacementY();
            x = dep.getDeplacementX();
        }

        Tuile tuiRemove = Dam.getTuile(tui.getPointY() - y, tui.getPointX() - x);

        if (tuiRemove.getPieceTuile() != null) {
            Piece p = (Piece) tuiRemove.getPieceTuile();
            tuiRemove.RemovePieceTuile();
            if (p.getTeam().equalsIgnoreCase(team1)) {
                ListePieceTeam1.remove(p);
            } else {
                ListePieceTeam2.remove(p);
            }
            return true;
        }

        return false;
    }
    //recuper le bon mouvement de la piece
    private void Deplacement(Tuile tuile) {
        Piece p = (Piece) TuilSelect.getPieceTuile();
        ArrayList<IDeplacement> delp = p.getDeplacementPiece();
        int size = delp.size();
        int i = 0;
        boolean flag = true;
        while (flag && i < size) {
            if (checkDeplacement(delp.get(i), TuilSelect.getPointY(), TuilSelect.getPointX(), tuile)) {
                flag = false;
            }
            i++;
        }

        IDeplacement simp = delp.get(i - 1);
        PrintDeplacement(simp, TuilSelect.getPointY(), TuilSelect.getPointX(), tuile);

    }

    //permet de verifier si le deplacement de la piece est pour une prise ou non
    public boolean checkPrise(Tuile tui) {
        Piece p = (Piece) TuilSelect.getPieceTuile();
        ArrayList<IDeplacement> delp = p.getDeplacementPiece();
        int size = delp.size();
        int i = 0;
        boolean flag = true;
        while (flag && i < size) {
            if (checkDeplacement(delp.get(i), TuilSelect.getPointY(), TuilSelect.getPointX(), tui)) {
                flag = false;
            }
            i++;
        }

        Tuile center = new Tuile();
        int x = 0;
        int y = 0;
        IDeplacement dep = delp.get(i - 1);

        y = dep.getDeplacementY();
        x = dep.getDeplacementX();
        center = Dam.getTuile(TuilSelect.getPointY() + y, TuilSelect.getPointX() + x);

        if ((center != null) && (center.getPieceTuile() != null) && (!center.getPieceTuile().getTeam().equalsIgnoreCase(teamPlay))) {
            return false;
        }

        return true;
    }
    //verifie si un joueur à gagnier
    public boolean Vitory() {
        if (rule.isKillAll()) {
            if (teamPlay.equalsIgnoreCase(team1)) {
                return ListePieceTeam2.isEmpty();
            } else {
                return ListePieceTeam1.isEmpty();
            }
        }
        if (killOne.isSelect()) {

            if (teamPlay.equalsIgnoreCase(team1)) {

                int SizeT = ListePieceTeam1.size();
                boolean flag = true;
                for (int i = 0; i < SizeT; i++) {
                    if (ListePieceTeam1.get(i).getName().equalsIgnoreCase(killOne.getPieceNameTeam1())) {
                        flag = false;
                    }
                }
                return flag;
            } else {
                int SizeT = ListePieceTeam2.size();
                boolean flag = true;
                for (int i = 0; i < SizeT; i++) {
                    if (ListePieceTeam2.get(i).getName().equalsIgnoreCase(killOne.getPieceNameTeam2())) {
                        flag = false;
                    }
                }
                return flag;
            }
        }

        return false;
    }
    
    private void Restart() {
        //clear
        teamPlay = team1;

        Dam.RemoveAllPiece();
        Dam.ClearColor();

        //replace
        int size = ListePiece.size();
        for (int i = 0; i < size; i++) {
            Piece p = (Piece) ListePiece.get(i);
            ArrayList<Placement> listPlac = new ArrayList<>();
            listPlac = p.getAllPlacement();
            int SizePlacement = listPlac.size();

            for (int j = 0; j < SizePlacement; j++) {
                Piece p2 = new Piece();
                p2 = p;
                Dam.PlaceOnePiece(p2, listPlac.get(j).getPlacementY(), listPlac.get(j).getPlacementX(), 500, 350);
                if (p2.getTeam().equalsIgnoreCase(team1)) {
                    ListePieceTeam1.add(p2);
                } else {
                    ListePieceTeam2.add(p2);
                }
            }
        }
    }

    private void ChangePlayer(boolean flag) {
        if (flag) {
            if (TuilSelect.getPieceTuile().getTeam().equalsIgnoreCase(team1)) {
                teamPlay = team2;
            } else {
                teamPlay = team1;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Tuile tuile = (Tuile) e.getSource();
        boolean Change = true;

        //Color les deplacement 
        if ((tuile.getPieceTuile() != null) && (tuile.getPieceTuile().getTeam().equalsIgnoreCase(teamPlay))) {
            Piece p = (Piece) tuile.getPieceTuile();

            Dam.ClearColor();
            ArrayList<IDeplacement> listDepl = new ArrayList<>();
            listDepl = p.getDeplacementPiece();
            int SizeDepl = listDepl.size();
            int i = 0;
            boolean flag = true;
            while ((i < SizeDepl) && (flag)) {
                if (!listDepl.get(i).getIDDeplacement().equalsIgnoreCase("DC")) {
                    DeplacementSimple deplS = (DeplacementSimple) listDepl.get(i);
                    flag = ColorDeplSimple(deplS, tuile.getPointY(), tuile.getPointX(), p);

                } else {
                    DeplacementComplexe DeplC = (DeplacementComplexe) listDepl.get(i);
                    flag = ColorDeplComplexe(DeplC, tuile.getPointY(), tuile.getPointX(), p);
                }
                i++;
            }
            TuilSelect = tuile;

            //print deplacement
        } else if (tuile.getBackground() == colorDepl) {
            if (tuile.getPieceTuile() == null && checkPrise(tuile)) {
                prise = false;
                Deplacement(tuile);
                if (rule.isContinue()) {
                    Change = true;
                }
                //prise direct  
            } else {
                if (rule.isContinue()) {
                    Change = false;
                }
                prise = true;
                if (rule.isPriseDirect()) {
                    PriseDirect(tuile);
                    Deplacement(tuile);
                } else {
                    if (tuile.getPieceTuile() == null) {
                        if (PriseInDirect(tuile)) {
                            Deplacement(tuile);
                        }
                    }
                }
                //check si un joueur à gagnier
                boolean vic = Vitory();
                if (vic) {
                    String teamWin = teamPlay;
                    int choix = JOptionPane.showConfirmDialog(null, "L'équipe " + teamPlay + " a gagnier, voulez-vous recommencer", "victoire", JOptionPane.YES_NO_OPTION);
                    if (choix == JOptionPane.YES_OPTION) {
                        Restart();
                    } else {
                        JOptionPane.showMessageDialog(null, "Au revoir", "Bonne Game", JOptionPane.OK_CANCEL_OPTION);
                        ecoteClose.Closing();
                        this.dispose();
                    }
                }
            }
            ChangePlayer(Change);
            Dam.ClearColor();

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ecoteClose.Closing();
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
    public void Evol(Tuile tuile) {
        Piece evol = BeforEvol;

        if (evol.getTeam().equalsIgnoreCase(team1)) {
            ListePieceTeam1.remove(evol);
            ListePieceTeam1.add(tuile.getPieceTuile());
        } else {
            ListePieceTeam2.remove(evol);
            ListePieceTeam2.add(tuile.getPieceTuile());
        }

        BeforEvol = new Piece();
        Dam.PlaceOnePiece(tuile.getPieceTuile(), tuile.getPointY(), tuile.getPointX(), this.getWidth(), this.getHeight());
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Dam.ReSize(this.getWidth(), this.getHeight());
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

    private class Threadinit extends Thread {

        @Override
        public void run() {

            try {
                this.sleep(100);
            } catch (InterruptedException ex) {
            }

        }
    }
}
