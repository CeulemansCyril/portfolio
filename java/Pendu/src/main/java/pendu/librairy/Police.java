/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendu.librairy;

import java.util.ArrayList;

/**
 *
 * @author ceule
 */
public class Police {

    public Police() {
    }
    //recuper toute les tailles de police disponible
    public ArrayList GetTailleDePolice(){
        ArrayList list = new ArrayList();
        for (int i = 10; i <= 20; i++) {
            list.add(i);
        }
        return list;        
    } 
    //recuper toute les polices disponible
    public ArrayList GetPolice(){
        ArrayList list = new ArrayList();
        list.add("Arial");
        list.add("Arial Black");
        list.add("Bookman Old Style");
        list.add("FreeSans");
        list.add("Gill Sans Cyr MT");
        list.add("LucidaSans");
        list.add("Courier");
        list.add("FreeSerif");
        list.add("Algerian");
        list.add("Bauhaus 93");
        list.add("Bradley Hand ITC");
        return list;        
    } 
   //recuper toute les text-alignements disponible
    public ArrayList GetTextAling(){
        ArrayList list = new ArrayList();
        list.add("Center-Align");
        list.add("Right-Align");
        list.add("Left-Align");
        return list;        
    } 
    
}
