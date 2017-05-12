package com.zinnaworks.daily.dao;

import java.util.HashMap;
import java.util.List;

import com.zinnaworks.daily.constant.Const;
import com.zinnaworks.daily.vo.DailyVO;

public class Single {
	private static Single DBInstance;
	
	private HashMap<String , DailyDao> DBMap;
	
	private String CurrentMode;

	private Single() {
		DBMap = new HashMap<String, DailyDao>();
	}

	public static Single getInstance() {

		if (DBInstance == null) {
			DBInstance = new Single();
			
			DBInstance.DBMap.put(Const.DB_TYPE_MEM, new MemDailyDao());
			DBInstance.DBMap.put(Const.DB_TYPE_ORACLE, new OracleDailyDao());
			DBInstance.DBMap.put(Const.DB_TYPE_NETWORK, new NetworkDailyDao());
		}
		
		return DBInstance;
	}
	
	public static void releaseInstance(){
		
		if(DBInstance != null){
			if(DBInstance.DBMap != null &&DBInstance.DBMap.size() > 0){
				DBInstance.DBMap.get(Const.DB_TYPE_MEM).release();
				DBInstance.DBMap.get(Const.DB_TYPE_NETWORK).release();
				DBInstance.DBMap.get(Const.DB_TYPE_ORACLE).release();
				
				DBInstance.DBMap.clear();
			}
			
			DBInstance.DBMap = null;
			DBInstance = null;
		}
		
		
	}
	
	public void setDBObject(String mode){
		CurrentMode = mode;
	}
	
	public DailyDao getDBObject(String mode){
		return DBMap.get(mode);
	}
	
	public boolean insert(DailyVO insert){
		return DBMap.get(CurrentMode).insertDaily(insert);
	}
	
	public boolean delete(DailyVO delete){
		return DBMap.get(CurrentMode).deleteDaily(delete);
	}
	
	public boolean update(DailyVO update){
		return DBMap.get(CurrentMode).updateDaily(update);
	}
	
	public List<Object> search(DailyVO search){
		return DBMap.get(CurrentMode).searchDaily(search);
	}
	
	public List<Object> listAll(){
		return DBMap.get(CurrentMode).listAll();
	}
	
}
