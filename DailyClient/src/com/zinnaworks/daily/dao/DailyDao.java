package com.zinnaworks.daily.dao;

import java.util.List;

import com.zinnaworks.daily.vo.DailyVO;

public abstract class DailyDao {
	
	public abstract boolean insertDaily(DailyVO addInfo);

	public abstract boolean deleteDaily(DailyVO deleteInfo);

	public abstract boolean updateDaily(DailyVO updateInfo);

	public abstract List<Object> searchDaily(DailyVO searchInfo);

	public abstract List<Object> listAll();
	
	public abstract void release();

}
