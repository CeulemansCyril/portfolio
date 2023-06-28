/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Librairy;

import JeuxDame.Object.ObjGameConfig.GameConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author ceule
 */
public class CompressFile {

    private ZipOutputStream ZoS;
  
    public void CompressGame(GameConfig config) throws IOException {
        //recuperation de la date et de l'heure
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmm");
        Date date = new Date();
        
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + File.separator + "Desktop/" + config.getGameName()+ format.format(date) + ".zip");

        byte[] buf = new byte[1024];
        ZoS = new ZipOutputStream(fos);
        ArrayList<String> listPiece = config.getListPieceName();
        int size = listPiece.size();
        for (int i = 0; i < size; i++) {
            //Ajout de l'entrée piece
            File file = new File("./src/Template/" + listPiece.get(i) + ".json");
            FileInputStream fis = new FileInputStream("./src/Template/" + listPiece.get(i) + ".json");
            ZipEntry zipEnter = new ZipEntry("Template/"+file.getName());
            try {
                ZoS.putNextEntry(zipEnter);
            } catch (IOException ex) {
            }
            //Copie du fichier
            int sizeBuf = 0;
            while ((sizeBuf = fis.read(buf, 0, buf.length)) > 0) {
                ZoS.write(buf, 0, buf.length);
            }
            //fermeture des flux
            fis.close();
        }
        //Ajout de l'entrée game
        File file = new File("./src/Game/" + config.getGameName() + ".json");
        FileInputStream fis = new FileInputStream("./src/Game/" + config.getGameName() + ".json");
        ZipEntry zipEnter = new ZipEntry("Game/"+file.getName());
        try {
            ZoS.putNextEntry(zipEnter);
        } catch (IOException ex) {
        }
        //Copie du fichier
        int sizeBuf = 0;
        while ((sizeBuf = fis.read(buf, 0, buf.length)) > 0) {
            ZoS.write(buf, 0, buf.length);
        }
        //fermeture des flux
        fis.close();
        ZoS.close();

    }
    
    
}
