package manager.dialog.popups;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.Query;
import javax.swing.JButton;
import manager.dialog.BaseDialog;
import manager.window.MainManager;

/**
 * Button that reveals
 * @author user
 */
public class BaseDialogButton extends JButton{
    BaseDialog bd;
    
    public BaseDialogButton(BaseDialog bd) {
        this.bd = bd;
        this.setText(bd.getTitle());
        this.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        bd.remodel();
                        bd.setVisible(true);
                    }
                }
        );
        this.setVisible(true);
    }
}
