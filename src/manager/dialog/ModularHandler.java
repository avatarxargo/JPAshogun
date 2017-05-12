package manager.dialog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import manager.dialog.DBVariable.DBVarType;
import manager.persistence.Province;
import manager.persistence.pulled.ProvinceLocal;
import manager.window.MainManager;

/**
 *
 * @author David Hrusa
 */
public class ModularHandler implements TableHandler {

    private ArrayList<DBVariable> tableVar;
    private String tableName, displayTableName;
    private Object cls;
    private boolean firstid;
    private Class classa;
    
    public ModularHandler(String displayName, String dbName, boolean firstid, Class classa) {
        tableName = dbName;
        displayTableName = displayName;
        tableVar = new ArrayList<DBVariable>();
        this.firstid = firstid;
        this.classa = classa;
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
    
    /**Returns the call for the querry*/
    private String getQueryDeleteCall() {
        String out = "DELETE FROM "+getTableName()+" WHERE ";
        boolean comma = false;
        int index = 1;
        for(DBVariable v: tableVar) {
            if(comma) {
                out+=" AND "+v.idname+"= ?"+(index++);
            } else {
                comma = true;
                out+=v.idname+"= ?"+(index++);
            }
        }
        out+=";";
        return out;
    }
    
    /**Generates a dropdown combo box, that holds a menu of available options for this table with [id] at the start*/
    public JComboBox getComboBox() {
        Query query = MainManager.getEM().createNativeQuery(getQuerryCall());
        List<Object[]> listN = query.getResultList();
        String[] lines = new String[listN.size()];
        String line;
        for(int i = 0; i<listN.size(); ++i) {
            line = "";
            Object[] data = listN.get(i);
            for (int j=0; j< data.length; ++j) {
                if(j==0) {
                    line+="["+data[j]+"] ";
                } else {
                    line+=data[j]+" ";                
                }
            }
            lines[i] = line;
        }
        JComboBox cmb = new JComboBox(lines);
        return cmb;
    }

    @Override
    public void reloadSelect(BaseDialog bd) {
        //querry into database
        Query queryN = MainManager.getEM().createNativeQuery(getQuerryCall());
        List<Object[]> listN = queryN.getResultList();
        System.out.println("Pulling items:");
        if(listN.size()==0) {
            bd.setTableData(null,getColumnNames());
            return;
        }
        if(listN.get(0) instanceof Object[]) {
            Object[][] data = new Object[listN.size()][listN.get(0).length];
            int index = 0;
            for (Iterator<Object[]> itN = listN.iterator(); itN.hasNext();) {
                Object[] obj = itN.next();
                data[index++] = obj;
            }
            //set the model
            bd.setTableData(data,getColumnNames());
        } else {
            Object[] pull = listN.toArray();
            Object[][] data = new Object[listN.size()][1];
            int index = 0;
            for (Iterator<Object[]> itN = listN.iterator(); itN.hasNext();) {
                Object obj = itN.next();
                data[index++][0] = obj;
            }
            //set the model
            bd.setTableData(data,getColumnNames());
        }
        
    }

    @Override
    public void newWizard(BaseDialog bd) {
        NewWizardModular.generateWizard(bd, "new "+displayTableName, tableVar, tableName, firstid);
    }
    
    @Override
    public void newWizard(BaseDialog bd, Object[] stval) {
        NewWizardModular.generateWizard(bd, stval, "new "+displayTableName, tableVar, tableName, firstid);
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
    public void removeById(Object key, Object[] row) {
        if(classa!=null) {
            Object obj = MainManager.getEM().find(classa, (int) key);
            MainManager.getEM().getTransaction().begin();
            MainManager.getEM().remove(obj);
            MainManager.getEM().getTransaction().commit();
        } else {
            Query query = MainManager.getEM().createNativeQuery(getQueryDeleteCall());
            for(int i=0;i<row.length;++i) {
                System.out.println("Setting var "+(i+1)+" to "+row[i]);
                query.setParameter(i+1, row[i]);
            }
            System.out.println(getQueryDeleteCall());
            MainManager.getEM().getTransaction().begin();
            query.executeUpdate();
            MainManager.getEM().getTransaction().commit();
        }
    }
}
