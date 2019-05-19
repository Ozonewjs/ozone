package com.ozone.mfls.beans;


public class SA_USERS {
	public String userid;
	public String  username;
	public String  sex;
	public String  password;
	public String  status;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SA_USERS{" +
				"userid='" + userid + '\'' +
				", username='" + username + '\'' +
				", sex='" + sex + '\'' +
				", password='" + password + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}
