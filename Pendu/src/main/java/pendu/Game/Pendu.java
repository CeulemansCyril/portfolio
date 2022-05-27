package pendu.Game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import pendu.Controleur.DataControleur;
import pendu.Controleur.ViewControleur;
import pendu.Listener.ListenerChar;
import pendu.Listener.ListenerOption;
import pendu.Object.Option;
import pendu.panel.panelClavier;
import pendu.panel.panelLabel;

/**
 *
 * @author Utilisateur
 */
public class Pendu extends JFrame implements ListenerChar, ActionListener, ListenerOption,MouseListener {

    private String mots;
    private panelLabel panelLabel = new panelLabel("");
    private JLabel labelimg = new JLabel();
    private boolean twoplayer = true;
    private int chance = 0;
    private String langue;
    private panelClavier panelbut;
    private JButton butOption = new JButton("Option");
    private boolean sound = true;

    /**
     * @param args the command line arguments
     */
    public Pendu(String mots, String Langue, boolean twop, Option op) {
        motIni(mots);
        this.langue = Langue;
        this.twoplayer = twop;
        this.mots = mots;
        this.sound = op.isSound();
        initComponent(Langue, op);
    }

    private void initComponent(String Langue, Option op) {
        panelbut = new panelClavier(Langue, this);

        this.setLayout(new BorderLayout());
        this.add(panelLabel, BorderLayout.NORTH);

        this.add(labelimg, BorderLayout.LINE_START);

        this.add(panelbut, BorderLayout.CENTER);

        setImage("0");

        this.setSize(650, 422);

        butOption.setActionCommand("Option");
        butOption.addActionListener(this);
        panelLabel.addElement(butOption);

        panelLabel.setTaillePolice(op.getTaillePolice(), op.getPolice());
        panelLabel.setTextAligne(op.getSenseDuText());
        
        labelimg.addMouseListener(this);
            

        this.setTitle("Pendu");

        this.setResizable(false);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    //cache le mot
    public void motIni(String Mot) {
        int taille = Mot.length();
        String cacher = "";

        for (int i = 0; i < taille; i++) {
            if (caraSp(Mot.charAt(i))) {
                cacher = cacher + Mot.charAt(i);
            } else {
                cacher = cacher + "";
            }

        }
        panelLabel.setText(cacher);
    }

    //verifie si le caracter est un character speciaux si oui return true
    public boolean caraSp(Character c) {
        boolean flag = false;
        int ascii = c;
        if ((ascii >= 33) && (ascii <= 66)) {
            flag = true;
        } else if ((ascii >= 91) && (ascii <= 96)) {
            flag = true;
        } else if ((ascii >= 123) && (ascii <= 126)) {
            flag = true;
        } else if ((ascii >= 128) && (ascii <= 254)) {
            flag = true;
        }
        return flag;
    }

    //dévoile le mots 
    private void motFind(Character c) {
        String motSmall = mots.toLowerCase();
        boolean flag = true;
        if (motSmall.contains(c + "")) {

            //dévoile le mot cacher
            int taille = mots.length();
            String mot = panelLabel.getText();
            String cacher = "";
            for (int i = 0; i < taille; i++) {
                //si le charactére est une lettre on l'ajoute a cacher 
                if (mot.charAt(i) != '') {
                    cacher = cacher + mot.charAt(i);
                } else if (mots.charAt(i) == c) {
                    cacher = cacher + mots.charAt(i);
                    //si non on ajoute un      
                } else {
                    cacher = cacher + "";
                }
            }
            panelLabel.setText(cacher);

        } else {
            flag = false;
            //le joueur pert une vie et on change l'image 
            chance += 1;
            if(chance<=5){
                setImage(chance+"");
            }else{
                //String id = chance+DataControleur.getInstance().getOptionStyleImg();
                String id = chance+"Steve";
                setImage(id);
            }
            
        }
        victory(flag);
    }

    //verifie si le joueur à perdu ou gagnier
    public void victory(boolean flag) {
        String mot = panelLabel.getText();
        String txt = "";
        boolean fin = false;
        if (mot.equals(mots)) {
            if (sound) {
                //joue le son de victoire
                DataControleur.getInstance().getSound("Win");
            }
            txt = "Vous avez gagnier";
            fin = true;
        } else if (chance > 10) {
            //joue le son de défaite
            DataControleur.getInstance().getSound("Lose");
            txt = "Vous avez perdu, le mot myster est : " + mots;
            fin = true;
        }
        //fait apparaitre le pop up pour recommencer une party
        if (fin) {
            int choix = JOptionPane.showOptionDialog(null, txt + " , voulez-vous recommencer ?", "Demande de confirmation", JOptionPane.YES_NO_OPTION, DISPOSE_ON_CLOSE, null, null, null);
            if (choix == 0) {
                //pop up si le joueur jouait contre l'IA
                int choix2 = 0;
                if (twoplayer == false) {
                    choix2 = JOptionPane.showOptionDialog(null, "voulez-vous recommencer avec cette liste ?", "Demande de confirmation", JOptionPane.YES_NO_OPTION, DISPOSE_ON_CLOSE, null, null, null);
                }
                //selection du nouveau mot si joeur vs IA
                if ((choix2 == 0) && (twoplayer == false)) {
                    String motsrestard = DataControleur.getInstance().ChooseMot("-", langue);
                    if (motsrestard != null) {
                        motIni(motsrestard);
                        mots = motsrestard;
                        chance = 0;
                        setImage(chance+"");
                        panelbut.resetAllBut();
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous avez déjà fait toute les mots de la liste veuiller choisir une autre liste", "Erreur", JOptionPane.ERROR_MESSAGE);
                        this.dispose();
                        ViewControleur.getInstance().CallMenuChoixGame();
                    }
                //si le joeur veut choisir une autre liste    
                } else if ((choix2 == 1) && (twoplayer == false)) {
                    this.dispose();
                    ViewControleur.getInstance().CallMenuChoixGame();
                } else {
                    //si le joueur joue en joeur vs joueur
                    //demande aux joeur d'entrée un mot
                    String NewR = new String();
                    //boucle si le mot n'est pas valide
                    do {
                        NewR = JOptionPane.showInputDialog(null, "", "Entre un mots", DISPOSE_ON_CLOSE);
                        if (NewR.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Erreur mot invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (NewR.isBlank());
                    //reset tout
                    motIni(NewR);
                    chance = 0;
                    setImage(chance+"");
                    mots = NewR;
                    panelbut.resetAllBut();
                }

            } else {
                this.dispose();
            }
        } else {
            if (sound) {
                if ((chance == 5) && (!flag)) {
                    DataControleur.getInstance().getSound("AmongUs");
                } else {
                    if (flag) {
                        //joue le son "bonne réponse"
                        DataControleur.getInstance().getSound("Good");
                    } else {
                        //joue le son "mauvaise réponse"
                        DataControleur.getInstance().getSound("Bad");
                    }
                }
            }
        }
    }

    //set l'image du pendu
    private void setImage(String id) {
        labelimg.setIcon(DataControleur.getInstance().IniImg(id, 200, 300));
    }

    //set les option par défaut ou choisit pour le joueur
    private void SetOption(Option op) {
        panelLabel.setTaillePolice(op.getTaillePolice(), op.getPolice());
        panelLabel.setTextAligne(op.getSenseDuText());
    }

    @Override
    public void ButPresse(Character c) {
        motFind(c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "Option":
                ViewControleur.getInstance().CallOptionMenu(this);
                break;
        }
    }

    //modifie les options selon les choix de l'utilisateur
    @Override
    public void SetNewOption(String Action, String information) {
        Option op = DataControleur.getInstance().getOption();
        switch (Action) {
            case "Police":
                op.setPolice(information);
                break;
            case "Taille":
                int nb = Integer.parseInt(information);
                op.setTaillePolice(nb);
                break;
            case "Aling":
                op.setSenseDuText(information);
                break;
            case "Sound":
                op.setSound(Boolean.parseBoolean(information));
                sound = Boolean.parseBoolean(information);
                break;
            case "img":
                op.setStyleImg(information);
                if(chance>5){
                  setImage(chance+information);  
                }
                break;
        }
        SetOption(op);
        DataControleur.getInstance().setOption(op);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
       ViewControleur.getInstance().CallImgMenu(this); 
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
