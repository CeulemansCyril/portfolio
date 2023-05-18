/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendu.Controleur;

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import org.json.simple.JSONObject;
import pendu.Object.Option;
import pendu.librairy.FichierTxT;
import pendu.librairy.Langue;
import pendu.librairy.Police;
import pendu.librairy.ReadJson;
import pendu.librairy.Sound;
import pendu.librairy.WriteJSon;

/**
 *
 * @author Utilisateur
 */
public class DataControleur {

    private String NomDeList = new String();
    private ArrayList ListMot = new ArrayList();
    private Option option ;

    public DataControleur() {
       
    }

    public static DataControleur getInstance() {
        return DataHolder.INSTANCE;
    }

    private static class DataHolder {

        private static final DataControleur INSTANCE = new DataControleur();
    }

    //return l'alphabet du clavier
    public String getLangClavier(String langue) {
        Langue lg = new Langue();
        return lg.Lang(langue);
    }
    
  
    //cree une image icon avec les dimension demander
    public ImageIcon IniImg(String ID, int Wi, int Hei) {
        ImageIcon icon = new ImageIcon("./Ressource/img/" + ID + ".png");
        Image img = icon.getImage();
        Image imgScal = img.getScaledInstance(Wi, Hei, Image.SCALE_SMOOTH);
        ImageIcon ScalIcon = new ImageIcon(imgScal);
        return ScalIcon;
    }

    //recuper toute les langues disponible
    public String[] getallLang() {
        Langue lg = new Langue();
        return lg.getAllLang();
    }
    //recuper toute les langues disponible en format arraylist
    public ArrayList getallLangAr() {
        Langue lg = new Langue();
        return lg.getAllLangArrayl();
    }

    //cree le fichier Json
    public void CreeFichierJson() {
        //recuper toute les langue disponible
        String[] tabLg = DataControleur.getInstance().getallLang();
        int taille = tabLg.length;
        JSONObject Jobj = new JSONObject();
        JSONObject fil = new JSONObject();
        fil.put("-", "-");
        //insert les langue dans un JSonObject
        for (int i = 0; i < taille; i++) {
            Jobj.put(tabLg[i], fil);
        }
        try {
            //ecrit dans un fichier les langue
            Files.write(Path.of(getPath()), Jobj.toJSONString().getBytes());
        } catch (IOException ex) {
        }

    }
    //ajoute une langue dans un fichier
    public void AddLangInJsonFile(String lang) {
        ReadJson read = new ReadJson();
        JSONObject Jobj = read.getLangueinFil();
        JSONObject fil = new JSONObject();
        fil.put("-", "-");
        //insert une langue dans un JSonObject

        Jobj.put(lang, fil);

        try {
            //ecrit dans un fichier les langue
            Files.write(Path.of(getPath()), Jobj.toJSONString().getBytes());
        } catch (IOException ex) {
        }

    }
    //return l'emplacement du fichier JSon contenant les liste de mot

    public String getPath() {
        return "./Ressource/Json/ListMot.json";
    }

    //recuper toute les noms de liste contenue dans une langue
    public ArrayList getAllListInLangue(String Langue) {
        ReadJson read = new ReadJson();
        return read.getListLangue(Langue);
    }
    //recuper toute les mots contenue dans une liste

    public ArrayList getAllMotInList(String Langue, String nom) {
        ReadJson read = new ReadJson();
        return read.ReadMotList(nom, Langue);
    }

    //enregistre une liste
    public void SaugardeList(String Langue, ArrayList list, String nomList) {
        WriteJSon writ = new WriteJSon();
        writ.WriteListMot(Langue, list, nomList);
    }
    //enregistre une modification apporter à une liste

    public void ModifList(String Langue, ArrayList list, String nomList) {
        WriteJSon writ = new WriteJSon();
        writ.ModifListMot(Langue, list, nomList);
    }
    //suprime une liste

    public void RemoveList(String Langue, String nomList) {
        WriteJSon writ = new WriteJSon();
        writ.DeleteListMot(Langue, nomList);
    }

    //choisit un mot aléatoir
    public String ChooseMot(String nomList, String Langue) {
        if (nomList.equalsIgnoreCase("-")) {
            nomList = this.NomDeList;
        }
        //si le joueur veux rejouer avec une autre liste on va la recuperer
        if (!nomList.equalsIgnoreCase(NomDeList)) {
            ListMot = getAllMotInList(Langue, nomList);
            this.NomDeList = nomList;
        }
        if (ListMot != null) {
            ArrayList ListMotUtiliser = ReadLogFile();
            //on prepare le random
            Random random = new Random();
            String mot = new String();
            //on fait le random
            boolean find = false;
            do {

                find = false;
                int nbRandom = random.nextInt(ListMot.size());
                mot = (String) ListMot.get(nbRandom);

                if (ListMotUtiliser.contains(mot)) {
                    ListMot.remove(mot);
                    if(ListMot == null){
                        return null;
                    }
                } else {
                    find = false;
                    //on suprime le mot utiliser de la liste de mot et on l'ajoute à celuis des mot utiliser
                    AddWordInLogFile(mot);
                    ListMot.remove(mot);
                }
            } while (find);
            return mot;
        } else {
            return null;
        }
    }
    
    //permet de rcupérer toute les Texts alignement disponible
    public ArrayList GetTextAlign(){
        Police po = new Police();
        return po.GetTextAling();
    }
    //permet de rcupérer toute les Police disponible
     public ArrayList GetPolice(){
        Police po = new Police();
        return po.GetPolice();
    }
     //permet de recupérer toute les tailles de police disponible
      public ArrayList GetTailleDePolice(){
        Police po = new Police();
        return po.GetTailleDePolice();
    }
    
    public void setOption(Option op){
        this.option = op;
        ModifFillOption(op);
    }
    public Option getOption(){
        return this.option;
    }
    public String getOptionLang(){
        return this.option.getLangue();
    }
     public String getOptionStyleImg(){
        return this.option.getStyleImg();
    }
    //verif si le mot est de la bonne langue
    public boolean VerifMotToLang(String mots,String lang){
        int taille = mots.length(); 
        String Langue = getLangClavier(lang);
        for (int i = 0; i<taille;i++){
            char c = mots.charAt(i);
             int ascii = c;
            if(Langue.contains(String.valueOf(c))){}
                   
        else if ((ascii >= 33) && (ascii <= 66)) {
      
        } else if ((ascii >= 91) && (ascii <= 96)) {
          
        } else if ((ascii >= 123) && (ascii <= 126)) {
         
        } else if ((ascii >= 128) && (ascii <= 254)) {}
        else{
            return false;
        }    
        }
        return true;
    }
    //permet de lancer le sons
    public void getSound(String txt){
        Sound sound = new Sound();
         sound.SoundPlay(txt);
    }
    //recuper tout les mots contenue dans le fichier log
    public ArrayList ReadLogFile(){
        FichierTxT log = new FichierTxT();
        return log.getAllWord();
    }
    //suprime le fichier log
    public void DeleteLogFile(){
        FichierTxT log = new FichierTxT();
        log.deletFill();
    }
    //cree le fichier log
    public void CreatLogFile(){
        FichierTxT log = new FichierTxT();
        log.createFill();
    }
    
    //ajoute un mots dans le fichier log
    public void AddWordInLogFile(String mots){
        FichierTxT log = new FichierTxT();
        log.addWordInFill(mots);
    }
    //verifie si le fichier existe
    public boolean VerifFill(){
        FichierTxT log = new FichierTxT();
        return log.CheckFileExist();
    }
    //verifie si le fichier existe
    public boolean VerifDateFill(){
        FichierTxT log = new FichierTxT();
        return log.CheckDateFile();
    }
    //permet de cree le fichier option
    public void WriteFillOption(Option op){
        WriteJSon wri = new WriteJSon();
        wri.WriteFileOption(op);
    }
    //permet de modifier le fichier option
    public void ModifFillOption(Option op){
        WriteJSon wri = new WriteJSon();
        wri.WriteFileOption(op);
    }
    //recupert les option dans le fichier
    public void getOptionFill(){
        ReadJson read = new ReadJson();
        this.option= read.getOptionFill();
    }
    public ArrayList ReadFillUser(Path pa){
        FichierTxT Fil = new FichierTxT();
        return Fil.getAllWordInFile(pa);
    }

}
