/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Panel.PanelComboBoxTemplate;

import JeuxDame.Object.ObjTemplate.Template;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author ceule
 */
public class ComboBoxTemplateRenderer implements ListCellRenderer{

    public ComboBoxTemplateRenderer() {
    }
    

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Template temp = (Template) value;
        JLabel labTemp = new JLabel(temp.getNameFile());
        return labTemp;
    }
    
}
