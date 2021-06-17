package com.github.ksgfk.teaathome.control.inter;

import www.models.Userdata;

public interface ControlUserdataInter  {
	public boolean add(Userdata data);
	public boolean deleteuserid(int userid);
	public boolean deleteproductid(int productid);
	public boolean deleteid(int id);
	public boolean updata(Userdata data);
	public Userdata findUserid(int userid);
}
