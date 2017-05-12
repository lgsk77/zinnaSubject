package com.zinnaworks.daily.vo;

public class DailyVO {
	
	private String details;
	private String name;
	private String email;
	private String age;
	
	public DailyVO(){
		this.details = null;
		this.name = null;
		this.email = null;
		this.age = null;
	}
	
	public DailyVO(String name, String email, String details,String age){
		this.details = details;
		this.name = name;
		this.email = email;
		this.age = age;
	}
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
}
