package first.sample.controller;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="root")
public class XmlOutput {
	private String result;
	private String reason;
	
	@XmlElement
	public String getResult(){
		return result;
	}
	
	public void setResult(String result){
		this.result = result;
	}
	
	@XmlElement
	public String getReason(){
		return reason;
	}
	
	public void setReason(String reason){
		this.reason= reason;
	}
}
