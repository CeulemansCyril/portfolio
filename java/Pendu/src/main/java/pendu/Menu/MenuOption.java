/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendu.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pendu.Controleur.DataControleur;
import pendu.Listener.ListenerOption;
import pendu.Object.Option;
import pendu.panel.panelComboWhitListener;

/**
 *
 * @author ceule
 */
public class MenuOption extends JFrame implements ActionListener{

    private JPanel pan = new JPanel();
    private JButton butsound = new JButton();
    private ListenerOption ecoute;
    private Option op = DataControleur.getInstance().getOption();
    private boolean sound = op.isSound();

    public MenuOption(ListenerOption ecoutes) {
        this.ecoute = ecoutes;
        initComponet();
    }

    private void initComponet() {
        pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
        
        panelComboWhitListener comboPolice = new panelComboWhitListener("Police d'écriture", DataControleur.getInstance().GetPolice(), ecoute);
        panelComboWhitListener comboTaille = new panelComboWhitListener("Taille de police", DataControleur.getInstance().GetTailleDePolice(), ecoute);
        panelComboWhitListener comboSenseDuText = new panelComboWhitListener("Alignement du text", DataControleur.getInstance().GetTextAlign(), ecoute);
        
        pan.add(comboPolice);
        pan.add(comboTaille);
        pan.add(comboSenseDuText);
        pan.add(butsound);

        comboPolice.setActioCommande("Police");
        comboTaille.setActioCommande("Taille");
        comboSenseDuText.setActioCommande("Aling");
        butsound.setActionCommand("Sound");
        
        butsound.setAlignmentX(CENTER_ALIGNMENT);
        
        butsound.addActionListener(this);
        //set icon du boutton sond 
        if(sound){
            butsound.setIcon(DataControleur.getInstance().IniImg("1300", 20, 20));
        }else{
            butsound.setIcon(DataControleur.getInstance().IniImg("1200", 20, 20));
        }

        this.add(pan);
        
        this.setResizable(false);

        this.setTitle("Option");

        this.setBounds(100, 100, 500, 300);
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String Action = e.getActionCommand();
        //change l'image du sons en fonction du choix de l'utilisateur
        if(Action.equalsIgnoreCase("Sound")){
            if(sound){
                butsound.setIcon(DataControleur.getInstance().IniImg("1200", 20, 20));
            }else{
                butsound.setIcon(DataControleur.getInstance().IniImg("1300", 20, 20));
            }
            sound =!sound;
            ecoute.SetNewOption(Action, sound+"");
        }
    }

}
