/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendu.librairy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pendu.Controleur.DataControleur;
import pendu.Object.Option;

/**
 *
 * @author ceule
 */
public class ReadJson {

    public ReadJson() {
    }
    //recuper dans le fichier txt les mot contenue dans une liste
    public ArrayList ReadMotList(String nomList, String Langue) {

        JSONParser JSonP = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        Reader reader = null;

        try {
            reader = new FileReader(DataControleur.getInstance().getPath());
        } catch (FileNotFoundException ex) {
            //si le fichier n'existe pas on va le crée mais vide de toute liste
            DataControleur.getInstance().CreeFichierJson();
            return null;
        }

        try {
            jsonObject = (JSONObject) JSonP.parse(reader);

        } catch (ParseException ex) {
        } catch (IOException ex) {
        }
        //reccuper les liste enregister dans la langue
        if (!jsonObject.containsKey(Langue)) {
            DataControleur.getInstance().AddLangInJsonFile(Langue);
        }
        JSONObject JsLangue = (JSONObject) jsonObject.get(Langue);

        //si il n y a aucune list dans la langue revoie null
        if (JsLangue == null) {
            return null;
        } else {
            //lit les liste contenue dans la langue
            //recherche la liste voulut
            if (JsLangue.containsKey(nomList)) {
                ArrayList list = new ArrayList();
                JSONArray array = (JSONArray) JsLangue.get(nomList);

                int taille = array.size();
                for (int i = 0; i < taille; i++) {
                    list.add(array.get(i));
                }

                return list;
            }

        }

        return null;
    }

    //recuper tout les nom des liste contenue dans une langue
    public ArrayList getListLangue(String Langue) {
        JSONParser JSonP = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        Reader reader = null;

        try {
            reader = new FileReader(DataControleur.getInstance().getPath());
        } catch (FileNotFoundException ex) {
            //si le fichier n'existe pas on va le crée mais vide de toute liste
            DataControleur.getInstance().CreeFichierJson();
            return null;
        }

        try {
            jsonObject = (JSONObject) JSonP.parse(reader);

        } catch (ParseException ex) {
        } catch (IOException ex) {
        }
        //reccuper les liste enregister dans la langue

        if (!jsonObject.containsKey(Langue)) {
            DataControleur.getInstance().AddLangInJsonFile(Langue);
        }
        JSONObject JsLangue = (JSONObject) jsonObject.get(Langue);
        Object[] li = JsLangue.keySet().toArray();
        int taille = li.length;
        //ajout toute les langue dans une arraylist
        ArrayList list = new ArrayList();
        for (int i = 0; i < taille - 1; i++) {
            list.add(li[i]);
        }

        return list;
    }
   //recuper toute les langue disponible dans le fichier txt
    public JSONObject getLangueinFil() {
        JSONParser JSonP = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        Reader reader = null;

        try {
            reader = new FileReader(DataControleur.getInstance().getPath());
        } catch (FileNotFoundException ex) {
            //si le fichier n'existe pas on va le crée mais vide de toute liste
            DataControleur.getInstance().CreeFichierJson();
        }

        try {
            jsonObject = (JSONObject) JSonP.parse(reader);

        } catch (ParseException ex) {
        } catch (IOException ex) {
        }

        return jsonObject;
    }
    //recuper les option contenue dans le fichier
    public Option getOptionFill(){
       Option op = new Option();
        JSONParser JSonP = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        Reader reader = null;
        //recuper les option dans le fichier
        try {
            reader = new FileReader("./Ressource/Json/Option.json");
            jsonObject = (JSONObject) JSonP.parse(reader);
            op.setLangue((String) jsonObject.get("Langue"));
            op.setPolice((String) jsonObject.get("Police"));
            
            Object taille = jsonObject.get("TaillePolice");            
            op.setTaillePolice(Integer.valueOf(taille.toString()));
            
            op.setSenseDuText((String) jsonObject.get("SenseTxt"));
            
            Object Son = jsonObject.get("Son");
            op.setSound(Boolean.parseBoolean(Son.toString()));
            
            op.setStyleImg((String) jsonObject.get("StyleImg"));
        } catch (FileNotFoundException ex) {
            //si le fichier n'existe pas 
            //on le cree et on set les options par defaut
            Option option=DefaultOption();
           DataControleur.getInstance().WriteFillOption(option);
           return DefaultOption();
        } catch (IOException ex) {
        } catch (ParseException ex) {        
        }
       return op;
    }
       //Les option par defaut
    public Option DefaultOption(){
         Option option = new Option();
        option.setPolice("Arial");
        option.setSenseDuText("Center-Align");
        option.setTaillePolice(10);
        option.setSound(true);
        option.setLangue("Français");
        option.setStyleImg("Classic");
        return option;
    }
}
