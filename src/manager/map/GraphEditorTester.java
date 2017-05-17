package manager.map;

import javax.swing.JButton;
import javax.swing.JFrame;
import manager.dialog.popups.JProvinceButton;

import manager.window.TransactionPopUp;


public class GraphEditorTester extends JFrame {

    GraphEditor graphEditor;

    public GraphEditorTester() {
        setTitle("Map of Japan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(350,0);
        reeditor();
    }
    
    public void passTarget(JProvinceButton target) {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    	graphEditor.passTarget(target);
    }
    
    public void createButtonWizard(JButton button) {
        GraphEditorTester get = new GraphEditorTester();
    }
    
    public void reeditor() {
        getContentPane().removeAll();
        graphEditor = new GraphEditor(900, 800,this);
        getContentPane().add(graphEditor);
        pack();
        setVisible(true);
    }
    
    public GraphEditor getEditor() {
        return graphEditor;
    }
}