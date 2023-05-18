/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendu.librairy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pendu.Controleur.DataControleur;
import pendu.Object.Option;

/**
 *
 * @author ceule
 */
public class WriteJSon {

    public WriteJSon() {
    }
    //ecrit une liste dans le fichier ListMot
    public void WriteListMot(String Langue, ArrayList list, String nomList) {
        JSONParser JSonP = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        Reader reader = null;

        try {
            reader = new FileReader(DataControleur.getInstance().getPath());
        } catch (FileNotFoundException ex) {
            //si le fichier n'existe pas on va le crée mais vide de toute liste
            DataControleur.getInstance().CreeFichierJson();
            try {
                reader = new FileReader(DataControleur.getInstance().getPath());
            } catch (FileNotFoundException ex1) {
            }
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
        //insert la nouvelle liste

        JsLangue.put(nomList, list);
        jsonObject.put(Langue, JsLangue);

        try {
            //ecrit dans le fichier ListMot
            Files.write(Path.of(DataControleur.getInstance().getPath()), jsonObject.toJSONString().getBytes());
        } catch (IOException ex) {
        }
    }
    //modifie l'enregistrement d'une liste dans le fichier json ListMot
    public void ModifListMot(String Langue, ArrayList list, String nomList) {
        JSONParser JSonP = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        Reader reader = null;

        try {
            reader = new FileReader(DataControleur.getInstance().getPath());
        } catch (FileNotFoundException ex) {
            //si le fichier n'existe pas on va le crée mais vide de toute liste
            DataControleur.getInstance().CreeFichierJson();
            try {
                reader = new FileReader(DataControleur.getInstance().getPath());
            } catch (FileNotFoundException ex1) {
            }
        }
        try {
            //recuper la toute les liste
            jsonObject = (JSONObject) JSonP.parse(reader);

        } catch (ParseException ex) {
        } catch (IOException ex) {
        }
        //recuper les liste enregister dans la langue

        if (!jsonObject.containsKey(Langue)) {
            DataControleur.getInstance().AddLangInJsonFile(Langue);
        }
        JSONObject JsLangue = (JSONObject) jsonObject.get(Langue);
        //modifie la liste
        JsLangue.replace(nomList, list);
        //replace la liste
        jsonObject.put(Langue, JsLangue);
        try {
             //ecrit dans le fichier ListMot 
            Files.write(Path.of(DataControleur.getInstance().getPath()), jsonObject.toJSONString().getBytes());
        } catch (IOException ex) {
        }
    }
    //suprime une liste dans le fichier ListMOt
    public void DeleteListMot(String Langue, String List) {
        JSONParser JSonP = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        Reader reader = null;

        try {
            reader = new FileReader(DataControleur.getInstance().getPath());
        } catch (FileNotFoundException ex) {
            //si le fichier n'existe pas on va le crée mais vide de toute liste
            DataControleur.getInstance().CreeFichierJson();
            try {
                reader = new FileReader(DataControleur.getInstance().getPath());
            } catch (FileNotFoundException ex1) {
            }
        }
        try {
            //recuper la premiere liste
            jsonObject = (JSONObject) JSonP.parse(reader);

        } catch (ParseException ex) {
        } catch (IOException ex) {
        }
        //reccuper les liste enregister dans la langue

        if (!jsonObject.containsKey(Langue)) {
            DataControleur.getInstance().AddLangInJsonFile(Langue);
        }
        JSONObject JsLangue = (JSONObject) jsonObject.get(Langue);
        //suprime la liste
        JsLangue.remove(List);
        //replace la liste
        jsonObject.put(Langue, JsLangue);
        try {
             //ecrit dans le fichier ListMot 
            Files.write(Path.of(DataControleur.getInstance().getPath()), jsonObject.toJSONString().getBytes());
        } catch (IOException ex) {
        }
    }
    //Cree le fichier des Option
    public void WriteFileOption(Option op){
        JSONObject jsonObject = new JSONObject();
       
        //insert les donner dans l'objet Json
        jsonObject.put("Langue",op.getLangue());
        jsonObject.put("Police", op.getPolice());
        jsonObject.put("TaillePolice", op.getTaillePolice());
        jsonObject.put("SenseTxt", op.getSenseDuText());
        jsonObject.put("Son", op.isSound());
        jsonObject.put("StyleImg", op.getStyleImg());
        
        try {
            //Ecrit dans le fichier
            Files.write(Path.of("./Ressource/Json/Option.json"), jsonObject.toJSONString().getBytes());
        } catch (IOException ex) {
        }
    }
    //va modifier les option dans le fichier Json option
    public void ModifFileOption(Option op){
        JSONParser JSonP = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        Reader reader = null;
        boolean modif = false;
        
        try {
            reader = new FileReader("./Ressource/Json/Option.json");
            jsonObject = (JSONObject) JSonP.parse(reader);
            //verifie si le joueur n'a pas modifier les option
            //si oui modifie les option dans le fichier json
            if(jsonObject.get("Langue")!=op.getLangue()){
                jsonObject.replace("Langue", op.getLangue());
                modif =true;
            }
            if(jsonObject.get("Police")!=op.getPolice()){
                jsonObject.replace("Police", op.getPolice());
                modif =true;
            }
            if(Integer.parseInt((String) jsonObject.get("TaillePolice"))!=op.getTaillePolice()){
                jsonObject.replace("TaillePolice", op.getTaillePolice());
                modif =true;
            }
            if(jsonObject.get("SenseTxt")!=op.getSenseDuText()){
                jsonObject.replace("SenseTxt", op.getSenseDuText());
                modif =true;
            }
            if(Boolean.parseBoolean((String) jsonObject.get("Son"))!=op.isSound()){
                jsonObject.replace("Son", op.isSound());
                modif =true;
            }
            if(jsonObject.get("StyleImg")!=op.getStyleImg()){
                jsonObject.replace("StyleImg", op.getStyleImg());
                modif =true;
            }
            //si il y a eu des modification, vas modifier le fichier
            if(modif){
                Files.write(Path.of("./Ressource/Json/Option.json"), jsonObject.toJSONString().getBytes());
            }
            
        } catch (FileNotFoundException ex) {
            WriteFileOption(op);
        } catch (IOException ex) {
        } catch (ParseException ex) {}
    }

 
}
