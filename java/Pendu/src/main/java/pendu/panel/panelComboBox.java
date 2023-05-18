/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendu.panel;

import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class panelComboBox extends JPanel {
    
    private JLabel text = new JLabel();
    private JComboBox combot =new JComboBox();
    private JPanel pancombo = new JPanel();
    private JPanel panLab= new JPanel();
    
    public panelComboBox(String Text,ArrayList list,String langue) {
        initComponent(Text, list,langue);
        
    }
    private void initComponent(String Text,ArrayList list,String langue){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        fillCombo(list);
        text.setText(Text);
        text.setSize(Text.length(),20);
        
        panLab.add(text);
        pancombo.add(combot);
        
        DefaultValue(langue);
        
        this.add(panLab);
        this.add(pancombo);
    }
    //remplit la comboBox
    private void fillCombo(ArrayList list){
        int taille = list.size();
        for (int i = 0; i < taille; i++) {
            combot.addItem( list.get(i));
        }
    }
    //recuper le text selectionner
    public String getText(){
        return (String) combot.getSelectedItem();
    }
    public void  DefaultValue(String langue){
        combot.setSelectedItem(langue);
    }
  
}
