/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class PanelComboBox extends JPanel {

    private JLabel text = new JLabel();
    private JComboBox combot = new JComboBox();
    private JPanel pancombo = new JPanel();
    private JPanel panLab = new JPanel();

    /**
     * Constructs a PanelComboBox with the specified text, list of items, and
     * default value.
     *
     * @param Text the label text to display
     * @param list the list of items for the JComboBox
     * @param DefaultVal the default value to select in the JComboBox
     */
    public PanelComboBox(String Text, ArrayList list, String DefaultVal) {
        initComponent(Text, list, DefaultVal);
    }

    private void initComponent(String Text, ArrayList list, String DefaultVal) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        if (!(list == null)) {
            fillCombo(list);
            DefaultValue(DefaultVal);
        }

        text.setText(Text);
        text.setSize(Text.length(), 20);

        panLab.add(text);
        pancombo.add(combot);

        this.add(panLab);
        this.add(pancombo);
    }

    /**
     * Fills the JComboBox with items from the provided list.
     *
     * @param list the list of items to fill the JComboBox with
     */
    private void fillCombo(ArrayList list) {
        int taille = list.size();
        for (int i = 0; i < taille; i++) {
            combot.addItem("" + list.get(i));
        }
    }

    /**
     * Clears the JComboBox and refills it with the items from the provided
     * list.
     *
     * @param list the list of items to refill the JComboBox with
     */
    public void RefillCombo(ArrayList list) {
        //ClearCombo();
        combot.removeAllItems();
        int taille = list.size();
        for (int i = 0; i < taille; i++) {
            combot.addItem(list.get(i));
        }
    }
    
    private void ClearCombo (){
        int size = combot.getItemCount();
        for (int i = 0; i < size; i++) {
            combot.remove(i);
        }
    }

    /**
     * Gets the selected item from the JComboBox.
     *
     * @return the selected item from the JComboBox
     */
    public Object getSelectItem() {
        return combot.getSelectedItem();
    }

    /**
     * Sets the default value to select in the JComboBox.
     *
     * @param Val the default value to select
     */
    public void DefaultValue(String Val) {
        int Size = combot.getItemCount()+1;
        int i = 0;
        boolean flag = true;
        while (i < Size && flag) {
            String obj = String.valueOf(combot.getItemAt(i));
            if (obj!=null && obj.equalsIgnoreCase(Val)) {
               combot.setSelectedIndex(i);
               flag=false;
            }
            i++;
        }
      

    }

    /**
     * Adds an item listener to the JComboBox.
     *
     * @param listener the item listener to add
     */
    public void addItemChangeListener(ItemListener list) {
        combot.addItemListener(list);
    }

    /**
     * Adds a component to the PanelComboBox.
     *
     * @param component the component to add
     */
    public void AddElement(Component e) {
        this.add(e);
    }

    /**
     * Sets the action command of the JComboBox.
     *
     * @param txt the action command to set
     */
    public void SetActionCommand(String txt) {
        combot.setActionCommand(txt);
    }

    /**
     * Enables or disables the JComboBox.
     *
     * @param flag true to enable, false to disable
     */
    public void Enable(boolean flag) {
        combot.enable(flag);
    }

    /**
     * Sets the background color of the JComboBox.
     *
     * @param color the background color to set
     */
    public void setBackgroundColor(Color c) {
        combot.setBackground(c);
    }

    /**
     * Sets the name of the JComboBox.
     *
     * @param name the name to set
     */
    public void setNameCombo(String name) {
        combot.setName(name);
    }

}
