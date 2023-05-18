/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author Utilisateur
 */
public class Dames2 extends javax.swing.JFrame {

    private JPanel chessBoard;
    private final JPanel gui = new JPanel(new BorderLayout(10, 10));
    private final JButton[][] chessCase = new JButton[10][10];
    private static final String COLS = "ABCDEFGHIJ";
    private String Tour = "B";
    private Point pion;
    private Point PionEnemy;
    private Point PionEnemy2;
    private Point caseKillP = null;
    private Point caseKillP2 = null;
    private Point caseKillD_HG;
    private Point caseKillD_HD;
    private Point caseKillD_BG;
    private Point caseKillD_BD;
    private Point PionEnemy_HG;
    private Point PionEnemy_HD;
    private Point PionEnemy_BG;
    private Point PionEnemy_BD;
    private int PionNoir = 20;
    private int PionBlanc = 20;
    private static final JFrame f = new JFrame("Dames");
    private int HSizeBut = 0;
    private int XChessesMax = 0;
    private int XChessesMin = 0;
    private int YChessesMax = 0;
    private int YChessesMin = 0;
    private boolean SizeCheese=true;

    public Dames2() {
        initComposant();
        initDame();
    }

    private void initDame() {
        chessBoard = new JPanel(new GridLayout(0, 11));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        Insets boutton = new Insets(0, 0, 0, 0);
        for (int li = 0; li < 10; li++) {
            for (int col = 0; col < 10; col++) {
                JButton button = new JButton();
                button.setMargin(boutton);
                //pion noir
                if (col < 4) {
                    if (col % 2 == 1) {
                        if (li % 2 == 0) {
                            button.setIcon(IniImg("PionNoir"));
                        }
                    } else {
                        if (li % 2 == 1) {
                            button.setIcon(IniImg("PionNoir"));
                        }
                    }
                }
                //pion blanc
                if (col > 5) {
                    if (col % 2 == 1) {
                        if (li % 2 == 0) {
                            button.setIcon(IniImg("PionBla"));
                        }
                    } else {
                        if (li % 2 == 1) {
                            button.setIcon(IniImg("PionBla"));
                        }
                    }

                }
                //case vide
                if ((col > 4) && (col < 5)) {
                    ImageIcon icon = new ImageIcon(
                            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                    button.setIcon(icon);
                }
                //background
                if (li % 2 == 0) {
                    if (col % 2 == 0) {
                        button.setBackground(Color.BLACK);
                    } else {
                        button.setBackground(Color.WHITE);
                    }
                } else {
                    if (col % 2 == 0) {
                        button.setBackground(Color.white);
                    } else {
                        button.setBackground(Color.black);
                    }
                }
                //clicket boutton
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        JButton but = (JButton) e.getSource();
                        ImageIcon icon = (ImageIcon) but.getIcon();
                        //System.out.println(but.getLocation());
                        if(SizeCheese){
                            setSizeBut(but);
                            SizeCheese=false;
                        }     
                        if (icon != null) {
                            //Background change selon les déplacements que le pion peut faire 
                            Point p = new Point(but.getLocation());
                            pion = p;
                            clearsBackground();
                            int x = (int) p.getX();
                            int y = (int) p.getY();
                            //les pionts noir
                            if ((Tour.equals("N")) && (icon.getDescription().equals("PionN"))) {
                                PlayPionNoir(x, y);
                                //pion blanc   
                            } else if ((Tour.equals("B")) && (icon.getDescription().equals("PionB"))) {
                                PlayPionBlanc(x, y);
                            } //Dame blanche    
                            else if ((Tour.equals("B")) && (icon.getDescription().equals("DameB"))) {
                                resetEnemy();
                                int x2 = (int) x;
                                int y2 = (int) y;
                                DameColo(x2, y2);

                                // Dame Noire   
                            } else if ((Tour.equals("N")) && (icon.getDescription().equals("DameN"))) {
                                resetEnemy();
                                int x2 = (int) x;
                                int y2 = (int) y;
                                DameColo(x2, y2);

                            }
                            //deplacement  
                        } else if (but.getBackground() == Color.GRAY) {
                            Point p = new Point(but.getLocation());
                            clearsBackground();
                            if (Tour.equals("N")) {
                                //Deplacement 
                                if (PionDepla(but)) {
                                    Tour = "B";
                                }
                            } else if (Tour.equals("B")) {
                                //Deplacement 
                                if (PionDepla(but)) {
                                    Tour = "N";
                                }
                            }
                        }
                        if (PionBlanc == 0) {
                            Victory("Noir");
                        } else if (PionNoir == 0) {
                            Victory("Blanc");
                        }
                    }
                });
                chessCase[li][col] = button;
            }
        }

        chessBoard.add(new JLabel(""));
        for (int i = 0; i < 10; i++) {
            chessBoard.add(
                    new JLabel(COLS.substring(i, i + 1),
                            SwingConstants.CENTER));
        }
        for (int li2 = 0; li2 < 10; li2++) {
            for (int col2 = 0; col2 < 10; col2++) {
                switch (col2) {
                    case 0:
                        chessBoard.add(new JLabel("" + (li2 + 1),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessCase[col2][li2]);
                }
            }
        }

    }

    private void setSizeBut(JButton but) {
        HSizeBut = but.getHeight();
        int x = but.getX();
        int y = but.getY();
        boolean flag = true;
        //le Y min 
        while (flag) {
            y = y - HSizeBut;
            Point p2 = new Point(x, y);
            try {
                JButton but2 = (JButton) chessBoard.getComponentAt(p2);
                YChessesMin = but2.getY();
            } catch (ClassCastException s) {
                flag = false;
            }catch (NullPointerException v){
               flag = false; 
            }
        }
        if (YChessesMin == 0) {
            YChessesMin = but.getY();
        }
        x = but.getX();
        y = but.getY();
        flag = true;
        //le Y Max
        while (flag) {
            y = y + HSizeBut;
            Point p2 = new Point(x, y);
            try {
                JButton but2 = (JButton) chessBoard.getComponentAt(p2);
                YChessesMax = but2.getY();
            } catch (ClassCastException s) {
                flag = false;
            }catch (NullPointerException v){
               flag = false; 
            }
        }
        if(YChessesMax==0){
            YChessesMax=but.getY();
        }
        x = but.getX();
        y = but.getY();
        flag = true;
        //le X Min
        while (flag) {
            x = x - HSizeBut;
            Point p2 = new Point(x, y);
            try {
                JButton but2 = (JButton) chessBoard.getComponentAt(p2);
                XChessesMin = but2.getX();
            } catch (ClassCastException s) {
                flag = false;
            }catch (NullPointerException v){
               flag = false; 
            }
        }
        if(XChessesMin==0){
            XChessesMin=but.getX();
        }
        x = but.getX();
        y = but.getY();
        flag = true;
        //le X Max
        while (flag) {
            x = x + HSizeBut;
            Point p2 = new Point(x, y);
            try {
                JButton but2 = (JButton) chessBoard.getComponentAt(p2);
                XChessesMax = but2.getX();
            } catch (ClassCastException s) {
                flag = false;
            } catch (NullPointerException v){
               flag = false; 
            }
        }
        if(XChessesMax==0){
            XChessesMax=but.getX();
        }
    }

    //code des pion noir
    private void PlayPionNoir(int x, int y) {
        resetEnemy();
        ImageIcon iconPion;
        int caseX = x;
        int caseY = y;
        //2 case gris
        if (((caseX > XChessesMin) && (caseX < XChessesMax)) && (caseY < YChessesMax)) {
            caseX = caseX - HSizeBut;
            caseY = caseY + HSizeBut;
            pionCaseDeplace(caseX, caseY);
            Point p2 = new Point(caseX, caseY);
            JButton but2 = (JButton) chessBoard.getComponentAt(p2);
            try {
                iconPion = (ImageIcon) but2.getIcon();
                if ((iconPion.getDescription().equalsIgnoreCase("PionB")) || (iconPion.getDescription().equalsIgnoreCase("DameB"))) {
                    Point pPion = new Point(caseX, caseY);
                    PionEnemy = pPion;
                    pionCaseDeplaceEnemy(caseX - HSizeBut, caseY + HSizeBut);
                }
            } catch (NullPointerException z) {
            }
            caseX = x;
            caseY = y;
            caseX = caseX + HSizeBut;
            caseY = caseY + HSizeBut;
            pionCaseDeplace(caseX, caseY);
            p2 = new Point(caseX, caseY);
            but2 = (JButton) chessBoard.getComponentAt(p2);
            but2.setBackground(Color.GRAY);
            try {
                iconPion = (ImageIcon) but2.getIcon();
                if ((iconPion.getDescription().equalsIgnoreCase("PionB")) || (iconPion.getDescription().equalsIgnoreCase("DameB"))) {
                    Point pPion = new Point(caseX, caseY);
                    PionEnemy2 = pPion;
                    pionCaseDeplaceEnemy(caseX + HSizeBut, caseY + HSizeBut);
                }
            } catch (NullPointerException z) {
            }
            //case gris gauche   
        } else if ((caseX == XChessesMin) && (caseY < YChessesMax)) {
            caseX = caseX + HSizeBut;
            caseY = caseY + HSizeBut;
            Point p2 = new Point(caseX, caseY);
            JButton but2 = (JButton) chessBoard.getComponentAt(p2);
            but2.setBackground(Color.GRAY);
            try {
                iconPion = (ImageIcon) but2.getIcon();
                if ((iconPion.getDescription().equalsIgnoreCase("PionB")) || (iconPion.getDescription().equalsIgnoreCase("DameB"))) {
                    Point pPion = new Point(caseX, caseY);
                    PionEnemy = pPion;
                    pionCaseDeplaceEnemy(caseX + HSizeBut, caseY + HSizeBut);
                }
            } catch (NullPointerException z) {
            }
            //case gris droite   
        } else if ((caseX == XChessesMax) && (y < YChessesMax)) {
            caseX = caseX - HSizeBut;
            caseY = caseY + HSizeBut;
            Point p2 = new Point(caseX, caseY);
            JButton but2 = (JButton) chessBoard.getComponentAt(p2);
            but2.setBackground(Color.GRAY);
            try {
                iconPion = (ImageIcon) but2.getIcon();
                if ((iconPion.getDescription().equalsIgnoreCase("PionB")) || (iconPion.getDescription().equalsIgnoreCase("DameB"))) {
                    Point pPion = new Point(caseX, caseY);
                    PionEnemy = pPion;
                    pionCaseDeplaceEnemy(caseX - HSizeBut, caseY + HSizeBut);
                }
            } catch (NullPointerException z) {
            }
        }
    }

    //code des pions blanc
    private void PlayPionBlanc(int x, int y) {
        resetEnemy();
        ImageIcon iconPion;
        int caseX = x;
        int caseY = y;
        //2 case gris
        if (((caseX > XChessesMin) && (caseX <XChessesMax )) && (caseY > YChessesMin)) {
            caseX = caseX + HSizeBut;
            caseY = caseY - HSizeBut;
            pionCaseDeplace(caseX, caseY);
            Point p2 = new Point(caseX, caseY);
            JButton but2 = (JButton) chessBoard.getComponentAt(p2);
            try {
                iconPion = (ImageIcon) but2.getIcon();
                if ((iconPion.getDescription().equalsIgnoreCase("PionN")) || (iconPion.getDescription().equalsIgnoreCase("DameN"))) {
                    Point pPion = new Point(caseX, caseY);
                    PionEnemy = pPion;
                    pionCaseDeplaceEnemy(caseX + HSizeBut, caseY - HSizeBut);

                }
            } catch (NullPointerException z) {
            }
            caseX = x;
            caseY = y;
            caseX = caseX - HSizeBut;
            caseY = caseY - HSizeBut;
            pionCaseDeplace(caseX, caseY);
            p2 = new Point(caseX, caseY);
            but2 = (JButton) chessBoard.getComponentAt(p2);
            try {
                iconPion = (ImageIcon) but2.getIcon();
                if ((iconPion.getDescription().equalsIgnoreCase("PionN")) || (iconPion.getDescription().equalsIgnoreCase("DameN"))) {
                    Point pPion = new Point(caseX, caseY);
                    PionEnemy2 = pPion;
                    pionCaseDeplaceEnemy(caseX - HSizeBut, caseY - HSizeBut);
                }
            } catch (NullPointerException z) {
            }

            //case gauche gris    
        } else if ((caseX == XChessesMin) && (caseY > XChessesMin)) {
            caseX = caseX + HSizeBut;
            caseY = caseY - HSizeBut;
            Point p2 = new Point(caseX, caseY);
            JButton but2 = (JButton) chessBoard.getComponentAt(p2);
            but2.setBackground(Color.GRAY);
            try {
                iconPion = (ImageIcon) but2.getIcon();
                if ((iconPion.getDescription().equalsIgnoreCase("PionN")) || (iconPion.getDescription().equalsIgnoreCase("DameN"))) {
                    Point pPion = new Point(caseX, caseY);
                    PionEnemy = pPion;
                    pionCaseDeplaceEnemy(caseX + HSizeBut, caseY - HSizeBut);

                }
            } catch (NullPointerException z) {
            }
            //case droite gris    
        } else if ((caseX == XChessesMax) && (caseY > YChessesMin)) {
            caseX = caseX - HSizeBut;
            caseY = caseY - HSizeBut;
            Point p2 = new Point(caseX, caseY);
            JButton but2 = (JButton) chessBoard.getComponentAt(p2);
            but2.setBackground(Color.GRAY);
            try {
                iconPion = (ImageIcon) but2.getIcon();
                if ((iconPion.getDescription().equalsIgnoreCase("PionN")) || (iconPion.getDescription().equalsIgnoreCase("DameN"))) {
                    Point pPion = new Point(caseX, caseY);
                    PionEnemy = pPion;
                    pionCaseDeplaceEnemy(caseX - HSizeBut, caseY - HSizeBut);

                }
            } catch (NullPointerException z) {
            }
        }
    }

    private void Victory(String win) {
        JButton Res = new JButton("Recommencer");
        JButton Qui = new JButton("Quiter");
        JLabel lab = new JLabel("Les Pions " + win + " on gagnier");
        JPanel panel = new JPanel();
        panel.add(lab);
        panel.add(Res);
        panel.add(Qui);
        JFrame frame = new JFrame("Victoir");
        frame.add(panel);
        frame.setSize(200, 200);
        frame.setVisible(true);
        //reset
        Res.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Tour = "B";
                PionBlanc = 20;
                PionNoir = 20;
                replacePion();

            }
        });
        //quitte
        Qui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                f.dispose();
            }
        });

    }

    private void replacePion() {
        //reset tout les bouton
        int y = 67;
        for (int li = 0; li < 10; li++) {
            int x = 67;
            for (int col = 0; col < 10; col++) {
                Point p = new Point(x, y);
                JButton button = (JButton) chessBoard.getComponentAt(p);
                //background
                if (li % 2 == 0) {
                    if (col % 2 == 0) {
                        button.setIcon(null);
                        button.setBackground(Color.BLACK);
                    } else {
                        button.setIcon(null);
                        button.setBackground(Color.WHITE);

                    }
                } else {
                    if (col % 2 == 0) {
                        button.setIcon(null);
                        button.setBackground(Color.white);

                    } else {
                        button.setIcon(null);
                        button.setBackground(Color.black);
                    }
                }
                placeImg(button, col % HSizeBut, li % HSizeBut);
                x = x + HSizeBut;
            }
            y = y + HSizeBut;
        }

    }

    //place les image
    private void placeImg(JButton button, int col, int li) {
        //pion noir
        if (li < 4) {
            if (li % 2 == 1) {
                if (col % 2 == 0) {
                    button.setIcon(IniImg("PionNoir"));
                }
            } else {
                if (col % 2 == 1) {
                    button.setIcon(IniImg("PionNoir"));
                }
            }
        }
        //pion blanc
        if (li > 5) {
            if (li % 2 == 1) {
                if (col % 2 == 0) {
                    button.setIcon(IniImg("PionBla"));
                }
            } else {
                if (col % 2 == 1) {
                    button.setIcon(IniImg("PionBla"));
                }
            }

        }
    }

    //color les cases pour pion
    private void pionCaseDeplace(int caseX, int caseY) {
        if (((caseX >= XChessesMin) && (caseX <= XChessesMax)) && ((caseY >= YChessesMin) && (caseY <= YChessesMax))) {
            Point p = new Point(caseX, caseY);
            JButton but = (JButton) chessBoard.getComponentAt(p);
            but.setBackground(Color.GRAY);
        }
    }

    //color les cases pour dame
    private boolean DameCaseDeplace(int caseX, int caseY) {
        if (((caseX >= XChessesMin) && (caseX <= XChessesMax)) && ((caseY >= YChessesMin) && (caseY <= YChessesMax))) {
            Point p = new Point(caseX, caseY);
            JButton but = (JButton) chessBoard.getComponentAt(p);
            but.setBackground(Color.GRAY);
            return true;
        }
        return false;
    }

    //localise les pion que la dame peut prendre
    private boolean DameKill(int caseX, int caseY, String Cote) {

        try {
            Point p = new Point(caseX, caseY);
            JButton but = (JButton) chessBoard.getComponentAt(p);
            ImageIcon icon = (ImageIcon) but.getIcon();
            if (icon != null) {
                Point p2 = new Point();

                if ((Cote.equals("HG")) && (PionEnemy_HG == null)) {
                    p2.setLocation(caseX - HSizeBut, caseY - HSizeBut);
                    DameCaseDeplace(caseX - HSizeBut, caseY - HSizeBut);
                    caseKillD_HG = p2;
                    p2 = new Point();
                    p2.setLocation(caseX, caseY);
                    PionEnemy_HG = p2;
                    return false;
                } else if ((Cote.equals("HD")) && (PionEnemy_HD == null)) {
                    p2.setLocation(caseX + HSizeBut, caseY - HSizeBut);
                    DameCaseDeplace(caseX + HSizeBut, caseY - HSizeBut);
                    caseKillD_HD = p2;
                    p2 = new Point();
                    p2.setLocation(caseX, caseY);
                    PionEnemy_HD = p2;
                    return false;
                } else if ((Cote.equals("BD")) && (PionEnemy_BD == null)) {
                    p2.setLocation(caseX + HSizeBut, caseY + HSizeBut);
                    DameCaseDeplace(caseX + HSizeBut, caseY + HSizeBut);
                    caseKillD_BD = p2;
                    p2 = new Point();
                    p2.setLocation(caseX, caseY);
                    PionEnemy_BD = p2;
                    return false;
                } else if ((Cote.equals("BG")) && (PionEnemy_BG == null)) {
                    p2.setLocation(caseX - HSizeBut, caseY + HSizeBut);
                    DameCaseDeplace(caseX - HSizeBut, caseY + HSizeBut);
                    caseKillD_BG = p2;
                    p2 = new Point();
                    p2.setLocation(caseX, caseY);
                    PionEnemy_BG = p2;
                    return false;
                }
            }
        } catch (NullPointerException v) {
        }
        return true;
    }

    private void DameColo(int x, int y) {
        int caseX = x;
        int caseY = y;
        boolean ennemiLock = true;
        caseX = caseX + HSizeBut;
        caseY = caseY + HSizeBut;
        //Bas Droit diago    
        while ((DameCaseDeplace(caseX, caseY)) && (ennemiLock)) {
            if (ennemiLock) {
                ennemiLock = DameKill(caseX, caseY, "BD");
            }
            caseX = caseX + HSizeBut;
            caseY = caseY + HSizeBut;
        }
        ennemiLock = true;
        caseX = x;
        caseY = y;
        caseX = caseX - HSizeBut;
        caseY = caseY + HSizeBut;
        //Bas Gauche diago
        while ((DameCaseDeplace(caseX, caseY)) && (ennemiLock)) {
            if (ennemiLock) {
                ennemiLock = DameKill(caseX, caseY, "BG");
            }
            caseX = caseX - HSizeBut;
            caseY = caseY + HSizeBut;
        }
        ennemiLock = true;
        caseX = x;
        caseY = y;
        caseX = caseX + HSizeBut;
        caseY = caseY - HSizeBut;
        //Haut droit diago
        while ((DameCaseDeplace(caseX, caseY)) && (ennemiLock)) {
            if (ennemiLock) {
                ennemiLock = DameKill(caseX, caseY, "HD");
            }
            caseX = caseX + HSizeBut;
            caseY = caseY - HSizeBut;
        }

        ennemiLock = true;
        caseX = x;
        caseY = y;
        caseX = caseX - HSizeBut;
        caseY = caseY - HSizeBut;
        //Haut Gauche diago
        while ((DameCaseDeplace(caseX, caseY)) && (ennemiLock)) {
            if (ennemiLock) {
                ennemiLock = DameKill(caseX, caseY, "HG");
            }
            caseX = caseX - HSizeBut;
            caseY = caseY - HSizeBut;
        }

    }

    //color la case qui prend le pion
    private void pionCaseDeplaceEnemy(int caseX, int caseY) {
        if (((caseX >= XChessesMin) && (caseX <= XChessesMax)) && ((caseY >= YChessesMin) && (caseY <= YChessesMax))) {
            Point p = new Point(caseX, caseY);
            JButton but = (JButton) chessBoard.getComponentAt(p);
            but.setBackground(Color.GRAY);
            //recuper la case qui prendra le pion
            if (((PionEnemy != null) && (caseKillP == null)) && ((PionEnemy.getX() >= XChessesMin) && (PionEnemy.getX() <= XChessesMax)) && ((PionEnemy.getY() >= YChessesMin) && (PionEnemy.getY() <= YChessesMax))) {
                caseKillP = p;
            } else if (((PionEnemy2 != null) && (caseKillP2 == null)) && ((PionEnemy2.getX() >= XChessesMin) && (PionEnemy2.getX() <= XChessesMax)) && ((PionEnemy2.getY() >= YChessesMin) && (PionEnemy2.getY() <= YChessesMax))) {
                caseKillP2 = p;
            }
        }
    }

    //deplace un pion
    private boolean PionDepla(JButton but) {
        Point p = but.getLocation();
        boolean flag = true;
        flag = PionKiller(p);
        //recuper le pion qu'on veux deplacer
        JButton butPion = (JButton) chessBoard.getComponentAt(pion);
        //change pion blanc en dame blanc
        if ((p.getY() == YChessesMin) && (Tour.equals("B"))) {
            butPion.setIcon(null);
            but.setIcon(IniImg("DamesBla"));

            //change pion noir en dame noir    
        } else if ((p.getY() == YChessesMax) && (Tour.equals("N"))) {
            butPion.setIcon(null);
            but.setIcon(IniImg("DamesNoir"));
            //efface l'image (deplace le pion)    
        } else {
            ImageIcon icon2 = (ImageIcon) butPion.getIcon();
            butPion.setIcon(null);
            but.setIcon(icon2);
        }
        return flag;
    }

    private boolean PionKiller(Point p) {
        boolean flag = true;
        try {
            if ((PionEnemy != null) || (PionEnemy2 != null)) {
                //prend pion noir
                if (Tour.equals("B")) {
                    if ((caseKillP != null) && (caseKillP.getLocation().equals(p))) {
                        JButton but2 = (JButton) chessBoard.getComponentAt(PionEnemy);
                        but2.setIcon(null);
                        PionNoir--;
                        flag = false;
                    } else if ((caseKillP2 != null) && (caseKillP2.getLocation().equals(p))) {
                        JButton but2 = (JButton) chessBoard.getComponentAt(PionEnemy2);
                        but2.setIcon(null);
                        PionNoir--;
                        flag = false;
                    }
                } else {
                    //prend pion blanc
                    if ((caseKillP != null) && (caseKillP.getLocation().equals(p))) {
                        JButton but2 = (JButton) chessBoard.getComponentAt(PionEnemy);
                        but2.setIcon(null);
                        PionBlanc--;
                        flag = false;
                    } else if ((caseKillP2 != null) && (caseKillP2.getLocation().equals(p))) {
                        JButton but2 = (JButton) chessBoard.getComponentAt(PionEnemy2);
                        but2.setIcon(null);
                        PionBlanc--;
                        flag = false;
                    }
                }
            }
            if (PionEnemy_BD != null) {
                //prend pion noir
                if (Tour.equals("B")) {
                    if (caseKillD_BD.getLocation().equals(p)) {
                        JButton but2 = (JButton) chessBoard.getComponentAt(PionEnemy_BD);
                        but2.setIcon(null);
                        PionNoir--;
                        flag = false;
                    }
                    //prend pion blanc    
                } else {
                    if (caseKillD_BD.getLocation().equals(p)) {
                        JButton but2 = (JButton) chessBoard.getComponentAt(PionEnemy_BD);
                        but2.setIcon(null);
                        PionBlanc--;
                        flag = false;
                    }
                }
            }
            if (PionEnemy_BG != null) {
                //prend pion noir
                if (Tour.equals("B")) {
                    if (caseKillD_BG.getLocation().equals(p)) {
                        JButton but2 = (JButton) chessBoard.getComponentAt(PionEnemy_BG);
                        but2.setIcon(null);
                        PionNoir--;
                        flag = false;
                    }
                    //prend pion blanc    
                } else {
                    if (caseKillD_BG.getLocation().equals(p)) {
                        JButton but2 = (JButton) chessBoard.getComponentAt(PionEnemy_BG);
                        but2.setIcon(null);
                        PionBlanc--;
                        flag = false;
                    }
                }

            }
            if (PionEnemy_HD != null) {
                //prend pion noir
                if (Tour.equals("B")) {
                    if (caseKillD_HD.getLocation().equals(p)) {
                        JButton but2 = (JButton) chessBoard.getComponentAt(PionEnemy_HD);
                        but2.setIcon(null);
                        PionNoir--;
                        flag = false;
                    }
                    //prend pion blanc    
                } else {
                    if (caseKillD_HD.getLocation().equals(p)) {
                        JButton but2 = (JButton) chessBoard.getComponentAt(PionEnemy_HD);
                        but2.setIcon(null);
                        PionBlanc--;
                        flag = false;
                    }
                }
            }
            if (PionEnemy_HG != null) {
                //prend pion noir
                if (Tour.equals("B")) {
                    if (caseKillD_HG.getLocation().equals(p)) {
                        JButton but2 = (JButton) chessBoard.getComponentAt(PionEnemy_HG);
                        but2.setIcon(null);
                        PionNoir--;
                        flag = false;
                    }
                    //prend pion blanc    
                } else {
                    if (caseKillD_HG.getLocation().equals(p)) {
                        JButton but2 = (JButton) chessBoard.getComponentAt(PionEnemy_HG);
                        but2.setIcon(null);
                        PionBlanc--;
                        flag = false;
                    }
                }
            }
        } catch (NullPointerException v) {
        }
        resetEnemy();
        return flag;
    }

    public void resetEnemy() {
        //reset des enemy 
        PionEnemy = null;
        PionEnemy2 = null;
        PionEnemy_BD = null;
        PionEnemy_BG = null;
        PionEnemy_HD = null;
        PionEnemy_HG = null;
        caseKillP = null;
        caseKillP2 = null;
        caseKillD_BD = null;
        caseKillD_BG = null;
        caseKillD_HD = null;
        caseKillD_HG = null;
    }

    //reset le background
    private void clearsBackground() {
        int y = YChessesMin;
        for (int li = 0; li < 10; li++) {
            int x = XChessesMin;
            for (int col = 0; col < 10; col++) {
                Point p = new Point(x, y);
                JButton button = (JButton) chessBoard.getComponentAt(p);
                //background
                if (li % 2 == 0) {
                    if (col % 2 == 0) {
                        button.setBackground(Color.BLACK);
                    } else {
                        button.setBackground(Color.WHITE);
                    }
                } else {
                    if (col % 2 == 0) {
                        button.setBackground(Color.white);
                    } else {
                        button.setBackground(Color.black);
                    }
                }
                x = x + HSizeBut;
            }
            y = y + HSizeBut;
        }
    }

    //change la taille de l'image
    private ImageIcon IniImg(String ID) {
        ImageIcon icon = new ImageIcon("./img/Dames/" + ID + ".png");
        Image img = icon.getImage();
        Image imgScal = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon ScalIcon = new ImageIcon(imgScal);
        if (ID.equals("PionNoir")) {
            ScalIcon.setDescription("PionN");
        } else if (ID.equals("PionBla")) {
            ScalIcon.setDescription("PionB");
        } else if (ID.equals("DamesBla")) {
            ScalIcon.setDescription("DameB");
        } else if (ID.equals("DamesNoir")) {
            ScalIcon.setDescription("DameN");
        }

        return ScalIcon;
    }

    private void initComposant() {
        f.add(gui);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        f.setResizable(false);
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setSize(742, 765);
        f.setVisible(true);        
    }
}
