/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuxDame.PanelMenu.CreatGame.PanelDeplacementComplexe;

import JeuxDame.Object.ObjPiece.DeplacementComplexe;
import JeuxDame.Object.ObjPiece.IPiece;
import JeuxDame.Object.PanelMenuPiece;
import JeuxDame.Panel.PanelComboBox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import JeuxDame.Listener.ListenerDeplacementComplexe;
import JeuxDame.Object.ObjPiece.IDeplacement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComboBox;

/**
 *
 * @author ceule
 */
public class PanelDeplacementComplexe extends PanelMenuPiece implements ItemListener, ActionListener, MouseListener {

    private PanelComboBox comboRow;
    private PanelComboBox comboCol;
    private PanelComboBox comboPlacement;
    private JList<DeplacementComplexe> ListDeplacement = new JList<>();
    private DefaultListModel<DeplacementComplexe> model = new DefaultListModel();
    private JButton butAdd = new JButton("Ajouter");
    private JCheckBox CheckPaint = new JCheckBox("Deplacement non-plein");
    private int SizeCol;
    private int SizeRow;
    private ListenerDeplacementComplexe ecoute;
    private boolean FirstEnter = true;

    /**
     * Constructs a PanelDeplacementComplexe object with the specified size of
     * rows and columns, and the provided listener for complex movements.
     *
     * @param SizeRow The size of rows.
     * @param SizeCol The size of columns.
     * @param PEcoute The listener for complex movements.
     */
    public PanelDeplacementComplexe(int SizeRow, int SizeCol, ListenerDeplacementComplexe PEcoute) {
        this.SizeRow = SizeRow;
        this.SizeCol = SizeCol;
        this.ecoute = PEcoute;
        initComponent();
    }

    /**
     * Initializes the components of the panel.
     */
    private void initComponent() {
        ListModelDeplacementComplexe mod = new ListModelDeplacementComplexe();
        ListDeplacement.setCellRenderer(mod);
        ListDeplacement.setModel(model);

        butAdd.setActionCommand("Ajout");
        butAdd.addActionListener(this);

        //fills comboPlacement
        int Size;
        String[] listC = {"Centre", "Haut-Gauche", "Haut-Droit", "Bas-Gauche", "Bas-Droit"};
        Size = listC.length;
        ArrayList listCom = new ArrayList();
        for (int i = 0; i < Size; i++) {
            listCom.add(listC[i]);
        }
        comboPlacement = new PanelComboBox("Placement de la piece", listCom, "Centre");
        comboPlacement.setNameCombo("Placement");
        comboPlacement.addItemChangeListener(this);
        //fills comboCol
        Size = -((SizeCol - 2) / 2);
        ArrayList listCol = new ArrayList();
        for (int i = -Size; i + 2 > Size; Size++) {
            listCol.add(Size);
        }
        comboCol = new PanelComboBox("Colonne", listCol, "0");
        comboCol.setNameCombo("Col");
        comboCol.addItemChangeListener(this);
        //fills comboRow
        Size = -((SizeRow - 2) / 2);
        ArrayList listRow = new ArrayList();
        for (int i = -Size; i + 2 > Size; Size++) {
            listRow.add(Size);
        }
        comboRow = new PanelComboBox("Ligne", listRow, "0");
        comboRow.setNameCombo("Row");
        comboRow.addItemChangeListener(this);

        comboCol.DefaultValue("0");
        comboRow.DefaultValue("0");
        //init CheckBox
        CheckPaint.setActionCommand("Paint");
        CheckPaint.addActionListener(this);
        CheckPaint.setAlignmentX(CENTER_ALIGNMENT);

        JPanel panelColRow = new JPanel(new GridLayout(1, 3));
        panelColRow.add(comboRow);
        panelColRow.add(comboCol);
        panelColRow.add(butAdd);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        comboPlacement.setAlignmentX(CENTER_ALIGNMENT);
        ListDeplacement.setAlignmentX(CENTER_ALIGNMENT);
        panelColRow.setAlignmentX(CENTER_ALIGNMENT);

        this.add(comboPlacement);
        this.add(panelColRow);
        this.add(ListDeplacement);
        this.add(CheckPaint);
        this.addMouseListener(this);

    }

    @Override
    public boolean isEmpty() {

        if (!model.isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public IPiece getPieceData(IPiece p) {
        if (!isEmpty()) {
            ArrayList<DeplacementComplexe> list = new ArrayList();
            int listSize = model.getSize();
            for (int i = 0; i < listSize; i++) {
                list.add((DeplacementComplexe) model.getElementAt(i));
            }
            p.addMutiDeplacementPiece(list);
        }

        return p;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox combo = (JComboBox) e.getSource();
        String Name = combo.getName();

        switch (Name) {
            case "Placement":
                ecoute.PlacementPieceComplexe((String) comboPlacement.getSelectItem(), true);
                break;
            case "Col":
            case "Row":
                ecoute.ColorDeplacement(Integer.parseInt((String) comboRow.getSelectItem()), Integer.parseInt((String) comboCol.getSelectItem()), CheckPaint.isSelected(), (String) comboPlacement.getSelectItem());
                
                break;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String Action = e.getActionCommand();

        switch (Action) {
            case "Ajout":
                DeplacementComplexe deplace = new DeplacementComplexe(Integer.parseInt((String) comboRow.getSelectItem()), Integer.parseInt((String) comboCol.getSelectItem()), "DC", !CheckPaint.isSelected());
                model.addElement(deplace);
                break;
            case "Paint":
                ecoute.ColorDeplacement(Integer.parseInt((String) comboRow.getSelectItem()), Integer.parseInt((String) comboCol.getSelectItem()), CheckPaint.isSelected(), (String) comboPlacement.getSelectItem());
                break;

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (FirstEnter) {
            ecoute.PlacementPieceComplexe((String) comboPlacement.getSelectItem(), true);
            FirstEnter = false;
        } else {
            ecoute.PlacementPieceComplexe((String) comboPlacement.getSelectItem(), false);
        }

        ecoute.ColorDeplacement(Integer.parseInt((String) comboRow.getSelectItem()), Integer.parseInt((String) comboCol.getSelectItem()), CheckPaint.isSelected(), (String) comboPlacement.getSelectItem());

    }

    @Override
    public void mouseExited(MouseEvent e) {
        FirstEnter = true;
    }

    @Override
    public void UpdatePieceData(IPiece p) {
    }

    @Override
    public void LoadPieceData(IPiece p) {
        ArrayList<IDeplacement> listDeplacement = p.getDeplacementPiece();
        int SizeDeplacement = listDeplacement.size();

        for (int i = 0; i < SizeDeplacement; i++) {
            if (listDeplacement.get(i).getIDDeplacement().equalsIgnoreCase("DC")) {
                DeplacementComplexe depl = (DeplacementComplexe) listDeplacement.get(i);
                model.addElement(depl);
            }
        }
    }

    @Override
    public void Clear() {
        model = new DefaultListModel();
        ListDeplacement.setModel(model);
    }

}
