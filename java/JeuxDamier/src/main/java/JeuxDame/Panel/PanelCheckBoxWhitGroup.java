/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Panel;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class PanelCheckBoxWhitGroup extends JPanel{
    private ArrayList<Checkbox> listCheck = new ArrayList<>();
    private CheckboxGroup group = new CheckboxGroup();
    /**
     * Constructs a PanelCheckBoxWhitGroup with the specified label, name, and initial selection state.
     *
     * @param txt      the label text for the checkbox
     * @param name     the name of the checkbox
     * @param selected the initial selection state of the checkbox
     */
    public PanelCheckBoxWhitGroup(String txt,String Name,boolean Selected) {
        Checkbox check = new Checkbox(txt, group, Selected);
        check.setName(Name);
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(check);
        
        listCheck.add(check);
    }
    /**
     * Adds a checkbox with the specified label and name to the panel.
     *
     * @param txt  the label text for the checkbox
     * @param name the name of the checkbox
     */
    public void addCheckBox(String txt,String Name){
        Checkbox check = new Checkbox(txt);
        check.setCheckboxGroup(group);
        check.setName(Name);
        this.add(check);
         listCheck.add(check);
    }
    /**
     * Returns all the checkboxes in the panel.
     *
     * @return an ArrayList containing all the checkboxes
     */
    public ArrayList<Checkbox> getAllBox(){
        return listCheck;
    }
     /**
     * Sets the selection state of the checkbox with the specified name.
     *
     * @param name the name of the checkbox to select
     */    
    public void setSelect(String name){
        int size = listCheck.size();
        
        for (int i = 0; i < size; i++) {
            if(listCheck.get(i).getName()==name){
                listCheck.get(i).setState(true);
            }
        }
    }
}
