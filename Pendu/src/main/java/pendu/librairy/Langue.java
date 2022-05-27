/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendu.librairy;

import java.util.ArrayList;

/**
 *
 * @author Utilisateur
 */
public class Langue {
    public String Lang(String lang){
        switch (lang) {
            case"Allemand":
                return "abcdefghijklmnopqrstuvwxyzäöüß";
            case"Anglais":
            case "Français":
            case "Néerlandais":
                return "abcdefghijklmnopqrstuvwxyz";
            case"Russe":
                return "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";      
           
        }
        return "";
    }
    public String[] getAllLang(){
        String[] tab = new String[5];
        tab[0]="Français";
        tab[1]="Anglais";
        tab[2]="Allemand";
        tab[3]="Néerlandais";
        tab[4]="Russe";
        return tab;
    }
    //recuper les langue de getallLang et le transforme en Arraylist
    public ArrayList getAllLangArrayl(){
        String[] list = getAllLang();
        int taille = list.length;
        ArrayList listAr =new ArrayList();
        for (int i = 0; i < taille; i++) {
            listAr.add(list[i]);
        }
        return listAr;
    }
    
}
