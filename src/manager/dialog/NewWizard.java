package manager.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class NewWizard extends JFrame{
    
    public NewWizard(String name) { 
        this.setSize(400, 200);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	this.setLocation(200, 200);
        this.setTitle(name);
    }
    
    public static void provinceWizard(BaseDialog bd) {
        NewWizard nw = new NewWizard("Create New Province");
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
    }
}
