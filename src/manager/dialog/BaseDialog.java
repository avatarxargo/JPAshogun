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
import manager.window.MainManager;
import manager.window.TransactionPopUp;

/**
 * Generic dialog for acessing a view or a table
 * @author David Hrusa
 */
public class BaseDialog extends JFrame {
    
    private JPanel content, contentR, contentL;
    private JButton b_new, b_edit, b_refresh, b_rem;
    
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
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
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
        //
        b_edit = new JButton("Edit");
        b_edit.addActionListener(
				new ActionListener()
				{
						public void actionPerformed(ActionEvent arg0) {
                                                    //select data from row    
                                                    int index = table.getSelectedRow();
                                                    if(index<0) {return;}
                                                    Object[] rowval = new Object[table.getColumnCount()];
                                                    for(int i=0;i<rowval.length;++i) {
                                                        rowval[i] = table.getValueAt(index, i);
                                                    }
                                                    th.newWizard(me,rowval);
						}
				}
		);
        //
        b_refresh = new JButton("Refresh");
        b_refresh.addActionListener(
				new ActionListener()
				{
						public void actionPerformed(ActionEvent arg0) {
                                                    me.remodel();
                                                    MainManager.reeditor();
						}
				}
		);
        contentR.add(b_refresh);
        //
        //contentR.add(b_edit);
        b_rem = new JButton("Remove");
        b_rem.addActionListener(
				new ActionListener()
				{
						public void actionPerformed(ActionEvent arg0) {
                                                    int index = table.getSelectedRow();
                                                    if(index<0) {return;}
                                                    Object key = table.getValueAt(index, 0);
                                                    Object[] row = new Object[table.getColumnCount()];
                                                    for(int i=0; i<row.length;++i) {
                                                        row[i] = table.getValueAt(index, i);
                                                    }
                                                    System.out.println(key);
                                                    th.removeById(key,row);
                                                    remodel();
                                                    while(index>0) {
                                                    if(index<table.getRowCount()) {
                                                        table.setRowSelectionInterval(index, index);
                                                        break;
                                                    } else {index--;}}
                                                    MainManager.reeditor();
						}
				}
		);
        //
        contentR.add(b_rem);
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
        table = new JTable(new Object[][]{{"NOT CONNECTED TO DATABASE"}}, new Object[]{"0"});
        table.setCellSelectionEnabled(false);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        remodel();
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(400,355));
        contentL.add(scroll);
        //launch
        content.add(contentL,BorderLayout.WEST);
        content.add(contentR,BorderLayout.EAST);
        this.add(content);
        this.setVisible(false);
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
        if(!MainManager.inited) {return;}
        th.reloadSelect(this);
        //items.setModel(listModel); 
    }
}
