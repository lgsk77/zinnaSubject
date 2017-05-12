package com.zinnaworks.kbell.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zinnaworks.kbell.dao.DailyDAO;
import com.zinnaworks.kbell.vo.DailyVO;

@Service("OracleService")
public class OracleDailyService implements DailyService {

	@Resource
	private DailyDAO dailydao;

	@Override
	public Map<String, Object> crudService(String crud) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "OK");
		if (crud.equals("list")) {
			map.put("data", listService());
		} else {
			map.put("result", "false");
			map.put("reason", "not crud");
		}
		return map;

	}

	@Override
	public Map<String, Object> crudService(String crud, String name, String email, String detail, String age) {

		Map<String, Object> map = new HashMap<String, Object>();

		DailyVO dailyVO = new DailyVO();
		dailyVO.setName(name);
		dailyVO.setEmail(email);
		dailyVO.setAge(age);
		dailyVO.setDetails(detail);

		map.put("result", "OK");

		if (crud.equals("update")) {
			if (!updateService(dailyVO)) {
				map.put("result", "false");
				map.put("reason", "update fail");
			}
		} else if (crud.equals("insert")) {
			if (!insertService(dailyVO)) {
				map.put("result", "false");
				map.put("reason", "insert fail");
			}
		} else if (crud.equals("delete")) {
			if (!deleteService(dailyVO)) {
				map.put("result", "false");
				map.put("reason", "delete fail");
			}
		} else if (crud.equals("search")) {
			map.put("data", searchService(dailyVO));
		} else if (crud.equals("list")) {
			map.put("data", listService());
		} else {
			map.put("result", "false");
			map.put("reason", "not crud");
		}
		return map;

	}

	@Override
	public boolean insertService(DailyVO dailyVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", dailyVO.getName());
		map.put("email", dailyVO.getEmail());
		map.put("detail", dailyVO.getDetails());
		map.put("age", dailyVO.getAge());
		return dailydao.insertDaily(map);
	}

	@Override
	public boolean deleteService(DailyVO dailyVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", dailyVO.getName());
		map.put("email", dailyVO.getEmail());
		map.put("detail", dailyVO.getDetails());
		map.put("age", dailyVO.getAge());
		return dailydao.deleteDaily(map);
	}

	@Override
	public boolean updateService(DailyVO dailyVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", dailyVO.getName());
		map.put("email", dailyVO.getEmail());
		map.put("detail", dailyVO.getDetails());
		map.put("age", dailyVO.getAge());
		return dailydao.updateDaily(map);
	}

	@Override
	public List<Map<String, Object>> searchService(DailyVO dailyVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", dailyVO.getName());
		map.put("email", dailyVO.getEmail());
		map.put("detail", dailyVO.getDetails());
		map.put("age", dailyVO.getAge());
		return dailydao.searchDaily(map);
	}

	@Override
	public List<Map<String, Object>> listService() {
		return dailydao.selectDailyList();
	}

}
