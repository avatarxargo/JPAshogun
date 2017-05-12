package manager.map;

import javax.swing.JButton;
import javax.swing.JFrame;
import manager.dialog.popups.JProvinceButton;

import manager.window.TransactionPopUp;


public class GraphEditorTester extends JFrame {

    GraphEditor graphEditor;

    public GraphEditorTester() {
        setTitle("Map of Japan");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        graphEditor = new GraphEditor(500, 500);
        getContentPane().add(graphEditor);
        pack();
        setVisible(true);    }
    
    public void passTarget(JProvinceButton target) {
    	graphEditor.passTarget(target);
    }
    
    public void createButtonWizard(JButton button) {
        GraphEditorTester get = new GraphEditorTester();
    }
}