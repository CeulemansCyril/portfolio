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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import pendu.Controleur.DataControleur;
import pendu.Controleur.ViewControleur;
import pendu.Object.Option;
import pendu.panel.panelComboBox;

/**
 *
 * @author ceule
 */
public class MenuChoixGame extends JFrame implements ActionListener {

    private JPanel pan = new JPanel();
    private JButton twoPlayer = new JButton("2 joueur");
    private JButton playerIa = new JButton("joueur vs IA");
    private Option option = DataControleur.getInstance().getOption();
    private panelComboBox panelCombo = new panelComboBox("Langue du clavier et des mot choisit aléatoirement", DataControleur.getInstance().getallLangAr(),option.getLangue());

    public MenuChoixGame() {
        initComponent();
    }

    private void initComponent() {
        pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));

        twoPlayer.addActionListener(this);
        twoPlayer.setActionCommand("2p");
        playerIa.addActionListener(this);
        playerIa.setActionCommand("IA");

        pan.add(twoPlayer);
        pan.add(playerIa);
        pan.add(panelCombo);

        twoPlayer.setAlignmentX(CENTER_ALIGNMENT);
        playerIa.setAlignmentX(CENTER_ALIGNMENT);
        panelCombo.setAlignmentX(CENTER_ALIGNMENT);

        this.add(pan);

        this.setTitle("Play Game");

        this.setBounds(100, 100, 400, 200);

        this.setResizable(false);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String mot = new String();
        String Lang = new String();
        switch (action) {
            case "2p":
                Lang = panelCombo.getText();
                if (!Lang.isBlank()) {
                    //si on change la langue par défaut, on le change dans les option
                    if(!option.getLangue().equals(Lang)){
                        option.setLangue(Lang);
                        DataControleur.getInstance().setOption(option);
                    }
                    //pop up pour demander le mot à l'utilisateur
                    mot = JOptionPane.showInputDialog(null, "", "Entre un mots", DISPOSE_ON_CLOSE);
                    //verifie le mot
                    if ((mot != null) && (!mot.isBlank()) && (DataControleur.getInstance().VerifMotToLang(mot, Lang))) {
                        this.dispose();
                        ViewControleur.getInstance().PenduGame(mot, Lang, true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur mot invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur 404 : besoin d'une langue", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "IA":
                //recuper la langue
                Lang = panelCombo.getText();
                //verifie la langue
                if (!Lang.isBlank()) {
                    //cree le pop up^pour le choix de la liste
                    JComboBox combo = new JComboBox();
                    ArrayList li = DataControleur.getInstance().getAllListInLangue(Lang);
                    if ((li.size()>0)) {
                        int taille = li.size();
                        for (int i = 0; i < taille; i++) {
                            combo.addItem(li.get(i));
                        }
                        //pop up
                        int verif=JOptionPane.showConfirmDialog(null, combo, "Choisiser la liste a jouer", DISPOSE_ON_CLOSE);
                        String nom = (String) combo.getSelectedItem();
                        //si il n'y pas de liste choisit infome le joueur de son erreur
                        if ((!nom.isBlank())&&(verif>-1)) {
                            mot = DataControleur.getInstance().ChooseMot(nom, Lang);
                            if (mot != null) {
                                this.dispose();
                                ViewControleur.getInstance().PenduGame(mot, Lang, false);
                            } else {
                                JOptionPane.showMessageDialog(null, "Erreur aucun mot disponible dans cette list, veullier choisir une autre liste", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Erreur 404 : besoin d'une liste", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }else {
                    JOptionPane.showMessageDialog(null, "Erreur 404 : Aucune liste disponible dans cette langue", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur 404 : besoin d'une langue", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

                break;

        }
    }

}
