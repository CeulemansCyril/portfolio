/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.PanelMenu.CreatGame.PanelDeplacementComplexe;

import JeuxDame.Object.ObjPiece.DeplacementComplexe;
import JeuxDame.Object.ObjPiece.DeplacementSimple;
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
public class ListModelDeplacementComplexe implements ListCellRenderer {

    public ListModelDeplacementComplexe() {
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        PanelLabel Label = new PanelLabel("");
        if (!isSelected)
       try {
            DeplacementComplexe depl = (DeplacementComplexe) value;
            Label = new PanelLabel("(Ligne: " + depl.getDeplacementY() + " Colonne: " + depl.getDeplacementX() +" ColorAll: "+depl.isColorAll()+ ")");
            Label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } catch (ClassCastException e) {
        } //deletes the selected value
        else {
            DefaultListModel mod = (DefaultListModel) list.getModel();
            mod.removeElement(value);
            list.setModel(mod);
        }

        return Label;

    }

}
