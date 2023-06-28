/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.PanelMenu.CreatGame;

import JeuxDame.Listener.ListenerNewPiece;
import JeuxDame.Object.ObjPiece.IPiece;
import JeuxDame.Object.PanelMenuPiece;
import JeuxDame.Object.Piece;
import JeuxDame.Panel.PanelButon;
import JeuxDame.Panel.PanelComboBox;
import JeuxDame.Panel.PanelLabel;
import JeuxDame.PopUp.PopUpErreur;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ceule
 */
public class PanelCreationPiece extends PanelMenuPiece implements ActionListener, ListenerNewPiece {

    private PanelLabel NamePiece = new PanelLabel("Nom de la piece");
    private JTextField FieldNamePiece = new JTextField(10);
    private PanelComboBox comboTeam;
    private ArrayList ListPieceName = new ArrayList();
    private PanelButon butChoseFile = new PanelButon(this, "Selectionner une image (.png)", "File", 40, 50);
    private JLabel img = new JLabel();
    private JLabel PathFile = new JLabel();
    private JFileChooser imgFile = new JFileChooser();
    private String FilePAth = new String();

    /**
     * Constructs a PanelCreationPiece object with the provided list of teams
     * and list of piece names.
     *
     * @param ListTeam The list of teams to populate the team selection combo
     * box.
     * @param ListPieceName The list of piece names.
     */
    public PanelCreationPiece(ArrayList ListTeam, ArrayList ListPieceName) {
        comboTeam = new PanelComboBox("Couleur de la piece (Equipe)", ListTeam, ""+ListTeam.get(0));
        this.ListPieceName = ListPieceName;
        InitComponent();
    }

    /**
     * Initializes the components of the panel.
     */

    private void InitComponent() {
        initChoseFile();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        NamePiece.AddElement(FieldNamePiece);

        img.setAlignmentX(CENTER_ALIGNMENT);
        NamePiece.setAlignmentX(CENTER_ALIGNMENT);
        comboTeam.setAlignmentX(CENTER_ALIGNMENT);
        PathFile.setAlignmentX(CENTER_ALIGNMENT);
        butChoseFile.setAlignmentX(CENTER_ALIGNMENT);

        NamePiece.setName("Name");
        butChoseFile.setName("File");
        comboTeam.setName("Team");

        this.add(NamePiece);
        this.add(comboTeam);
        this.add(PathFile);
        this.add(butChoseFile);
        this.add(img);
    }

    /**
     * Initializes the file chooser to filter PNG files.
     */
    private void initChoseFile() {
        //filtre les fichiers
        imgFile.setAcceptAllFileFilterUsed(false);
        imgFile.setFileFilter(new FileNameExtensionFilter("PNG file", "png"));
    }

    /**
     * Retrieves the path of the selected file.
     *
     * @return The path of the selected file.
     */
    public String getPath() {
        return FilePAth;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        switch (action) {
            case "File":
                int verif = imgFile.showOpenDialog(this);

                if (verif == JFileChooser.APPROVE_OPTION) {
                    //recupere le path absolue de l'image
                    FilePAth = imgFile.getSelectedFile().getAbsolutePath();
                    //recupere l'image
                    File fileImg = imgFile.getSelectedFile();
                    Image imgage = null;
                    try {
                        imgage = ImageIO.read(fileImg);
                    } catch (IOException ex) {
                    }
                    //si l'image n'a pas pu être charger return une erreur
                    if (imgage != null) {
                        PathFile.setText(imgFile.getSelectedFile().getName());
                        imgage = imgage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        //set l'image    
                        Icon imga = new ImageIcon(imgage);

                        img.setIcon(imga);
                    } else {
                        PopUpErreur err = new PopUpErreur("Erreur imposible de charger l'image");
                    }
                }

                break;
        }
    }

    @Override
    public IPiece getPieceData(IPiece p) {
        p.setName(FieldNamePiece.getText());

        Icon TempIcon = img.getIcon();

        ImageIcon icon = (ImageIcon) TempIcon;
        Path pa = Path.of(PathFile.getText());
        icon.setDescription(String.valueOf(pa.getFileName()));

        p.setImg(icon);
        p.setTeam((String) comboTeam.getSelectItem());
        return p;
    }

    @Override
    public boolean isEmpty() {
        if (!(NamePiece.getText().isBlank()) && !(comboTeam.getSelectItem().equals("...")) && !(PathFile.getText().isBlank())) {
            return false;
        } else if ((ListPieceName != null) && (ListPieceName.contains(NamePiece.getText()))) {
            PopUpErreur err = new PopUpErreur("Le nom de cette piece est déjà utiliser");
        }

        PopUpErreur err = new PopUpErreur("Veulliez remplir tout les champs");
        return true;
    }

    @Override
    public void UpdatePieceData(IPiece p) {
    }

    @Override
    public void LoadPieceData(IPiece p) {
        FieldNamePiece.setText(p.getName());
        comboTeam.DefaultValue(p.getTeam());
        PathFile.setText(p.getImg().getDescription());
        img.setIcon(p.getImg());
    }

    @Override
    public void Clear() {
        FieldNamePiece.setText(null);
        comboTeam.DefaultValue("...");
        PathFile.setText(null);
        img.setIcon(null);
    }

    @Override
    public void NewPiece(Piece name) {
        ListPieceName.add(name.getName());
    }

}
