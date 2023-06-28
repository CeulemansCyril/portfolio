/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Panel;

import JeuxDame.Listener.ListenerColorChange;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author ceule
 */
public class PanelColorTuile extends JPanel implements ChangeListener, MouseListener {

    private JSlider R = new JSlider(0, 255);
    private JSlider G = new JSlider(0, 255);
    private JSlider B = new JSlider(0, 255);
    private JTextField TxtR = new JTextField(5);
    private JTextField TxtG = new JTextField(5);
    private JTextField TxtB = new JTextField(5);
    private JTextField LabelR = new JTextField(1);
    private JTextField LabelG = new JTextField(1);
    private JTextField LabelB = new JTextField(1);
    private JPanel tuile = new JPanel();
    private ListenerColorChange ecoute;
    private String TuileCible;
    
     /**
     * Constructs a PanelColorTuile with the specified listener, target tile, base color, and key listener.
     *
     * @param ecoute         the color change listener
     * @param tuileCible     the target tile
     * @param baseColorTuile the base color of the tile
     * @param key            the key listener for text fields
     */
    public PanelColorTuile(ListenerColorChange ecoute, String tuileCible, Color baseColorTuile, KeyListener key) {
        this.ecoute = ecoute;
        this.TuileCible = tuileCible;
        tuile.setBackground(baseColorTuile);
        initComponent();
        TxtR.addKeyListener(key);
        TxtG.addKeyListener(key);
        TxtB.addKeyListener(key);

        TxtR.setText(baseColorTuile.getRed() + "");
        TxtG.setText(baseColorTuile.getGreen() + "");
        TxtB.setText(baseColorTuile.getBlue() + "");

        R.setValue(baseColorTuile.getRed());
        G.setValue(baseColorTuile.getGreen());
        B.setValue(baseColorTuile.getBlue());

        TxtR.setName(tuileCible + " R");
        TxtG.setName(tuileCible + " G");
        TxtB.setName(tuileCible + " B");

        R.addChangeListener(this);
        B.addChangeListener(this);
        G.addChangeListener(this);

    }

    private void initComponent() {

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        //set Rouge
        LabelR.setText("R");
        LabelR.setEditable(false);
        JPanel labR = new JPanel();
        labR.add(LabelR);
        labR.add(R);
        labR.add(TxtR);
        labR.setAlignmentX(CENTER_ALIGNMENT);
        R.setName("R");

        //set Vert
        LabelG.setText("G");
        LabelG.setEditable(false);
        JPanel labG = new JPanel();
        labG.add(LabelG);
        labG.add(G);
        labG.add(TxtG);
        labG.setAlignmentX(CENTER_ALIGNMENT);
        G.setName("G");

        //set Bleu
        LabelB.setText("B");
        LabelB.setEditable(false);
        JPanel labB = new JPanel();
        labB.add(LabelB);
        labB.add(B);
        labB.add(TxtB);
        labB.setAlignmentX(CENTER_ALIGNMENT);
        B.setName("B");

        JPanel allColor = new JPanel();

        allColor.setLayout(new BoxLayout(allColor, BoxLayout.PAGE_AXIS));

        allColor.add(labR);
        allColor.add(labG);
        allColor.add(labB);

        allColor.setAlignmentX(RIGHT_ALIGNMENT);

        tuile.addMouseListener(this);

        this.add(allColor);
        this.add(tuile);

    }
    /**
     * Updates the color and value of the specified text field.
     *
     * @param txtField the text field to update ("R", "G", "B", or "Tuile")
     * @param val      the new value for the text field
     */
    public void Update(String txtField, String Val) {
        int value = Integer.parseInt(Val);
        switch (txtField) {
            case "R":
                R.setValue(value);
                break;
            case "G":
                G.setValue(value);
                break;
            case "B":
                B.setValue(value);
                break;
            case "Tuile":
                Color col = tuile.getBackground();
                R.setValue(col.getRed());
                G.setValue(col.getGreen());
                B.setValue(col.getBlue());

                TxtR.setText(col.getRed() + "");
                TxtG.setText(col.getGreen() + "");
                TxtB.setText(col.getBlue() + "");
                break;

        }
        tuile.setBackground(new Color(Integer.parseInt(TxtR.getText()), Integer.parseInt(TxtG.getText()), Integer.parseInt(TxtB.getText())));
        ecoute.ColorChange(new Color(Integer.parseInt(TxtR.getText()), Integer.parseInt(TxtG.getText()), Integer.parseInt(TxtB.getText())), TuileCible);
    }
     /**
     * Returns the color of the tile.
     *
     * @return the color of the tile
     */
    public Color getColor() {
        return tuile.getBackground();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        try {
            JSlider slide = (JSlider) e.getSource();
            String name = slide.getName();

            switch (name) {
                case "R":
                    TxtR.setText(slide.getValue() + "");
                    break;
                case "G":
                    TxtG.setText(slide.getValue() + "");
                    break;
                case "B":
                    TxtB.setText(slide.getValue() + "");
                    break;
            }

            tuile.setBackground(new Color(Integer.parseInt(TxtR.getText()), Integer.parseInt(TxtG.getText()), Integer.parseInt(TxtB.getText())));
            ecoute.ColorChange(new Color(Integer.parseInt(TxtR.getText()), Integer.parseInt(TxtG.getText()), Integer.parseInt(TxtB.getText())), TuileCible);

        } catch (ClassCastException x) {

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Color col = JColorChooser.showDialog(null, "Choisit ta couleur", Color.white);
        if (col != null) {
            tuile.setBackground(col);
            Update("Tuile", "42");
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

}
