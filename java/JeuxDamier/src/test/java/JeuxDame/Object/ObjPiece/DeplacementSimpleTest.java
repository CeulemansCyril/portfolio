/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package JeuxDame.Object.ObjPiece;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Utilisateur
 */
public class DeplacementSimpleTest {
    
    public DeplacementSimpleTest() {
    }

    @Test
    public void testGetNbDeplacement() {
        System.out.println("testGetNbDeplacement");
        DeplacementSimple instance = new DeplacementSimple(5, 3, 4, "id");

        assertEquals(4, instance.getNbDeplacement());      
    }
    
    @Test
    public void testGetDeplacementX() {
        System.out.println("testGetDeplacementX");
        DeplacementSimple instance = new DeplacementSimple(5, 3, 4, "id");

        assertEquals(3, instance.getDeplacementX());
    }
    
    @Test
    public void testGetDeplacementY() {
        System.out.println("testGetDeplacementY");
        DeplacementSimple instance = new DeplacementSimple(5, 3, 4, "id");

        assertEquals(5, instance.getDeplacementY());
    }
    
    @Test
    public void testGetID() {
        System.out.println("testGetID");
        DeplacementSimple instance = new DeplacementSimple(5, 3, 4, "id");
        
        assertEquals("id", instance.getIDDeplacement());
     

    }
}
