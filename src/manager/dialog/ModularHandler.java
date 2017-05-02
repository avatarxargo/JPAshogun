package manager.dialog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import javax.swing.DefaultListModel;
import manager.dialog.DBVariable.DBVarType;
import manager.persistence.Province;
import manager.window.MainManager;

/**
 *
 * @author David Hrusa
 */
public class ModularHandler implements TableHandler {

    private ArrayList<DBVariable> tableVar;
    private String tableName, displayTableName;
    private Object cls;
    
    public ModularHandler(String displayName, String dbName) {
        tableName = dbName;
        displayTableName = displayName;
        tableVar = new ArrayList<DBVariable>();
    }
    
    /**Inserts one variable into the handler definition*/
    public void addVariable(String displayName, String dbName, DBVarType type) {
        tableVar.add(new DBVariable(displayName, dbName, type));
    }
    
    @Override
    public String getDisplayName() {
        return displayTableName;
    }

    @Override
    public String getTableName() {
        return tableName;
    }
    
    /**Returns the call for the querry*/
    private String getQuerryCall() {
        String out = "SELECT ";
        boolean comma = false;
        for(DBVariable v: tableVar) {
            if(comma) {
                out+=","+v.idname;
            } else {
                comma = true;
                out+=v.idname;
            }
        }
        out+=" FROM "+getTableName();
        return out;
    }

    @Override
    public void reloadSelect(BaseDialog bd) {
        //querry into database
        Query queryN = MainManager.getEM().createNativeQuery(getQuerryCall());
        List<Object[]> listN = queryN.getResultList();
        System.out.println("Pulling items:");
        Object[][] data = new Object[listN.size()][listN.get(0).length];
        int index = 0;
        for (Iterator<Object[]> itN = listN.iterator(); itN.hasNext();) {
            Object[] obj = itN.next();
            data[index++] = obj;
        }
        //set the model
        bd.setTableData(data,getColumnNames());
    }

    @Override
    public void newWizard(BaseDialog bd) {
        NewWizard.provinceWizard(bd);
        //bd.getListModel().addElement("bananaNEW");
        //bd.remodel();
    }

    @Override
    public String[] getColumnNames() {
        String[] out = new String[tableVar.size()];
        for(int i = 0; i < tableVar.size(); ++i) {
            out[i] = tableVar.get(i).dname;
        }
        return out;
    }

    @Override
    public void removeById(Object key) {
        Object obj = MainManager.getEM().find(Province.class, (long) key);
        MainManager.getEM().getTransaction().begin();
        MainManager.getEM().remove(obj);
        MainManager.getEM().getTransaction().commit();
        /*Query query = MainManager.getEM().createNativeQuery("DELETE FROM province WHERE province_id = ?1");
        query.setParameter(1, key).executeUpdate();*/
    }
}
