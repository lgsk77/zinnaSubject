package com.zinnaworks.daily.dao;

import java.util.List;

import org.json.simple.JSONObject;

import com.zinnaworks.daily.network.ConnectServer;
import com.zinnaworks.daily.vo.DailyVO;

public class NetworkDailyDao extends DailyDao {

	private ConnectServer server = new ConnectServer();

	@Override
	public boolean insertDaily(DailyVO addInfo) {
		String url ="http://localhost:8080/kbell/daily";
		List<Object> list = server.sendPost(url,"insert",addInfo);
		JSONObject jsonObject = (JSONObject) list.get(0);
		if (jsonObject.get("result").equals("OK")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteDaily(DailyVO deleteInfo) {
		String url ="http://localhost:8080/kbell/daily";
		List<Object> list = server.sendPost(url,"delete", deleteInfo);
		JSONObject jsonObject = (JSONObject) list.get(0);
		if (jsonObject.get("result").equals("OK")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateDaily(DailyVO updateInfo) {
		String url ="http://localhost:8080/kbell/daily";		
		List<Object> list = server.sendPost(url,"update", updateInfo);
		System.out.println(list);
		JSONObject jsonObject = (JSONObject) list.get(0);
		if (jsonObject.get("result").equals("OK")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Object> searchDaily(DailyVO searchInfo) {
		String url ="http://localhost:8080/kbell/daily";	
		return server.sendPost(url,"search", searchInfo);
	}

	@Override
	public List<Object> listAll() {
		DailyVO dailyvo = new DailyVO();
		String url ="http://localhost:8080/kbell/daily?crud=list&name="+dailyvo.getName()+"&email="+dailyvo.getEmail()+"&detail="+dailyvo.getDetails()+"&age="+dailyvo.getAge();
		return server.sendGet(url);
	}

	@Override
	public void release() {
		server = null ;
	}
}
