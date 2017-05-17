package manager.dialog.popups;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import manager.dialog.DBVariable;
import manager.dialog.DBVariable.DBVarType;
import manager.dialog.NewWizardTransaction;
import manager.persistence.Clan;
import manager.persistence.OwnedBuildings;
import manager.persistence.Province;
import manager.window.MainManager;
import manager.window.TransactionPopUp;

/**
 * lets the user inspect the province in detail and issue transactions
 *
 * @author hrusa
 */
public class ProvinceInspector extends JFrame {

    private JLabel labelName;
    private JLabel labelOwner;
    private JLabel labelArmy;
    private JTable buildings;
    private JScrollPane buildingss;
    private DefaultTableModel buildingsdtm;
    private JScrollPane transactionss;
    private JTable transactions;
    private DefaultTableModel transactionsdtm;
    private Province province;
    private Clan clan;
    private Collection<OwnedBuildings> ob;

    private JPanel textarea;
    private JButton btransaction;

    /**
     * generates the inspector with provided id
     */
    public ProvinceInspector(int id) {
        province = MainManager.getEM().find(Province.class, id);
        //
        this.setSize(400, 400);
        this.setTitle("Inspector: " + province.getNameProvince());
        this.setVisible(true);
        //
        this.setLayout(new GridLayout(3, 1));
        textarea = new JPanel();
        textarea.setLayout(new GridLayout(2, 2));
        labelName = new JLabel(province.getNameProvince());
        if (province.getClanControlId() != null) {
            labelOwner = new JLabel("Controled by: " + province.getClanControlId().getNameClan());
        } else {
            labelOwner = new JLabel("Controled by: nobody");
        }
        labelArmy = new JLabel("Army: " + String.valueOf(province.getArmyUnits()));
        textarea.add(labelName);
        textarea.add(labelOwner);
        textarea.add(labelArmy);
        btransaction = new JButton("+ Transaction");
        textarea.add(btransaction);
        this.add(textarea);
        //
        ob = province.getOwnedBuildingsCollection();
        int index = 0;
        Object[][] bdata = new Object[ob.size()][2];
        for (OwnedBuildings i : ob) {
            bdata[index][0] = i.getBuildingId().getNameBuilding();
            bdata[index][1] = i.getAmountBuildings();
            index++;
        }
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setDataVector(bdata, new Object[]{"building", "count"});
        buildings = new JTable(dtm);
        buildings.setCellSelectionEnabled(false);
        buildings.setColumnSelectionAllowed(false);
        buildings.setRowSelectionAllowed(false);
        buildings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        buildings.getTableHeader().setReorderingAllowed(false);
        buildingss = new JScrollPane(buildings);
        add(buildingss);
        //
        String transQ = "SELECT * from transactions WHERE province1 = ?;";
        Query q = MainManager.getEM().createNativeQuery(transQ);
        q.setParameter(1, province.getIdProvince());
        List<Object[]> listN = q.getResultList();
        
        Object[][] tdata = new Object[listN.size()][6];
        DBVarType[] dbtype = new DBVarType[]{DBVarType.VARCHAR,DBVarType.LONG,DBVarType.PROVINCE_FK,DBVarType.PROVINCE_FK,DBVarType.LONG,DBVarType.BUILDING_FK};
        index = 0;
        for(Object[] row: listN) {
            for(int i=0; i<row.length; ++i) {
                tdata[index][i] = DBVariable.parseValue(row[i], dbtype[i]);
            }
            index++;
        }
        dtm = new DefaultTableModel();
        dtm.setDataVector(tdata, new Object[]{"type","day","prov 1","(prov 2)","count","(building)"});
        transactions = new JTable(dtm);
        transactions.setCellSelectionEnabled(false);
        transactions.setColumnSelectionAllowed(false);
        transactions.setRowSelectionAllowed(false);
        transactions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        transactions.getTableHeader().setReorderingAllowed(false);
        transactionss = new JScrollPane(transactions);
        this.add(transactionss);
        
        ProvinceInspector me = this;

        btransaction.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        NewWizardTransaction wizard = new NewWizardTransaction(province,me);
                    }
                });

    }
    
    public void refresh() {
        refreshBuildings();
        refreshTransactions();
        this.repaint();
    }

    private void refreshBuildings() {
        ob = province.getOwnedBuildingsCollection();
        int index = 0;
        Object[][] bdata = new Object[ob.size()][2];
        for (OwnedBuildings i : ob) {
            bdata[index][0] = i.getBuildingId().getNameBuilding();
            bdata[index][1] = i.getAmountBuildings();
            index++;
        }
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setDataVector(bdata, new Object[]{"building", "count"});
        buildings.setModel(dtm);
    }

    private void refreshTransactions() {
        String transQ = "SELECT * from transactions WHERE province1 = ?;";
        Query q = MainManager.getEM().createNativeQuery(transQ);
        q.setParameter(1, province.getIdProvince());
        List<Object[]> listN = q.getResultList();
        
        Object[][] tdata = new Object[listN.size()][6];
        DBVarType[] dbtype = new DBVarType[]{DBVarType.VARCHAR,DBVarType.LONG,DBVarType.PROVINCE_FK,DBVarType.PROVINCE_FK,DBVarType.LONG,DBVarType.BUILDING_FK};
        int index = 0;
        for(Object[] row: listN) {
            for(int i=0; i<row.length; ++i) {
                tdata[index][i] = DBVariable.parseValue(row[i], dbtype[i]);
            }
            index++;
        }
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setDataVector(tdata, new Object[]{"type","day","prov 1","(prov 2)","count","(building)"});
        transactions.setModel(dtm);
    }
}
