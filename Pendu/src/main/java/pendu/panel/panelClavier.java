/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendu.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import pendu.Controleur.DataControleur;
import pendu.Listener.ListenerChar;

/**
 *
 * @author Utilisateur
 */
public class panelClavier extends JPanel implements ActionListener, KeyListener {

    JButton[] tabBut;

    private final ListenerChar ecoute;

    public panelClavier(String lg, ListenerChar ecouter) {
        inicomponent(lg);
        this.ecoute = ecouter;
    }

    private void inicomponent(String lg) {
        String langue = DataControleur.getInstance().getLangClavier(lg);
        int Taille = langue.length();
        double racine = Math.sqrt(Taille);
        tabBut = new JButton[Taille];

        int Ligne = (int) Math.floor(racine);
        int colone = (int) Math.ceil(racine);

        this.setLayout(new GridLayout(Ligne, colone));

        for (int i = 0; i < Taille; i++) {
            JButton but = new JButton(langue.charAt(i) + "");
            but.setActionCommand("Car");
            but.addActionListener(this);
            but.addKeyListener(this);
            tabBut[i] = but;
            this.add(but);
        }
    }
    //ré active tout les bouton
    public void resetAllBut(){
        int taille = tabBut.length;
        for (int i = 0; i < taille; i++) {
            tabBut[i].setEnabled(true);
        }
    }

    //recuper le bouton présser et le désactive
    @Override
    public void actionPerformed(ActionEvent e) {
        String Action = e.getActionCommand();
        switch (Action) {
            case "Car":
                JButton but = (JButton) e.getSource();
                but.setEnabled(false);
                Character c = but.getText().charAt(0);
                ecoute.ButPresse(c);
                break;

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //recuper le bouton présser et le désactive
    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        int taille = tabBut.length;
        int i = 0;
        boolean trouver = true;
        while ((i < taille) && (trouver)) {
            char lettre = tabBut[i].getText().charAt(0);
            if (lettre == c) {
                trouver = false;
                tabBut[i].setEnabled(false);
                ecoute.ButPresse(lettre);
            }
            i++;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
