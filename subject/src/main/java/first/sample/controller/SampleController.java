package first.sample.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.weaver.Lint;
import org.codehaus.plexus.component.configurator.converters.composite.MapConverter;
import org.springframework.oxm.GenericMarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.xml.XmlMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;

import first.common.common.CommandMap;
import first.sample.service.SampleService;

@Controller
public class SampleController {
	// Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "sampleService")
	private SampleService sampleService;
	
	@ExceptionHandler(RuntimeException.class)
	public String handlerRuntimeException(){
		return null;
	}
	@RequestMapping(value = "/subject", method = RequestMethod.GET)
	public @ResponseBody Object getInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(request.getParameter("stb_id"));
		String format = StringUtils.defaultIfEmpty(request.getParameter("response_format"), "json");
		String method = StringUtils.defaultIfEmpty(request.getParameter("mode"), "3");
		String stbid = StringUtils.defaultIfEmpty(request.getParameter("stb_id"), "");
		String masking = StringUtils.defaultIfEmpty(request.getParameter("masking"), "y");
		// "{75B8E4E2-6D88-11E6-85BB-77C5AE692835}");333A4179-1C64-447A-AFF3-EB9E5F4A12ED

		String result = "OK";
		String reason = null;

		method = method.toLowerCase();
		format = format.toLowerCase();

		if (method.equals("test1")) {
			return test1(format, result, reason);
		} else if (method.equals("1")) {
			return test2(format, result, reason, stbid, masking);
		} else if (method.equals("2")) {
			return test3(stbid);
		} else {
			method = "Error : Parameter Error";
			return method;
		}
	}

	// 과제 1
	public Object test1(String format, String resultValue, String reasonValue) {

		XmlOutput xmlFormat = new XmlOutput();
		xmlFormat.setReason(reasonValue);
		xmlFormat.setResult(resultValue);

		JsonOutput jsonFormat = new JsonOutput();
		jsonFormat.setResult(resultValue);
		jsonFormat.setReason(reasonValue);
		format = format.toLowerCase();

		if (format.equals("xml")) {
			return xmlFormat;
		} else if (format.equals("json")) {
			return jsonFormat;
		} else {
			jsonFormat.setResult("fail");
			jsonFormat.setReason("bad_response_format");
			return jsonFormat;
		}
	}

	// 과제3
	public Object test2(String format, String resultValue, String reasonValue, String stbID, String masking)
			throws Exception {

		masking("정종");
		Map<String, Object> ID = new HashMap<String, Object>();
		ID.put("stbID", stbID);

		// 데이터 가져옴
		List<Map<String, Object>> dataList = sampleService.selectBoardList(ID);
		// stb id 입력 안받을경우
		if (stbID.equals("")) {
			resultValue = "FAIL";
			reasonValue = "stbID를 받지 못 했습니다.";
		} else if (dataList.size() == 0) {
			resultValue = "FAIL";
			reasonValue = "data가 없습니다.";
		}

		// jsonformat 설정
		JsonOutput jsonFormat = new JsonOutput();
		jsonFormat.setResult(resultValue);
		jsonFormat.setReason(reasonValue);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", resultValue);
		jsonMap.put("reason", reasonValue);

		Map<String, Object> jsonDataMap = new HashMap<String, Object>();
		// jsonDataMap = dataList.get(0);
		// System.out.println((String) jsonDataMap.get("sname"));
		for (int i = 0; i < dataList.size(); i++) {
			jsonDataMap = dataList.get(i);
			String ssname = (String) jsonDataMap.get("sname");
			// System.out.println(ssname);
			if (masking.equals("y")) {
				jsonDataMap.put("sname", masking(ssname));
				dataList.set(i, jsonDataMap);
			} else if (masking.equals("n")) {
				dataList.set(i, jsonDataMap);
				;
			}
			// dataList.set(i, jsonDataMap);
		}
		jsonMap.put("data", dataList);

		// xmlformat 설정
		List<XmlChildData> xmlList = new ArrayList<XmlChildData>();

		XmlData xmlData = new XmlData();
		xmlData.setResult(resultValue);
		xmlData.setReason(reasonValue);

		for (int i = 0; i < dataList.size(); i++) {
			XmlChildData child = new XmlChildData();
			Map<String, Object> dataMap = dataList.get(i);
			String ssname = (String) dataMap.get("sname");
			// System.out.println(ssname);
			if (masking.equals("y")) {
				child.setSname(masking(ssname));
			} else {
				child.setSname((String) dataMap.get("sname"));
			}
			child.setStartDate((String) dataMap.get("sstart_date"));
			xmlList.add(child);
		}
		xmlData.setItems(xmlList);

		if (format.equals("json")) {
			return jsonMap;
		} else if (format.equals("xml")) {
			return xmlData;
		} else {
			jsonFormat.setResult("fail");
			jsonFormat.setReason("bad_response_format");
			return jsonFormat;
		}
	}

  	//과제4
	public Object test3(String stbID) {
		// List<Map<String, Object>> dataList = new
		// ArrayList<Map<String,Object>>();
		Map<String, Object> outPut = new HashMap<String, Object>();
		String fileName = "c:\\test.txt";
		File file = new File(fileName);
		int num = 1;

		stbID = sidReplace(stbID);

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));

			String line = null;
			while ((line = reader.readLine()) != null) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				String[] arr = line.split(" ");
				String[] arr2 = arr[6].split("&");
				String sid = null;
				String IP = arr[0];
				String DATE = sidReplace(arr[3]);
				for (int i = 0; i < arr2.length; i++) {
					if (arr2[i].contains("stb_id")) {
						sid = arr2[i];
						sid = sidReplace(sid.substring(7, sid.length()));
						if (stbID.equals(sid)) {
							System.out.println(sid);
							dataMap.put("IP", IP);
							dataMap.put("DATE", DATE);
							outPut.put("log" + Integer.toString(num), dataMap);
							num++;
							// dataList.add(dataMap);
						}
					}
				}
			}
			reader.close();
			return outPut;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String masking(String name) {
		String reName = null;
		if (!name.equals(null)) {
			reName = name.substring(0, 1) + "*" + name.substring(2, name.length());
		}
		return reName;
	}

	public String sidReplace(String sid) {
		sid = sid.replace("{", "");
		sid = sid.replace("}", "");
		sid = sid.replace("%7B", "");
		sid = sid.replace("%7D", "");
		sid = sid.replace("[", "");
		return sid;
	}
}
