package com.zinnaworks.daily.service;

import java.util.List;

import javax.swing.JTextArea;

import com.zinnaworks.daily.constant.Const;
import com.zinnaworks.daily.dao.Single;
import com.zinnaworks.daily.vo.DailyVO;

//스레드풀로 만든거.
public class PoolDailyService extends Thread {


	private String command;

	private Single single;

	private DailyVO vo;

	private JTextArea area;

	public PoolDailyService(String command, String type, JTextArea area, DailyVO vo) {
		this.area = area;
		this.vo = vo;
		this.command = command;
		single = Single.getInstance();
		single.setDBObject(type);
	}

	/*public void setArea(JTextArea area) {
		this.area = area;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public void setDailyVO(DailyVO vo) {
		this.vo = vo;
	}*/

	@Override
	public void run() {
		System.out.println("::run::called::" + this.getName());
		
		 /*try { Thread.sleep(5000); } 
		 catch (InterruptedException e) {
		  e.printStackTrace();
		 }*/
		 
		List<Object> mResultList = null;
		if (command.equals(Const.DB_COMMAND_INSERT)) {
			area.setText("Insert \n");
			if (!single.insert(vo)) {
				area.setText("Insert Fail");
			}
		} else if (command.equals(Const.DB_COMMAND_UPDATE)) {
			area.setText("Update \n");
			if (!single.update(vo)) {
				area.setText("Update Fail");
			}
		} else if (command.equals(Const.DB_COMMAND_DELETE)) {
			area.setText("Delete \n");
			if (!single.delete(vo)) {
				area.setText("Delete Fail");
			}
		} else if (command.equals(Const.DB_COMMAND_SEARCH)) {
			area.setText("Search \n");
			mResultList = single.search(vo);
			if (mResultList != null) {
				for (int i = 0; i < mResultList.size(); i++) {
					DailyVO resultvo = (DailyVO) mResultList.get(i);
					area.append("name : " + resultvo.getName() + " email : " + resultvo.getEmail() + " age : "
							+ resultvo.getAge() + " detail :" + resultvo.getDetails() + '\n');
				}
			} else {
				area.setText("Search List not exist");
			}

		} else if (command.equals(Const.DB_COMMAND_LIST)) {
			area.setText("list \n");
			mResultList = single.listAll();
			if (mResultList != null) {
				for (int i = 0; i < mResultList.size(); i++) {
					DailyVO resultvo = (DailyVO) mResultList.get(i);
					area.append("name : " + resultvo.getName() + " email : " + resultvo.getEmail() + " age : "
							+ resultvo.getAge() + " detail :" + resultvo.getDetails() + '\n');
				}
			} else {
				area.setText("List not exist");
			}
		}
		System.out.println("::run::exit::" + this.getName());
	}

}
