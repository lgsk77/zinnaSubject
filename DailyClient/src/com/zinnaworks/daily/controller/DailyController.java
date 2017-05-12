package com.zinnaworks.daily.controller;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import com.zinnaworks.daily.constant.Const;
import com.zinnaworks.daily.manager.ThreadManager;
import com.zinnaworks.daily.service.PoolDailyService;
import com.zinnaworks.daily.ui.UserUI;
import com.zinnaworks.daily.ui.UserUI.OnButtonListener;
import com.zinnaworks.daily.vo.DailyVO;

public class DailyController implements OnButtonListener {

	private UserUI userUI;

	private ThreadManager threadManager;
	
	String type;
	
	JTextArea tarea;
	
	public DailyController(JTextArea tarea, String type) {

		userUI = new UserUI(this);
		this.type= type;
		this.tarea = tarea;
		
		threadManager = ThreadManager.getInstance();
	}

	@Override
	public void action(ActionEvent e) {
		if (e.getActionCommand() == "insert") {
			System.out.println("insert click");
			threadManager.clickButton(new PoolDailyService(Const.DB_COMMAND_INSERT,type,tarea,new DailyVO(userUI.getNameText().getText(),
					userUI.getEmailText().getText(), userUI.getDetailText().getText(), userUI.getAgeText().getText())));
		} else if (e.getActionCommand() == "delete") {
			threadManager.clickButton(new PoolDailyService(Const.DB_COMMAND_DELETE,type,tarea,new DailyVO(userUI.getNameText().getText(),
					userUI.getEmailText().getText(), userUI.getDetailText().getText(), userUI.getAgeText().getText())));
			System.out.println("delete click");
		} else if (e.getActionCommand() == "update") {
			threadManager.clickButton(new PoolDailyService(Const.DB_COMMAND_UPDATE,type,tarea,new DailyVO(userUI.getNameText().getText(),
					userUI.getEmailText().getText(), userUI.getDetailText().getText(), userUI.getAgeText().getText())));
			System.out.println("update click");
		} else if (e.getActionCommand() == "search") {
			threadManager.clickButton(new PoolDailyService(Const.DB_COMMAND_SEARCH,type,tarea,new DailyVO(userUI.getNameText().getText(),
					userUI.getEmailText().getText(), userUI.getDetailText().getText(), userUI.getAgeText().getText())));
			System.out.println("search click");
		} else if (e.getActionCommand() == "list") {
			threadManager.clickButton(new PoolDailyService(Const.DB_COMMAND_LIST,type,tarea,new DailyVO(userUI.getNameText().getText(),
					userUI.getEmailText().getText(), userUI.getDetailText().getText(), userUI.getAgeText().getText())));
			System.out.println("list click");
		} else if (e.getActionCommand() == "exit") {
			System.out.println("exit click");
			userUI.getUserFrame().dispose();
		}
	}

}
