package com.igeekhome.thepoor.view;
import java.util.List;
import java.util.Scanner;import org.omg.Messaging.SyncScopeHelper;

import com.igeekhome.thepoor.domain.Bill;
import com.igeekhome.thepoor.service.BillService;

public class MainView {
	private BillService service = new BillService();
	
	public void run(){
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("------------- 穷13学生记账软件 -------------");
			System.out.println("\t\t 1.记一笔");
			System.out.println("\t\t 2.看账单");
			System.out.println("\t\t 3.改账单");
			System.out.println("\t\t 4.删账单");
			System.out.println("\t\t 0.退出");
			System.out.println();
			service.totalOut();
			service.totalIn();
			System.out.print("\n请输入:");
			int choose = sc.nextInt();
			switch(choose){
			case 1:
				addBill();
				break;
			case 2:
				selectBill();
				break;
			case 3:
				editBill();
				break;
			case 4:
				deleteBill();
				break;
			case 0:
				System.exit(0);
				break;
			}
		}
	}
	
	public void addBill(){
		System.out.println("选择的是添加账单功能");
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t 1.支出");
		System.out.println("\t\t 2.收入");
		System.out.print("请输入:");
		int categoryNumber = sc.nextInt();
		String category;
		if(categoryNumber==1){
			category = "支出";
		}else{
			category = "收入";
		}
		System.out.print("请输入金额:");
		double money = sc.nextDouble();
		System.out.print("请输入日期 (yyyy-mm-dd):");
		String createTime = sc.next();
		System.out.print("请输入具体描述:");
		String description = sc.next();
		
		Bill bill = new Bill(category,money,createTime,description);
		service.addBill(bill);
		System.out.println("恭喜添加账单成功!");
	}
	
	public void editBill(){
		selectAll();
		System.out.println("选择的是编辑功能");
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入ID:");
		int bill_id = sc.nextInt();
		System.out.println("请选择类型:");
		System.out.println("\t\t 1.支出");
		System.out.println("\t\t 2.收入");
		String category;
		int categoryNumber = sc.nextInt();
		if(categoryNumber == 1){
			category = "支出";
		}else{
			category = "收入";
		}
		System.out.print("请输入金额:");
		double money = sc.nextDouble();
		System.out.print("请输入时间(yyyy-mm-dd):");
		String createTime = sc.next();
		System.out.println("请输入说明:");
		String description = sc.next();
		
		Bill bill = new Bill(bill_id,category,money,createTime,description);
		service.editBill(bill);
		System.out.println("账单更新成功");
	}
	
	public void selectBill(){
		System.out.println("选择的是查询账单功能 ");
		System.out.println("\t\t 1.查询所有");
		System.out.println("\t\t 2.条件查询");
		System.out.print("请输入:");
		Scanner sc = new Scanner(System.in);
		int selectChoose = sc.nextInt();
		switch(selectChoose){
		case 1:
			selectAll();
			break;
		case 2:
			select();
			break;
		}
	}
	
	public void selectAll(){
		List<Bill> list = service.selectAll();
		if(list.size()!=0){
			print(list);
			
		}else{
			System.out.println("没有查询到数据");
		}
	}
		
	public void select(){
		System.out.println("选择的是条件查询");
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入开始日期(yyyy-mm-dd):");
		String startDate = sc.next();
		System.out.print("请输入结束日期(yyyy-mm-dd):");
		String endDate = sc.next();
		List<Bill> list = service.select(startDate, endDate);
		if(list.size()!=0){
			print(list);
		}else{
			System.out.println("没有查询到数据");
		}
	}
	
	public void deleteBill(){
		selectAll();
		System.out.println("选择的是删除账单功能");
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入要删除的ID:");
		int deleteId = sc.nextInt();
		service.deleteBill(deleteId);
		System.out.println("账单删除成功！");
	}
	
	public void print(List<Bill> list){
		System.out.println("ID\t\t类型\t\t金额\t\t时间\t\t说明");
		for(Bill bill : list){
			System.out.println(bill.getBillId()+"\t\t"+bill.getCategory()+"\t\t"+
		bill.getMoney()+"\t\t"+bill.getCreateTime()+"\t\t"+bill.getDescription());
		}
	}
}
