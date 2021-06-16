package com.github.ksgfk.teaathome.models;



import jdk.javadoc.doclet.Taglet;

public class User {

    private int id;
    private String name;
    private String password;
    private String sex;
    private String photo;
    private String phone;
    private String receive;
    private int permission;
    private String[] question;
    private String[] answer;

    public User(int id, String name, String password, String sex, String photo, String phone, String receive,
        int permission, String[] question, String[] answer) {
        if(!checkname(name)||!checkpassword(password)||!checkquestion(question)||!checkphone(phone)) 
        return;
    	this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.photo = photo;
        this.phone = phone;
        this.receive = receive;
        this.permission = permission;
        for (int i = 0; i < question.length; i++) {
            this.question[i] = question[i];
        }
        for (int i = 0; i < answer.length; i++) {
            this.answer[i] = answer[i];
        }
    }

    private boolean checkphone(String phone) {
        String reg = "13[0-9]{9}";
        return phone.matches(reg);
    }
    private boolean checkname(String name) {
    	if(name.length()<16)
    		return true;
    	return false;
    } 
    private boolean checkpassword(String password) {
    	if(password.length()<16) 
    		return true;	
    	return false;
    }
    private boolean checkquestion(String[] question) {
    	for(int i=0;i<question.length;i++) {
    		if(question[i].length()>20)
    			return false;
    	}
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

	public String[] getQuestion() {
		return question;
	}

	public void setQuestion(String[] question) {
		this.question = question;
	}

	public String[] getAnswer() {
		return answer;
	}

	public void setAnswer(String[] answer) {
		this.answer = answer;
	}
 

}