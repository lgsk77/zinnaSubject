package com.zinnaworks.daily.controller;

import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.zinnaworks.daily.constant.Const;
import com.zinnaworks.daily.manager.ThreadManager;
import com.zinnaworks.daily.ui.MainUI;
import com.zinnaworks.daily.ui.MainUI.OnButtonListener;

public class DailyMainContoller implements OnButtonListener {
	
	private MainUI mainUI;
	
	private ThreadManager threadManager;
	
	public DailyMainContoller(){
		mainUI = new MainUI(this);
		
	}

	@Override
	public void action(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == mainUI.getButton()){
			System.out.println("add user");
			new DailyController(mainUI.getTarea(),Const.DB_TYPE_NETWORK);
		} else if (e.getSource() == mainUI.getExitButton()) {
			System.out.println("main exit click");
			threadManager = ThreadManager.getInstance();
			threadManager.releaseInstance();
			mainUI.getFrame().dispose();
		}
	}

}
