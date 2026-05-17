package travel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import travel.agent.TravelAgencyAgent;

public class TravelAgencyGUI extends JFrame {

	private TravelAgencyAgent agent;
	private JTextField destinationField;
	private JTextField renameDestinationField;
	private JTextField propDestinationField;
	private JTextField propertyValueField;

	public TravelAgencyGUI(TravelAgencyAgent agent) {
		this.agent = agent;
		init();
	}

	private void init() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		setSize(600, 300);

		destinationField = new JTextField();
		renameDestinationField = new JTextField();
		propDestinationField = new JTextField();
		propertyValueField = new JTextField();

		JLabel destL = new JLabel("Destination");
		JLabel renameDestL = new JLabel("New Name Dest");
		JLabel propDestL = new JLabel("Destination");
		JLabel propValueL = new JLabel("Value");

		JButton addBtn = new JButton("Add");
		JButton delBtn = new JButton("Remove");
		JButton renameBtn = new JButton("Rename");
		JButton addActBtn = new JButton("Add Activity");
		JButton remActBtn = new JButton("Remove Activity");
		JButton addAccBtn = new JButton("Add Accomedation");
		JButton remAccBtn = new JButton("Remove Accomendation");

		destL.setBounds(20, 30, 150, 30);
		destinationField.setBounds(190, 30, 150, 30);
		addBtn.setBounds(360, 30, 100, 30);
		delBtn.setBounds(480, 30, 100, 30);

		renameDestL.setBounds(20, 80, 150, 30);
		renameDestinationField.setBounds(190, 80, 150, 30);
		renameBtn.setBounds(360, 80, 100, 30);

		propDestL.setBounds(20, 130, 150, 30);
		propDestinationField.setBounds(190, 130, 150, 30);
		addActBtn.setBounds(360, 130, 100, 30);
		remActBtn.setBounds(480, 130, 100, 30);

		propValueL.setBounds(20, 180, 150, 30);
		propertyValueField.setBounds(190, 180, 150, 30);
		addAccBtn.setBounds(360, 180, 100, 30);
		remAccBtn.setBounds(480, 180, 100, 30);

		panel.add(destinationField);
		panel.add(renameDestinationField);
		panel.add(propDestinationField);
		panel.add(propertyValueField);

		panel.add(destL);
		panel.add(renameDestL);
		panel.add(propDestL);
		panel.add(propValueL);

		panel.add(addBtn);
		panel.add(delBtn);
		panel.add(renameBtn);
		panel.add(addActBtn);
		panel.add(remActBtn);
		panel.add(addAccBtn);
		panel.add(remAccBtn);

		addBtn.addActionListener(addDestClicked);
		delBtn.addActionListener(deleteDestClicked);
		renameBtn.addActionListener(renameDestClicked);
		addActBtn.addActionListener(addActivityClicked);
		remActBtn.addActionListener(removeActivityClicked);
		addAccBtn.addActionListener(addAccommodationClicked);
		remAccBtn.addActionListener(removeAccommodationClicked);

		add(panel);
		setVisible(true);

	}

	private ActionListener addDestClicked = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = destinationField.getText().trim();

			if (!name.isEmpty()) {
				agent.addDestination(name);
			}
		}
	};

	private ActionListener deleteDestClicked = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = destinationField.getText().trim();

			if (!name.isEmpty()) {
				agent.deleteDestination(name);
			}
		}
	};
	
	private ActionListener renameDestClicked = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String oldName = destinationField.getText().trim();
			String newName = renameDestinationField.getText().trim();
			
			if(!oldName.isEmpty() && !newName.isEmpty()) {
				agent.renameDestination(oldName, newName);
			}			
		}
	};
	
	
	private ActionListener addActivityClicked = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String destinatioName = propDestinationField.getText().trim();
			String activityName = propertyValueField.getText().trim();
			
			if(!destinatioName.isEmpty() && !activityName.isEmpty()) {
				agent.addActivity(destinatioName, activityName);
			}
		}
	};
	
	private ActionListener removeActivityClicked = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String destinatioName = propDestinationField.getText().trim();
			String activityName = propertyValueField.getText().trim();
			
			if(!destinatioName.isEmpty() && !activityName.isEmpty()) {
				agent.removeActivity(destinatioName, activityName);
			}			
		}
	};
	
	private ActionListener addAccommodationClicked = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String destinatioName = propDestinationField.getText().trim();
			String activityName = propertyValueField.getText().trim();
			
			if(!destinatioName.isEmpty() && !activityName.isEmpty()) {
				agent.addAccomedation(destinatioName, activityName);
			}
		}
	};
	
	private ActionListener removeAccommodationClicked = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String destinatioName = propDestinationField.getText().trim();
			String activityName = propertyValueField.getText().trim();
			
			if(!destinatioName.isEmpty() && !activityName.isEmpty()) {
				agent.removeAccomedation(destinatioName, activityName);
			}			
		}
	};

}
