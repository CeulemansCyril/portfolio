/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Panel.PanelJlistWhitDeletButon;

import JeuxDame.Panel.PanelLabel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author ceule
 */
public class ListModelDeletButon implements ListCellRenderer{

    public ListModelDeletButon() {
    }
        

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        PanelLabel Lab = new PanelLabel((String) value);
        Lab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        //deletes the selected value
        if(isSelected){
            DefaultListModel mod = (DefaultListModel) list.getModel();
            mod.removeElement(value);
            if(mod.isEmpty())mod.addElement(" ");
            else if((mod.size()>=2)&&(mod.contains(" "))) mod.removeElement(" ");
            list.setModel(mod);
        }
        
        return Lab;
    }
    
    
  
    
}
