package first.sample.controller;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlData {

	@XmlElement(name = "result")
	private String result;
	
	@XmlElement(name = "reason")
	private String reason;
	
	@XmlElement(name = "data")
	private List<XmlChildData> items;
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<XmlChildData>  getItems() {
		return items;
	}

	public void setItems(List<XmlChildData>  xmllist) {
		this.items = xmllist;
	}
	
	
}

/*@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlData {

	@XmlElement(name = "items")
	private List<XmlChildData> items;
	
	public List<XmlChildData>  getItems() {
		return items;
	}

	public void setItems(List<XmlChildData>  items) {
		this.items = items;
	}
}*/