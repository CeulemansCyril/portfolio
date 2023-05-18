/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendu.librairy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Utilisateur
 */
public class FichierTxT {
    
    //cree le fichier log
    public void createFill(){
        File fill = new File("./Ressource/Log.txt");        
        try {
            fill.createNewFile();
        } catch (IOException ex) {}
    }
    //ajoute une mots dans le fichier log
    public void addWordInFill(String mots){  
        try {
             FileWriter write=write = new FileWriter("./Ressource/Log.txt", true);
             BufferedWriter BufWri = new BufferedWriter(write);
             
             BufWri.newLine();
            //ecrit le mot dans le fichier
             BufWri.write(mots);
             //liber la mémoire
             BufWri.close();
             write.close();
        } catch (IOException ex) {
            //cree le fichier si il n'existe pas
            createFill();
            addWordInFill(mots);
        }
        
    }
    //suprime le fichier
    public void deletFill(){
        File fill = new File("./Ressource/Log.txt");
        fill.delete();
    }
    //recuper toute les mot contenue dans le fichier log
    public ArrayList getAllWord(){
        ArrayList list = new ArrayList();
        try {
             File doc = new File("./Ressource/Log.txt");
            Scanner scan = new Scanner(doc);
            
            //lire le fichier et ajoute le mot dans l'arraylist
            while(scan.hasNextLine()){
                list.add(scan.nextLine());
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {}
        
        return list;
    }
    //vérifie si le fichier existe
    public boolean CheckFileExist(){
        File fill = new File("./Ressource/Log.txt");
        return fill.exists();
    }
    //verifie la date du fichier log
    public boolean CheckDateFile(){
        try {
            File fill = new File("./Ressource/Log.txt");
            //recuper les atribut du fichier
            BasicFileAttributes attrs = Files.readAttributes(fill.toPath(), BasicFileAttributes.class);
            FileTime fileCreation = attrs.creationTime();
             //converti la dite de creation du fichier en dadte
            String date = fileCreation.toString();
            String[] split = date.split("T");
            Date timeCreation = Date.valueOf(split[0]);
            Date timeNow = Date.valueOf(LocalDate.now());

            //si la date du jour est supérieur à celle de la creation on return true
            if(timeNow.after(timeCreation)){
                return true;
            }
            
            return false;
        } catch (IOException ex) {
          return false;
        }
    }
     //recuper toute les mot contenue dans un fichier Txt
    public ArrayList getAllWordInFile(Path pa){
        ArrayList list = new ArrayList();
        try {
            File doc = new File(pa.toString());
            Scanner scan = new Scanner(doc);
            
            //lire le fichier et ajoute le mot dans l'arraylist
            while(scan.hasNextLine()){
                list.add(scan.nextLine());
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {}
        
        return list;
    }
}
