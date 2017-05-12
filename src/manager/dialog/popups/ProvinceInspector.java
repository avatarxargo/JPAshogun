package manager.dialog.popups;

import java.awt.GridLayout;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import manager.persistence.Province;
import manager.window.MainManager;

/**
 * lets the user inspect the province in detail and issue transactions
 * @author hrusa
 */
public class ProvinceInspector extends JFrame {
    private JLabel labelName;
    private JLabel labelOwner;
    private JLabel labelArmy;
    private JTable buildings;
    private DefaultTableModel buildingsdtm;
    private JTable transactions;
    private DefaultTableModel transactionsdtm;
    private Province province;
    
    /**generates the inspector with provided id*/
    public ProvinceInspector(int id) {
        province = MainManager.getEM().find(Province.class,id);
        //
        this.setSize(400, 400);
        this.setTitle("Inspector: "+ province.getProvinceName());
        this.setVisible(true);
        //
        this.setLayout(new GridLayout(5,1));
        labelName = new JLabel(province.getProvinceName());
        labelOwner = new JLabel("Controled by: "+String.valueOf(province.getClanIdClan()));
        labelArmy = new JLabel("Army: "+String.valueOf(province.getArmyUnits()));
        this.add(labelName);
        this.add(labelOwner);
        this.add(labelArmy);
        //
        buildings = new JTable(1,2);
        this.add(buildings);
        
        transactions = new JTable(1,4);
        this.add(transactions);
    }
    
    private void refreshBuildings() {
        Query queryN = MainManager.getEM().createNativeQuery("SELECT "
                + "building_name" + "FROM building");
        List<Object[]> listN = queryN.getResultList();
        System.out.println("Pulling items:");
        if(listN.size()==0) {
            return;
        }
        Object[][] data = new Object[listN.size()][listN.get(0).length];
        int index = 0;
        for (Iterator<Object[]> itN = listN.iterator(); itN.hasNext();) {
            Object[] obj = itN.next();
            data[index++] = obj;
        }
        buildingsdtm = new DefaultTableModel(data, new String[]{"Building"});
    }
    
    private void refreshTransactions() {
        
    }
}
