package first.sample.controller;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlChildData {

	@XmlElement(name = "sname")
	private String sname;

	@XmlElement(name = "sstartdate")
	private String sstartdate;

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getStartDate() {
		return sstartdate;
	}

	public void setStartDate(String sstartdate) {
		this.sstartdate = sstartdate;
	}

}

/*@XmlAccessorType(XmlAccessType.FIELD)
public class XmlChildData {

	@XmlElement(name = "email")
	private String email;

	@XmlElement(name = "name")
	private String name;

	@XmlElement(name = "position")
	private String position;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}*/