/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Panel;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class PanelButon extends JPanel{
    
    private JButton but = new JButton();
     /**
     * Constructs a PanelButton with the specified ActionListener, label text, action command, height, and width.
     *
     * @param action        the ActionListener for the button
     * @param txt           the label text for the button
     * @param actionCommand the action command associated with the button
     * @param height        the height of the button
     * @param width         the width of the button
     */
    public PanelButon(ActionListener action,String txt,String ActionCommande,int Height, int Width) {
        but.setActionCommand(ActionCommande);
        but.setText(txt);
        but.addActionListener(action);
        but.setSize(Width, Height);
        
        this.add(but);
    }
    /**
     * Sets the enable/disable state of the button.
     *
     * @param flag the state to set, true to enable the button, false to disable it
     */
    public void SetButEnable(boolean Flag){
        but.setEnabled(Flag);
    }
    
}
