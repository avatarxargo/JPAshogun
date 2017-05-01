package manager.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.control.SelectionMode;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import manager.window.TransactionPopUp;

/**
 * Generic dialog for acessing a view or a table
 * @author David Hrusa
 */
public class BaseDialog extends JFrame {
    
    private JPanel content, contentR, contentL;
    private JButton b_new, b_rem;
    
    private DefaultListModel listModel;
    private JList items;
    private JTable table;
    private DefaultTableModel dtm;
    private JScrollPane scroll;
    
    private TableHandler th;
    private BaseDialog me;
    
    public BaseDialog(TableHandler th) {
        //init
        this.th = th;
        this.me = this;
        this.setTitle(th.getDisplayName());
        this.setSize(600, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setLocation(200, 200);
        content = new JPanel();
        contentL = new JPanel();
        contentR = new JPanel();
        contentR.setLayout(new GridLayout(10,1));	
        content.setLayout(new BorderLayout());	
        //
        b_new = new JButton("Add");
        b_new.addActionListener(
				new ActionListener()
				{
						public void actionPerformed(ActionEvent arg0) {
                                                    th.newWizard(me);
						}
				}
		);
        //b_new.setPreferredSize(new Dimension(100,30));
        contentR.add(b_new);
        b_rem = new JButton("Remove");
        b_rem.addActionListener(
				new ActionListener()
				{
						public void actionPerformed(ActionEvent arg0) {
                                                    int index = items.getSelectedIndex();
                                                    System.out.println(index);
                                                    listModel.remove(index);
                                                    remodel();
                                                    items.setSelectedIndex(index);
						}
				}
		);
        //b_rem.setPreferredSize(new Dimension(100,30));
        contentR.add(b_rem);
        //
        /*
        String categories[] = { "Household - querry from memory - a very important thing", "Office", "Extended Family",
        "Company (US)", "Company (World)", "Team", "Will",
        "Birthday Card List", "High School", "Country", "Continent",
        "Planet" , "Household - querry from memory - a very important thing", "Office", "Extended Family",
        "Company (US)", "Company (World)", "Team", "Will",
        "Birthday Card List", "High School", "Country", "Continent",
        "Planet" };
        listModel = new DefaultListModel();
        for(String s: categories) {
            listModel.addElement(s);
        }*/
        items = new JList();
        table = new JTable(new Object[][]{{1,"hora",2,3},{2,"kutna",2,3}}, th.getColumnNames());
        table.setCellSelectionEnabled(false);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        remodel();
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(400,355));
        contentL.add(scroll);
        //launch
        content.add(contentL,BorderLayout.WEST);
        content.add(contentR,BorderLayout.EAST);
        this.add(content);
        this.setVisible(true);
    }
    
    public DefaultListModel getListModel() {
        return listModel;
    }
    
    public void setListModel(DefaultListModel lm) {
        listModel = lm;
    }
    
    public void setTableData(Object[][] data,String[] names) {
        dtm = new DefaultTableModel(data,names);       
        table.setModel(dtm);
    }
    
    public void remodel() {
        th.reloadSelect(this);
        //items.setModel(listModel); 
    }
}
