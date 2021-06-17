package com.github.ksgfk.teaathome.control.inter;

import com.github.ksgfk.teaathome.models.BuyInfo;

import java.util.List;

public interface ControlBuyinfoInter {
	public boolean add(BuyInfo buyinfo);
	public boolean delete(BuyInfo butinfo0);
	public BuyInfo findid(int id);
	public boolean updata(BuyInfo buyinfo);
	public List<BuyInfo> findUesrid(int userid);
	public List<BuyInfo> findProductid(int productid);
}
