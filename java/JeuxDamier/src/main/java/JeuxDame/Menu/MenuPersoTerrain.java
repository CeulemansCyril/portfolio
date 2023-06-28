/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Menu;

import JeuxDame.Listener.ListenerColorChange;
import JeuxDame.Listener.ListenerColorDeplacement;
import JeuxDame.Listener.ListenerNewSizeTerrain;
import JeuxDame.Listener.ListernerWindowsClose;
import JeuxDame.Panel.PanelColorTuile;
import JeuxDame.PanelMenu.PanelTextFielSizeTerrain;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ceule
 */
public class MenuPersoTerrain extends JFrame implements KeyListener, WindowListener {

    private PanelColorTuile colorOne;
    private PanelColorTuile colorTwo;
    private PanelColorTuile colorDepl;
    private PanelTextFielSizeTerrain sizeTerr;
    private JPanel ContenentPan = (JPanel) this.getContentPane();
    private ListenerColorDeplacement ecouter;
    private ListernerWindowsClose ecouteClose;


    public MenuPersoTerrain(ListenerColorChange ecou, Color one, Color two, Color depl, ListenerColorDeplacement ecouter, ListernerWindowsClose close, ListenerNewSizeTerrain ecouteTer, int Row, int Col) {
        colorOne = new PanelColorTuile(ecou, "One", one, this);
        colorTwo = new PanelColorTuile(ecou, "Two", two, this);
        colorDepl = new PanelColorTuile(ecou, "Depl", depl, this);
        sizeTerr = new PanelTextFielSizeTerrain(Row, Col, ecouteTer);

        this.ecouter = ecouter;
        this.ecouteClose = close;

        initComponent();
        this.addWindowListener(this);
        this.setVisible(true);
    }

    private void initComponent() {
        colorOne.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Couleur case paire"));
        colorTwo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Couleur case impaire"));
        colorDepl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Couleur des deplacement"));
        sizeTerr.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Taille du terrain"));

        ContenentPan.setLayout(new BoxLayout(ContenentPan, BoxLayout.PAGE_AXIS));

        colorDepl.setAlignmentX(CENTER_ALIGNMENT);
        colorOne.setAlignmentX(CENTER_ALIGNMENT);
        colorTwo.setAlignmentX(CENTER_ALIGNMENT);
        sizeTerr.setAlignmentX(CENTER_ALIGNMENT);

        ContenentPan.add(colorOne);
        ContenentPan.add(colorTwo);
        ContenentPan.add(colorDepl);
        ContenentPan.add(sizeTerr);

        this.setResizable(false);
        this.setTitle("Modification du terrain");
        this.setSize(600, 600);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        JTextField txt = (JTextField) e.getSource();
        boolean flag = true;

        String[] split = txt.getName().split(" ");

        String action = split[0];
        String cible = split[1];
        String txtField = txt.getText();

        if ((e.getKeyChar() >= '0') && (e.getKeyChar() <= '9')) {
            int size = 0;

            size = Integer.parseInt(txtField + e.getKeyChar());

            if (size >= 0 && size <= 255) {
                if (action.contains("One")) {
                    colorOne.Update(cible, String.valueOf(size));
                } else if (action.contains("Two")) {
                    colorTwo.Update(cible, String.valueOf(size));
                } else if (action.contains("Depl")) {
                    colorDepl.Update(cible, String.valueOf(size));
                    ecouter.NewColorDepl(colorDepl.getColor());
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            txt.setEditable(true);
            flag = false;
        }
        if (flag) {
            txt.setEditable(false);
        }
        String splito = txt.getText();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        JTextField txt = (JTextField) e.getSource();
        txt.setEditable(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        ecouter.NewColorDepl(colorDepl.getColor());
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ecouter.ClearColor();
        ecouteClose.Closing();
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

}
