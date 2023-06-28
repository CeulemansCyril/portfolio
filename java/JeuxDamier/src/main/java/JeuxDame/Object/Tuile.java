/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.Object;

import JeuxDame.Object.ObjPiece.IPiece;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Utilisateur
 */
public class Tuile extends JLabel {

    private IPiece objectTuile;
    private Color couleur;
    private int pointY;
    private int pointX;
 
     /**
     * Constructs a Tuile with a black border.
     */
    public Tuile() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void setImage(ImageIcon Path,int w,int h) {
        
        Image imgage = Path.getImage();

        imgage = imgage.getScaledInstance(w/18, h/18, Image.SCALE_SMOOTH);
        //set l'image    
        ImageIcon imga = new ImageIcon(imgage);
        this.setIcon(imga);
    }
    /**
     * Resizes the image of the tile.
     *
     * @param width  the new width
     * @param height the new height
     */
    public void ResizeImage(int w,int h){
        setImage(objectTuile.getImg(),w,h);
    }
    
    private void removeImage(){
        this.setIcon(null);
        this.updateUI();
    }
    /**
     * Returns the piece associated with the tile.
     *
     * @return the piece associated with the tile
     */
    public IPiece getPieceTuile() {
        return objectTuile;
    }
     /**
     * Sets the piece associated with the tile and updates the image.
     *
     * @param piece  the piece to set
     * @param width  the width of the tile
     * @param height the height of the tile
     */
    public void setPieceTuile(IPiece objectTuile,int w,int h) {
        this.objectTuile = objectTuile;
        setImage(objectTuile.getImg(),w,h);

    }
     /**
     * Removes the piece associated with the tile and clears the image.
     */
    public void RemovePieceTuile() {
        this.objectTuile = null;
        removeImage();

    }
    /**
     * Returns the color of the tile.
     *
     * @return the color of the tile
     */
    public Color getCouleur() {
        return this.getBackground();
    }
 /**
     * Sets the color of the tile.
     *
     * @param couleur the color to set
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
        this.setOpaque(true);
        this.setBackground(couleur);
    }
/**
     * Sets the position of the tile.
     *
     * @param x the x-coordinate of the position
     * @param y the y-coordinate of the position
     */
    public void setPLace(int x, int y) {
        this.pointY = y;
        this.pointX = x;
    }
/**
     * Returns the x-coordinate of the tile's position.
     *
     * @return the x-coordinate of the tile's position
     */
    public int getPointX() {
        return this.pointX;
    }
/**
     * Returns the y-coordinate of the tile's position.
     *
     * @return the y-coordinate of the tile's position
     */
    public int getPointY() {
        return this.pointY;
    }
    
    /**
     * Checks if the tile is empty (has no associated piece).
     *
     * @return true if the tile is empty, false otherwise
     */
    public boolean isEmpty(){
        if(objectTuile!=null)return false; 
        return true;
    }

}
