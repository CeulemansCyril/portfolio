/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Panel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class PanelMenuDeroulant extends JPanel implements MouseListener {

    private JPanel PanelContenent = new JPanel();
    private JPanel PanelContenue;
    private boolean deroule = false;
    private JLabel menu = new JLabel();
    private String TxtMenu;
    private boolean Enable = true;

    /**
     * Constructs a PanelMenuDeroulant object with the specified menu text and
     * content panel.
     *
     * @param txtMenu The text to display in the menu.
     * @param PanelContenue The panel to be displayed as the content.
     */
    public PanelMenuDeroulant(String txtMenu, JPanel PanelContenue) {
        this.PanelContenue = PanelContenue;
        this.TxtMenu = txtMenu;
        initComponent();
    }

    /**
     * Initializes the components of the PanelMenuDeroulant.
     */
    private void initComponent() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        menu.setText(TxtMenu + " >");
        menu.addMouseListener(this);
        menu.setAlignmentX(CENTER_ALIGNMENT);

        PanelContenent.add(PanelContenue);
        this.add(menu);
    }

    /**
     * Enables or disables the PanelMenuDeroulant.
     *
     * @param flag {@code true} to enable the panel, {@code false} to disable
     * it.
     */
    public void Enable(boolean flag) {
        Enable = flag;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Enable) {
            if (deroule) {
                menu.setText(TxtMenu + " >");
                this.remove(PanelContenent);
                deroule = false;
            } else {
                menu.setText(TxtMenu + " -");
                this.add(PanelContenent);
                deroule = true;
            }
            this.updateUI();
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
