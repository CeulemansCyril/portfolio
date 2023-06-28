/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.PanelMenu;

import JeuxDame.Listener.ListenerNewSizeTerrain;
import JeuxDame.Panel.PanelJTextField;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ceule
 */
public class PanelTextFielSizeTerrain extends JPanel implements KeyListener {

    private PanelJTextField boxRow;
    private PanelJTextField boxCol;
    private ArrayList<ListenerNewSizeTerrain> ecouter = new ArrayList<>();
    private int Row = 0;
    private int Col = 0;

    /**
     * Constructs a PanelTextFielSizeTerrain object with the specified number of
     * rows and columns.
     *
     * @param Row The number of rows.
     * @param Col The number of columns.
     */
    public PanelTextFielSizeTerrain(int Row, int Col) {
        initComponent(Row, Col);
    }

    /**
     * Constructs a PanelTextFielSizeTerrain object with the specified number of
     * rows and columns, and adds the provided listener for new terrain size.
     *
     * @param Row The number of rows.
     * @param Col The number of columns.
     * @param ecoute The listener for new terrain size.
     */
    public PanelTextFielSizeTerrain(int Row, int Col, ListenerNewSizeTerrain ecoute) {
        this.ecouter.add(ecoute);
        initComponent(Row, Col);
    }

    /**
     * Constructs a PanelTextFielSizeTerrain object with the specified number of
     * rows and columns, and adds the provided listeners for new terrain size.
     *
     * @param Row The number of rows.
     * @param Col The number of columns.
     * @param ecoute The first listener for new terrain size.
     * @param ecoute2 The second listener for new terrain size.
     */
    public PanelTextFielSizeTerrain(int Row, int Col, ListenerNewSizeTerrain ecoute, ListenerNewSizeTerrain ecoute2) {
        this.ecouter.add(ecoute);
        this.ecouter.add(ecoute2);
        initComponent(Row, Col);
    }

    /**
     * Initializes the components of the panel with the specified number of rows
     * and columns.
     *
     * @param Row The number of rows.
     * @param Col The number of columns.
     */
    private void initComponent(int Row, int Col) {
        this.Row = Row;
        this.Col = Col;
        //Col
        ArrayList listCol = new ArrayList();
        for (int i = 0; i < Col; i++) {
            listCol.add(i);
        }
        boxCol = new PanelJTextField("Colonne", String.valueOf(Col), "TxtCol");
        boxCol.addName("Col");
        //Row
        ArrayList listRow = new ArrayList();
        for (int i = 0; i < Row; i++) {
            listRow.add(i);
        }
        boxRow = new PanelJTextField("Ligne", String.valueOf(Row), "TxtRow");
        boxRow.addName("Row");

        boxCol.addNewKeyListener(this);
        boxRow.addNewKeyListener(this);

        this.add(boxRow);
        this.add(boxCol);
    }

    /**
     * Returns the number of rows.
     *
     * @return The number of rows.
     */
    public int getRow() {
        return Integer.parseInt((String) boxRow.getText());
    }

    /**
     * Returns the number of columns.
     *
     * @return The number of columns.
     */
    public int getCol() {
        return Integer.parseInt((String) boxRow.getText());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        JTextField txt = (JTextField) e.getSource();
        String txtName = txt.getName();

        if ((e.getKeyChar() >= '0') && (e.getKeyChar() <= '9')) {
            int size = 0;

            size = Integer.parseInt(txt.getText() + e.getKeyChar());

            if (size >= 1 && size <= 50) {
                txt.setText(String.valueOf(size));
            } else if (size > 50) {
                txt.setText(String.valueOf(50));
                size = 100;
                
            } else if (size < 1) {
                txt.setText(String.valueOf(1));
                size = 1;
               
            }

            switch (txtName) {
                case "Col":
                    this.Col = size;
                    break;
                case "Row":
                    this.Row = size;
                    break;
            }

        } else {
            if (txt.getText().length() > 0) {
                String nbString = "";
                if ((!txt.getText().isBlank())&&(txt.getText().length() - 1) > 0) {
                    nbString = txt.getText().substring(-1, txt.getText().length() - 1);
                } else {
                    nbString = txt.getText();
                }
                if (!nbString.isBlank()) {
                    int size = Integer.parseInt(nbString);
                    switch (txtName) {
                        case "Col":
                            this.Col = size;
                            break;
                        case "Row":
                            this.Row = size;
                            break;
                    }
                }
            }
        }
        txt.setEditable(false);

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        JTextField txt = (JTextField) e.getSource();
        txt.setEditable(true);

        int size = ecouter.size();
        for (int i = 0; i < size; i++) {
            ecouter.get(i).NewSizeTerrain(Row, Col);
        }

    }

}
