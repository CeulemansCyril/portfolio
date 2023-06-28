/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Panel;

import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Utilisateur
 */
public class PanelJTextField extends JPanel {

    private JTextField txt = new JTextField();
    private JLabel lab = new JLabel();

    /**
     * Constructs a PanelJTextField object with the specified label text, field
     * text, and action command.
     *
     * @param txtLabel The label text.
     * @param txtFiel The initial text in the text field.
     * @param actionCommand The action command for the text field.
     */
    public PanelJTextField(String txtLabel, String txtFiel, String ActionCommande) {
        txt.setText(txtFiel);
        lab.setText(txtLabel);
        txt.setActionCommand(ActionCommande);

        initComponent();
    }

    /**
     * Sets the name of the text field.
     *
     * @param name The name to be set for the text field.
     */

    public void addName(String name) {
        txt.setName(name);
    }

    private void initComponent() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        lab.setAlignmentX(CENTER_ALIGNMENT);
        txt.setAlignmentX(CENTER_ALIGNMENT);

        this.add(lab);
        this.add(txt);
    }

    /**
     * Returns the text entered in the text field.
     *
     * @return The text entered in the text field.
     */

    public String getText() {
        return txt.getText();
    }

    /**
     * Adds a KeyListener to the text field.
     *
     * @param e The KeyListener to be added.
     */
    public void addNewKeyListener(KeyListener e) {
        txt.addKeyListener(e);
    }

}
