/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendu.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import pendu.Controleur.DataControleur;
import pendu.Controleur.ViewControleur;

/**
 *
 * @author ceule
 */
public class MenuChooseList extends JFrame implements ActionListener {

    JPanel pan = new JPanel();

    public MenuChooseList() {
        initCompennet();
    }

    private void initCompennet() {
        JButton butAjout = new JButton("Crée une nouvelle liste");
        butAjout.addActionListener(this);
        butAjout.setActionCommand("Ajout");
        JButton butModif = new JButton("Modifie une  liste");
        butModif.addActionListener(this);
        butModif.setActionCommand("Modif");
        JButton butSup = new JButton("Suprime une  liste");
        butSup.addActionListener(this);
        butSup.setActionCommand("Sup");
        JButton butTxt = new JButton("Crée une nouvelle liste a partir d'un fichier TxT");
        butTxt.addActionListener(this);
        butTxt.setActionCommand("Doc");

        pan.add(butAjout);
        pan.add(butModif);
        pan.add(butSup);
        pan.add(butTxt);
        pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));

        butAjout.setAlignmentX(CENTER_ALIGNMENT);
        butModif.setAlignmentX(CENTER_ALIGNMENT);
        butSup.setAlignmentX(CENTER_ALIGNMENT);
        butTxt.setAlignmentX(CENTER_ALIGNMENT);

        this.add(pan);

        this.setTitle("Menu des listes");

        this.setResizable(false);

        this.setBounds(100, 100, 400, 200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        JComboBox combo = new JComboBox();
        String Lang = new String();
        switch (action) {
            case "Ajout":
                combo = new JComboBox(DataControleur.getInstance().getallLang());
                JOptionPane.showMessageDialog(null, combo, "Choisiser la langue de la liste", DISPOSE_ON_CLOSE);
                Lang = (String) combo.getSelectedItem();
                //si l'utilisateur à choisit une langue on luis demandera un nom
                if (!Lang.isBlank()) {
                    String nom = JOptionPane.showInputDialog(null, "Entrer le nom de la nouvelle liste", "Demande de confirmation", DISPOSE_ON_CLOSE);
                    //si il n'y pas de nom tous seras arrêter
                    if (!nom.isBlank()) {
                        ArrayList li = new ArrayList();
                        ViewControleur.getInstance().CallMenuList(nom, li, Lang, true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur 404 : besoin d'un nom", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Erreur 404 : besoin d'une langue", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Modif":
                combo = new JComboBox(DataControleur.getInstance().getallLang());
                JOptionPane.showMessageDialog(null, combo, "Choisiser la langue de la liste", DISPOSE_ON_CLOSE);
                Lang = (String) combo.getSelectedItem();
                //si l'utilisateur à choisit une langue on luis demandera un nom
                if (!Lang.isBlank()) {
                    combo = new JComboBox();
                    ArrayList li = DataControleur.getInstance().getAllListInLangue(Lang);
                    int taille = li.size();
                    for (int i = 0; i < taille; i++) {
                        combo.addItem(li.get(i));
                    }
                    JOptionPane.showMessageDialog(null, combo, "Choisiser la liste a modifier", DISPOSE_ON_CLOSE);
                    String nom = (String) combo.getSelectedItem();
                    //si il n'y pas de nom tous seras arrêter
                    if (!nom.isBlank()) {
                        ArrayList liMot = DataControleur.getInstance().getAllMotInList(Lang, nom);
                        ViewControleur.getInstance().CallMenuList(nom, liMot, Lang, false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur 404 : besoin d'une liste", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Erreur 404 : besoin d'une langue", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

                break;
            case "Sup":
                combo = new JComboBox(DataControleur.getInstance().getallLang());
                JOptionPane.showMessageDialog(null, combo, "Choisiser la langue de la liste", DISPOSE_ON_CLOSE);
                Lang = (String) combo.getSelectedItem();
                //si l'utilisateur à choisit une langue on luis demandera un nom
                if (!Lang.isBlank()) {
                    combo = new JComboBox();
                    ArrayList li = DataControleur.getInstance().getAllListInLangue(Lang);
                    int taille = li.size();
                    for (int i = 0; i < taille; i++) {
                        combo.addItem(li.get(i));
                    }
                    JOptionPane.showMessageDialog(null, combo, "Choisiser la liste a suprimer", DISPOSE_ON_CLOSE);
                    String nom = (String) combo.getSelectedItem();
                    //si il n'y pas de nom tous seras arrêter
                    if (!nom.isBlank()) {
                        int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous vraimen suprimer la liste : " + nom + " ?", "Demande de confirmation", JOptionPane.YES_NO_OPTION);
                        if (choix == 0) {
                            DataControleur.getInstance().RemoveList(Lang, nom);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur 404 : besoin d'une liste", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Erreur 404 : besoin d'une langue", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Doc":
                combo = new JComboBox(DataControleur.getInstance().getallLang());
                JOptionPane.showMessageDialog(null, combo, "Choisiser la langue de la liste", DISPOSE_ON_CLOSE);
                Lang = (String) combo.getSelectedItem();
                if (!Lang.isBlank()) {
                    JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(null, "Attention pour que la convertion fonctionne les mots doit être en liste sans mise en forme et pas d'espace avant le mot", "Erreur", JOptionPane.ERROR_MESSAGE);
                    JFileChooser FileChoose = new JFileChooser();
                    FileFilter filter = new FileNameExtensionFilter("Document texte", "txt");
                    //filtre les fichier autoriser
                    FileChoose.setAcceptAllFileFilterUsed(false);
                    FileChoose.addChoosableFileFilter(filter);

                    int choix = FileChoose.showOpenDialog(panel);
                    //recuper le path du fichier choisit
                    if (choix == JFileChooser.APPROVE_OPTION) {
                        ArrayList list = DataControleur.getInstance().ReadFillUser(FileChoose.getSelectedFile().toPath());
                        ArrayList listGame = new ArrayList();
                        int taille = list.size();
                        for (int i = 0; i < taille; i++) {
                            String mot = list.get(i).toString();
                            //verifie le mot pout la liste
                            if ((!mot.isBlank()) && (DataControleur.getInstance().VerifMotToLang(mot, Lang))) {
                                listGame.add(mot);
                            }
                        }
                        String nom;
                        do{
                             nom = JOptionPane.showInputDialog(null, "Entrer le nom de la nouvelle liste", "Demande de confirmation", DISPOSE_ON_CLOSE);
                        }while(nom.isBlank());
                        ViewControleur.getInstance().CallMenuList(nom, listGame, Lang, false);
                    }
                }else{
                     JOptionPane.showMessageDialog(null, "Erreur 404 : besoin d'une langue", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}
