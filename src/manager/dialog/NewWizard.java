package manager.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

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
        nw.setVisible(true);
        nw.add(name);
        nw.setLayout(new BoxLayout(nw.getContentPane(),BoxLayout.Y_AXIS));
        //
        
        //OK
        JButton ok = new JButton("OK");
        ok.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) {
                    String created = "["+name.getText()+"]";
                    bd.getListModel().addElement(created);
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
