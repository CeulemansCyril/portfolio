/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeux.Controleur;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Utilisateur
 */
public class jTableRender extends DefaultTableCellRenderer {
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        /**
         * Fixer la couleur de fond de la premiÃ¨re colonne en jaune
         */
        if(row%2==0){
            if(column%2==0){
               component.setBackground(Color.WHITE);
            }else{
                component.setBackground(Color.lightGray);
            }
        }else{
            if(column%2==0){
                component.setBackground(Color.LIGHT_GRAY);
            }else{
                component.setBackground(Color.WHITE);
            }
        }
        
        
        return component;
    }

}
