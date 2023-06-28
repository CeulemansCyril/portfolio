/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.PopUp;

import JeuxDame.Panel.PanelButon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class PopUpErreur extends JFrame implements ActionListener {

    private PanelButon butOk = new PanelButon(this, "OK", "OK", 50, 100);
    private JLabel label = new JLabel();

    /**
     * Constructs a PopUpErreur object with the specified error message.
     *
     * @param MSG The error message.
     */
    public PopUpErreur(String MSG) {
        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));

        this.setTitle("Erreur");

        label.setText(MSG);
        label.setAlignmentX(CENTER_ALIGNMENT);
        butOk.setAlignmentX(CENTER_ALIGNMENT);

        pan.add(label);
        pan.add(butOk);

        this.setSize(MSG.length() + 300, 100);

        this.add(pan);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }

}
