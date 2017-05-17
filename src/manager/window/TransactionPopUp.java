package manager.window;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import manager.map.GraphEditorTester;

public class TransactionPopUp extends JFrame{
	
	private JPanel content;
	
	private JComboBox type, province2;

	JButton province;
	private JSpinner unit;
	private JCheckBox c_type, c_province, c_province2, c_unit;
	private JButton submit,cancel;
	
	private GraphEditorTester map;
	
	private TransactionPopUp me;
	
	public TransactionPopUp(boolean[] checks) {
		this.setTitle("Create new Transaction");
		this.setSize(300, 350);
		me = this;

        ImageIcon iconset = new ImageIcon(this.getClass().getResource("/flagadd.png"));
    	this.setIconImage(iconset.getImage());
		
		content = new JPanel();
		content.setLayout(new GridLayout(6,2));
		//--------
		content.add(new JLabel("Remember:"));
		content.add(new JLabel(""));
		//--------
		submit = new JButton("submit");
		submit.addActionListener(
				new ActionListener()
				{
						public void actionPerformed(ActionEvent arg0) {
							ManagerWindow.passChecks(new boolean[]{c_type.isSelected(),c_province.isSelected(),c_province2.isSelected()});
							setVisible(false); //you can't see me!
							dispose(); //Destroy the JFrame object												
						}
				}
		);
		cancel = new JButton("cancel");
		cancel.addActionListener(
				new ActionListener()
				{
						public void actionPerformed(ActionEvent arg0) {
							ManagerWindow.passChecks(new boolean[]{c_type.isSelected(),c_province.isSelected(),c_province2.isSelected()});
							setVisible(false); //you can't see me!
							dispose(); //Destroy the JFrame object												
						}
				}
		);
		//--------
		type = new JComboBox();
		type.addItem("Build");
		type.addItem("Train");
		type.addItem("Move/Attack");
		c_type = new JCheckBox("Activity:");
		c_type.setSelected(checks[0]);
		content.add(c_type);
		content.add(type);
		type.addActionListener(
				new ActionListener()
				{
						public void actionPerformed(ActionEvent arg0) {
							toggle();													
						}
				}
		);
		//--------
		province = new JButton();
		province.setText("<< Select >>");
		province.addActionListener(
				new ActionListener()
				{
						public void actionPerformed(ActionEvent arg0) {
							map = new GraphEditorTester();
						    ImageIcon iconsetmap = new ImageIcon(this.getClass().getResource("/flagmap.png"));
							map.setIconImage(iconsetmap.getImage());
							map.setDefaultCloseOperation(HIDE_ON_CLOSE);	
							//map.passTarget(me);
						}
				}
		);
		c_province = new JCheckBox("Province:");
		c_province.setSelected(checks[1]);
		content.add(c_province);
		content.add(province);
		//--------
		province2 = new JComboBox();
		province2.addItem("[1] Kyushu");
		province2.addItem("[2] Edo");
		province2.addItem("[3] Osaka");
		province2.addItem("[4] Honshu");
		c_province2 = new JCheckBox("Target Province:");
		c_province2.setSelected(checks[2]);
		//--------
		unit = new JSpinner(new SpinnerNumberModel(1,1,9999,1));
		c_unit = new JCheckBox("Number of troops:");
		c_unit.setSelected(checks[2]);
		content.add(c_unit);
		content.add(unit);
		
		this.add(content);
		toggle();
		this.setVisible(true);
	}
	
	/**Receives data from the map.*/
	public void mapData(String arg) {
		province.setText(arg);
	}
	
	private void toggle() {
		String val = ((String)type.getSelectedItem());
		if(val.equals("Move/Attack")) {
			c_unit.setText("Amount of troops:");
			content.remove(c_unit);
			content.remove(unit);
			content.add(c_province2);
			content.add(province2);
			content.add(c_unit);
			content.add(unit);
			//c_province2.setVisible(true);
			//province2.setVisible(true);
			}
		else {
			content.remove(c_province2);
			content.remove(province2);
			//c_province2.setVisible(false);
			//province2.setVisible(false);
			}
		
		if(val.equals("Build")) {
			c_unit.setText("Number of buildings:");
		} else {
			c_unit.setText("Number of troops:");
		}

		content.remove(cancel);
		content.remove(submit);
		content.add(cancel);
		content.add(submit);
		content.validate();
	}
	

	
	public class Module {
		private JCheckBox check;
		private Component component;
		
		public Component getComponent() {
			return component;
		}
		
		public void setText(String arg) {
			check.setText(arg);
		}
		
		public void hide() {
			content.remove(check);
			content.remove(component);
		}
		
		public void add() {
			content.add(check);
			content.add(component);
		}
		
		public Module(String name, Component component) {
			this.check = new JCheckBox(name);
			this.component = component;
		}
	}
}
