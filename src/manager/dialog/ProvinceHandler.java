package manager.dialog;

import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import javax.swing.DefaultListModel;
import manager.persistence.pulled.ProvinceLocal;
import manager.window.MainManager;

/**
 *
 * @author David Hrusa
 */
public class ProvinceHandler implements TableHandler {

    @Override
    public String getDisplayName() {
        return "Provinces";
    }

    @Override
    public String getTableName() {
        return "province";
    }

    @Override
    public void reloadSelect(BaseDialog bd) {
        //DefaultListModel dlm = new DefaultListModel();
        //querry into database
        Query queryN = MainManager.getEM().createNativeQuery("SELECT province_id, province_name, x, y FROM province");
        List<Object[]> listN = queryN.getResultList();
        System.out.println("Pulling provinces:");
        Object[][] data = new Object[listN.size()][listN.get(0).length];
        int index = 0;
        for (Iterator<Object[]> itN = listN.iterator(); itN.hasNext();) {
            Object[] obj = itN.next();
            //String line = "["+obj[0]+"] "+obj[1];
            //dlm.addElement(line);
            data[index++] = obj;
        }
        //set the model
        bd.setTableData(data,getColumnNames());
        //bd.setListModel(dlm);
    }

    @Override
    public void newWizard(BaseDialog bd) {
        NewWizard.provinceWizard(bd);
        //bd.getListModel().addElement("bananaNEW");
        //bd.remodel();
    }

    @Override
    public String[] getColumnNames() {
        return new String[]{"id","name","pos_x","pos_y"};
    }

    @Override
    public void removeById(Object key) {
        ProvinceLocal prov = MainManager.getEM().find(ProvinceLocal.class, (long) key);
        MainManager.getEM().getTransaction().begin();
        MainManager.getEM().remove(prov);
        MainManager.getEM().getTransaction().commit();
        /*Query query = MainManager.getEM().createNativeQuery("DELETE FROM province WHERE province_id = ?1");
        query.setParameter(1, key).executeUpdate();*/
    }

    @Override
    public void newWizard(BaseDialog bd, Object[] stval) {
        newWizard(bd);
    }
    
}
