/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.PanelMenu.CreatGame;

import JeuxDame.Listener.ListenerNewPiece;
import JeuxDame.Listener.ListenerResize;
import JeuxDame.Object.ObjPiece.Evolution;
import JeuxDame.Object.ObjPiece.IPiece;
import JeuxDame.Object.ObjPiece.Placement;
import JeuxDame.Object.PanelMenuPiece;
import JeuxDame.Object.Piece;
import JeuxDame.Object.Tuile;
import JeuxDame.Panel.PanelComboBox;
import JeuxDame.Panel.PanelDamier;
import JeuxDame.Panel.PanelJlistWhitDeletButon.PanelJlistWhitDeletOption;
import JeuxDame.Panel.PanelMenuDeroulant;
import JeuxDame.PopUp.PopUpErreur;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class PanelEvolutionPlacementPiece extends PanelMenuPiece implements ItemListener, ActionListener, MouseListener, ListenerNewPiece, ListenerResize {

    private PanelDamier Dam;
    private PanelComboBox Box;
    private PanelComboBox BoxPiece;
    private JButton ButAdd = new JButton("Ajouter");
    private PanelJlistWhitDeletOption JListPiece = new PanelJlistWhitDeletOption();
    private JCheckBox EnebleEvol = new JCheckBox();
    private ArrayList ListPieceName = new ArrayList();
    private ArrayList<Piece> ListPieceEvol = new ArrayList<>();
    private HashSet ListPieceTest = new HashSet<>();
    private ArrayList<String> listPieceForEvol = new ArrayList<>();
    private IPiece piece;
    private PanelMenuDeroulant menu;
    private String Team1;
    private String TeamSelected;
    private int SizeCol;
    private int SizeRow;
    private int w;
    private int h;
    private boolean flagRefile = false;

    /**
     * Constructeur de la classe PanelEvolutionPlacementPiece.
     *
     * @param SizeCol Le nombre de colonnes du damier.
     * @param SizeRow Le nombre de lignes du damier.
     * @param listeP La liste des pièces disponibles.
     * @param team1 L'équipe 1.
     * @param p La pièce actuelle.
     * @param w La largeur de la pièce.
     * @param h La hauteur de la pièce.
     * @param one La couleur un.
     * @param two La couleur deux.
     * @param Depl La couleur de déplacement.
     */
    public PanelEvolutionPlacementPiece(int SizeCol, int SizeRow, ArrayList<Piece> listeP, String team1, IPiece p, int w, int h, Color one, Color two, Color Depl) {
        Dam = new PanelDamier(SizeCol, SizeRow, one, two, Depl);
        ListPieceEvol.addAll(listeP);
        Team1 = team1;
        TeamSelected = team1;
        this.SizeRow = SizeRow;
        this.SizeCol = SizeCol;
        this.piece = p;
        this.w = w;
        this.h = h;
        initComponent(SizeRow, SizeCol);
    }

    /**
     * Méthode d'initialisation des composants du panneau.
     *
     * @param NBRow Le nombre de lignes.
     * @param NBCol Le nombre de colonnes.
     */
    private void initComponent(int NBRow, int NBCol) {
        this.setLayout(new GridLayout(1, 2));
        JPanel Pan = new JPanel();
        Pan.setLayout(new BoxLayout(Pan, BoxLayout.PAGE_AXIS));

        ArrayList list = new ArrayList();
        for (int i = 1; i < NBRow; i++) {
            list.add(i);
        }

        Box = new PanelComboBox("Ligne", list, "1");
        Box.addItemChangeListener(this);
        Box.setAlignmentX(CENTER_ALIGNMENT);

        EnebleEvol.addActionListener(this);
        EnebleEvol.setActionCommand("EnableEvol");

        JListPiece.setAlignmentX(CENTER_ALIGNMENT);
        JListPiece.addMouseListener(this);

        setBoxPiece();

        ButAdd.setActionCommand("Add");
        ButAdd.addActionListener(this);

        JListPiece.addTitle("List des evolutions");
        JListPiece.setListSize(120, 25);

        Dam.AddMouseListenerAllTuile(this);

        this.add(Dam);
        Pan.add(EnebleEvol);
        Pan.add(Box);
        Pan.add(BoxPiece);
        Pan.add(JListPiece);

        menu = new PanelMenuDeroulant("Evolution", Pan);

        if (!listPieceForEvol.isEmpty()) {
            menu.Enable(false);
        }
        EnableEvolution(false);

        PlacementPiece();

        this.add(menu);
        flagRefile = false;
    }

    /**
     * Place toutes les pièces dans le damier.
     */
    private void PlacementPiece() {

        int size = ListPieceEvol.size();
        for (int i = 0; i < size; i++) {
            Dam.PlaceOnePieceInMultyTuile(ListPieceEvol.get(i), w, h);
        }

    }

    /**
     * Configure la combobox des pièces pour l'évolution.
     */
    private void setBoxPiece() {

        int size = ListPieceEvol.size();

        for (int i = 0; i < size; i++) {
            if ((piece != null) && (!ListPieceEvol.get(i).getName().equalsIgnoreCase(piece.getName())) && (ListPieceEvol.get(i).getTeam() == TeamSelected)) {
                listPieceForEvol.add(ListPieceEvol.get(i).getName());
            }
        }

        BoxPiece = new PanelComboBox("Piece", listPieceForEvol, "");
        BoxPiece.AddElement(ButAdd);
        BoxPiece.setAlignmentX(CENTER_ALIGNMENT);

    }

    /**
     * Met à jour la combobox des pièces pour l'évolution.
     */
    private void RefileBoxPiece() {
        listPieceForEvol = new ArrayList<>();

        int size = ListPieceEvol.size();

        for (int i = 0; i < size; i++) {
            if (ListPieceEvol.get(i).getTeam().equalsIgnoreCase(TeamSelected)) {
                listPieceForEvol.add(ListPieceEvol.get(i).getName());
            }
        }

        if (listPieceForEvol.isEmpty()) {
            menu.Enable(false);
        } else {
            menu.Enable(true);
        }

        BoxPiece.RefillCombo(listPieceForEvol);

    }

    /**
     * Active ou désactive l'évolution des pièces.
     *
     * @param flag Le drapeau d'activation.
     */
    private void EnableEvolution(boolean flag) {
        ButAdd.enable(flag);
        Box.Enable(flag);
        BoxPiece.Enable(flag);
    }

    /**
     * Met à jour la combobox de sélection de ligne.
     *
     * @param team1 Indique si c'est l'équipe 1.
     */
    private void RefileCombo(boolean team1) {
        ArrayList list = new ArrayList();

        if (team1) {
            for (int i = 2; i <= SizeRow; i++) {
                list.add(i);
            }
        } else {
            for (int i = SizeRow-1; i >= 1; i--) {
                list.add(i);
            }
        }

        flagRefile = true;
        Box.RefillCombo(list);
    }

    /**
     * Vérifie les pièces dans la liste ListPieceEvol et les supprime si elles
     * ne sont pas de type Piece.
     */
    private void CheckArrayListPieceEvol() {
        int size = ListPieceEvol.size();

        for (int i = 0; i < size; i++) {
            try {
                Piece p = ListPieceEvol.get(i);
            } catch (ClassCastException e) {
                ListPieceEvol.remove(i);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if ((e.getStateChange() == ItemEvent.SELECTED) && (!flagRefile)) {
            String r =(String) e.getItem();
            Dam.ColorRow(Integer.parseInt(r) );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "Add":
                String txt = (String) BoxPiece.getSelectItem();
                JListPiece.addItem(txt);
                break;
            case "EnableEvol":
                if (EnebleEvol.isSelected()) {
                    EnableEvolution(true);
                    if (!Team1.equalsIgnoreCase(TeamSelected)) {
                        RefileCombo(true);
                    } else {
                        RefileCombo(false);
                    }
                    Dam.ColorRow((int) Box.getSelectItem());
                    updateUI();
                } else {
                    EnableEvolution(false);
                    Dam.ClearColor();
                    updateUI();
                }
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            Tuile tui = (Tuile) e.getSource();

            int midele = SizeRow / 2;

            if (Team1.equals(TeamSelected)) {
                if (tui.getPointY() < midele) {
                    if (tui.getPieceTuile() == null) {
                        tui.setPieceTuile(piece, w, h);
                    } else if (tui.getPieceTuile().getName() == piece.getName()) {
                        tui.RemovePieceTuile();
                    }
                }
            } else {
                if (tui.getPointY() > midele) {
                    if (tui.getPieceTuile() == null) {
                        tui.setPieceTuile(piece, w, h);
                    } else if (tui.getPieceTuile().getName() == piece.getName()) {
                        tui.RemovePieceTuile();
                    }
                }
            }
        } catch (ClassCastException m) {
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
        try {
            PanelJlistWhitDeletOption list = (PanelJlistWhitDeletOption) e.getSource();
            if (list.getBackground() == Color.red) {
                list.setBackground(null);
            }
        } catch (ClassCastException m) {
        }
        flagRefile = false;
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public boolean isEmpty() {
        if (EnebleEvol.isSelected()) {
            if (JListPiece.isEmpty()) {
                PopUpErreur err = new PopUpErreur("Erreur veuillier attribuer une evolution a la piece");
                return true;
            }
        }

        return false;
    }

    @Override
    public IPiece getPieceData(IPiece p) {
        if (!Dam.isEmpty()) {
            p.addMultyPlacement(Dam.getAllPlacementOnePiece((Piece) p));
        }
        if (!JListPiece.isEmpty()) {
            EnableEvolution(true);
            String i = String.valueOf(Box.getSelectItem());
            int b = Integer.parseInt(i);
            p.setEvolution(new Evolution(JListPiece.getAllData(), b));
            EnableEvolution(false);
        }

        return p;
    }

    @Override
    public void UpdatePieceData(IPiece p) {
        this.piece = p;

        if (!Team1.equals(piece.getTeam())) {
            TeamSelected = piece.getTeam();
            RefileCombo(false);
            RefileBoxPiece();
        } else {
            TeamSelected = Team1;
            RefileCombo(true);
            RefileBoxPiece();
        }

    }

    @Override
    public void LoadPieceData(IPiece p) {
        TeamSelected = p.getTeam();

        boolean flagTeam = true;

        if (!Team1.equalsIgnoreCase(p.getTeam())) {
            flagTeam = false;
        }
        RefileCombo(flagTeam);
        RefileBoxPiece();
        ArrayList listEvol = p.getNamePieceEvolution();
        if (listEvol != null) {
            int SizeEvol = listEvol.size();
            for (int i = 0; i < SizeEvol; i++) {
                JListPiece.addItem(listEvol.get(i));
            }
            menu.Enable(true);
            EnableEvolution(true);
            EnebleEvol.setSelected(true);
            
            Box.DefaultValue(""+p.getPointEvolution());
            Box.updateUI();
            Dam.ColorRow(p.getPointEvolution());

        } 
        ArrayList<Placement> listPlacement = p.getAllPlacement();
        if (listPlacement != null) {
            Dam.PlaceOnePieceInMultyTuile(p, w, h);
        }
        updateUI();
    }

    @Override
    public void Clear() {
        Piece p = new Piece();
        p = (Piece) piece;

        piece = new Piece();
        JListPiece.ClearAll();

        EnableEvolution(false);
        Dam.ClearColor();
        updateUI();
    }

    @Override
    public void NewPiece(Piece name) {
        ListPieceEvol.add(name);
        CheckArrayListPieceEvol();

    }

    @Override
    public void Resize(int w, int h) {
        Dam.ReSize(w, h);
        this.w = w;
        this.h = h;
    }

}
