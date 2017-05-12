package com.zinnaworks.kbell.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OracleDailyDAO implements DailyDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public boolean insertDaily(Map<String, Object> map) {
		if((sqlSession.insert("daily.insertdaily", map))>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteDaily(Map<String, Object> map) {
		if(sqlSession.delete("daily.deletedaily",map)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateDaily(Map<String, Object> map) {
		if(sqlSession.update("daily.updatedaily", map)>0){
			return true;
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> searchDaily(Map<String, Object> map) {
		return sqlSession.selectList("daily.searchdaily",map);
	}
	
	@Override
	public List<Map<String, Object>> selectDailyList(){
        return sqlSession.selectList("daily.selectdaily");
    }
}
