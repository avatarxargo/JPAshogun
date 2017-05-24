package manager.dialog.popups;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import manager.map.GraphEditorTester;
import manager.window.MainManager;

/**
 * Special button that brings up a province map to select from.
 */
public class JNewDayButton extends JButton {

    private JNewDayButton me;
    private static JNewDayButton stme;

    public JNewDayButton() {
        setText("New Day");
        me = this;
        stme = this;
        //add the action
        addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        System.out.println("Starting New Day");
                        newDay();
                    }
                }
        );
    }

    private static void newDay() {
        
        int st_call=0, st_move=0, st_train=0, st_build=0;
        
        //INCOME
        Query q = MainManager.getEM().createNativeQuery("SELECT "
                + "get_owner(province_id),get_building_cost_resource(building_id),amount_buildings "
                + "FROM owned_buildings");
        List<Object[]> res = q.getResultList();
        q = MainManager.getEM().createNativeQuery("select add_resource(?,?,?)");
        MainManager.getEM().getTransaction().begin();
        for(Object[] o: res) {
            q.setParameter(1, o[0]);
            q.setParameter(2, o[1]);
            q.setParameter(3, o[2]);
            Object dump = q.getResultList();
        }
        MainManager.getEM().getTransaction().commit();
        
        //TRAIN
        q = MainManager.getEM().createNativeQuery("SELECT id_transaction_train FROM transaction_train"
                + " WHERE simday_number=current_day()");
        res = q.getResultList();
        q = MainManager.getEM().createNativeQuery("select evaluate_train(?)");
        MainManager.getEM().getTransaction().begin();
        for(Object o: res) {
            st_call++;
            st_train++;
            q.setParameter(1, o);
            Object dump = q.getResultList();
        }
        MainManager.getEM().getTransaction().commit();
        
        //BUILD
        q = MainManager.getEM().createNativeQuery("SELECT id_transaction_build FROM transaction_build"
                + " WHERE simday_number=current_day()");
        res = q.getResultList();
        q = MainManager.getEM().createNativeQuery("select evaluate_build(?)");
        MainManager.getEM().getTransaction().begin();
        for(Object o: res) {
            st_call++;
            st_build++;
            q.setParameter(1, o);
            Object dump = q.getResultList();
        }
        MainManager.getEM().getTransaction().commit();
        
        //MOVE
        q = MainManager.getEM().createNativeQuery("SELECT id_transaction_move FROM transaction_move"
                + " WHERE simday_number=current_day()");
        res = q.getResultList();
        q = MainManager.getEM().createNativeQuery("select evaluate_move(?)");
        MainManager.getEM().getTransaction().begin();
        for(Object o: res) {
            st_call++;
            st_move++;
            q.setParameter(1, o);
            Object dump = q.getResultList();
        }
        MainManager.getEM().getTransaction().commit();
        
        //INCREASE DAY NUMBER
        q = MainManager.getEM().createNativeQuery("INSERT INTO simday (day_number) VALUES (1+day_id_to_num(current_day()))");
        MainManager.getEM().getTransaction().begin();
        q.executeUpdate();
        MainManager.getEM().getTransaction().commit();
        
        q = MainManager.getEM().createNativeQuery("select day_id_to_num(current_day())");
        Object newdaynum = q.getResultList().get(0);
        
        MainManager.reeditor();
        
        //REPORT
        JOptionPane.showMessageDialog(stme,
                    "Successfuly created day number "+newdaynum+"\n"+
                    "Total transactions called: "+st_call+"\n"+
                    "Build transactions: "+st_build+"\n"+
                    "Train transactions: "+st_train+"\n"+
                    "Move transactions: "+st_move+"\n",
                    "Welcome to Day "+newdaynum,
                    JOptionPane.INFORMATION_MESSAGE);
    }
}
