/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Object.ObjTemplate;

/**
 *
 * @author ceule
 */
public class TemplateGame extends Template{

    public TemplateGame(String File) {
        setNameFile(File);
        setPath(File);
    }

    @Override
    public void setNameFile(String NameFile) {
        String[] split = NameFile.split(".json");
        super.setNameFile(split[0]); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
