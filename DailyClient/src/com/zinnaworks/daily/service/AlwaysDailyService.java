package com.zinnaworks.daily.service;

import java.util.List;

import javax.swing.JTextArea;

import com.zinnaworks.daily.dao.Single;
import com.zinnaworks.daily.vo.DailyVO;


//wait과 notify를 이용해서 thread가 항상 돌지만 이벤트가 발생시에 notify를 해서 하나씩 이용하기 위한 클래스

public class AlwaysDailyService extends Thread{
	
	public static final String KBELL_COMMAND_UNKNOWN = "unknown";
	public static final String KBELL_COMMAND_INSERT = "insert";
	public static final String KBELL_COMMAND_UPDATE = "update";
	public static final String KBELL_COMMAND_DELETE = "delete";
	public static final String KBELL_COMMAND_SEARCH = "search";
	public static final String KBELL_COMMAND_LIST = "list";
	
	private String command ;
	
	private Single single;
	
	private DailyVO vo;
	
	private JTextArea area;
	
	public AlwaysDailyService(String type){
		command = KBELL_COMMAND_UNKNOWN;
		single = Single.getInstance();
		single.setDBObject(type);

	}
	
	public void setArea(JTextArea area){
		this.area = area;
	}
	
	public void setCommand(String command){
		this.command = command;
	}
	
	public synchronized void waitCommand(){
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void notifyCommand(){
		this.notify();
	}
	
	public void setDailyVO(DailyVO vo){
		this.vo = vo;
	}
	
	@Override
	public void run(){
		while(!isInterrupted()){
			System.out.println("::run::called");
			
			waitCommand();
		
			List<Object> mResultList = null;
			if(command.equals(KBELL_COMMAND_INSERT)){
				area.setText("Insert \n");
				if(!single.insert(vo)){
					area.setText("Insert Fail");
				}
			}else if(command.equals(KBELL_COMMAND_UPDATE)){
				area.setText("Update \n");
				if(!single.update(vo)){
					area.setText("Update Fail");
				}
			}else if(command.equals(KBELL_COMMAND_DELETE)){
				area.setText("Delete \n");
				if(!single.delete(vo)){
					area.setText("Delete Fail");
				}
			}else if(command.equals(KBELL_COMMAND_SEARCH)){
				area.setText("Search \n");
				mResultList = single.search(vo);
				if(mResultList!= null){
					for(int i=0;i<mResultList.size();i++){
						DailyVO resultvo = (DailyVO) mResultList.get(i);
						area.append("name : " + resultvo.getName() + " email : " + resultvo.getEmail() + " age : "
								+ resultvo.getAge() + " detail :" + resultvo.getDetails() + '\n');
					}
				} else {
					area.setText("Search List not exist");
				}
				
			}else if(command.equals(KBELL_COMMAND_LIST)){
				area.setText("list \n");
				mResultList = single.listAll();
				if(mResultList!= null){
					for(int i=0;i<mResultList.size();i++){
						DailyVO resultvo = (DailyVO) mResultList.get(i);
						area.append("name : " + resultvo.getName() + " email : " + resultvo.getEmail() + " age : "
								+ resultvo.getAge() + " detail :" + resultvo.getDetails() + '\n');
					}
				} else {
					area.setText("List not exist");
				}
			}
			System.out.println("::run::exit");
		}
	}

}
