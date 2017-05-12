package com.zinnaworks.daily.dao;

import java.util.ArrayList;
import java.util.List;

import com.zinnaworks.daily.vo.DailyVO;

public class MemDailyDao extends DailyDao {
	private List<Object> list = new ArrayList<Object>();

	public boolean insertDaily(DailyVO info) {
		return list.add(info);
	}

	public boolean deleteDaily(DailyVO deleteInfo) {
		for (int i = 0; i < list.size(); i++) {
			DailyVO info = (DailyVO) list.get(i);
			if (deleteInfo.getDetails().equals(info.getDetails()) && deleteInfo.getName().equals(info.getName())) {
				list.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean updateDaily(DailyVO updateInfo) {
		for (int i = 0; i < list.size(); i++) {
			DailyVO info = (DailyVO) list.get(i);
			if (updateInfo.getName().equals(info.getName())) {
				list.set(i, updateInfo);
				return true;
			}
		}
		return false;
	}

	public List<Object> searchDaily(DailyVO searchInfo) {
		List<Object> searchList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			DailyVO info = (DailyVO) list.get(i);
			if (searchInfo.getName().equals(info.getName())) {
				System.out.println(i + "�̸� : " + info.getName() + "���� :" + info.getDetails());
				searchList.add(info);
			}
		}
		return searchList;
	}

	public List<Object> listAll() {
		for (int i = 0; i < list.size(); i++) {
			DailyVO info = (DailyVO) list.get(i);
			System.out.println(i + "�̸� : " + info.getName() + "���� :" + info.getDetails());
		}
		return list;
	}

	@Override
	public void release() {
		if(list != null && list.size() > 0){
			list.clear();
			list = null;
		}
	}
}
