package com.zinnaworks.daily.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserUI implements ActionListener{
	
	static int userNum = 0;

	JFrame userFrame = new JFrame("����" + userNum);

	private JTextField nameText = new JTextField(10);
	private JTextField ageText = new JTextField(10);
	private JTextField emailText = new JTextField(10);
	private JTextField detailText = new JTextField(10);
	
	private JButton insertButton ;
	private JButton deleteButton ;
	private JButton updateButton ;
	private JButton searchButton ;
	private JButton listButton ;
	private JButton exitButton ;
	
	public interface OnButtonListener{
		void action(ActionEvent e);
	}
	
	private OnButtonListener mListener;
	
	public UserUI(OnButtonListener onButtonListener) {
		mListener = onButtonListener;

		userNum++;
		
		JPanel panel = new JPanel();
		
		insertButton = new JButton("insert");
		deleteButton = new JButton("delete");
		updateButton = new JButton("update");
		searchButton = new JButton("search");
		listButton = new JButton("list");
		exitButton = new JButton("exit");
		
		getInsertButton().setActionCommand("insert");
		getInsertButton().addActionListener(this);
		getDeleteButton().setActionCommand("delete");
		getDeleteButton().addActionListener(this);
		getUpdateButton().setActionCommand("update");
		getUpdateButton().addActionListener(this);
		getSearchButton().setActionCommand("search");
		getSearchButton().addActionListener(this);
		getListButton().setActionCommand("list");
		getListButton().addActionListener(this);
		getExitButton().setActionCommand("exit");
		getExitButton().addActionListener(this);
		
		//
		panel.add(new JLabel("name :"));
		panel.add(nameText);
		panel.add(new JLabel("age :"));
		panel.add(ageText);
		panel.add(new JLabel("email :"));
		panel.add(emailText);
		panel.add(new JLabel("detail :"));
		panel.add(detailText);
		panel.add(insertButton);
		panel.add(deleteButton);
		panel.add(updateButton);
		panel.add(searchButton);
		panel.add(listButton);
		panel.add(exitButton);
		userFrame.add(panel);

		//
		userFrame.setSize(200, 300);
		//
		userFrame.setVisible(true);
		userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mListener.action(e);
	}
	
	public JTextField getNameText() {
		return nameText;
	}

	public JTextField getAgeText() {
		return ageText;
	}

	public JTextField getEmailText() {
		return emailText;
	}

	public JTextField getDetailText() {
		return detailText;
	}
	
	public JFrame getUserFrame(){
		return userFrame;
	}
	
	public JButton getInsertButton() {
		return insertButton;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JButton getListButton() {
		return listButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}
}
