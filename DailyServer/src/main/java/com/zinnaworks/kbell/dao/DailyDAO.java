package com.zinnaworks.kbell.dao;

import java.util.List;
import java.util.Map;

public interface DailyDAO {
	
	public boolean insertDaily(Map<String,Object> map);
	public boolean deleteDaily(Map<String,Object> map);
	public boolean updateDaily(Map<String,Object> map);
	public List<Map<String, Object>> searchDaily(Map<String,Object> map);
    public List<Map<String, Object>> selectDailyList() ;
    
}
