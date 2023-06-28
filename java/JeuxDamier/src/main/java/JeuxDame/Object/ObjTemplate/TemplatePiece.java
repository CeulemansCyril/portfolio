/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Object.ObjTemplate;

/**
 *
 * @author ceule
 */
public class TemplatePiece extends Template{

    public TemplatePiece(String File) {
        this.setNameFile(File);
        setPath(File);
    }

    @Override
    public final void setNameFile(String NameFile) {
         String[] split = NameFile.split(".json");
        super.setNameFile( split[0]); 
    }
    
}
