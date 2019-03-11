package com.igeekhome.thepoor.dao;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.igeekhome.thepoor.domain.Bill;
import com.igeekhome.thepoor.tools.JDBCUtils;

public class BillDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	/**
	 * ��ѯ�����˵�
	 * @return
	 */
	public List<Bill> selectAll(){
		try{
			String sql = "SELECT * FROM bill";
			List<Bill> list = qr.query(sql, new BeanListHandler<>(Bill.class));
			return list;
		}catch(SQLException e){
			System.out.println(e);
			throw new RuntimeException("��ѯ�˵�ʧ��");
		}
	}
	
	/**
	 * ������ѯ�˵�
	 * ��ʼ���� - ��������
	 */
	public List<Bill> select(String startDate , String endDate){
		try{
			String sql = "SELECT * FROM bill WHERE createTime BETWEEN ? AND ?";
			Object[] params = {startDate,endDate};
			return qr.query(sql,new BeanListHandler<>(Bill.class),params);
		}catch(SQLException ex){
			System.out.println(ex);
			throw new RuntimeException("������ѯʧ��");
		}
	}
	
	/**
	 * ����˵�
	 */
	public void addBill(Bill bill){
		try{
			String sql = "INSERT INTO Bill(category,money,createTime,description) VALUES(?,?,?,?)";
			Object[] params = {bill.getCategory(),bill.getMoney(),bill.getCreateTime(),bill.getDescription()};
			qr.update(sql,params);
		}catch(SQLException ex){
			System.out.println(ex);
			throw new RuntimeException("�������ʧ��");
		}
	}
	
	/**
	 * �����˵�
	 */
	public void editBill(Bill bill){
		try{
			String sql = "UPDATE bill SET category=?,money=?,createTime=?,description=? WHERE billId=?";
			Object[] params = {bill.getCategory(),bill.getMoney(),bill.getCreateTime(),bill.getDescription(),bill.getBillId()};
			qr.update(sql,params);
		}catch(SQLException ex){
			System.out.println(ex);
			throw new RuntimeException("�༭����ʧ��");
		}
	}
	
	/**
	 * ɾ���˵�
	 */
	public void deleteBill(int billId){
		try{
			String sql = "DELETE FROM bill WHERE billId=?";
			qr.update(sql,billId);
		}catch(SQLException ex){
			System.out.println(ex);
			throw new RuntimeException("�˵�ɾ��ʧ��");
		}
	}
	
	
	public void totalOut(){
		try{
			double money = 0;
			String sql = "SELECT money FROM bill WHERE category='֧��'";
			List<Bill> bills = qr.query(sql, new BeanListHandler<>(Bill.class));
			for(Bill bill : bills){
				money+=bill.getMoney();
			}
			System.out.println("������֧�� ��"+money);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void totalIn(){
		try{
			double money = 0;
			String sql = "SELECT money FROM bill WHERE category='����'";
			List<Bill> bills = qr.query(sql, new BeanListHandler<>(Bill.class));
			for(Bill bill : bills){
				money+=bill.getMoney();
			}
			System.out.println("���������� ��"+money);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
