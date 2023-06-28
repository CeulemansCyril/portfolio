/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Panel;

import JeuxDame.Object.ObjPiece.DeplacementComplexe;
import JeuxDame.Object.ObjPiece.DeplacementSimple;
import JeuxDame.Object.ObjPiece.IPiece;
import JeuxDame.Object.ObjPiece.Placement;
import JeuxDame.Object.Piece;
import JeuxDame.Object.Tuile;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Utilisateur
 */
public class PanelDamier extends JPanel {

    private Tuile Damier[][];
    private int SizeCol;
    private int SizeRow;
    private Color ColorOne;
    private Color ColorSecond;
    private Color ColorDeplacement;

    /**
     * Constructs a PanelDamier object with the specified parameters.
     *
     * @param SizeCol The number of columns in the damier.
     * @param SizeRow The number of rows in the damier.
     * @param ColorOne The color of the first type of tile.
     * @param ColorSecond The color of the second type of tile.
     * @param Deplacement The color for highlighting tile movements.
     */
    public PanelDamier(int SizeCol, int SizeRow, Color ColorOne, Color ColorSecond, Color Deplacement) {
        Damier = new Tuile[SizeRow + 1][SizeCol + 1];
        this.SizeCol = SizeCol + 1;
        this.SizeRow = SizeRow + 1;
        this.ColorOne = ColorOne;
        this.ColorSecond = ColorSecond;
        this.ColorDeplacement = Deplacement;
        initComponent();
    }

    private void initComponent() {
        this.setLayout(new GridLayout(SizeRow, SizeCol));
        //set separator
        Tuile tuper = new Tuile();
        tuper.setCouleur(Color.gray);
        Damier[0][0] = tuper;

        //set Alphabet
        int Ascii = 65;
        int boucle = 1;
        for (int col = 1; col < SizeCol; col++) {
            if (Ascii == 90) {
                Ascii = 65;
                boucle++;
            }
            char txt = (char) Ascii;
            Tuile lab = new Tuile();
            String texte = "";
            for (int i = 0; i < boucle; i++) {
                texte = texte + txt;
            }
            lab.setText(texte);
            lab.setCouleur(Color.lightGray);
            Damier[0][col] = lab;
            Ascii++;
        }

        for (int row = 1; row < SizeRow; row++) {
            //set number
            Tuile tuil = new Tuile();
            tuil.setText("" + row);
            Damier[row][0] = tuil;
            //SetColor
            for (int col = 1; col < SizeCol; col++) {
                Tuile tuile = new Tuile();
                tuile.setPLace(col, row);
                if (((row % 2 == 0) && (col % 2 == 0)) || ((row % 2 == 1) && (col % 2 == 1))) {
                    tuile.setCouleur(ColorOne);
                } else {
                    tuile.setCouleur(ColorSecond);
                }

                Damier[row][col] = tuile;
            }
        }

        printDamier();

    }
    
    public void UpdateViewDamier(){
        for (int row = 0; row < SizeRow; row++) {
            for (int col = 0; col < SizeCol; col++) {
               Damier[row][col].updateUI();
            }
        }
    }

    private void printDamier() {
        for (int row = 0; row < SizeRow; row++) {
            for (int col = 0; col < SizeCol; col++) {
                this.add(Damier[row][col]);
            }
        }
    }

    public Tuile getTuile(int Row, int Col) {
        if (((Col >= 1) && (Col < SizeCol)) && ((Row >= 1) && (Row < SizeRow))) {
            return Damier[Row][Col];
        }
        return null;
    }

    public ArrayList<Tuile> getTuileWhitPieceTeam(String team) {
        ArrayList<Tuile> list = new ArrayList<>();

        for (int Row = 0; Row < SizeRow; Row++) {
            for (int Col = 0; Col < SizeCol; Col++) {
                Tuile t = getTuile(Row, Col);
                if ((t.getPieceTuile() != null) && (t.getPieceTuile().getTeam().equalsIgnoreCase(team))) {
                    list.add(t);
                }
            }
        }

        return list;
    }

    /**
     * Colors the specified column with the designated color for highlighting.
     *
     * @param Col The column to be colored.
     */
    public void ColorCol(int Col) {
        ClearColor();
        for (int row = 1; row < SizeRow; row++) {
            Damier[row][Col].setCouleur(ColorDeplacement);
        }
    }

    /**
     * Colors the specified row with the designated color for highlighting.
     *
     * @param Row The row to be colored.
     */
    public void ColorRow(int Row) {
        ClearColor();
        if (Row == 0) {
            Row = 1;
        }
        for (int col = 1; col < SizeCol; col++) {
            Damier[Row][col].setCouleur(ColorDeplacement);
        }
    }

    /**
     * Colors a single tile on the board based on a simple movement.
     *
     * @param Dep The simple movement representing the tile to be colored.
     */
    public void ColorOneSimpleDeplacement(DeplacementSimple Dep) {
        Damier[Dep.getDeplacementY()][Dep.getDeplacementX()].setCouleur(ColorDeplacement);
    }

    /**
     * Colors all tiles based on a simple movement.
     *
     * @param Dep The simple movement representing the pattern of tiles to be
     * colored.
     * @param y The starting row position of the movement.
     * @param x The starting column position of the movement.
     */
    //color all piece move
    public void ColorSimpleDeplacement(DeplacementSimple Dep, int y, int x) {
        int count = Dep.getNbDeplacement();
        int CountX = x;
        int CountY = y;

        for (int i = 0; i < count; i++) {
            CountY = CountY + Dep.getDeplacementY();
            CountX = CountX + Dep.getDeplacementX();
            if (((CountX >= 1) && (CountX < SizeCol)) && ((CountY >= 1) && (CountY < SizeRow))) {
                Damier[CountY][CountX].setCouleur(ColorDeplacement);

            }

        }
    }

    //color all piece move
    public void ColorSimpleDeplacementGame(DeplacementSimple Dep, int y, int x, boolean saut, String teamPlay, boolean indirect) {
        int count = Dep.getNbDeplacement();
        int CountX = x;
        int CountY = y;
        int i = 0;
        boolean flag = true;
        while (i < count && flag) {
            CountY = CountY + Dep.getDeplacementY();
            CountX = CountX + Dep.getDeplacementX();
            if (((CountX >= 1) && (CountX < SizeCol)) && ((CountY >= 1) && (CountY < SizeRow))) {
                Damier[CountY][CountX].setCouleur(ColorDeplacement);
                Piece p = (Piece) Damier[CountY][CountX].getPieceTuile();
                //prise indirect
                if ((p != null) && (!p.getTeam().equalsIgnoreCase(teamPlay)) && (indirect)) {
                    CountY = CountY + Dep.getDeplacementY();
                    CountX = CountX + Dep.getDeplacementX();
                    if (((CountX >= 1) && (CountX < SizeCol)) && ((CountY >= 1) && (CountY < SizeRow))) {
                        Damier[CountY][CountX].setCouleur(ColorDeplacement);
                    }
                    flag=false;
                    //piece blocquer   
                } else if ((p != null) && (p.getTeam().equalsIgnoreCase(teamPlay)) && (!saut)) {
                    flag = false;
                }
            }
            i++;
        }
    }

    public void ColorComplexeDeplacementGame(DeplacementComplexe Dep, int y, int x, boolean indirect, String teamPlay) {
        if (Dep.isColorAll()) {
            boolean flag = false;
            int count = 0;
            if (Dep.getDeplacementX() < 0) {
                count = -Dep.getDeplacementX();
                flag = true;
            } else {
                count = Dep.getDeplacementX();
            }
            int colX = 0;
            //col
            for (int col = 1; col < count; col++) {
                colX = x;
                //negatif         
                if (flag) {
                    colX = colX - col;
                } else {
                    colX = colX + col;
                }

                if ((colX >= 1) && (colX < SizeCol)) {
                    Damier[y][colX].setCouleur(ColorDeplacement);
                }
            }

            flag = false;
            count = 0;
            if (Dep.getDeplacementY() < 0) {
                count = -Dep.getDeplacementY();
                flag = true;
            } else {
                count = Dep.getDeplacementY();
            }
            int colY = 0;
            //col
            for (int row = 1; row < count; row++) {
                colY = x;
                //negatif         
                if (flag) {
                    colY = colY - row;
                } else {
                    colY = colY + row;
                }

                if ((colY >= 1) && (colY < SizeCol)) {
                    Damier[colY][colX].setCouleur(ColorDeplacement);
                }
            }
            if (indirect) {
                Piece p = (Piece) Damier[colY][colX].getPieceTuile();
                if (p != null && !p.getTeam().equalsIgnoreCase(teamPlay)) {
                    int yS = 0;
                    int xS = 0;
                    //set xS
                    if (Dep.getDeplacementX() < 0) {
                        xS = Dep.getDeplacementX() / -(Dep.getDeplacementX());
                    } else {
                        xS = Dep.getDeplacementX() / Dep.getDeplacementX();
                    }
                    //set yS
                    if (Dep.getDeplacementY() < 0) {
                        yS = Dep.getDeplacementY() / -(Dep.getDeplacementY());
                    } else {
                        yS = Dep.getDeplacementY() / Dep.getDeplacementY();
                    }

                    ColorSimpleDeplacement(new DeplacementSimple(yS, xS, 1, "DC"), colY, colX);
                }
            }

        } else {
            int CountX = x;
            int CountY = y;

            CountY = CountY + Dep.getDeplacementY();
            CountX = CountX + Dep.getDeplacementX();
            if ((CountX >= 1) && (CountX < SizeCol) && (CountY >= 1) && (CountY < SizeRow)) {
                Damier[CountY][CountX].setCouleur(ColorDeplacement);
                if (indirect) {
                    Piece p = (Piece) Damier[CountY][CountX].getPieceTuile();
                    if (p != null && !p.getTeam().equalsIgnoreCase(teamPlay)) {
                        int yS = 0;
                        int xS = 0;
                        //set xS
                        if (Dep.getDeplacementX() < 0) {
                            xS = Dep.getDeplacementX() / -(Dep.getDeplacementX());
                        } else {
                            xS = Dep.getDeplacementX() / Dep.getDeplacementX();
                        }
                        //set yS
                        if (Dep.getDeplacementY() < 0) {
                            yS = Dep.getDeplacementY() / -(Dep.getDeplacementY());
                        } else {
                            yS = Dep.getDeplacementY() / Dep.getDeplacementY();
                        }

                        ColorSimpleDeplacement(new DeplacementSimple(yS, xS, 1, "DC"), CountY, CountX);
                    }
                }
            }

        }

    }

    /**
     * Colors all tiles based on a complex movement.
     *
     * @param Dep The complex movement representing the pattern of tiles to be
     * colored.
     * @param PieceRow The starting row position of the piece.
     * @param PieceCol The starting column position of the piece.
     */
    //color all piece move
    public void ColorComplexeDeplacement(DeplacementComplexe Dep, int PieceRow, int PieceCol) {
        //color one tuile
        if (Dep.isColorAll()) {
            Damier[PieceRow + Dep.getDeplacementY()][PieceCol + Dep.getDeplacementX()].setCouleur(ColorDeplacement);
        } else {
            //color all tuil colone
            int colonne = PieceCol;
            if ((Dep.getDeplacementX() < 0) && ((Dep.getIDDeplacement().contains("Droit")) || (Dep.getIDDeplacement().equals("Centre")))) {
                int countX = Dep.getDeplacementX();
                for (int i = 0; countX < i; countX++) {
                    Damier[PieceRow][PieceCol + countX].setCouleur(ColorDeplacement);
                }
                colonne = PieceCol + Dep.getDeplacementX();
            } else if (Dep.getDeplacementX() > 0) {
                for (int i = 0; i < Dep.getDeplacementX(); i++) {
                    Damier[PieceRow][PieceCol + i + 1].setCouleur(ColorDeplacement);
                }
                colonne = PieceCol + Dep.getDeplacementX();
            }
            //color all tuile row
            if ((Dep.getDeplacementY() < 0) && ((Dep.getIDDeplacement().contains("Gauche")) || (Dep.getIDDeplacement().equals("Centre")))) {
                int countY = Dep.getDeplacementY();
                for (int i = 0; countY < i; countY++) {
                    Damier[PieceRow + countY][colonne].setCouleur(ColorDeplacement);
                }
            } else if (Dep.getDeplacementY() > 0) {
                for (int i = 0; i < Dep.getDeplacementY(); i++) {
                    Damier[PieceRow + i + 1][colonne].setCouleur(ColorDeplacement);
                }
            }
        }
    }

    /**
     * Sets the default color for all tiles on the board.
     */
    //set default color
    public void ClearColor() {
        for (int row = 1; row < SizeRow; row++) {
            //SetColor
            for (int col = 1; col < SizeCol; col++) {

                if (((row % 2 == 0) && (col % 2 == 0)) || ((row % 2 == 1) && (col % 2 == 1))) {
                    Damier[row][col].setCouleur(ColorOne);
                } else {
                    Damier[row][col].setCouleur(ColorSecond);
                }

            }
        }
    }

    /**
     * Resizes the game board.
     *
     * @param Row The new number of rows for the game board.
     * @param Col The new number of columns for the game board.
     */
    public void ReSizeDamier(int Row, int Col) {
        this.SizeCol = Col;
        this.SizeRow = Row;
        Damier = new Tuile[Row + 1][Col + 1];
        initComponent();
    }

    /**
     * Recolors the game board with the specified colors and clears the current
     * colors.
     *
     * @param one The color to set for the first type of tile.
     * @param two The color to set for the second type of tile.
     * @param depl The color to set for the tiles representing a movement.
     */
    public void ReColor(Color one, Color two, Color depl) {
        this.ColorOne = one;
        this.ColorSecond = two;
        this.ColorDeplacement = depl;
        ClearColor();
    }

    /**
     * Adds a MouseListener to all tiles on the game board.
     *
     * @param m The MouseListener to add.
     */
    public void AddMouseListenerAllTuile(MouseListener m) {
        for (int row = 1; row < SizeRow; row++) {
            for (int col = 1; col < SizeCol; col++) {
                Damier[row][col].addMouseListener(m);
            }
        }
    }

    /**
     * Removes all pieces from the game board.
     */
    public void RemoveAllPiece() {
        for (int row = 1; row < SizeRow; row++) {
            for (int col = 1; col < SizeCol; col++) {
                Damier[row][col].RemovePieceTuile();
            }
        }
    }

    /**
     * Removes a specific piece from all tiles on the game board.
     *
     * @param p The piece to remove.
     */
    public void RemoveOnePieceInAllTuile(IPiece p) {
        ArrayList<Placement> listPlace = p.getAllPlacement();
        int Size = listPlace.size();
        for (int j = 0; j < Size; j++) {
            Damier[listPlace.get(j).getPlacementY()][listPlace.get(j).getPlacementX()].RemovePieceTuile();
        }
    }

    /**
     * Checks if all placements of a piece on the game board are valid.
     *
     * @param p The piece to check placements for.
     * @return {@code true} if all placements are valid, {@code false}
     * otherwise.
     */
    public boolean CheckAllPlacement(IPiece p) {
        ArrayList<Placement> listDep = p.getAllPlacement();
        int Size = listDep.size();
        int flag = 0;

        for (int i = 0; i < Size; i++) {
            int Row = listDep.get(i).getPlacementY();
            int Col = listDep.get(i).getPlacementX();

            if (((listDep.get(i).getPlacementY() > SizeRow) && (listDep.get(i).getPlacementX() > SizeCol)) && (Damier[Row][Col].getPieceTuile() != null)) {
                flag++;
            }
        }
        if (listDep.isEmpty()) {
            return true;
        }
        if (listDep.size() == flag) {
            return false;
        }

        return true;
    }

    /**
     * Places a piece on a specific tile on the game board.
     *
     * @param p The piece to place.
     * @param Row The row position of the tile.
     * @param Col The column position of the tile.
     * @param w The width of the piece.
     * @param h The height of the piece.
     */
    public void PlaceOnePiece(IPiece p, int Row, int Col, int w, int h) {
        Damier[Row][Col].setPieceTuile(p, w, h);
    }

    /**
     * Places multiple pieces on the game board.
     *
     * @param listPi The list of pieces to place.
     * @param w The width of the pieces.
     * @param h The height of the pieces.
     */
    public void PlaceMultyPiece(ArrayList<IPiece> listPi, int w, int h) {
        int SizeP = listPi.size();

        for (int i = 0; i < SizeP; i++) {
            ArrayList<Placement> listPlace = listPi.get(i).getAllPlacement();
            int Size = listPlace.size();
            for (int j = 0; j < Size; j++) {
                Damier[listPlace.get(j).getPlacementY()][listPlace.get(j).getPlacementX()].setPieceTuile(listPi.get(i), w, h);
            }
        }
    }

    /**
     * Places a piece on multiple tiles on the game board.
     *
     * @param p The piece to place.
     * @param w The width of the piece.
     * @param h The height of the piece.
     */
    public void PlaceOnePieceInMultyTuile(IPiece p, int w, int h) {

        ArrayList<Placement> listPlace = p.getAllPlacement();
        int Size = listPlace.size();
        for (int j = 0; j < Size; j++) {
            int x = listPlace.get(j).getPlacementX();
            int y = listPlace.get(j).getPlacementY();
            if (((y < SizeRow) && (x < SizeCol))) {
                Damier[y][x].setPieceTuile(p, w, h);
            }
        }

    }

    /**
     * Checks if the game board is empty (contains no pieces).
     *
     * @return {@code true} if the game board is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        for (int row = 1; row < SizeRow; row++) {
            for (int col = 1; col < SizeCol; col++) {
                if (!Damier[row][col].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets the placements of all pieces on the game board.
     *
     * @return A list of placements representing the positions of all pieces.
     */
    public ArrayList<Placement> getAllPlacementPiece() {
        ArrayList<Placement> listPlacement = new ArrayList<>();

        if (!isEmpty()) {
            for (int row = 1; row < SizeRow; row++) {
                for (int col = 1; col < SizeCol; col++) {
                    if (!Damier[row][col].isEmpty()) {
                        listPlacement.add(new Placement(col, row));
                    }
                }
            }
        }

        return listPlacement;
    }

    /**
     * Gets the placements of a specific piece on the game board.
     *
     * @param p The piece to get placements for.
     * @return A list of placements representing the positions of the specified
     * piece.
     */
    public ArrayList<Placement> getAllPlacementOnePiece(Piece p) {
        ArrayList<Placement> listPlacement = new ArrayList<>();

        if (!isEmpty()) {
            for (int row = 1; row < SizeRow; row++) {
                for (int col = 1; col < SizeCol; col++) {
                    if (!Damier[row][col].isEmpty()) {
                        Piece damP = (Piece) Damier[row][col].getPieceTuile();
                        if (damP.getName().equalsIgnoreCase(p.getName())) {
                            listPlacement.add(new Placement(col, row));
                        }
                    }
                }
            }
        }

        return listPlacement;
    }

    /**
     * Resizes all pieces on the game board.
     *
     * @param w The new width of the pieces.
     * @param h The new height of the pieces.
     */
    public void ReSize(int w, int h) {
        for (int row = 1; row < SizeRow; row++) {
            for (int col = 1; col < SizeCol; col++) {
                if (!Damier[row][col].isEmpty()) {
                    Damier[row][col].ResizeImage(w, h);
                }
            }
        }
    }
}
