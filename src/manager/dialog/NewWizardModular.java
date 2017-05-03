package manager.dialog;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.persistence.Query;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import manager.persistence.Province;
import manager.window.MainManager;

/**
 * Provides wizards for various classes form the database.
 *
 * @author David Hrusa
 */
public class NewWizardModular extends JFrame {

    private String tableName;
    private ArrayList<DBVariable> vars;
    private ArrayList<VarField> field;

    public NewWizardModular(String name, ArrayList<DBVariable> vars, String tableName) {
        this.setSize(400, 200);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocation(200, 200);
        this.setTitle(name);
        this.tableName = tableName;
        this.vars = vars;
        field = new ArrayList<VarField>();

        //content
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        for (int i = 1; i < vars.size(); ++i) {
            VarField vf = new VarField();
            vf.var = vars.get(i);
            switch (vf.var.type) {
                case LONG:
                    JSpinner spn = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 10));
                    add(spn);
                    vf.cmp = spn;
                    break;
                case PROVINCE_FK:
                    JButton btn = new JButton("< Select Province >");
                    btn.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent arg0) {
                                    System.out.println("Selecting province");
                                }
                            }
                    );
                    add(btn);
                    vf.cmp = btn;
                    break;
                default:
                    JTextField fld = new JTextField();
                    add(fld);
                    vf.cmp = fld;
            }
            field.add(vf);
        }
        setVisible(true);
    }

    private class VarField {

        public DBVariable var;
        public Component cmp;

        public void setVarVal(Object val) {
            switch (var.type) {
                case LONG:
                    ((JSpinner) cmp).setValue(val);
                default:
                    ((JTextField) cmp).setText((String) val);
            }
        }

        public Object getValue() {
            switch (var.type) {
                case LONG:
                    return ((JSpinner) cmp).getValue();
                default:
                    return ((JTextField) cmp).getText();
            }
        }
    }

    //http://www.codemiles.com/jpa/insert-using-native-query-t6205.html
    //query.setParameter(1, id);
    //query.setParameter(2, title);
    //query.setParameter(3, creationDate);
    //query.executeUpdate(); - See more at: http://www.codemiles.com/jpa/insert-using-native-query-t6205.html#sthash.YOB4p8Tp.dpuf
    /**
     * Returns the call for the querry
     */
    private String getQuerryCall() {
        String out = "INSERT INTO " + tableName + " (";
        boolean comma = false;
        for (int i = 1; i < vars.size(); ++i) {
            if (comma) {
                out += "," + vars.get(i).idname;
            } else {
                comma = true;
                out += vars.get(i).idname;
            }
        }
        out += ") VALUES (";
        comma = false;
        for (int i = 1; i < vars.size(); ++i) {
            if (comma) {
                out += ",?";
            } else {
                comma = true;
                out += "?";
            }
        }
        out += ")";
        return out;
    }

    public static void generateWizard(BaseDialog bdm, String name, ArrayList<DBVariable> vars, String tableName) {
        NewWizardModular nw = new NewWizardModular(name, vars, tableName);
        nw.addOk(bdm);
    }

    public static void generateWizard(BaseDialog bdm, Object[] stval, String name, ArrayList<DBVariable> vars, String tableName) {
        NewWizardModular nw = new NewWizardModular(name, vars, tableName);
        for (int i = 0; i < stval.length; ++i) {
            nw.setVarVal(i, stval[i]);
        }
        nw.addOk(bdm);
    }

    public void setVarVal(int varid, Object val) {
        field.get(varid).setVarVal(val);
    }

    private void addOk(BaseDialog bd) {
        JButton ok = new JButton("OK");
        ok.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        Query query = MainManager.getEM().createNativeQuery(getQuerryCall());
                        System.out.println(getQuerryCall());
                        for (int i = 0; i < field.size(); ++i) {
                            query.setParameter(i + 1, field.get(i).getValue());
                            System.out.println(i + " - " + field.get(i).getValue());
                        }
                        MainManager.getEM().getTransaction().begin();
                        query.executeUpdate();
                        MainManager.getEM().getTransaction().commit();
                    }
                }
        );
        add(ok);
    }

    /*public static void provinceWizard(BaseDialog bd) {
     NewWizardModular nw = new NewWizardModular("Create New Province");
     JTextField name = new JTextField();
     JSpinner xpos = new JSpinner(new SpinnerNumberModel(0,-1000,1000,10));
     JSpinner ypos = new JSpinner(new SpinnerNumberModel(0,-1000,1000,10));
     nw.setVisible(true);
     nw.add(name);
     nw.add(xpos);
     nw.add(ypos);
     nw.setLayout(new BoxLayout(nw.getContentPane(),BoxLayout.Y_AXIS));
     //
     //OK
     JButton ok = new JButton("OK");
     ok.addActionListener(
     new ActionListener()
     {
     public void actionPerformed(ActionEvent arg0) {
     //insert province into database
     MainManager.addProvince((int)xpos.getValue(), (int)ypos.getValue(), name.getText());
     //refresh table
     bd.remodel();
     //done creating clean up.
     nw.removeAll();
     nw.dispose();
     }
     }
     );
     nw.add(ok);
     }*/
}
