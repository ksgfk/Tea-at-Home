package com.github.ksgfk.teaathome.control.inter;

import java.util.List;

import www.models.Buy_info;

public interface ControlBuyinfoInter {
	public boolean add(Buy_info buyinfo);
	public boolean delete(Buy_info butinfo0);
	public Buy_info findid(int id);
	public boolean updata(Buy_info buyinfo);
	public List<Buy_info> findUesrid(int userid);
	public List<Buy_info> findProductid(int productid);
}
