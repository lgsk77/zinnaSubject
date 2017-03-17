package first.sample.controller;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

public class XmlUserNum {
	@XmlAccessorType(XmlAccessType.FIELD)
	public class XmlChildData {

		@XmlElement(name = "user_service_num")
		private String user_service_num;

		public String getUserServiceNum() {
			return user_service_num;
		}

		public void setUserServiceNum(String user_service_num) {
			this.user_service_num = user_service_num;
		}

	}
}
