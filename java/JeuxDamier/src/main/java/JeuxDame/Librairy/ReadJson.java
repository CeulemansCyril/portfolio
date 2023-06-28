/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Librairy;

import JeuxDame.Object.ObjGameConfig.GameConfig;
import JeuxDame.Object.ObjGameConfig.GameRule;
import JeuxDame.Object.ObjGameConfig.KillOne;
import JeuxDame.Object.ObjPiece.DeplacementComplexe;
import JeuxDame.Object.ObjPiece.DeplacementSimple;
import JeuxDame.Object.ObjPiece.Evolution;
import JeuxDame.Object.ObjPiece.Placement;
import JeuxDame.Object.Piece;
import JeuxDame.Object.ObjTemplate.TemplateGame;
import JeuxDame.Object.ObjTemplate.TemplatePiece;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ceule
 */
public class ReadJson {

    public ReadJson() {
    }

    public Piece ReadPiece(String name) {
        Piece p = new Piece(null, " ", 0, " ", new Evolution(), new ArrayList(), new ArrayList());

        JSONParser parser = new JSONParser();
        Reader read = null;
        JSONObject objJS = new JSONObject();

        try {
            read = new FileReader("./src/Template/" + name);
        } catch (FileNotFoundException ex) {
            return null;
        }

        try {
            objJS = (JSONObject) parser.parse(read);
        } catch (IOException ex) {
            return null;
        } catch (ParseException ex) {
            return null;
        }

        p.setName((String) objJS.get("Name"));
        p.setTeam((String) objJS.get("Team"));
        p.setSautPiece((boolean) objJS.get("isSaut"));

        //get deplacement
        JSONArray JSArrayDeplacement = (JSONArray) objJS.get("Deplacement");
        int SizeDeplacement = JSArrayDeplacement.size();
        for (int i = 0; i < SizeDeplacement; i++) {
            JSONObject objDeplacement = (JSONObject) JSArrayDeplacement.get(i);
            String id = (String) objDeplacement.get("id");
            if (id.equalsIgnoreCase("DC")) {
                DeplacementComplexe depl = new DeplacementComplexe((int) (long) objDeplacement.get("Y"), (int) (long) objDeplacement.get("X"), (String) objDeplacement.get("id"), (boolean) objDeplacement.get("isColor"));
                p.addDeplacementPiece(depl);
            } else {
                DeplacementSimple depl = new DeplacementSimple((int) (long) objDeplacement.get("Y"), (int) (long) objDeplacement.get("X"), (int) (long) objDeplacement.get("NBDeplace"), (String) objDeplacement.get("id"));
                p.addDeplacementPiece(depl);
            }
        }
        //get Evolution
        if (objJS.containsKey("Evolution")) {
            JSONObject objEvol = (JSONObject) objJS.get("Evolution");
            JSONObject ArrayNameEvol =  (JSONObject) objEvol.get("Name Evol");
            int Size = ArrayNameEvol.size();
            ArrayList listEv = new ArrayList();
            for (int i = 0; i < Size; i++) {
                listEv.add(ArrayNameEvol.get(""+i));
            }
            p.setEvolution(new Evolution(listEv, (int) (long) objEvol.get("Point Evol")));

        }
        //get Placement
        if (objJS.containsKey("Placement")) {
            JSONArray arrayPlacement = (JSONArray) objJS.get("Placement");
            int SizePlacement = arrayPlacement.size();
            JSONObject objPlacement = new JSONObject();
            for (int i = 0; i < SizePlacement; i++) {
                objPlacement = (JSONObject) arrayPlacement.get(i);
                p.addOnePlacement(new Placement((int) (long) objPlacement.get("X"), (int) (long) objPlacement.get("Y")));
            }
        }
        //get image
        String by = (String) objJS.get("Img");

        byte[] data = Base64.getDecoder().decode(by);

        ByteArrayInputStream Buf = new ByteArrayInputStream(data);
        try {
            BufferedImage bImage = ImageIO.read(Buf);
            Image img = bImage;
            ImageIcon icon = new ImageIcon(bImage);

            String Desc = (String) objJS.get("ImgDes");
            icon.setDescription(Desc);

            p.setImg(icon);
        } catch (IOException ex) {
        }

        return p;
    }

    public ArrayList<TemplatePiece> listFileTemplate() {
        ArrayList list = new ArrayList();

        File file = new File("./src/Template/");
        String[] fileList = file.list();
        if (fileList != null) {
            int size = fileList.length;

            for (int i = 0; i < size; i++) {
                TemplatePiece plate = new TemplatePiece(fileList[i]);
                list.add(plate);
            }
        }
        return list;
    }

    public GameConfig ReadGame(String name) {
        GameConfig config = new GameConfig();

        JSONParser parser = new JSONParser();
        Reader read = null;
        JSONObject objJS = new JSONObject();

        try {
            read = new FileReader("./src/Game/" + name + ".json");
        } catch (FileNotFoundException ex) {
            return null;
        }

        try {
            objJS = (JSONObject) parser.parse(read);
        } catch (IOException ex) {
            return null;
        } catch (ParseException ex) {
            return null;
        }
         long l =(long) objJS.get("Col");
        config.setCol((int) l);
         l =(long) objJS.get("Row");
        config.setRow((int)l);
        config.setGameName((String) objJS.get("Name"));
        config.setTeam1((String) objJS.get("Team1"));
        config.setTeam2((String) objJS.get("Team2"));
         l =(long) objJS.get("ColorOne");
        config.setOne(new Color( (int)l ));
         l =(long) objJS.get("ColorTwo");
        config.setTwo(new Color( (int)l));
         l =(long) objJS.get("ColorDepl");
        config.setDepl(new Color( (int) l));

        //get list piece name
        JSONArray arrayName = (JSONArray) objJS.get("ListPieceName");
        int SizePlacement = arrayName.size();
       
        ArrayList listNameP = new ArrayList();
        for (int i = 0; i < SizePlacement; i++) {
            String objListNameP = (String) arrayName.get(i);
            listNameP.add(objListNameP);
        }
        config.setListPieceName(listNameP);

        JSONObject objRule = new JSONObject();
        objRule = (JSONObject) objJS.get("Rule");

        //get GameRule
        JSONObject objKillOne = (JSONObject) objRule.get("KillOne");
        KillOne killOne = new KillOne((boolean) objKillOne.get("isSelect"));
        if (killOne.isSelect()) {
            killOne.setPieceNameTeam1((String) objKillOne.get("PieceTeam1"));
            killOne.setPieceNameTeam2((String) objKillOne.get("PieceTeam2"));
        }

        GameRule rule = new GameRule();
        rule.setKillone(killOne);
        rule.setKillAll((boolean) objRule.get("KillAll"));
        rule.setPriseDirect((boolean) objRule.get("PriseDirect"));
        rule.setContinue((boolean) objRule.get("Continue"));
        config.setRule(rule);

        return config;
    }

    public ArrayList<TemplateGame> listFileGame() {
        ArrayList list = new ArrayList();

        File file = new File("./src/Game/");
        String[] fileList = file.list();
        if (fileList != null) {
            int size = fileList.length;

            for (int i = 0; i < size; i++) {
                TemplateGame temp = new TemplateGame(fileList[i]);
                list.add(temp);
            }
        }
        return list;
    }

}
