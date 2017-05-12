package com.zinnaworks.daily.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.zinnaworks.daily.vo.DailyVO;

public class ConnectServer {
	
	public synchronized List<Object> sendGet(String url) {
		List<Object> list = new ArrayList<Object>();

		try{
			URL obj  = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0" );
			
			//response 부분
			int responseCode = con.getResponseCode();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while((inputLine = in.readLine()) !=null){
				if(inputLine!=null){
					response.append(inputLine);
					list = stringToJson(inputLine);
				}
			}
			in.close();
			con.disconnect();
		} catch (Exception e){
	
		}
		return list;
	}
	
	public synchronized List<Object> sendPost(String url,String crud,DailyVO dailyvo) {
		List<Object> list = new ArrayList<Object>();

		try{
			URL obj = new URL(url);
		
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			con.setRequestMethod("POST"); 
			con.setRequestProperty("Accept-Language", "en-US,enq=0.5");
	
			//parameter setting
			String urlParameter = "crud=" +crud+"&name="+dailyvo.getName()+"&email="+dailyvo.getEmail()+"&detail="+dailyvo.getDetails()+"&age="+dailyvo.getAge();
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameter);
			wr.flush();
			wr.close();
			
			//response 부분
			int responseCode = con.getResponseCode();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			
	
			while((inputLine = in.readLine()) !=null){
				if(inputLine!=null){
					response.append(inputLine);
					list = stringToJson(inputLine);
					//System.out.println(inputLine);
				}
			}
			
			in.close();
			con.disconnect();
		} catch (Exception e){
			
		}
		return list;
	}
	
	private List<Object> stringToJson(String jsonInfo){
	
		JSONParser jsonParser = new JSONParser();
		List<Object> list = new ArrayList<Object>();
		
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonInfo);
			if(jsonObject.get("result").equals("OK")){
				JSONArray dataInfoArray =(JSONArray) jsonObject.get("data");
				if(dataInfoArray != null){
					for(int i=0; i<dataInfoArray.size(); i++){
						DailyVO vo =  new DailyVO();
		                //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
		                JSONObject dataObject = (JSONObject) dataInfoArray.get(i);
		                 
		                vo.setName((String) dataObject.get("NAME"));
		                vo.setEmail((String) dataObject.get("EMAIL"));
		                vo.setDetails((String) dataObject.get("DETAILS"));
		                vo.setAge((String) dataObject.get("AGE"));
		                list.add(vo);
		            }
				} else {
					list.add(jsonObject);
				}
			} else if( jsonObject.get("result").equals("false")){
				list.add(jsonObject);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	

}
