package manager.dialog.popups;

import java.util.List;
import javax.persistence.Query;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import manager.dialog.DBVariable;
import manager.window.MainManager;

public class JBuildingCombo extends JComboBox {
    
    public JBuildingCombo() {
        DefaultComboBoxModel model = new DefaultComboBoxModel( new String[]{"provinces"} );
        this.setModel(model);
    }
    
    private Object[] ids;
    
    public void loadProvinces() {
        String transQ = "SELECT  id_building, name_building FROM building;";
        Query q = MainManager.getEM().createNativeQuery(transQ);
        List<Object[]> listN = q.getResultList();
        
        Object[] data = new Object[listN.size()];
        ids = new Object[listN.size()];
        int index = 0;
        for(Object[] row: listN) {
            data[index] = row[1];
            ids[index++] = row[0];
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel( data );
        this.setModel(model);
    }
    
    public Object getId(int index) {
        return ids[index];
    }
}
