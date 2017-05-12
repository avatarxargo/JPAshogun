package manager.dialog.popups;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import manager.map.GraphEditorTester;

/**
 * Special button that brings up a province map to select from.
 */
public class JProvinceButton extends JButton {

    private boolean chosen;
    private GraphEditorTester map;
    private int id;
    private String name;
    private JProvinceButton me;
    
    public void receiveData(int id, String name) {
        this.id = id;
        this.name = name;
        setText(name);
        chosen = true;
        this.repaint();
    }

    public JProvinceButton() {
        setText(" << Select Province >> ");
        chosen = false;
        me = this;
        //add the action
        addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        System.out.println("Selecting province");
                        //
                        map = new GraphEditorTester();
                        map.passTarget(me);
                    }
                }
        );
    }

    /**
     * Returns the foreign key to the currently selected province.
     */
    public int getFK() {
        return chosen ? id : 0;
    }
}
