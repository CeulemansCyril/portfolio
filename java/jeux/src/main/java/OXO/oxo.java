package OXO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Utilisateur
 */
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class oxo extends javax.swing.JFrame {

    /**
     * Creates new form oxo
     */
    int nbCout = 9;

    public oxo(String id) {
        initComponents();
        jLabelTour.setText(id);
        clearsAll();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelOXO = new javax.swing.JPanel();
        jLabelTour = new javax.swing.JLabel();
        jLabelRightUp = new javax.swing.JLabel();
        jLabelBottomUp = new javax.swing.JLabel();
        jLabelLeftBottom = new javax.swing.JLabel();
        jLabelBottom = new javax.swing.JLabel();
        jLabelRightBottom = new javax.swing.JLabel();
        jLabelLeftUp = new javax.swing.JLabel();
        jLabeLeftLow = new javax.swing.JLabel();
        jLabeBottomLow = new javax.swing.JLabel();
        jLabeRightLow = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelScoreO = new javax.swing.JLabel();
        jLabelScoreX = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("OXO");

        jLabelTour.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelRightUp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelRightUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelRightUpMouseClicked(evt);
            }
        });

        jLabelBottomUp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelBottomUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBottomUpMouseClicked(evt);
            }
        });

        jLabelLeftBottom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelLeftBottom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLeftBottomMouseClicked(evt);
            }
        });

        jLabelBottom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelBottom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBottomMouseClicked(evt);
            }
        });

        jLabelRightBottom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelRightBottom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelRightBottomMouseClicked(evt);
            }
        });

        jLabelLeftUp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelLeftUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLeftUpMouseClicked(evt);
            }
        });

        jLabeLeftLow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabeLeftLow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabeLeftLowMouseClicked(evt);
            }
        });

        jLabeBottomLow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabeBottomLow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabeBottomLowMouseClicked(evt);
            }
        });

        jLabeRightLow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabeRightLow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabeRightLowMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelOXOLayout = new javax.swing.GroupLayout(jPanelOXO);
        jPanelOXO.setLayout(jPanelOXOLayout);
        jPanelOXOLayout.setHorizontalGroup(
            jPanelOXOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
            .addGroup(jPanelOXOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelOXOLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelOXOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelOXOLayout.createSequentialGroup()
                            .addComponent(jLabeLeftLow, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabeBottomLow, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabeRightLow, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelOXOLayout.createSequentialGroup()
                            .addComponent(jLabelLeftBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabelBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabelRightBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelOXOLayout.createSequentialGroup()
                            .addComponent(jLabelLeftUp, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanelOXOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelOXOLayout.createSequentialGroup()
                                    .addComponent(jLabelBottomUp, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabelRightUp, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabelTour, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap()))
        );
        jPanelOXOLayout.setVerticalGroup(
            jPanelOXOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
            .addGroup(jPanelOXOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelOXOLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelTour, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelOXOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelRightUp, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelBottomUp, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelLeftUp, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelOXOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelLeftBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelRightBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelOXOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabeLeftLow, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabeBottomLow, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabeRightLow, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(51, Short.MAX_VALUE)))
        );

        jLabel1.setText("Score");

        jLabel2.setText("X");

        jLabel3.setText("O");

        jLabelScoreO.setText("0");

        jLabelScoreX.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelScoreX, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelScoreO, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelScoreX)
                    .addComponent(jLabelScoreO))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanelOXO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jPanelOXO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelRightUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelRightUpMouseClicked
        Icon ico = jLabelRightUp.getIcon();
        //si la case est prise, le joueur ne peut pas l'utiliser
        if (ico.toString() == "Blanc") {
            nbCout--;
            //recuper le joueur et la taille de la case
            String id = jLabelTour.getText();
            int Wid = jLabelRightUp.getWidth();
            int Hei = jLabelRightUp.getHeight();
            jLabelRightUp.setIcon(IniImg(id, Wid, Hei));
            //change de joueur
            if (id.endsWith("X")) {
                jLabelTour.setText("O");
            } else {
                jLabelTour.setText("X");
            }
            victory(id);
        }
        if (nbCout == 0) {
            clearsAll();
        }
    }//GEN-LAST:event_jLabelRightUpMouseClicked

    private void jLabelBottomUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBottomUpMouseClicked
        Icon ico = jLabelBottomUp.getIcon();
        if (ico.toString() == "Blanc") {
            nbCout--;
            String id = jLabelTour.getText();
            int Wid = jLabelBottomUp.getWidth();
            int Hei = jLabelBottomUp.getHeight();
            jLabelBottomUp.setIcon(IniImg(id, Wid, Hei));
            if (id.endsWith("X")) {
                jLabelTour.setText("O");
            } else {
                jLabelTour.setText("X");
            }
            victory(id);
        }
        if (nbCout == 0) {
            clearsAll();
        }
    }//GEN-LAST:event_jLabelBottomUpMouseClicked

    private void jLabelLeftUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLeftUpMouseClicked
        Icon ico = jLabelLeftUp.getIcon();
        if (ico.toString() == "Blanc") {
            nbCout--;
            String id = jLabelTour.getText();
            int Wid = jLabelLeftUp.getWidth();
            int Hei = jLabelLeftUp.getHeight();
            jLabelLeftUp.setIcon(IniImg(id, Wid, Hei));
            if (id.endsWith("X")) {
                jLabelTour.setText("O");
            } else {
                jLabelTour.setText("X");
            }
            victory(id);
        }
        if (nbCout == 0) {
            clearsAll();
        }
    }//GEN-LAST:event_jLabelLeftUpMouseClicked

    private void jLabelRightBottomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelRightBottomMouseClicked
        Icon ico = jLabelRightBottom.getIcon();
        if (ico.toString() == "Blanc") {
            String id = jLabelTour.getText();
            int Wid = jLabelRightBottom.getWidth();
            int Hei = jLabelRightBottom.getHeight();
            jLabelRightBottom.setIcon(IniImg(id, Wid, Hei));
            if (id.endsWith("X")) {
                jLabelTour.setText("O");
            } else {
                jLabelTour.setText("X");
            }
            victory(id);
            nbCout--;
        }
        if (nbCout == 0) {
            clearsAll();
        }
    }//GEN-LAST:event_jLabelRightBottomMouseClicked

    private void jLabelBottomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBottomMouseClicked
        Icon ico = jLabelBottom.getIcon();
        if (ico.toString() == "Blanc") {
            nbCout--;
            String id = jLabelTour.getText();
            int Wid = jLabelBottom.getWidth();
            int Hei = jLabelBottom.getHeight();
            jLabelBottom.setIcon(IniImg(id, Wid, Hei));
            if (id.endsWith("X")) {
                jLabelTour.setText("O");
            } else {
                jLabelTour.setText("X");
            }
            victory(id);
        }
        if (nbCout == 0) {
            clearsAll();
        }
    }//GEN-LAST:event_jLabelBottomMouseClicked

    private void jLabelLeftBottomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLeftBottomMouseClicked
        Icon ico = jLabelLeftBottom.getIcon();
        if (ico.toString() == "Blanc") {
            nbCout--;
            String id = jLabelTour.getText();
            int Wid = jLabelLeftBottom.getWidth();
            int Hei = jLabelLeftBottom.getHeight();
            jLabelLeftBottom.setIcon(IniImg(id, Wid, Hei));
            if (id.endsWith("X")) {
                jLabelTour.setText("O");
            } else {
                jLabelTour.setText("X");
            }
            victory(id);
        }
        if (nbCout == 0) {
            clearsAll();
        }
    }//GEN-LAST:event_jLabelLeftBottomMouseClicked

    private void jLabeRightLowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabeRightLowMouseClicked
        Icon ico = jLabeRightLow.getIcon();
        if (ico.toString() == "Blanc") {
            nbCout--;
            String id = jLabelTour.getText();
            int Wid = jLabeRightLow.getWidth();
            int Hei = jLabeRightLow.getHeight();
            jLabeRightLow.setIcon(IniImg(id, Wid, Hei));
            if (id.endsWith("X")) {
                jLabelTour.setText("O");
            } else {
                jLabelTour.setText("X");
            }
            victory(id);
        }
        if (nbCout == 0) {
            clearsAll();
        }
    }//GEN-LAST:event_jLabeRightLowMouseClicked

    private void jLabeBottomLowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabeBottomLowMouseClicked
        Icon ico = jLabeBottomLow.getIcon();
        if (ico.toString() == "Blanc") {
            nbCout--;
            String id = jLabelTour.getText();
            int Wid = jLabeBottomLow.getWidth();
            int Hei = jLabeBottomLow.getHeight();
            jLabeBottomLow.setIcon(IniImg(id, Wid, Hei));
            if (id.endsWith("X")) {
                jLabelTour.setText("O");
            } else {
                jLabelTour.setText("X");
            }
            victory(id);
        }
        if (nbCout == 0) {
            clearsAll();
        }
    }//GEN-LAST:event_jLabeBottomLowMouseClicked

    private void jLabeLeftLowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabeLeftLowMouseClicked
        Icon ico = jLabeLeftLow.getIcon();
        if (ico.toString() == "Blanc") {
            nbCout--;
            String id = jLabelTour.getText();
            int Wid = jLabeLeftLow.getWidth();
            int Hei = jLabeLeftLow.getHeight();
            jLabeLeftLow.setIcon(IniImg(id, Wid, Hei));
            if (id.endsWith("X")) {
                jLabelTour.setText("O");
            } else {
                jLabelTour.setText("X");
            }
            victory(id);
        }
        if (nbCout == 0) {
            clearsAll();
        }
    }//GEN-LAST:event_jLabeLeftLowMouseClicked

    private ImageIcon IniImg(String ID, int Wid, int Hei) {
        ImageIcon icon = new ImageIcon("./img/oxo/" + ID + ".png");
        Image img = icon.getImage();
        Image imgScal = img.getScaledInstance(Wid, Hei, Image.SCALE_SMOOTH);
        ImageIcon ScalIcon = new ImageIcon(imgScal);
        ScalIcon.setDescription(ID);
        return ScalIcon;
    }

    private void victory(String id) {
        Icon Leftup = jLabelLeftUp.getIcon();
        Icon LeftBottom = jLabelLeftBottom.getIcon();
        Icon LeftLow = jLabeLeftLow.getIcon();
        Icon Rightup = jLabelRightUp.getIcon();
        Icon RightBottom = jLabelRightBottom.getIcon();
        Icon RightLow = jLabeRightLow.getIcon();
        Icon Bottom = jLabelBottom.getIcon();
        Icon BottomUp = jLabelBottomUp.getIcon();
        Icon BottomLow = jLabeBottomLow.getIcon();
        //reussite ligne
        if ((Leftup.toString() == id) && (BottomUp.toString() == id) && (Rightup.toString() == id)) {
            clearsAll();
            score(id);
        }
        if ((LeftBottom.toString() == id) && (RightBottom.toString() == id) && (Bottom.toString() == id)) {
            clearsAll();
            score(id);
        }
        if ((RightLow.toString() == id) && (LeftLow.toString() == id) && (BottomLow.toString() == id)) {
            clearsAll();
            score(id);
        }
        //reussite colone
        if ((RightBottom.toString() == id) && (RightLow.toString() == id) && (Rightup.toString() == id)) {
            clearsAll();
            score(id);
        }
        if ((Bottom.toString() == id) && (BottomLow.toString() == id) && (BottomUp.toString() == id)) {
            clearsAll();
            score(id);
        }
        if ((LeftBottom.toString() == id) && (LeftLow.toString() == id) && (Leftup.toString() == id)) {
            clearsAll();
            score(id);
        }
        //reussite diagonal
        if ((Leftup.toString() == id) && (Bottom.toString() == id) && (RightLow.toString() == id)) {
            clearsAll();
            score(id);
        }
        //reussite diagonal
        if ((LeftLow.toString() == id) && (Bottom.toString() == id) && (Rightup.toString() == id)) {
            clearsAll();
            score(id);
        }

    }

    public void clearsAll() {
        Icon icon = IniImg("Blanc", jLabeBottomLow.getWidth(), jLabeBottomLow.getHeight());
        jLabeBottomLow.setIcon(icon);
        jLabeRightLow.setIcon(icon);
        jLabeLeftLow.setIcon(icon);
        jLabelBottom.setIcon(icon);
        jLabelLeftBottom.setIcon(icon);
        jLabelRightBottom.setIcon(icon);
        jLabelBottomUp.setIcon(icon);
        jLabelLeftUp.setIcon(icon);
        jLabelRightUp.setIcon(icon);
        jPanelOXO.updateUI();
        nbCout = 9;
    }

    public void score(String id) {
        if (id.equalsIgnoreCase("x")) {
            int Score = Integer.parseInt(jLabelScoreX.getText());
            Score++;
            String sco = String.valueOf(Score);
            jLabelScoreX.setText(sco);
        } else {
            int Score = Integer.parseInt(jLabelScoreO.getText());
            Score++;
            String sco = String.valueOf(Score);
            jLabelScoreO.setText(sco);
        }
    }

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabeBottomLow;
    private javax.swing.JLabel jLabeLeftLow;
    private javax.swing.JLabel jLabeRightLow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelBottom;
    private javax.swing.JLabel jLabelBottomUp;
    private javax.swing.JLabel jLabelLeftBottom;
    private javax.swing.JLabel jLabelLeftUp;
    private javax.swing.JLabel jLabelRightBottom;
    private javax.swing.JLabel jLabelRightUp;
    private javax.swing.JLabel jLabelScoreO;
    private javax.swing.JLabel jLabelScoreX;
    private javax.swing.JLabel jLabelTour;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelOXO;
    // End of variables declaration//GEN-END:variables
}