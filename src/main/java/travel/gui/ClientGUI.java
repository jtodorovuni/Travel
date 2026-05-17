package travel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import travel.agent.ClientAgent;

public class ClientGUI extends JFrame{
	
	private JTextField searchField;
	private JButton searchButton;
	private JTextArea resultArea;
	
	private ClientAgent agent;

	
	
	public ClientGUI(ClientAgent agent) {
		this.agent = agent;
		init();
	}


	private void init() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel label = new JLabel("Search here:");
		searchField = new JTextField();
		searchButton = new JButton("Search");
		resultArea = new JTextArea();
		
		panel.add(label);
		panel.add(searchButton);
		panel.add(searchField);
		panel.add(resultArea);
		add(panel);
		
		setSize(500, 700);
		
		label.setBounds(20,20,460,30);
		searchField.setBounds(20, 70, 460, 30);
		searchButton.setBounds(200, 120, 100, 30);
		resultArea.setBounds(20,170,460,500);
		
		searchButton.addActionListener(searchClicked);
		
		setVisible(true);
				
	}
	
	private ActionListener searchClicked = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String search = searchField.getText();
			agent.searchDestination(search);			
		}
	};
	
	public void showResult(String text) {
		resultArea.setText(text);
	}
}
