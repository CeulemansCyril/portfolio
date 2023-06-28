/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Panel.PanelJlistWhitDeletButon;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class PanelJlistWhitDeletOption extends JPanel {

    private JList list = new JList();
    private DefaultListModel mod = new DefaultListModel();

    /**
     * Constructs a PanelJListWithDeleteOption with a default model, cell
     * renderer, and empty default value.
     */
    public PanelJlistWhitDeletOption() {
        ListModelDeletButon model = new ListModelDeletButon();
        list.setCellRenderer(model);
        list.setModel(mod);
        DefaultValue();
        this.add(list);
    }

    /**
     * Adds an item to the JList.
     *
     * @param e the item to add
     */
    public void addItem(Object e) {
        mod.addElement(e);
        list.setModel(mod);

    }

    private void DefaultValue() {
        if (!CheckEmpty()) {
            mod.remove(0);
        } else {
            mod.add(0, " ");
        }
    }

    /**
     * Sets the name of the JList.
     *
     * @param txt the name to set
     */
    public void setNameList(String txt) {
        list.setName(txt);
    }

    /**
     * Adds a title to the JList.
     *
     * @param txt the title to add
     */
    public void addTitle(String txt) {
        list.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), txt));
    }

    /**
     * Sets the size of the JList cells.
     *
     * @param width the width of the cells
     * @param height the height of the cells
     */
    public void setListSize(int Width, int Height) {
        list.setFixedCellHeight(Height);
        list.setFixedCellWidth(Width);
    }

    /**
     * Returns all the data in the JList as an ArrayList.
     *
     * @return the ArrayList containing all the data
     */
    public ArrayList getAllData() {
        ArrayList liste = new ArrayList();
        int size = mod.size();

        for (int i = 0; i < size; i++) {
            liste.add(mod.elementAt(i));
        }

        return liste;
    }

    private boolean CheckEmpty() {
        ArrayList listEmp = getAllData();
        if ((listEmp != null) && (!listEmp.isEmpty())) {
            return false;
        }

        return true;
    }

    public boolean isEmpty() {
        ArrayList listEmp = getAllData();
        if ((listEmp != null) && (!listEmp.isEmpty())) {
            int Size = listEmp.size();

            for (int i = 0; i < Size; i++) {
                if (!listEmp.get(i).equals(" ")) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Clears all the items from the JList.
     */
    public void ClearAll() {
        mod = new DefaultListModel();
        list.setModel(mod);
    }

}
