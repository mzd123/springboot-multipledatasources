package com.mzd.multipledatasources.bean;

public class TestBean {
	private String id;
	private String userid;
	private int score;
	private String classid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	@Override
	public String toString() {
		return "TestBean [id=" + id + ", userid=" + userid + ", score=" + score + ", classid=" + classid + "]";
	}

}
