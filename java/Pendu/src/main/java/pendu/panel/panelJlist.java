/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendu.panel;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import pendu.Listener.ListenerList;

/**
 *
 * @author ceule
 */
public class panelJlist extends JPanel {
     private JList list =new JList();
    private DefaultListModel mod = new DefaultListModel();
    private ListenerList ecoute;

    public panelJlist(ListenerList li) {
        this.ecoute=li;
        initComponent();
    }
    private void initComponent(){
        this.setLayout(new FlowLayout());
        list.setSize(100, 200);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object loc = list.locationToIndex(e.getPoint());
                String mot = (String) list.getModel().getElementAt((int) loc);
                ecoute.getMot(mot);
            }
        
        
        });
        this.add(list);
    }
    public void FillList(ArrayList ListMot){
        int taille = ListMot.size();
        
        for (int i = 0; i < taille; i++) {
            mod.addElement( ListMot.get(i));
        }
         list.setModel(mod);
    }
    public void removeList(){
        mod.removeAllElements();
        
    }
    public ArrayList getList(){
        ArrayList Arlist =new ArrayList();
        int taille = mod.size();
        for (int i = 0; i < taille; i++) {
            Arlist.add(mod.get(i));
        }
        return Arlist;
    }



 
}
