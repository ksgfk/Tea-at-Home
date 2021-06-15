package com.etcxm.www.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etcxm.www.dao.ProductDAO;
import com.etcxm.www.entity.Product;
import com.etcxm.www.utils.DBUtil;

/**
 * DAO�ӿڵ�ʵ���࣬��������ݿ�򽻵��������ݽ�����ɾ�Ĳ�Ĳ���
 * �ò��������ɳ־ò���ʵ�֡�Hibernate��MyBatis
 * @author cyxy
 *
 */
public class ProductDAOImpl implements ProductDAO{
	//�������ݿ�Ĺ����࣬ʵ����
	private DBUtil dbUtil=new DBUtil();			//Spring���ڿ�ܽ׶��й���Щʵ������

	//������Ʒ
	@Override
	public int insert(Product product) {
		String sql="insert into tproduct(product_name,product_price,product_store,product_pdate) values(?,?,?,?)";
		int i=dbUtil.update(sql, product.getProductName(),product.getProductPrice(),product.getProductStore(),product.getProductPdate());
		return i;
	}

	//�޸Ĳ�Ʒ
	@Override
	public int update(Product product) {
		String sql="update tproduct set product_name=?,product_price=?,product_store=?,product_pdate=? where id=?";
		int i=dbUtil.update(sql, product.getProductName(),product.getProductPrice(),product.getProductStore(),product.getProductPdate(),product.getId());
		return i;
	}

	//ɾ����Ʒ
	@Override
	public int delete(int id) {
		String sql="delete from tproduct where id=?";
		int i=dbUtil.update(sql, id);
		return i;
	}

	//��ѯ���в�Ʒ
	@Override
	public List<Product> findAll() {
		String sql="select * from tproduct";	//׼��sql���
		ResultSet resultSet=dbUtil.query(sql);	//ִ�в�ѯ
		//��װ�����ʵ���б�
		List<Product> list=new ArrayList<Product>();		//ʵ���б������洢Productʵ�塣
		//ѭ�������������ResultSet����ʵ����ӵ��б���
		try {
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String productName = resultSet.getString("product_name");
				double productPrice = resultSet.getDouble("product_price");
				int productStore = resultSet.getInt("product_store");
				String productPdate = resultSet.getString("product_pdate");
				Product product=new Product(id, productName, productPrice, productStore, productPdate);
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbUtil.close();
		}
		return list;
	}

	//����id�����ҵ�����Ʒ��Ϣ
	@Override
	public Product findById(int id) {
		String sql="select * from tproduct where id=?";	//׼��sql���
		ResultSet resultSet=dbUtil.query(sql,id);	//ִ�в�ѯ
		Product product=null;
		//ѭ�������������ResultSet����ʵ����ӵ��б���
		try {
			while(resultSet.next()){
				String productName = resultSet.getString("product_name");
				double productPrice = resultSet.getDouble("product_price");
				int productStore = resultSet.getInt("product_store");
				String productPdate = resultSet.getString("product_pdate");
				product=new Product(id, productName, productPrice, productStore, productPdate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbUtil.close();
		}
		return product;
	}

	//ͨ������ģ����ѯ
	@Override
	public List<Product> findByProductName(String productName) {
		String sql="select * from tproduct where product_name like ?";	//׼��sql���
		productName="%"+productName+"%";
		ResultSet resultSet=dbUtil.query(sql,productName);	//ִ�в�ѯ
		//��װ�����ʵ���б�
		List<Product> list=new ArrayList<Product>();		//ʵ���б������洢Productʵ�塣
		//ѭ�������������ResultSet����ʵ����ӵ��б���
		try {
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String productName1 = resultSet.getString("product_name");
				double productPrice = resultSet.getDouble("product_price");
				int productStore = resultSet.getInt("product_store");
				String productPdate = resultSet.getString("product_pdate");
				Product product=new Product(id, productName1, productPrice, productStore, productPdate);
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbUtil.close();
		}
		return list;
	}

	@Override
	public List<Product> findByProductPrice(double lowPrice, double highPrice) {

		String sql="select * from tproduct where product_price between ? and ?";
		ResultSet resultSet = dbUtil.query(sql, lowPrice, highPrice);
		List<Product> list=new ArrayList<Product>();
		try {
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String productName1 = resultSet.getString("product_name");
				double productPrice = resultSet.getDouble("product_price");
				int productStore = resultSet.getInt("product_store");
				String productPdate = resultSet.getString("product_pdate");
				Product product=new Product(id, productName1, productPrice, productStore, productPdate);
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbUtil.close();
		}
		return list;
	}

	@Override
	public List<Product> findAllByPage(int start, int pageSize) {

		String sql="select * from tproduct order by id limit ?,?";
		ResultSet resultSet=dbUtil.query(sql, start, pageSize);
		List<Product> list=new ArrayList<Product>();
		try {
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String productName1 = resultSet.getString("product_name");
				double productPrice = resultSet.getDouble("product_price");
				int productStore = resultSet.getInt("product_store");
				String productPdate = resultSet.getString("product_pdate");
				Product product=new Product(id, productName1, productPrice, productStore, productPdate);
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbUtil.close();
		}
		return list;
	}

	@Override
	public int count() {
		String sql="select count(*) from tproduct";
		ResultSet resultSet = dbUtil.query(sql);
		int allCount=0;
		try {
			while(resultSet.next()){
				allCount=resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbUtil.close();
		}
		return allCount;
	}

}
