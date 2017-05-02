package manager.dialog;

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
 * @author David Hrusa
 */
public class NewWizardModular extends JFrame{
    
    private String tableName;
    private ArrayList<DBVariable> vars;
        
    public NewWizardModular(String name, ArrayList<DBVariable> vars, String tableName) { 
        this.setSize(400, 200);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	this.setLocation(200, 200);
        this.setTitle(name);
        this.tableName = tableName;
        this.vars = vars;
    }
    
    private class VarField {
        public DBVariable var;
    }
    
    //http://www.codemiles.com/jpa/insert-using-native-query-t6205.html
    //query.setParameter(1, id);
    //query.setParameter(2, title);
    //query.setParameter(3, creationDate);
    //query.executeUpdate(); - See more at: http://www.codemiles.com/jpa/insert-using-native-query-t6205.html#sthash.YOB4p8Tp.dpuf
    
    /**Returns the call for the querry*/
    private String getQuerryCall() {
        String out = "INSERT INTO "+tableName+" (";
        boolean comma = false;
        for(DBVariable v: vars) {
            if(comma) {
                out+=","+v.idname;
            } else {
                comma = true;
                out+=v.idname;
            }
        }
        out+=") VALUES (";
        comma = false;
        for(DBVariable v: vars) {
            if(comma) {
                out+=",?";
            } else {
                comma = true;
                out+="?";
            }
        }
        out+=")";
        return out;
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
