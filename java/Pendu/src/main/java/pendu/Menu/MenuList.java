/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendu.Menu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import pendu.panel.panelLabel;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import pendu.Controleur.DataControleur;
import pendu.Listener.ListenerList;
import pendu.panel.panelJlist;

/**
 *
 * @author ceule
 */
public class MenuList extends JFrame implements ActionListener, ListenerList {

    panelJlist panList = new panelJlist(this);
    panelLabel panLabel = new panelLabel("");
    JButton butAdd = new JButton("Ajout");
    JButton butDelet = new JButton("Suprime");
    JButton butModif = new JButton("Modifier");
    String mots = new String();
    ArrayList ListMot = new ArrayList();
    String nom = new String();
    String Langue = new String();
    boolean flag =true;

    public MenuList(String nom, ArrayList li,String lang,boolean New) {
        this.nom = nom;
        this.setTitle(nom);
        this.ListMot = li;
        this.Langue=lang;
        this.flag=New;
        panList.FillList(ListMot);
        initComponet();
    }

    private void initComponet() {
        this.setLayout(new BorderLayout());
        panLabel.setEditable(true);

        butAdd.addActionListener(this);
        butAdd.setActionCommand("add");
        butDelet.addActionListener(this);
        butDelet.setActionCommand("del");
        butModif.addActionListener(this);
        butModif.setActionCommand("modif");

        panLabel.addElement(butAdd);
        panLabel.addElement(butDelet);
        panLabel.addElement(butModif);

        this.add(panList, BorderLayout.EAST);
        this.add(panLabel, BorderLayout.CENTER);

        this.setSize(700, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous saugarder cette liste", "Demande de confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
                //enregistre la liste
                if(choix==0){
                    if(flag){
                        ListMot = panList.getList();
                        DataControleur.getInstance().SaugardeList(Langue, ListMot, nom);
                    }else{
                        ListMot = panList.getList();
                        DataControleur.getInstance().ModifList(Langue, ListMot, nom);
                    }
                    close();
                }else if(choix==1){
                    //ferme sans sauvegarder
                    close();
                }
            }
        
        
        });
        
        this.setResizable(false);
        
        this.setVisible(true);
    }
    
    private void close(){
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String mot = new String();
        switch (action) {
            case "add":
                //recuper le mots dans le textfield et l'ajoute a la liste
                mot = panLabel.getText();
                if ((!mot.isBlank())&&(DataControleur.getInstance().VerifMotToLang(mot, Langue))) {
                    ListMot = panList.getList();
                    ListMot.add(mot);
                    panList.removeList();
                    panList.FillList(ListMot);
                    panLabel.setText("");
                }else{
                     JOptionPane.showMessageDialog(null, "Erreur mot invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

                break;
            case "del":
                //recuper le mots dans le textfield et l'efface de la liste
                mot = panLabel.getText();
                if (!mot.isBlank()) {
                    ListMot = panList.getList();
                    ListMot.remove(mot);
                    panList.removeList();
                    panList.FillList(ListMot);
                    panLabel.setText("");
                }
                break;
            case "modif":
                //recuper le mots dans le textfield 
                mot = panLabel.getText();
                if ((!mot.isBlank())&&(DataControleur.getInstance().VerifMotToLang(mot, Langue))) {
                    ListMot = panList.getList();
                    //recuper le mots dans la liste
                    boolean flag =true;
                    int id =-1;
                    while(flag){
                        id++;
                        if(ListMot.get(id)==mots){
                            flag=false;
                        }
                        
                    }
                 
                    //replace le mot
                    ListMot.set(id, mot);
                    panList.removeList();
                    panList.FillList(ListMot);
                    panLabel.setText("");
                }else{
                     JOptionPane.showMessageDialog(null, "Erreur mot invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    @Override
    public void getMot(String mot) {
        //recuper le mots que l'utilisateur à cliquer avec sa souris
        panLabel.setText(mot);
        this.mots=mot;
    }

}
