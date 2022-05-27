/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendu.Menu;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import pendu.Controleur.DataControleur;
import pendu.Listener.ListenerOption;

/**
 *
 * @author ceule
 */
public class MenuChoixImg extends JFrame implements MouseListener{
    private ListenerOption ecoute;
    private JLabel LabelClassique = new JLabel(DataControleur.getInstance().IniImg("11Classic", 100, 100));
    private JLabel LabelSteve = new JLabel(DataControleur.getInstance().IniImg("11Steve", 100, 100));
    private JLabel LabelAlex = new JLabel(DataControleur.getInstance().IniImg("11Alex", 100, 100));
    public MenuChoixImg(ListenerOption ecoutes) {
        this.ecoute = ecoutes;
        initComponent();
    }
    public void initComponent(){
        this.setLayout(new FlowLayout());
        
        LabelClassique.addMouseListener(this);
        LabelClassique.setName("Classic");     
        
        LabelSteve.addMouseListener(this);
        LabelSteve.setName("Steve");
        
        LabelAlex.addMouseListener(this);
        LabelAlex.setName("Alex");
        
        this.add(LabelClassique);
        this.add(LabelSteve);
        this.add(LabelAlex);
        
        this.setResizable(false);

        this.setTitle("Option Image");

        this.setBounds(100, 100, 400, 150);
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //recuper le nom du jlabel pour le choix du personnage et informe la classe pendu
        JLabel lab = (JLabel) e.getSource();
        String name= lab.getName();
        this.dispose();
        ecoute.SetNewOption("img",name);    
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //change la couleur de la bordure quand la sourit passe dessus
        JLabel lab = (JLabel) e.getSource();
        Border border = BorderFactory.createLineBorder(Color.orange,2);
        lab.setBorder(border);
    }

    @Override
    //enleve la couleur de la bordure quand la sourit quite l'image
    public void mouseExited(MouseEvent e) {
         JLabel lab = (JLabel) e.getSource();
        lab.setBorder(null);
    }
   
}
