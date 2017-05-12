package com.zinnaworks.kbell.service;

import java.util.List;
import java.util.Map;

import com.zinnaworks.kbell.vo.DailyVO;

public interface DailyService {
	public Map<String,Object> crudService(String crud);
	public Map<String,Object> crudService(String crud, String name, String email,String detail,String age);
	public boolean insertService(DailyVO dailyVO);
	public boolean deleteService(DailyVO dailyVO);
	public boolean updateService(DailyVO dailyVO);
	public List<Map<String, Object>> searchService(DailyVO dailyVO);
	public List<Map<String, Object>> listService();
}
