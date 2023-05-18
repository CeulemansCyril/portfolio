/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendu.panel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ceule
 */
public class panelLabel extends JPanel{
    private JTextField labtext =new JTextField(20);

    public panelLabel(String txt) {
        labtext.setText(txt);
        initcomponent();
    }
    
    private void initcomponent(){
        this.setLayout(new FlowLayout());
        labtext.setEditable(false);
        this.add(labtext);
        this.setSize(100, 100);
        setTextAligne("center");
    }
    public void setText(String txt){
        labtext.setText(txt);
    }

    public String getText(){
        return labtext.getText();
    }
    public void setTaillePolice(int taille,String Police){
        labtext.setFont(new Font(Police, Font.BOLD, taille));
        labtext.setSize(labtext.getWidth(), taille+4);
    }
    public void setTextAligne(String Align){
        double alg = 0;
        switch(Align){
            case "Center-Align":
                    labtext.setHorizontalAlignment(JLabel.CENTER);
                    break;
            case "Right-Align":
                    labtext.setHorizontalAlignment(JLabel.RIGHT);
                    break;
            case "Left-Align":
                    labtext.setHorizontalAlignment(JLabel.LEFT);
                    break;                          
        }
        
    }
    
    public void setEditable(boolean flag){
        labtext.setEditable(flag);
    }
    public void addElement(Component c){
        this.add(c);
    }
}
