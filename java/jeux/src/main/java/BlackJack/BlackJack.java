/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackJack;

import java.awt.Image;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ListModel;

/**
 *
 * @author Utilisateur
 */
public class BlackJack extends javax.swing.JFrame {

    /**
     * Creates new form BlackJack
     */
    public BlackJack() {
        initComponents();
        init();
    }

    public void init() {
        jLabelPointPlayer.setText("0");
        jLabelPointBanque.setText("0");
        jLabelTour.setText("Player");
    }
    //le joueur joue
    public void game() {
        //pioche une carte
        int tirage = tirage();
        //modif du score du joueur
        int score = SetPointPlayer(tirage);
        if (score == 21) {
            //win
            int restard = JOptionPane.showConfirmDialog(null, "Vous avez gagner, voulez-vous recommencer ? ", "Demande de confirmation", JOptionPane.YES_NO_OPTION);
            if (restard == 0) {
                clear();
            } else {
                this.dispose();
            }
        }
        //lose
        if (score >21) {
            int restard = JOptionPane.showConfirmDialog(null, "Vous avez perdu, voulez-vous recommencer ? ", "Demande de confirmation", JOptionPane.YES_NO_OPTION);
            if (restard == 0) {
                clear();
            } else {
                this.dispose();
            }
        }

    }
    //la banque joue
    public void banque() {
        int ScorePlayer = Integer.valueOf(jLabelPointPlayer.getText());
        int scoreB = 0;
        //arrette de jouer quand on dépase le score du joueur ou si la banque perd 
        do {
            int tirage = tirage();
            //change le score de la banue
            scoreB = SetPointbanque(tirage);
            if (scoreB > 21) {
                //win player
                int restard = JOptionPane.showConfirmDialog(null, "Vous avez gagner, voulez-vous recommencer ? ", "Demande de confirmation", JOptionPane.YES_NO_OPTION);
                if (restard == 0) {
                    //reset
                    clear();
                } else {
                    //close
                    this.dispose();
                }
            }
        } while (scoreB <= ScorePlayer);
        //defaite
        if ((scoreB <= 21) && (scoreB > ScorePlayer)) {
            int restard = JOptionPane.showConfirmDialog(null, "Vous avez perdu, voulez-vous recommencer ? ", "Demande de confirmation", JOptionPane.YES_NO_OPTION);
            if (restard == 0) {
                clear();
            } else {
                this.dispose();
            }
        }
    }
    //reset tout
    public void clear() {
        jLabelTour.setText("Player");
        jLabelPointBanque.setText("0");
        jLabelPointPlayer.setText("0");
        DefaultListModel mod = new DefaultListModel();
        jListBanque.setModel(mod);
        jListPlayer.setModel(mod);
        jLabelImgCard.setIcon(null);
    }

    //point du joueur
    public int SetPointPlayer(int Point) {
        int AddPoint = Integer.valueOf(jLabelPointPlayer.getText());
        //le cas de l'as
        if (Point == 1) {
            boolean flag = true;
            //demande utilisateur si il veut que l'as vaut 10 ou 1
            JRadioButton XButton = new JRadioButton("10");
            JRadioButton OButton = new JRadioButton("1");
            ButtonGroup group = new ButtonGroup();
            group.add(OButton);
            group.add(XButton);
            JPanel panel = new JPanel();
            panel.add(OButton);
            panel.add(XButton);
            //verifie si le joueur à  choisit quelque chose
            while (flag) {
                JOptionPane.showConfirmDialog(null, panel, "Quelle est la valeur de l'as ?", JOptionPane.OK_OPTION);
                if (XButton.isSelected()) {
                    AddPoint = AddPoint + 10;
                    flag = false;
                } else {
                    AddPoint = AddPoint + 1;
                    flag = false;
                }
            }
            //les autres
        } else {
            AddPoint = AddPoint + Point;
        }
        jLabelPointPlayer.setText(String.valueOf(AddPoint));
        return AddPoint;
    }

    //point de la banque
    public int SetPointbanque(int Point) {
        Random random = new Random();
        int AddPoint = Integer.valueOf(jLabelPointBanque.getText());
        //le cas de l'as
        if (Point == 1) {
            int x = random.nextInt(2);
            if ((x == 0) && (AddPoint < 12)) {
                AddPoint = AddPoint + 10;

            } else {
                AddPoint = AddPoint + 1;
            }

            //les autres
        } else {
            AddPoint = AddPoint + Point;
        }
        jLabelPointBanque.setText(String.valueOf(AddPoint));
        return AddPoint;
    }

    public int tirage() {
        String carte = "";
        //pioche d'un carte
        Random random = new Random();
        int tirage = random.nextInt(10);
        tirage++;
        //selection du type de la carte
        if (tirage != 10) {
            int type = random.nextInt(4);
            switch (type) {
                case 0:
                    carte = tirage + "_treffle";
                    break;
                case 1:
                    carte = tirage + "_pique";
                    break;
                case 2:
                    carte = tirage + "_coeur";
                    break;
                case 3:
                    carte = tirage + "_carreau";
                    break;
            }
            //si ses un 10 il selectionne entre roi,reine,valet,10
        } else {
            int type = random.nextInt(4);
            int type2 = random.nextInt(4);
            switch (type) {
                case 0:
                    carte = "" + tirage;
                    break;
                case 1:
                    carte = "reine";
                    break;
                case 2:
                    carte = "roi";
                    break;
                case 3:
                    carte = "valet";
                    break;
            }
            switch (type2) {
                case 0:
                    carte = carte + "_treffle";
                    break;
                case 1:
                    carte = carte + "_pique";
                    break;
                case 2:
                    carte = carte + "_coeur";
                    break;
                case 3:
                    carte = carte + "_carreau";
                    break;
            }
        }
        jLabelImgCard.setIcon(IniImg(carte));
        iniListe(carte);
        return tirage;
    }

    //remplit les liste des joueurs
    public void iniListe(String id) {
        DefaultListModel mod = new DefaultListModel();
        if (jLabelTour.getText() == "Player") {
            ListModel list = jListPlayer.getModel();
            int taille = list.getSize();
            int i = 0;
            while (i < taille) {
                mod.addElement(list.getElementAt(i));
                i++;
            }
            mod.addElement(IniImgList(id));
            jListPlayer.setModel(mod);
        } else {
            ListModel list = jListBanque.getModel();
            int taille = list.getSize();
            int i = 0;
            while (i < taille) {
                mod.addElement(list.getElementAt(i));
                i++;
            }
            mod.addElement(IniImgList(id));
            jListBanque.setModel(mod);

        }

    }

    //change la taille de l'image
    private ImageIcon IniImg(String ID) {
        ImageIcon icon = new ImageIcon("./img/BlackJack/" + ID + ".png");
        Image img = icon.getImage();
        Image imgScal = img.getScaledInstance(jLabelImgCard.getWidth(), jLabelImgCard.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon ScalIcon = new ImageIcon(imgScal);
        return ScalIcon;
    }
    //change la taille de l'image pour les list

    private ImageIcon IniImgList(String ID) {
        ImageIcon icon = new ImageIcon("./img/BlackJack/" + ID + ".png");
        Image img = icon.getImage();
        Image imgScal = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon ScalIcon = new ImageIcon(imgScal);
        return ScalIcon;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListBanque = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelPointBanque = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListPlayer = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelPointPlayer = new javax.swing.JLabel();
        jLabelImgCard = new javax.swing.JLabel();
        jButtonContinue = new javax.swing.JButton();
        jButtonFin = new javax.swing.JButton();
        jLabelTour = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BlackJack");

        jScrollPane1.setViewportView(jListBanque);

        jLabel1.setText("Banque");

        jLabel2.setText("Point :");
        jLabel2.setFocusable(false);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jScrollPane2.setViewportView(jListPlayer);

        jLabel3.setText("Player");

        jLabel4.setText("Point :");
        jLabel4.setFocusable(false);
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jButtonContinue.setText("Continue");
        jButtonContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContinueActionPerformed(evt);
            }
        });

        jButtonFin.setText("Fin");
        jButtonFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelPointBanque, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelImgCard, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonContinue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonFin))
                    .addComponent(jLabelTour, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelPointPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonContinue, jButtonFin});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPointBanque, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelImgCard, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonContinue)
                                    .addComponent(jButtonFin))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelTour, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelPointPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonContinue, jButtonFin});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContinueActionPerformed
        game();
    }//GEN-LAST:event_jButtonContinueActionPerformed

    private void jButtonFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinActionPerformed
        jLabelTour.setText("Banque");
        banque();
    }//GEN-LAST:event_jButtonFinActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonContinue;
    private javax.swing.JButton jButtonFin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelImgCard;
    private javax.swing.JLabel jLabelPointBanque;
    private javax.swing.JLabel jLabelPointPlayer;
    private javax.swing.JLabel jLabelTour;
    private javax.swing.JList<ImageIcon> jListBanque;
    private javax.swing.JList<ImageIcon> jListPlayer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
