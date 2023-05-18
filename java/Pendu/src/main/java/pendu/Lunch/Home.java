/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pendu.Lunch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import pendu.Controleur.DataControleur;
import pendu.Controleur.ViewControleur;

/**
 *
 * @author ceule
 */
public class Home extends JFrame implements ActionListener {
    private JPanel pan = new JPanel();

    /**
     * @param args the command line arguments
     */
    public Home() {
        DataControleur.getInstance().getOptionFill();
        initComponent();
        //si le fichier Log n'existe pas on va le cree
        if(!DataControleur.getInstance().VerifFill()){
            DataControleur.getInstance().CreatLogFile();
        //si le fichier existe on verifie la date 
        // si la date du fichier est de la veille on le suprime et on le recree 
        }else if(DataControleur.getInstance().VerifDateFill()){
            DataControleur.getInstance().DeleteLogFile();
            DataControleur.getInstance().CreatLogFile();
        }       
    }

    private void initComponent() {
        pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS));
        
        JButton butPlay = new JButton("Jeux");
        butPlay.addActionListener(this);
        butPlay.setActionCommand("Game");
         JButton butList = new JButton("Menu List");
        butList.addActionListener(this);
        butList.setActionCommand("List");
        JButton butRestMot = new JButton("Reset la liste des mots utiliser");
        butRestMot.addActionListener(this);
        butRestMot.setActionCommand("ResetMot");

        butPlay.setAlignmentX(CENTER_ALIGNMENT);
        butList.setAlignmentX(CENTER_ALIGNMENT);
        butRestMot.setAlignmentX(CENTER_ALIGNMENT);
        
        this.setBounds(100, 100, 300, 200);
    
        this.setTitle("Home");
        
        pan.add(butPlay);
        pan.add(butList);
        pan.add(butRestMot);
        this.add(pan);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        //this.setSize(500, 200);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Home();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "Game":
                ViewControleur.getInstance().CallMenuChoixGame();
                break;
            case "List":
                ViewControleur.getInstance().CallMenuChooseList();
                break;
            //si l'utilisateur veux effacer la liste des mots qu'il à déjà jouer aujourd'hui    
            case "ResetMot":
                
                int choix = JOptionPane.showOptionDialog(null,"Vraiment effacer la liste des mots que vous avez jouer aujourd'hui ?", "Demande de confirmation", JOptionPane.YES_NO_OPTION, DISPOSE_ON_CLOSE, null, null, null);
                if(choix==0){
                     DataControleur.getInstance().DeleteLogFile();
                     DataControleur.getInstance().CreatLogFile();
                }
                break;

        }
    }

}
