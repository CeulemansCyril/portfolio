/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Librairy;

import JeuxDame.Controlleur.DataControlleur;
import JeuxDame.Object.ObjGameConfig.GameConfig;
import JeuxDame.Object.ObjGameConfig.GameRule;
import JeuxDame.Object.ObjGameConfig.KillOne;
import JeuxDame.Object.ObjPiece.DeplacementComplexe;
import JeuxDame.Object.ObjPiece.DeplacementSimple;
import JeuxDame.Object.ObjPiece.IDeplacement;
import JeuxDame.Object.ObjPiece.Placement;
import JeuxDame.Object.Piece;
import JeuxDame.PopUp.PopUpErreur;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author ceule
 */
public class WriteJson {

    public WriteJson() {
    }

    public boolean SavePiece(Piece p, String GameName) {

        DataControlleur.getInstance().CheckDirectory("./src/Template");

        JSONObject objJs = new JSONObject();
        objJs.put("Name", p.getName());
        objJs.put("Team", p.getTeam());

        //set Deplacement
        int SizeDeplacement = p.getDeplacementPiece().size();
        ArrayList<IDeplacement> listDeplacement = p.getDeplacementPiece();
        JSONArray ArrayDeplacement = new JSONArray();
        for (int i = 0; i < SizeDeplacement; i++) {
            String id = listDeplacement.get(i).getIDDeplacement();
            JSONObject objTemp = new JSONObject();
            if (id.equals("DC")) {
                DeplacementComplexe depl = (DeplacementComplexe) listDeplacement.get(i);
                objTemp.put("X", depl.getDeplacementX());
                objTemp.put("Y", depl.getDeplacementY());
                objTemp.put("isColor", depl.isColorAll());
                objTemp.put("id", depl.getIDDeplacement());
            } else {
                DeplacementSimple depl = (DeplacementSimple) listDeplacement.get(i);
                objTemp.put("X", depl.getDeplacementX());
                objTemp.put("Y", depl.getDeplacementY());
                objTemp.put("NBDeplace", depl.getNbDeplacement());
                objTemp.put("id", depl.getIDDeplacement());
            }
            ArrayDeplacement.add(objTemp);
        }

        objJs.put("Deplacement", ArrayDeplacement);
        objJs.put("isSaut", p.isSautPiece());

        //set Evol
        if ((p.getNamePieceEvolution() != null) && (!p.getNamePieceEvolution().isEmpty())) {
            JSONArray array = new JSONArray();
            ArrayList list = p.getNamePieceEvolution();
            int size = list.size();
            JSONObject objEvolu = new JSONObject();
            for (int i = 0; i < size; i++) {
                objEvolu.put(i, list.get(i));
            }
            JSONObject objEvol = new JSONObject();
            objEvol.put("Name Evol", objEvolu);
            objEvol.put("Point Evol", p.getPointEvolution());
            objJs.put("Evolution", objEvol);
        }
        //set placement
        if ((p.getAllPlacement() != null) && (!p.getAllPlacement().isEmpty())) {
            JSONArray JSarrayPlacement = new JSONArray();
            ArrayList<Placement> arrayPlacement = p.getAllPlacement();
            int SizePlacement = arrayPlacement.size();
            for (int i = 0; i < SizePlacement; i++) {
                JSONObject objPlacement = new JSONObject();
                Placement pl = arrayPlacement.get(i);
                objPlacement.put("X", pl.getPlacementX());
                objPlacement.put("Y", pl.getPlacementY());
                JSarrayPlacement.add(objPlacement);
            }
            objJs.put("Placement", JSarrayPlacement);
        }

        //creat a temp file image
        Image img = p.getImg().getImage();
        BufferedImage bufImg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = bufImg.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(img, 0, 0, null);
        g.dispose();

        File tempImg = new File("./src/temp.png");
        try {
            ImageIO.write(bufImg, "png", tempImg);
        } catch (IOException ex) {
        }

        //save image
        File file = new File("./src/temp.png");
        byte[] bte = null;
        try {
            bte = FileUtils.readFileToByteArray(file);
        } catch (IOException ex) {
        }

        String base = Base64.getEncoder().encodeToString(bte);
        objJs.put("Img", base);
        objJs.put("ImgDes", p.getImg().getDescription());
        try {
            Files.write(Path.of("./src/Template/" + p.getName() + "-" + GameName + ".json"), objJs.toJSONString().getBytes());
        } catch (IOException ex) {
            PopUpErreur err = new PopUpErreur("Erreur Sauvegarde echouer");
            return false;
        }

        //delete temp image
        tempImg.delete();
        return true;
    }

    public boolean SaveGame(GameConfig config) {
        DataControlleur.getInstance().CheckDirectory("./src/Game");

        JSONObject objJs = new JSONObject();
        
        objJs.put("Row", config.getRow());
        objJs.put("Col", config.getCol());
        
        objJs.put("Name", config.getGameName());
        objJs.put("Team1", config.getTeam1());
        objJs.put("Team2", config.getTeam2());
        
        objJs.put("ColorOne", config.getOne().getRGB());
        objJs.put("ColorTwo", config.getTwo().getRGB());
        objJs.put("ColorDepl", config.getDepl().getRGB());

        //save ListPieceName
        JSONArray ListPieceName = new JSONArray();
        ListPieceName.addAll(config.getListPieceName());
        objJs.put("ListPieceName", ListPieceName);

        //save rule
        GameRule rule = config.getRule();

        //rule kill one
        KillOne kill = rule.getKillone();
        JSONObject objKill = new JSONObject();
        objKill.put("isSelect", kill.isSelect());
        if (kill.isSelect()) {
            objKill.put("PieceTeam1", kill.getPieceNameTeam1());
            objKill.put("PieceTeam2", kill.getPieceNameTeam2());
        }

        JSONObject objRule = new JSONObject();
        objRule.put("KillAll", rule.isKillAll());
        objRule.put("PriseDirect", rule.isPriseDirect());
        objRule.put("KillOne", objKill);
        objRule.put("Continue", rule.isContinue());

        objJs.put("Rule", objRule);

        try {
            Files.write(Path.of("./src/Game/" + config.getGameName() + ".json"), objJs.toJSONString().getBytes());
        } catch (IOException ex) {
            PopUpErreur err = new PopUpErreur("Erreur Sauvegarde echouer");
            return false;
        }

        return true;
    }

}
