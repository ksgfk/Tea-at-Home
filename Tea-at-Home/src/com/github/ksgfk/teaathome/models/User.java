package com.github.ksgfk.teaathome.models;

public class User {

    private int id;
    private String name;
    private String password;
    private String sex;
    private String photo;
    private String phone;
    private String receive;
    private int permission;
    private String question;
    private String answer;

    public User(int id, String name, String password, String sex, String photo, String phone, String receive,int permission, String question, String answer) {
//        if(!checkname(name)||!checkpassword(password)||!checkquestion(question)||!checkphone(phone))
//        return;
    	this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.photo = photo;
        this.phone = phone;
        this.receive = receive;
        this.permission = permission;
        this.answer=answer;
        this.question=question;
    }

    public User(String name,String password,String phone){
    	this(-1,name,password,null,null,phone,null,0,null,null);
	}

    public static boolean checkphone(String phone) {
        String reg = "1[358][0-9]{9}";
        return phone.matches(reg);
    }
	public static boolean checkname(String name) {
    	if(name.length()<16)
    		return true;
    	return false;
    }
	public static boolean checkpassword(String password) {
    	if(password.length()<16)
    		return true;
    	return false;
    }
	public static boolean checkquestion(String question) {
    	if(question.length()>20)
    		return false;
    	return true;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}



}