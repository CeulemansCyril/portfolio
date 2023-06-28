/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Object.ObjGameConfig;

/**
 *
 * @author ceule
 */
public class GameRule {
    private KillOne Killone;
    private boolean KillAll;
    private boolean PriseDirect;
    private boolean Continue;

    public GameRule(KillOne Killone, boolean KillAll, boolean PriseDirect,boolean Continue) {
        setKillAll(KillAll);
        setKillone(Killone);
        setPriseDirect(PriseDirect);
        setContinue(Continue);
    }

    public GameRule() {
    }
    
    

    public KillOne getKillone() {
        return Killone;
    }

    public void setKillone(KillOne Killone) {
        this.Killone = Killone;
    }
    
    

    public boolean isKillAll() {
        return KillAll;
    }

    public void setKillAll(boolean KillAll) {
        this.KillAll = KillAll;
    }

    public boolean isPriseDirect() {
        return PriseDirect;
    }

    public void setPriseDirect(boolean PriseDirect) {
        this.PriseDirect = PriseDirect;
    }

    public boolean isContinue() {
        return Continue;
    }

    public void setContinue(boolean Continue) {
        this.Continue = Continue;
    }

  
    
    
}
