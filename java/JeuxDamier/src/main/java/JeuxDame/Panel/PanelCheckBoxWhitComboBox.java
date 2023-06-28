/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Panel;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class PanelCheckBoxWhitComboBox extends JPanel{
    private JCheckBox Check = new JCheckBox();
    private PanelComboBox Combo = new PanelComboBox("", new ArrayList(), "") ;
    private String Action;
    private ArrayList<JCheckBox> listComboSup = new ArrayList<>();
     /**
     * Constructs a PanelCheckBoxWhitComboBox with the specified label, action command, and default selection state.
     *
     * @param txt           the label text for the checkbox
     * @param actionCommand the action command associated with the checkbox
     * @param defaultState  the default selection state of the checkbox
     */
    public PanelCheckBoxWhitComboBox(String txt, String ActionCommande, boolean DelfautStat) {
        Check.setText(txt);
        Check.setActionCommand(ActionCommande);
        Check.setSelected(DelfautStat);
        
        this.add(Check);
        Action=ActionCommande;
        
        listComboSup.add(Check);
    }
     /**
     * Returns whether the checkbox is currently selected.
     *
     * @return true if the checkbox is selected, false otherwise
     */
    public boolean isSelected(){
      return Check.isSelected();
    }
    /**
     * Sets the selection state of the checkbox.
     *
     * @param flag the selection state to set
     */
    public void setSelect(boolean flag){
        Check.setSelected(flag);
    }
    /**
     * Sets the selection state of the other checkboxes with the specified action command.
     *
     * @param flag           the selection state to set
     * @param actionCommande the action command of the checkboxes to set the selection state for
     */
    public void setOtherCheckBoxSelect(boolean flag,String ActionCommande){
        int Size = listComboSup.size();
        for (int i = 0; i < Size; i++) {
            if(listComboSup.get(i).getActionCommand()==ActionCommande)listComboSup.get(i).setSelected(flag);
        }
        
    }
    /**
     * Selects the specified item in the combo box.
     *
     * @param txt the item to select in the combo box
     */
    public void SelectItemCombot(String txt){
        Combo.DefaultValue(txt);
    }
       /**
     * Adds an ActionListener to the checkbox.
     *
     * @param l the ActionListener to add
     */
    public void AddActionListener(ActionListener l){
        Check.addActionListener(l);
    }
     /**
     * Adds a MouseListener to the checkbox.
     *
     * @param l the MouseListener to add
     */
    public void AddMouseListener(MouseListener l){
        Check.addMouseListener(l);
    }
    /**
     * Sets the combo box with the specified list of items and default item.
     *
     * @param list         the list of items for the combo box
     * @param defaultItem  the default item to select in the combo box
     */
    public void SetComboBox(ArrayList list, String DefaultItem){
        Combo = new PanelComboBox("", list,  DefaultItem);
        Combo.SetActionCommand(Action);
        this.add(Combo);
    }
    /**
     * Returns the selected item in the combo box.
     *
     * @return the selected item
     */
    public Object getSelectComboBox(){
        return Combo.getSelectItem();
    }
    
    /**
     * Sets the visibility of the combo box.
     *
     * @param flag the visibility state to set
     */
    public void SetVisibleComboBox(boolean flag){
        Combo.setVisible(flag);
    }
     /**
     * Adds an ItemListener to the combo box.
     *
     * @param l the ItemListener to add
     */
    public void AddItemListernerCombBox(ItemListener l){
        Combo.addItemChangeListener(l);

    }
    /**
     * Adds a checkbox with the specified label, action command, default selection state, and ActionListener.
     *
     * @param txt           the label text for the checkbox
     * @param actionCommand the action command associated with the checkbox
     * @param defaultState  the default selection state of the checkbox
     * @param l             the ActionListener for the checkbox
     */
    
    public void AddCheckBox(String txt,String ActionCommande, boolean DelfautStat,ActionListener l){
        JCheckBox CheckBox = new JCheckBox();
        CheckBox.setText(txt);
        CheckBox.setActionCommand(ActionCommande);
        CheckBox.setSelected(DelfautStat);
        if(l!=null)CheckBox.addActionListener(l);
        this.add(CheckBox);
        listComboSup.add(CheckBox);
    }
    /**
     * Removes the checkbox with the specified action command from the panel.
     *
     * @param actionCommand the action command of the checkbox to remove
     */
    public void RemoveComboBox(String ActionCommande){
        boolean flag = true;
        int i = 0;
        while(flag){
            JCheckBox com = listComboSup.get(i);
            if(ActionCommande.equalsIgnoreCase(com.getActionCommand())){
                listComboSup.remove(com);
                flag=false;
            }
            i++;
        }
    }
    /**
     * Returns whether the checkbox with the specified action command is checked.
     *
     * @param actionCommand the action command of the checkbox to check
     * @return true if the checkbox is checked, false otherwise
     */
    public boolean isCheckedCheckBox(String ActionCommande){
        boolean flag = true;
        int size = listComboSup.size();
        int i = -1;
        while(flag && i<size){
            i++;
            JCheckBox com = listComboSup.get(i);            
            if(ActionCommande.equalsIgnoreCase(com.getActionCommand()))flag=false;          
        }
        return listComboSup.get(i).isSelected();
    }
     /**
     * Sets the visibility of the checkbox with the specified action command.
     *
     * @param actionCommand the action command of the checkbox
     * @param visible       the visibility state to set
     */
    public void SetVisibleCheckBox(String ActionCommande, boolean Visible){
        boolean flag = true;
        int i = 0;
        int size = listComboSup.size();
        while(flag && i<size){
            JCheckBox com = listComboSup.get(i);
            if(ActionCommande.equalsIgnoreCase(com.getActionCommand())){
                listComboSup.get(i).setVisible(Visible);
                flag=false;
            }
            i++;
        }
    }
}
