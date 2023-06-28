/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Panel;

import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class PanelLabel extends JPanel {

    JLabel label;

    /**
     * Constructs a PanelLabel object with the specified text.
     *
     * @param txt The text to be displayed in the label.
     */
    public PanelLabel(String txt) {
        label = new JLabel(txt);

        label.setAlignmentX(CENTER_ALIGNMENT);

        this.setLayout(new FlowLayout());
        this.add(label);

    }

    /**
     * Adds a new element to the PanelLabel.
     *
     * @param e The component to be added.
     */

    public void AddElement(Component e) {
        this.add(e);
    }

    /**
     * Returns the text displayed in the label.
     *
     * @return The text of the label.
     */
    public String getText() {
        return label.getText();
    }

}
