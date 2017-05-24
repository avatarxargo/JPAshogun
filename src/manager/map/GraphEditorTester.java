package manager.map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import manager.dialog.popups.JProvinceButton;
import manager.persistence.Clan;
import manager.window.MainManager;

import manager.window.TransactionPopUp;

public class GraphEditorTester extends JFrame {

    GraphEditor graphEditor;

    private boolean destroyWorld;
    private JPanel sidebar;

    public GraphEditorTester() {
        setTitle("Map of Japan");
        ImageIcon iconset = new ImageIcon(this.getClass().getResource("/flagmap.png"));
        this.setIconImage(iconset.getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocation(350, 0);
        destroyWorld = true;
        GraphEditorTester me = this;
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(me,
                        "Are you sure to close this window?", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    MainManager.getEM().close();
                    System.exit(0);
                }
            }
        });
        reeditor();
    }
    
    public void residebar() {
        
        Query q = MainManager.getEM().createNativeQuery("select day_id_to_num(current_day())");
        Object dnum = q.getResultList().get(0);
        
        q = MainManager.getEM().createNativeQuery(
                "SELECT clan_control_id, SUM(army_units) FROM province GROUP BY clan_control_id");
        List<Object[]> res = q.getResultList();
        sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(res.size()+1,1));
        sidebar.add(new JLabel("  Day: "+dnum+"  "));
        
        int index = 0;
        for(Object[] o: res) {
            Clan cl = MainManager.getEM().find(Clan.class, o[0]);
            JTextArea tmp = new JTextArea(cl.getNameClan()+"\n"+"Army:"+o[1]);
            tmp.setEditable(false);
            sidebar.add(tmp);
            index++;
        }
    }

    public void passTarget(JProvinceButton target) {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        destroyWorld = false;
        graphEditor.passTarget(target);
    }

    public void createButtonWizard(JButton button) {
        GraphEditorTester get = new GraphEditorTester();
    }

    public void reeditor() {
        residebar();
        getContentPane().removeAll();
        this.setLayout(new BorderLayout());
        graphEditor = new GraphEditor(900, 800, this);
        getContentPane().add(graphEditor, BorderLayout.CENTER);
        getContentPane().add(sidebar, BorderLayout.EAST);
        pack();
        setVisible(true);
    }

    public GraphEditor getEditor() {
        return graphEditor;
    }
}
