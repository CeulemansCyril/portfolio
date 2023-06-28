/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package JeuxDame.Controlleur;

import JeuxDame.Librairy.CompressFile;
import JeuxDame.Librairy.ReadJson;
import JeuxDame.Librairy.WriteJson;
import JeuxDame.Object.ObjGameConfig.GameConfig;
import JeuxDame.Object.ObjTemplate.TemplateGame;
import JeuxDame.Object.ObjTemplate.TemplatePiece;
import JeuxDame.Object.Piece;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ceule
 */
public class DataControlleur {
    
    private DataControlleur() {
    }
    
    public static DataControlleur getInstance() {
        return DataControlleurHolder.INSTANCE;
    }
    
    private static class DataControlleurHolder {
        private static final DataControlleur INSTANCE = new DataControlleur();
    }
    
    public boolean SavePiece(Piece p,String GameName){
        WriteJson write = new WriteJson();
        return write.SavePiece(p,GameName);
    }
    public boolean SaveGame(GameConfig conf){
        WriteJson write = new WriteJson();
        return write.SaveGame(conf);
    }
    
    public Piece ReadPiece(String nameP){
        ReadJson read = new ReadJson();
        return read.ReadPiece(nameP);
    }
    public GameConfig ReadGame(String nameG){
        ReadJson read = new ReadJson();
        return read.ReadGame(nameG);
    }
    
    public void CheckDirectory(String path){
        File file = new File(path);
        if(!file.exists()){
            file = new File(path+"/");
            file.mkdir();
        }
    }
    
    public ArrayList CallListFileTemplate(){
        ReadJson r = new ReadJson();
          return r.listFileTemplate();
    }
    
    public ArrayList CallListFileGame(){
        ReadJson r = new ReadJson();
          return r.listFileGame();
    }
    
    public boolean RemoveTemplatePiece(TemplatePiece piece){
        File file = new File("./src/Template/"+piece.getPath());
        return file.delete();
    }
        public boolean RemoveTemplateGame(TemplateGame Game){
        File file = new File("./src/Game/"+Game.getPath());
        return file.delete();
    }
    
    public boolean CompressGame(String GameName){
        CompressFile cf = new CompressFile();
        try {
            cf.CompressGame(ReadGame(GameName));
            return true;
        } catch (IOException ex) {}
        return false;
    }
}
