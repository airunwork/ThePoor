package com.igeekhome.thepoor.service;

import java.util.List;

import com.igeekhome.thepoor.dao.BillDao;
import com.igeekhome.thepoor.domain.Bill;

public class BillService {
	private BillDao dao = new BillDao();
	
	public List<Bill> selectAll(){
		return dao.selectAll();
	}
	
	public void addBill(Bill bill){
		dao.addBill(bill);
	}
	
	public List<Bill> select(String startDate,String endDate){
		return dao.select(startDate, endDate);
	}
	
	public void editBill(Bill bill){
		dao.editBill(bill);
	}
	
	public void deleteBill(int billId){
		dao.deleteBill(billId);
	}
	
	public void totalOut(){
		dao.totalOut();
	}
	public void totalIn(){
		dao.totalIn();
	}
}
