package first.sample.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import first.sample.dao.SampleDAO;

@Service("sampleService")
public class SampleServiceImpl implements SampleService{
	
	@Resource(name="sampleDAO")
	private SampleDAO sampleDAO;
	
	@Override										//여기서 db에 입력할 parameter값을 입력
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
		//return이 받아 오는 값
		return sampleDAO.selectBoardList(map);
	}
 
}

