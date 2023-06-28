/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Menu;

import JeuxDame.Listener.ListenerRenameGame;
import JeuxDame.Listener.ListernerWindowsClose;
import JeuxDame.Panel.PanelButon;
import JeuxDame.Panel.PanelJTextField;
import JeuxDame.PopUp.PopUpErreur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ceule
 */
public class MenuRenameGame extends JFrame implements ActionListener,WindowListener{
    
    private PanelButon butOk = new PanelButon(this, "OK", "ok", 20, 50);
    private ListenerRenameGame ecoute;
    private PanelJTextField txt ;
    private ListernerWindowsClose ecouteClose;

    public MenuRenameGame(ListenerRenameGame ecoute,String name, ListernerWindowsClose ecouteClose) {
        this.ecoute = ecoute;
        this.ecouteClose = ecouteClose;
        txt= new PanelJTextField("Nom du jeux", name, "txtName");
        initComponent();
        this.setVisible(true);
    }
    
    private  void initComponent(){
        JPanel content = (JPanel) this.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        
        txt.setAlignmentX(CENTER_ALIGNMENT);
        butOk.setAlignmentX(CENTER_ALIGNMENT);
        
        content.add(txt);
        content.add(butOk);
        
        this.setTitle("Renomer");
        this.setSize(150, 150);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        String nam = txt.getText();
        if(!nam.equalsIgnoreCase("")){
            ecoute.RenameGame(nam);
            this.dispose();
        }else {
            PopUpErreur err = new PopUpErreur("Erreur le jeux doit avoir un nom");
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ecouteClose.Closing();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
}
