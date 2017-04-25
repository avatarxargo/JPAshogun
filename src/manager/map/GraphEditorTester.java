package manager.map;

import javax.swing.JFrame;

import manager.window.TransactionPopUp;


public class GraphEditorTester extends JFrame {

    GraphEditor graphEditor;

    public GraphEditorTester() {
        setTitle("Map of Japan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        graphEditor = new GraphEditor(500, 500);
        getContentPane().add(graphEditor);
        pack();
        setVisible(true);
    }
    
    public void passTarget(TransactionPopUp target) {
    	graphEditor.passTarget(target);
    }
}