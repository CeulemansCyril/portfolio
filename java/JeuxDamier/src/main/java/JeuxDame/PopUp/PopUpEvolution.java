/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.PopUp;

import JeuxDame.Controlleur.DataControlleur;
import JeuxDame.Listener.ListenerEvolution;
import JeuxDame.Object.Piece;
import JeuxDame.Object.Tuile;
import JeuxDame.Panel.PanelButon;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author ceule
 */
public class PopUpEvolution extends JFrame implements ActionListener{
    private ListenerEvolution Ecoute;
    private Tuile tuile ;
    private int w;
    private int h;
    private String GameName;

    public PopUpEvolution(ListenerEvolution Ecoute, Tuile tuile,int w,int h,String GameName) {
        this.Ecoute = Ecoute;
        this.tuile = tuile;
        this.w=w;
        this.h=h;
        this.GameName=GameName;
        initComponent();
        this.setVisible(true);
    }
    
    private void initComponent(){
        Piece p = (Piece) tuile.getPieceTuile();
        
        ArrayList<String> listEvol = p.getNamePieceEvolution();
        
        int SizeEvol = listEvol.size();
        
        int RowLayout = SizeEvol%3;
        this.setLayout(new GridLayout(RowLayout, 3));
        
        for (int i = 0; i < SizeEvol; i++) {
            PanelButon but = new PanelButon(this, listEvol.get(i), listEvol.get(i), 20, 50);
            this.add(but);
        }
        
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Choisissez l'evolution de la piece");
        this.setSize(300, 100*(SizeEvol/2));
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String NameP = e.getActionCommand();
        
        Piece p = DataControlleur.getInstance().ReadPiece(NameP+"-"+GameName+".json");
        
        tuile.setPieceTuile(p, w, h);
        
        Ecoute.Evol(tuile);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.dispose();
    }
    
}
