package com.github.ksgfk.teaathome.control.inter;

import com.github.ksgfk.teaathome.models.User;

public interface ControlUserInter {
	public boolean add(User user);
	public boolean deleteId(int id);
	public boolean deleteName(String name);
	public boolean updata(User user);
	public User findid( int id);
	public User findname(String name);
}
