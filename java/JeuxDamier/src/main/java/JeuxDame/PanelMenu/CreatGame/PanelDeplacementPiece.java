/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.PanelMenu.CreatGame;

import JeuxDame.Listener.ListenerDeplacementComplexe;
import JeuxDame.Listener.ListenerResize;
import JeuxDame.Object.ObjPiece.DeplacementComplexe;
import JeuxDame.Object.ObjPiece.DeplacementSimple;
import JeuxDame.Object.ObjPiece.IDeplacement;
import JeuxDame.Object.ObjPiece.IPiece;
import JeuxDame.Object.PanelMenuPiece;
import JeuxDame.Object.Piece;
import JeuxDame.Panel.PanelCheckBoxWhitComboBox;
import JeuxDame.Panel.PanelDamier;
import JeuxDame.Panel.PanelMenuDeroulant;
import JeuxDame.PanelMenu.CreatGame.PanelDeplacementComplexe.PanelDeplacementComplexe;
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
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * The PanelDeplacementPiece class represents a panel for configuring piece
 * movement options.
 */
public class PanelDeplacementPiece extends PanelMenuPiece implements ActionListener, MouseListener, ItemListener, ListenerDeplacementComplexe, ListenerResize {
    
    private IPiece piece = new Piece();
    private PanelDamier Dam;
    private PanelCheckBoxWhitComboBox CheckVerticalHaut = new PanelCheckBoxWhitComboBox("Deplacement vertical haut", "Haut", false);
    private PanelCheckBoxWhitComboBox CheckVerticalBas = new PanelCheckBoxWhitComboBox("Deplacement vertical bas", "Bas", false);
    private PanelCheckBoxWhitComboBox CheckHorizontalGauche = new PanelCheckBoxWhitComboBox("Deplacement horizontal gauche", "Horizontal_gauche", false);
    private PanelCheckBoxWhitComboBox CheckHorizontalDroite = new PanelCheckBoxWhitComboBox("Deplacement horizontal droite", "Horizontal_droit", false);
    private PanelCheckBoxWhitComboBox CheckDiagonalHG = new PanelCheckBoxWhitComboBox("Deplacement diagonal haut gauche", "Diagonal_haut_gauche", false);
    private PanelCheckBoxWhitComboBox CheckDiagonalHD = new PanelCheckBoxWhitComboBox("Deplacement diagonal haut droit", "Diagonal_haut_droit", false);
    private PanelCheckBoxWhitComboBox CheckDiagonalBG = new PanelCheckBoxWhitComboBox("Deplacement diagonal bas gauche", "Diagonal_bas_gauche", false);
    private PanelCheckBoxWhitComboBox CheckDiagonalBD = new PanelCheckBoxWhitComboBox("Deplacement diagonal bas droit", "Diagonal_bas_droit", false);
    private PanelCheckBoxWhitComboBox CheckSautPiece = new PanelCheckBoxWhitComboBox("La piece n'est pas bloquer par ses allies", "Saut", false);
    private PanelDeplacementComplexe DeplacementComplexe;
    private int MaxRow;
    private int MaxCol;
    private int w;
    private int h;

    /**
     * Constructs a PanelDeplacementPiece object.
     *
     * @param NBRow the number of rows in the chessboard
     * @param NBCol the number of columns in the chessboard
     * @param p the piece to be configured
     * @param w the width of the piece
     * @param h the height of the piece
     * @param one the color of the light squares on the chessboard
     * @param two the color of the dark squares on the chessboard
     * @param Depl the color used to highlight the valid movement squares
     */
    public PanelDeplacementPiece(int NBRow, int NBCol, IPiece p, int w, int h, Color one, Color two, Color Depl) {
        piece = p;
        Dam = new PanelDamier(NBRow, NBCol, one, two, Depl);
        MaxCol = NBRow;
        MaxRow = NBCol;
        this.w = w;
        this.h = h;
        initComponent();
    }
    
    private void initComponent() {
        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        this.setLayout(new GridLayout(1, 2));
        
        ArrayList NumCol = new ArrayList();
        ArrayList NumRow = new ArrayList();
        for (int i = 1; i < MaxCol; i++) {
            NumCol.add(i+"");
        }
        for (int i = 1; i < MaxRow; i++) {
            NumRow.add(i+"");
        }
        
        InitBasicDeplacementCheckBox(NumRow, NumCol);
        
        DeplacementComplexe = new PanelDeplacementComplexe(MaxRow, MaxCol, this);
        
        pan.add(CheckHorizontalGauche);
        pan.add(CheckHorizontalDroite);
        pan.add(CheckVerticalHaut);
        pan.add(CheckVerticalBas);
        pan.add(CheckDiagonalHG);
        pan.add(CheckDiagonalHD);
        pan.add(CheckDiagonalBG);
        pan.add(CheckDiagonalBD);
        
        PanelMenuDeroulant menu = new PanelMenuDeroulant("Deplacement basic", pan);
        PanelMenuDeroulant menuComplexe = new PanelMenuDeroulant("Deplacement personnaliser", DeplacementComplexe);
        
        JPanel PanelFullMenu = new JPanel();
        PanelFullMenu.setLayout(new BoxLayout(PanelFullMenu, BoxLayout.PAGE_AXIS));
        
        PanelFullMenu.add(menu);
        PanelFullMenu.add(menuComplexe);
        PanelFullMenu.add(CheckSautPiece);
        
        this.add(Dam);
        this.add(PanelFullMenu);
    }

    /**
     * Initializes the checkboxes for basic movements.
     *
     * @param NumRow the list of row numbers
     * @param NumCol the list of column numbers
     */
    private void InitBasicDeplacementCheckBox(ArrayList NumRow, ArrayList NumCol) {
        CheckVerticalHaut.SetComboBox(NumRow, 1 + "");
        CheckVerticalBas.SetComboBox(NumRow, 1 + "");
        CheckHorizontalGauche.SetComboBox(NumCol, 1 + "");
        CheckHorizontalDroite.SetComboBox(NumCol, 1 + "");
        CheckDiagonalHG.SetComboBox(NumCol, 1 + "");
        CheckDiagonalHD.SetComboBox(NumCol, 1 + "");
        CheckDiagonalBG.SetComboBox(NumCol, 1 + "");
        CheckDiagonalBD.SetComboBox(NumCol, 1 + "");

        //set Listener
        CheckVerticalHaut.AddActionListener(this);
        CheckVerticalBas.AddActionListener(this);
        CheckHorizontalGauche.AddActionListener(this);
        CheckHorizontalDroite.AddActionListener(this);
        CheckDiagonalHG.AddActionListener(this);
        CheckDiagonalHD.AddActionListener(this);
        CheckDiagonalBG.AddActionListener(this);
        CheckDiagonalBD.AddActionListener(this);
        
        CheckVerticalHaut.AddMouseListener(this);
        CheckVerticalBas.AddMouseListener(this);
        CheckHorizontalGauche.AddMouseListener(this);
        CheckHorizontalDroite.AddMouseListener(this);
        CheckDiagonalHG.AddMouseListener(this);
        CheckDiagonalHD.AddMouseListener(this);
        CheckDiagonalBG.AddMouseListener(this);
        CheckDiagonalBD.AddMouseListener(this);
        
        CheckVerticalHaut.AddItemListernerCombBox(this);
        CheckVerticalBas.AddItemListernerCombBox(this);
        CheckHorizontalGauche.AddItemListernerCombBox(this);
        CheckHorizontalDroite.AddItemListernerCombBox(this);
        CheckDiagonalHG.AddItemListernerCombBox(this);
        CheckDiagonalHD.AddItemListernerCombBox(this);
        CheckDiagonalBG.AddItemListernerCombBox(this);
        CheckDiagonalBD.AddItemListernerCombBox(this);

        //set combobox visible false
        CheckVerticalHaut.SetVisibleComboBox(false);
        CheckVerticalBas.SetVisibleComboBox(false);
        CheckHorizontalGauche.SetVisibleComboBox(false);
        CheckHorizontalDroite.SetVisibleComboBox(false);
        CheckDiagonalHG.SetVisibleComboBox(false);
        CheckDiagonalHD.SetVisibleComboBox(false);
        CheckDiagonalBG.SetVisibleComboBox(false);
        CheckDiagonalBD.SetVisibleComboBox(false);
        // set the second check box
        CheckVerticalHaut.AddCheckBox("Deplacement non-plein", "pleinH", false, this);
        CheckVerticalBas.AddCheckBox("Deplacement non-plein", "pleinB", false, this);
        CheckHorizontalGauche.AddCheckBox("Deplacement non-plein", "pleinG", false, this);
        CheckHorizontalDroite.AddCheckBox("Deplacement non-plein", "pleinD", false, this);
        CheckDiagonalHG.AddCheckBox("Deplacement non-plein", "pleinHG", false, this);
        CheckDiagonalHD.AddCheckBox("Deplacement non-plein", "pleinHD", false, this);
        CheckDiagonalBG.AddCheckBox("Deplacement non-plein", "pleinBG", false, this);
        CheckDiagonalBD.AddCheckBox("Deplacement non-plein", "pleinBD", false, this);
        CheckVerticalHaut.SetVisibleCheckBox("pleinH", false);
        CheckVerticalBas.SetVisibleCheckBox("pleinB", false);
        CheckHorizontalGauche.SetVisibleCheckBox("pleinG", false);
        CheckHorizontalDroite.SetVisibleCheckBox("pleinD", false);
        CheckDiagonalHG.SetVisibleCheckBox("pleinHG", false);
        CheckDiagonalHD.SetVisibleCheckBox("pleinHD", false);
        CheckDiagonalBG.SetVisibleCheckBox("pleinBG", false);
        CheckDiagonalBD.SetVisibleCheckBox("pleinBD", false);
        
    }

    /**
     * Places the piece on the chessboard.
     *
     * @param x the x-coordinate of the piece
     * @param y the y-coordinate of the piece
     */
    private void PlacementPiece(String txt) {
        int nbDeplace;
        int y = MaxRow;
        int x = MaxCol;
        Dam.RemoveAllPiece();
        Dam.ClearColor();
        switch (txt) {
            case "Haut":
                Dam.PlaceOnePiece(piece, MaxRow, MaxCol / 2, w, h);
                nbDeplace = Integer.parseInt((String) CheckVerticalHaut.getSelectComboBox());
                if (nbDeplace == 1) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(- 1, 0, 1, "H"), MaxRow, MaxCol / 2);
                } else if (CheckVerticalHaut.isCheckedCheckBox("pleinH")) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(-nbDeplace, 0, 1, "H"), MaxRow, MaxCol / 2);
                } else {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(-1, 0, nbDeplace, "H"), MaxRow, MaxCol / 2);
                }
                break;
            case "Bas":
                Dam.PlaceOnePiece(piece, MaxRow / MaxRow, MaxCol / 2, w, h);
                nbDeplace = Integer.parseInt((String) CheckVerticalBas.getSelectComboBox());
                if (nbDeplace == 1) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(1, 0, 1, "B"), MaxRow / MaxRow, MaxCol / 2);
                } else if (CheckVerticalBas.isCheckedCheckBox("pleinB")) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(nbDeplace, 0, 1, "B"), MaxRow / MaxRow, MaxCol / 2);
                } else {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(1, 0, nbDeplace, "B"), MaxRow / MaxRow, MaxCol / 2);
                }
                break;
            case "Horizontal_gauche":
                Dam.PlaceOnePiece(piece, MaxRow, MaxCol, w, h);
                nbDeplace = Integer.parseInt((String) CheckHorizontalGauche.getSelectComboBox());
                if (nbDeplace == 1) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(0, -1, 1, "G"), MaxRow, MaxCol);
                } else if (CheckHorizontalGauche.isCheckedCheckBox("pleinG")) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(0, -nbDeplace, 1, "G"), MaxRow, MaxCol);
                } else {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(0, -1, nbDeplace, "G"), MaxRow, MaxCol);
                }
                break;
            case "Horizontal_droit":
                Dam.PlaceOnePiece(piece, MaxRow, MaxCol / MaxCol, w, h);
                nbDeplace = Integer.parseInt((String) CheckHorizontalDroite.getSelectComboBox());
                if (nbDeplace == 1) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(0, 1, 1, "D"), MaxRow, MaxCol / MaxCol);
                } else if (CheckHorizontalDroite.isCheckedCheckBox("pleinD")) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(0, nbDeplace, 1, "D"), MaxRow, MaxCol / MaxCol);
                } else {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(0, 1, nbDeplace, "D"), MaxRow, MaxCol / MaxCol);
                }
                break;
            case "Diagonal_haut_gauche":
                Dam.PlaceOnePiece(piece, MaxRow, MaxCol, w, h);
                nbDeplace = Integer.parseInt((String) CheckDiagonalHG.getSelectComboBox());
                if (nbDeplace == 1) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(-1, -1, 1, "HG"), MaxRow, MaxCol);
                } else if (CheckDiagonalHG.isCheckedCheckBox("pleinHG")) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(-nbDeplace, -nbDeplace, 1, "HG"), MaxRow, MaxCol);
                } else {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(-1, -1, nbDeplace, "HG"), MaxRow, MaxCol);
                }
                break;
            case "Diagonal_haut_droit":
                Dam.PlaceOnePiece(piece, MaxRow, MaxCol / MaxCol, w, h);
                nbDeplace = Integer.parseInt((String) CheckDiagonalHD.getSelectComboBox());
                if (nbDeplace == 1) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(-1, 1, 1, "HD"), MaxRow, MaxCol / MaxCol);
                } else if (CheckDiagonalHD.isCheckedCheckBox("pleinHD")) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(-nbDeplace, nbDeplace, 1, "HD"), MaxRow, MaxCol / MaxCol);
                } else {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(-1, 1, nbDeplace, "HD"), MaxRow, MaxCol / MaxCol);
                }
                break;
            case "Diagonal_bas_gauche":
                Dam.PlaceOnePiece(piece, MaxRow / MaxRow, MaxCol, w, h);
                nbDeplace = Integer.parseInt((String) CheckDiagonalBG.getSelectComboBox());
                if (nbDeplace == 1) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(1, -1, 1, "BG"), MaxRow / MaxRow, MaxCol);
                } else if (CheckDiagonalBG.isCheckedCheckBox("pleinBG")) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(nbDeplace, -nbDeplace, 1, "BG"), MaxRow / MaxRow, MaxCol);
                } else {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(1, -1, nbDeplace, "BG"), MaxRow / MaxRow, MaxCol);
                }
                break;
            case "Diagonal_bas_droit":
                Dam.PlaceOnePiece(piece, MaxRow / MaxRow, MaxCol / MaxCol, w, h);
                nbDeplace = Integer.parseInt((String) CheckDiagonalBD.getSelectComboBox());
                if (nbDeplace == 1) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(1, 1, 1, "BD"), MaxRow / MaxRow, MaxCol / MaxCol);
                } else if (CheckDiagonalBD.isCheckedCheckBox("pleinBD")) {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(nbDeplace, nbDeplace, 1, "BD"), MaxRow / MaxRow, MaxCol / MaxCol);
                } else {
                    Dam.ColorSimpleDeplacement(new DeplacementSimple(1, 1, nbDeplace, "BD"), MaxRow / MaxRow, MaxCol / MaxCol);
                }
                break;
        }
    }

    /**
     * Retrieves the possible movement options for a given piece.
     *
     * @param p The piece for which to retrieve the movement options.
     * @return The updated piece with the added movement options.
     */
    private IPiece getDeplacementPiece(IPiece p) {
        int nbDeplace;
        int y = MaxRow;
        int x = MaxCol;
        ArrayList<DeplacementSimple> listDepla = new ArrayList<>();
        if (CheckVerticalHaut.isSelected()) {
            nbDeplace = Integer.parseInt((String) CheckVerticalHaut.getSelectComboBox());
            if (nbDeplace == 1) {
                listDepla.add(new DeplacementSimple(- 1, 0, 1, "H"));
            } else if (CheckVerticalHaut.isCheckedCheckBox("pleinH")) {
                listDepla.add(new DeplacementSimple(-nbDeplace, 0, 1, "H"));
            } else {
                listDepla.add(new DeplacementSimple(-1, 0, nbDeplace, "H"));
            }
        }
        if (CheckVerticalBas.isSelected()) {
            nbDeplace = Integer.parseInt((String) CheckVerticalBas.getSelectComboBox());
            if (nbDeplace == 1) {
                listDepla.add(new DeplacementSimple(1, 0, 1, "B"));
            } else if (CheckVerticalBas.isCheckedCheckBox("pleinB")) {
                listDepla.add(new DeplacementSimple(nbDeplace, 0, 1, "B"));
            } else {
                listDepla.add(new DeplacementSimple(1, 0, nbDeplace, "B"));
            }
        }
        if (CheckHorizontalGauche.isSelected()) {
            nbDeplace = Integer.parseInt((String) CheckHorizontalGauche.getSelectComboBox());
            if (nbDeplace == 1) {
                listDepla.add(new DeplacementSimple(0, -1, 1, "G"));
            } else if (CheckHorizontalGauche.isCheckedCheckBox("pleinG")) {
                listDepla.add(new DeplacementSimple(0, -nbDeplace, 1, "G"));
            } else {
                listDepla.add(new DeplacementSimple(0, -1, nbDeplace, "G"));
            }
        }
        if (CheckHorizontalDroite.isSelected()) {
            nbDeplace = Integer.parseInt((String) CheckHorizontalDroite.getSelectComboBox());
            if (nbDeplace == 1) {
                listDepla.add(new DeplacementSimple(0, 1, 1, "D"));
            } else if (CheckHorizontalDroite.isCheckedCheckBox("pleinD")) {
                listDepla.add(new DeplacementSimple(0, nbDeplace, 1, "D"));
            } else {
                listDepla.add(new DeplacementSimple(0, 1, nbDeplace, "D"));
            }
        }
        if (CheckDiagonalHG.isSelected()) {
            nbDeplace = Integer.parseInt((String) CheckDiagonalHG.getSelectComboBox());
            if (nbDeplace == 1) {
                listDepla.add(new DeplacementSimple(-1, -1, 1, "HG"));
            } else if (CheckDiagonalHG.isCheckedCheckBox("pleinHG")) {
                listDepla.add(new DeplacementSimple(-nbDeplace, -nbDeplace, 1, "HG"));
            } else {
                listDepla.add(new DeplacementSimple(-1, -1, nbDeplace, "HG"));
            }
        }
        if (CheckDiagonalHD.isSelected()) {
            nbDeplace = Integer.parseInt((String) CheckDiagonalHD.getSelectComboBox());
            if (nbDeplace == 1) {
                listDepla.add(new DeplacementSimple(-1, 1, 1, "HD"));
            } else if (CheckDiagonalHD.isCheckedCheckBox("pleinHD")) {
                listDepla.add(new DeplacementSimple(-nbDeplace, nbDeplace, 1, "HD"));
            } else {
                listDepla.add(new DeplacementSimple(-1, 1, nbDeplace, "HD"));
            }
        }
        if (CheckDiagonalBG.isSelected()) {
            nbDeplace = Integer.parseInt((String) CheckDiagonalBG.getSelectComboBox());
            if (nbDeplace == 1) {
                listDepla.add(new DeplacementSimple(1, -1, 1, "BG"));
            } else if (CheckDiagonalBG.isCheckedCheckBox("pleinBG")) {
                listDepla.add(new DeplacementSimple(nbDeplace, -nbDeplace, 1, "BG"));
            } else {
                listDepla.add(new DeplacementSimple(1, -1, nbDeplace, "BG"));
            }
        }
        if (CheckDiagonalBD.isSelected()) {
            nbDeplace = Integer.parseInt((String) CheckDiagonalBD.getSelectComboBox());
            if (nbDeplace == 1) {
                listDepla.add(new DeplacementSimple(1, 1, 1, "BD"));
            } else if (CheckDiagonalBD.isCheckedCheckBox("pleinBD")) {
                listDepla.add(new DeplacementSimple(nbDeplace, nbDeplace, 1, "BD"));
            } else {
                listDepla.add(new DeplacementSimple(1, 1, nbDeplace, "BD"));
            }
        }
        p.addMutiDeplacementPiece(listDepla);
        return p;
    }
    
    private void loadPiece(Piece p) {
        ArrayList<IDeplacement> listDeplacement = p.getDeplacementPiece();
        int SizeDeplacement = listDeplacement.size();
        String id = new String();
        int nbDep = 0;
        
        for (int i = 0; i < SizeDeplacement; i++) {
            id = listDeplacement.get(i).getIDDeplacement();
            if (!id.equalsIgnoreCase("DC")) {
                DeplacementSimple simp = (DeplacementSimple) listDeplacement.get(i);
                nbDep = simp.getNbDeplacement();
                int y = simp.getDeplacementY();
                int x = simp.getDeplacementX();
                switch (id) {
                    case "H":
                        CheckVerticalHaut.setSelect(true);
                        CheckVerticalHaut.SetVisibleCheckBox("pleinH", true);
                        CheckVerticalHaut.SetVisibleComboBox(true);
                        CheckVerticalHaut.SelectItemCombot(String.valueOf(nbDep));
                        if ((x > 1 || y > 1) || (x < -1 || y < -1)) {
                            CheckVerticalHaut.setOtherCheckBoxSelect(true, "pleinH");
                        }
                        PlacementPiece("Haut");
                        break;
                    case "B":
                        CheckVerticalBas.setSelect(true);
                        CheckVerticalBas.SetVisibleCheckBox("pleinB", true);
                        CheckVerticalBas.SetVisibleComboBox(true);
                        CheckVerticalBas.SelectItemCombot(String.valueOf(nbDep));
                        if ((x > 1 || y > 1) || (x < -1 || y < -1)) {
                            CheckVerticalBas.setOtherCheckBoxSelect(true, "pleinB");
                        }
                        PlacementPiece("Bas");
                        break;
                    case "G":
                        CheckHorizontalGauche.setSelect(true);
                        CheckHorizontalGauche.SetVisibleCheckBox("pleinG", true);
                        CheckHorizontalGauche.SetVisibleComboBox(true);
                        CheckHorizontalGauche.SelectItemCombot(String.valueOf(nbDep));
                        if ((x > 1 || y > 1) || (x < -1 || y < -1)) {
                            CheckHorizontalGauche.setOtherCheckBoxSelect(true, "pleinG");
                        }
                        PlacementPiece("Horizontal_gauche");
                        break;
                    case "D":
                        CheckHorizontalDroite.setSelect(true);
                        CheckHorizontalDroite.SetVisibleCheckBox("pleinD", true);
                        CheckHorizontalDroite.SetVisibleComboBox(true);
                        CheckHorizontalDroite.SelectItemCombot(String.valueOf(nbDep));
                        if ((x > 1 || y > 1) || (x < -1 || y < -1)) {
                            CheckHorizontalDroite.setOtherCheckBoxSelect(true, "pleinD");
                        }
                        PlacementPiece("Horizontal_droit");
                        break;
                    case "HG":
                        CheckDiagonalHG.setSelect(true);
                        CheckDiagonalHG.SetVisibleCheckBox("pleinHG", true);
                        CheckDiagonalHG.SetVisibleComboBox(true);
                        CheckDiagonalHG.SelectItemCombot(String.valueOf(nbDep));
                        if ((x > 1 || y > 1) || (x < -1 || y < -1)) {
                            CheckDiagonalHG.setOtherCheckBoxSelect(true, "pleinHG");
                        }
                        PlacementPiece("Diagonal_haut_gauche");
                        break;
                    case "HD":
                        CheckDiagonalHD.setSelect(true);
                        CheckDiagonalHD.SetVisibleCheckBox("pleinHD", true);
                        CheckDiagonalHD.SetVisibleComboBox(true);
                        CheckDiagonalHD.SelectItemCombot(String.valueOf(nbDep));
                        if ((x > 1 || y > 1) || (x < -1 || y < -1)) {
                            CheckDiagonalHD.setOtherCheckBoxSelect(true, "pleinHD");
                        }
                        PlacementPiece("Diagonal_haut_droit");
                        break;
                    case "BD":
                        CheckDiagonalBD.setSelect(true);
                        CheckDiagonalBD.SetVisibleCheckBox("pleinBD", true);
                        CheckDiagonalBD.SetVisibleComboBox(true);
                        CheckDiagonalBD.SelectItemCombot(String.valueOf(nbDep));
                        if ((x > 1 || y > 1) || (x < -1 || y < -1)) {
                            CheckDiagonalBD.setOtherCheckBoxSelect(true, "pleinBD");
                        }
                        PlacementPiece("Diagonal_bas_gauche");
                        break;
                    case "BG":
                        CheckDiagonalBG.setSelect(true);
                        CheckDiagonalBG.SetVisibleCheckBox("pleinBG", true);
                        CheckDiagonalBG.SetVisibleComboBox(true);
                        CheckDiagonalBG.SelectItemCombot(String.valueOf(nbDep));
                        if ((x > 1 || y > 1) || (x < -1 || y < -1)) {
                            CheckDiagonalBG.setOtherCheckBoxSelect(true, "pleinBG");
                        }
                        PlacementPiece("Diagonal_bas_gauche");
                        break;
                }
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String Action = e.getActionCommand();
        //place one piece of the first time
        if (Action.contains("plein")) {
            switch (Action) {
                case "pleinH":
                    PlacementPiece("Haut");
                    break;
                case "pleinB":
                    PlacementPiece("Bas");
                    break;
                case "pleinG":
                    PlacementPiece("Horizontal_gauche");
                    break;
                case "pleinD":
                    PlacementPiece("Horizontal_droit");
                    break;
                case "pleinHD":
                    PlacementPiece("Diagonal_haut_droit");
                    break;
                case "pleinHG":
                    PlacementPiece("Diagonal_haut_gauche");
                    break;
                case "pleinBG":
                    PlacementPiece("Diagonal_bas_gauche");
                    break;
                case "pleinBD":
                    PlacementPiece("Diagonal_bas_droit");
                    break;
            }
        } else {
            //clears checkerboard and place piece
            switch (Action) {
                case "Haut":
                    if (CheckVerticalHaut.isSelected()) {
                        CheckVerticalHaut.SetVisibleComboBox(true);
                        PlacementPiece(Action);
                        CheckVerticalHaut.SetVisibleCheckBox("pleinH", true);
                        
                    } else {
                        CheckVerticalHaut.SetVisibleComboBox(false);
                        Dam.RemoveAllPiece();
                        Dam.ClearColor();
                        CheckVerticalHaut.SetVisibleCheckBox("pleinH", false);
                    }
                    
                    break;
                case "Bas":
                    if (CheckVerticalBas.isSelected()) {
                        CheckVerticalBas.SetVisibleComboBox(true);
                        PlacementPiece(Action);
                        CheckVerticalBas.SetVisibleCheckBox("pleinB", true);
                        
                    } else {
                        CheckVerticalBas.SetVisibleComboBox(false);
                        Dam.RemoveAllPiece();
                        Dam.ClearColor();
                        CheckVerticalBas.SetVisibleCheckBox("pleinB", false);
                    }
                    break;
                case "Horizontal_gauche":
                    if (CheckHorizontalGauche.isSelected()) {
                        CheckHorizontalGauche.SetVisibleComboBox(true);
                        PlacementPiece(Action);
                        CheckHorizontalGauche.SetVisibleCheckBox("pleinG", true);
                        
                    } else {
                        CheckHorizontalGauche.SetVisibleComboBox(false);
                        Dam.RemoveAllPiece();
                        Dam.ClearColor();
                        CheckHorizontalGauche.SetVisibleCheckBox("pleinG", false);
                    }
                    break;
                case "Horizontal_droit":
                    if (CheckHorizontalDroite.isSelected()) {
                        CheckHorizontalDroite.SetVisibleComboBox(true);
                        PlacementPiece(Action);
                        CheckHorizontalDroite.SetVisibleCheckBox("pleinD", true);
                        
                    } else {
                        CheckHorizontalDroite.SetVisibleComboBox(false);
                        Dam.RemoveAllPiece();
                        Dam.ClearColor();
                        CheckHorizontalDroite.SetVisibleCheckBox("pleinD", false);
                    }
                    break;
                case "Diagonal_haut_gauche":
                    if (CheckDiagonalHG.isSelected()) {
                        CheckDiagonalHG.SetVisibleComboBox(true);
                        PlacementPiece(Action);
                        CheckDiagonalHG.SetVisibleCheckBox("pleinHG", true);
                        
                    } else {
                        CheckDiagonalHG.SetVisibleComboBox(false);
                        Dam.RemoveAllPiece();
                        Dam.ClearColor();
                        CheckDiagonalHG.SetVisibleCheckBox("pleinHG", false);
                    }
                    break;
                case "Diagonal_haut_droit":
                    if (CheckDiagonalHD.isSelected()) {
                        CheckDiagonalHD.SetVisibleComboBox(true);
                        PlacementPiece(Action);
                        CheckDiagonalHD.SetVisibleCheckBox("pleinHD", true);
                    } else {
                        CheckDiagonalHD.SetVisibleComboBox(false);
                        Dam.RemoveAllPiece();
                        Dam.ClearColor();
                        CheckDiagonalHD.SetVisibleCheckBox("pleinHD", false);
                    }
                    break;
                case "Diagonal_bas_gauche":
                    if (CheckDiagonalBG.isSelected()) {
                        CheckDiagonalBG.SetVisibleComboBox(true);
                        PlacementPiece(Action);
                        CheckDiagonalBG.SetVisibleCheckBox("pleinBG", true);
                        
                    } else {
                        CheckDiagonalBG.SetVisibleComboBox(false);
                        Dam.RemoveAllPiece();
                        Dam.ClearColor();
                        CheckDiagonalBG.SetVisibleCheckBox("pleinBG", false);
                    }
                    break;
                case "Diagonal_bas_droit":
                    if (CheckDiagonalBD.isSelected()) {
                        CheckDiagonalBD.SetVisibleComboBox(true);
                        PlacementPiece(Action);
                        CheckDiagonalBD.SetVisibleCheckBox("pleinBD", true);
                    } else {
                        CheckDiagonalBD.SetVisibleComboBox(false);
                        Dam.RemoveAllPiece();
                        Dam.ClearColor();
                        CheckDiagonalBD.SetVisibleCheckBox("pleinBD", false);
                    }
                    break;
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        JCheckBox Check = (JCheckBox) e.getSource();
        if (Check.isSelected()) {
            PlacementPiece(Check.getActionCommand());
        }
        
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            JComboBox combo = (JComboBox) e.getSource();
            PlacementPiece(combo.getActionCommand());
        }
    }
    
    @Override
    public boolean isEmpty() {
        if ((CheckDiagonalBD.isSelected()) || (CheckDiagonalBG.isSelected())
                || (CheckDiagonalHD.isSelected()) || (CheckDiagonalHG.isSelected())
                || (CheckHorizontalDroite.isSelected()) || (CheckHorizontalGauche.isSelected())
                || (CheckVerticalBas.isSelected()) || (CheckVerticalHaut.isSelected())) {
            return false;
        } else if (!DeplacementComplexe.isEmpty()) {
            return false;
        }
        
        PopUpErreur pop = new PopUpErreur("Erreur veuillez assigner un deplacement");
        return true;
    }
    
    @Override
    public IPiece getPieceData(IPiece p) {
        p = DeplacementComplexe.getPieceData(p);
        if (!isEmpty()) {
            p = getDeplacementPiece(p);
        }
        
        p.setSautPiece(CheckSautPiece.isSelected());
        
        return p;
    }
    
    @Override
    public void PlacementPieceComplexe(String txt, boolean clear) {
        if (clear) {
            Dam.RemoveAllPiece();
            Dam.ClearColor();
        }
        
        switch (txt) {
            case "Centre":
                Dam.PlaceOnePiece(piece, MaxRow / 2, MaxCol / 2, w, h);
                break;
            case "Haut-Gauche":
                Dam.PlaceOnePiece(piece, MaxRow / MaxRow, MaxCol / MaxCol, w, h);
                break;
            case "Haut-Droit":
                Dam.PlaceOnePiece(piece, MaxRow / MaxRow, MaxCol, w, h);
                break;
            case "Bas-Gauche":
                Dam.PlaceOnePiece(piece, MaxRow, MaxCol / MaxCol, w, h);
                break;
            case "Bas-Droit":
                Dam.PlaceOnePiece(piece, MaxRow, MaxCol, w, h);
                break;
            default:
        }
    }
    
    @Override
    public void ColorDeplacement(int Row, int Col, boolean full, String placement) {
        Dam.ClearColor();
        switch (placement) {
            case "Centre":
                Dam.ColorComplexeDeplacement(new DeplacementComplexe(Row, Col, placement, full), MaxRow / 2, MaxCol / 2);
                break;
            case "Haut-Gauche":
                Dam.ColorComplexeDeplacement(new DeplacementComplexe(Row, Col, placement, full), MaxRow / MaxRow, MaxCol / MaxCol);
                break;
            case "Haut-Droit":
                Dam.ColorComplexeDeplacement(new DeplacementComplexe(Row, Col, placement, full), MaxRow / MaxRow, MaxCol);
                break;
            case "Bas-Gauche":
                Dam.ColorComplexeDeplacement(new DeplacementComplexe(Row, Col, placement, full), MaxRow, MaxCol / MaxCol);
                break;
            case "Bas-Droit":
                Dam.ColorComplexeDeplacement(new DeplacementComplexe(Row, Col, placement, full), MaxRow, MaxCol);
                break;
            default:
        }
        Dam.updateUI();
    }
    
    @Override
    public void UpdatePieceData(IPiece p) {
        this.piece = p;
    }
    
    @Override
    public void LoadPieceData(IPiece p) {
        
        loadPiece((Piece) p);
        DeplacementComplexe.LoadPieceData(p);
        
        CheckSautPiece.setSelect(p.isSautPiece());
        
    }
    
    @Override
    public void Clear() {
        Dam.ClearColor();
        Dam.RemoveAllPiece();
        
        CheckVerticalHaut.setSelect(false);
        CheckVerticalHaut.SetVisibleCheckBox("pleinH", false);
        CheckVerticalHaut.SetVisibleComboBox(false);
        
        CheckVerticalBas.setSelect(false);
        CheckVerticalBas.SetVisibleCheckBox("pleinB", false);
        CheckVerticalBas.SetVisibleComboBox(false);
        
        CheckHorizontalGauche.setSelect(false);
        CheckHorizontalGauche.SetVisibleCheckBox("pleinG", false);
        CheckHorizontalGauche.SetVisibleComboBox(false);
        
        CheckHorizontalDroite.setSelect(false);
        CheckHorizontalDroite.SetVisibleCheckBox("pleinD", false);
        CheckHorizontalDroite.SetVisibleComboBox(false);
        
        CheckDiagonalHG.setSelect(false);
        CheckDiagonalHG.SetVisibleCheckBox("pleinHG", false);
        CheckDiagonalHG.SetVisibleComboBox(false);
        
        CheckDiagonalHD.setSelect(false);
        CheckDiagonalHD.SetVisibleCheckBox("pleinHD", false);
        CheckDiagonalHD.SetVisibleComboBox(false);
        
        CheckDiagonalBG.setSelect(false);
        CheckDiagonalBG.SetVisibleCheckBox("pleinBG", false);
        CheckDiagonalBG.SetVisibleComboBox(false);
        
        CheckDiagonalBD.setSelect(false);
        CheckDiagonalBD.SetVisibleCheckBox("pleinBD", false);
        CheckDiagonalBD.SetVisibleComboBox(false);
        
        CheckSautPiece.setSelect(false);
    }
    
    @Override
    public void Resize(int w, int h) {
        Dam.ReSize(w, h);
        this.w = w;
        this.h = h;
    }
    
}
