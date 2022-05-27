/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendu.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pendu.Listener.ListenerOption;

/**
 *
 * @author ceule
 */
public class panelComboWhitListener extends JPanel implements ActionListener {
        
    private JLabel text = new JLabel();
    private JComboBox combot =new JComboBox();
    private JPanel pancombo = new JPanel();
    private JPanel panLab= new JPanel();
    private ListenerOption ecoute;
    
    public panelComboWhitListener(String Text,ArrayList list,ListenerOption ecoutes) {
        this.ecoute=ecoutes;
        initComponent(Text, list);
        
    }
    private void initComponent(String Text,ArrayList list){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        fillCombo(list);
        text.setText(Text);
        text.setSize(Text.length(),20);
        
        panLab.add(text);
        pancombo.add(combot);
        
        combot.addActionListener(this);
        
        this.add(panLab);
        this.add(pancombo);
    }
    //remplit la combotbox
    private void fillCombo(ArrayList list){
        int taille = list.size();
        for (int i = 0; i < taille; i++) {
            combot.addItem( list.get(i));
        }
    }
    

    public String getText(){
        return (String) combot.getSelectedItem();
    }
   
    public void setActioCommande(String txt){
        combot.setActionCommand(txt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        JComboBox box =(JComboBox) e.getSource();
        ecoute.SetNewOption(action, box.getSelectedItem() +"");
       
    }
}
