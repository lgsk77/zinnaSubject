package com.zinnaworks.kbell.vo;

public class DailyVO {
	
	private String name;
	private String email;
	private String details;
	private String age;
	
	public DailyVO(){
		this.name=null;
		this.email=null;
		this.details=null;
		this.age=null;
	}
	
	public DailyVO(String name,String email,String details,String age){
		this.name=name;
		this.email=email;
		this.details=details;
		this.age=age;
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
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
}
