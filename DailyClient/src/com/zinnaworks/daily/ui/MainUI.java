package com.zinnaworks.daily.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainUI implements ActionListener{
	
	public interface OnButtonListener{
		void action(ActionEvent e);
	}
	
	private JFrame frame = new JFrame("main"); ;
	
	private JTextArea tarea = new JTextArea(20,20);
	
	private JButton button;
	private JButton exitButton;
	
	private OnButtonListener mListener;
	
	public MainUI(OnButtonListener onButtonListener){
		
		JPanel panel = new JPanel();

		button = new JButton("add user"); 
		exitButton = new JButton("exit");
		
		button.addActionListener(this);
		exitButton.addActionListener(this);
		
		tarea.setLineWrap(true);
		tarea.setWrapStyleWord(true);
		tarea.setEditable(false);
		
		panel.add(new JLabel("Main"));
		panel.add(button);
		panel.add(exitButton);
		
		frame.add(panel,BorderLayout.NORTH);
		frame.add(tarea,BorderLayout.SOUTH);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mListener = onButtonListener;
		
	}
	
	public JFrame getFrame(){
		return frame;
		
	}
	public JTextArea getTarea(){
		return tarea;
	}
	public void setTarea(String text){
		tarea.setText(text);
	}
	
	public JButton getButton(){
		return button;
	}
	
	public JButton getExitButton(){
		return exitButton;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		mListener.action(e);
	}
	
}
