package com.zinnaworks.daily.service;

import java.util.List;

import com.zinnaworks.daily.dao.Single;
import com.zinnaworks.daily.vo.DailyVO;

//간단히 버튼 이벤트 발생시에 쓰레드를 생성하여 할 수있게 만든 것 안쓴다.
public class DailyService {
	
	private Single single;
	
	public DailyService(String type){
		single = Single.getInstance();
		single.setDBObject(type);
	}
	
	public boolean insertService(String name,String email,String detail,String age){
		DailyVO dailyVO = new DailyVO();
		dailyVO.setName(name);
		dailyVO.setEmail(email);
		dailyVO.setAge(age);
		dailyVO.setDetails(detail);
		
		return single.insert(dailyVO);
	}
	
	public boolean deleteService(String name,String email,String detail,String age){
		DailyVO dailyVO = new DailyVO();
		dailyVO.setName(name);
		dailyVO.setEmail(email);
		dailyVO.setAge(age);
		dailyVO.setDetails(detail);
		
		return single.delete(dailyVO);
	}
	
	public boolean updateService(String name,String email,String detail,String age){
		DailyVO dailyVO = new DailyVO();
		dailyVO.setName(name);
		dailyVO.setEmail(email);
		dailyVO.setAge(age);
		dailyVO.setDetails(detail);
		
		return single.update(dailyVO);
	}
	
	public List<Object> searchService(String name,String email,String detail,String age) {
		DailyVO dailyVO = new DailyVO();
		dailyVO.setName(name);
		dailyVO.setEmail(email);
		dailyVO.setAge(age);
		dailyVO.setDetails(detail);
		
		return single.search(dailyVO);
	}
	
	public List<Object> listAllService() throws Exception{
		return single.listAll();

	}
	
}
