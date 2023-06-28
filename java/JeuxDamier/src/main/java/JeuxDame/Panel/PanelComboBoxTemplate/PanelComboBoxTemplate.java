/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Panel.PanelComboBoxTemplate;

import JeuxDame.Object.ObjTemplate.Template;
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
public class PanelComboBoxTemplate extends JPanel{
    
    private JComboBox<Template> combot = new JComboBox<>();
    private JLabel text = new JLabel();
    private JPanel panLab= new JPanel();
    private JPanel pancombo = new JPanel();
    
    /**
     * Constructs a PanelComboBoxTemplate with the specified text, list of templates, and default value.
     *
     * @param text        the text to display
     * @param listCombo   the list of templates to populate the JComboBox
     * @param defaultVal  the default value to select in the JComboBox
     */
    public PanelComboBoxTemplate(String Txt, ArrayList listCombo, String DefaultVal) {
        initComponent(Txt, listCombo, DefaultVal);
    }
    
     private void initComponent(String Text, ArrayList list, Object DefaultVal) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        if (!(list == null)) {
            fillCombo(list);
            DefaultValue(DefaultVal);
        }
        
        combot.setRenderer(new ComboBoxTemplateRenderer());
       
        text.setText(Text);
        text.setSize(Text.length(), 20);

        panLab.add(text);
        pancombo.add(combot);

        this.add(panLab);
        this.add(pancombo);
    }

    // Fills the JComboBox with the list of templates
    private void fillCombo(ArrayList<Template> list) {
        int taille = list.size();
        for (int i = 0; i < taille; i++) {
            combot.addItem(list.get(i));
        }
    }
        /**
     * Sets the default value in the JComboBox.
     *
     * @param value the default value to select
     */
     public void DefaultValue(Object Val) {
        int Size = combot.getItemCount();
        for (int i = 0; i < Size; i++) {
            Object obj = combot.getItemAt(i);
            if (obj.equals(Val)) {
               ComboBoxModel  mod = combot.getModel();
               mod.setSelectedItem(combot.getItemAt(i));
               combot.setModel(mod);
            }
        }
    }
     /**
     * Returns the selected item from the JComboBox.
     *
     * @return the selected item
     */
     public Object getSelectItem(){
         return combot.getSelectedItem();
     }


}
