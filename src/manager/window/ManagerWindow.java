package manager.window;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import manager.map.GraphEditor;
import manager.map.GraphEditorTester;

public class ManagerWindow extends JFrame {
	
	private JButton b_nextDay, b_addCommand, b_removeCommand, b_showMap;
	private JLabel l_currentDay;

	private JPanel content, buttons;
	private JTextArea writeoutArea;
	private JScrollPane writeout;
	
	private TransactionPopUp transaction;
	private GraphEditorTester map;
	
	private static boolean[] checks;
	
	public ManagerWindow() {
		checks = new boolean[]{true,false,false};
		
		b_nextDay = new JButton();
		b_nextDay.setText(">>> Start New Day >>>");
		
		b_addCommand = new JButton();
		b_addCommand.setText("+ Transaction");
		b_addCommand.addActionListener(
				new ActionListener()
				{
						public void actionPerformed(ActionEvent arg0) {
							if(transaction!=null) {
								transaction.setVisible(false); //you can't see me!
								transaction.dispose(); //Destroy the JFrame object
							}
							transaction = new TransactionPopUp(checks);													
						}
				}
		);
		b_removeCommand = new JButton();
		b_removeCommand.setText("Remove Transaction");
		b_removeCommand.addActionListener(
				new ActionListener()
				{
						public void actionPerformed(ActionEvent arg0) {
							//TODO add window to do this												
						}
				}
		);
		b_showMap = new JButton();
		b_showMap.setText("View Map");
		b_showMap.addActionListener(
				new ActionListener()
				{
						public void actionPerformed(ActionEvent arg0) {
							showMap();											
						}
				}
		);
		//------------------
		this.setTitle("Shogun Manager via Database");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(200, 200);
        ImageIcon iconset = new ImageIcon(this.getClass().getResource("/flag.png"));
    	this.setIconImage(iconset.getImage());
		//this.setResizable(false);
		//------------------
		writeoutArea = new JTextArea();
		writeoutArea.setText("Welcome to the Shogun Manager");
		writeout = new JScrollPane(writeoutArea);
		//------------------
		buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));
		buttons.add(b_nextDay);
		buttons.add(b_addCommand);
		buttons.add(b_removeCommand);
		buttons.add(b_showMap);
		
		content = new JPanel();
		content.setVisible(true);
		content.setLayout(new BorderLayout());
		content.add(writeout,BorderLayout.CENTER);
		content.add(buttons,BorderLayout.SOUTH);
		this.add(content);
		buttons.setVisible(true);
		content.setVisible(true);
		this.setVisible(true);
	}
	

	public void showMap() {
		if(map==null) {
			map = new GraphEditorTester();
		    ImageIcon iconsetmap = new ImageIcon(this.getClass().getResource("/flagmap.png"));
			map.setIconImage(iconsetmap.getImage());
			map.setDefaultCloseOperation(HIDE_ON_CLOSE);
		} else {
			if(!map.isVisible()) {
				map.setVisible(true);
			}
			map.requestFocus();
		}
	}
	
	public static void passChecks(boolean[] b) {
		checks = b;
	}
}
