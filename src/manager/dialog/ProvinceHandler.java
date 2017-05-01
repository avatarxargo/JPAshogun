package manager.dialog;

import javax.swing.DefaultListModel;

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
        DefaultListModel dlm = new DefaultListModel();
        dlm.addElement("banana");
        dlm.addElement("banana");
        dlm.addElement("banana");
        dlm.addElement("banana");
        dlm.addElement("banana");
        dlm.addElement("banana");
        dlm.addElement("banana");
        dlm.addElement("banana");
        dlm.addElement("banana");
        dlm.addElement("banana");
        dlm.addElement("banana");
        dlm.addElement("banana");
        dlm.addElement("banana");
        dlm.addElement("banana");
    }

    @Override
    public void newWizard(BaseDialog bd) {
        NewWizard.provinceWizard(bd);
        //bd.getListModel().addElement("bananaNEW");
        //bd.remodel();
    }
    
}
