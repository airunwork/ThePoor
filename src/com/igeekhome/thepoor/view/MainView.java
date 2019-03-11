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
			System.out.println("------------- ��13ѧ��������� -------------");
			System.out.println("\t\t 1.��һ��");
			System.out.println("\t\t 2.���˵�");
			System.out.println("\t\t 3.���˵�");
			System.out.println("\t\t 4.ɾ�˵�");
			System.out.println("\t\t 0.�˳�");
			System.out.println();
			service.totalOut();
			service.totalIn();
			System.out.print("\n������:");
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
		System.out.println("ѡ���������˵�����");
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t 1.֧��");
		System.out.println("\t\t 2.����");
		System.out.print("������:");
		int categoryNumber = sc.nextInt();
		String category;
		if(categoryNumber==1){
			category = "֧��";
		}else{
			category = "����";
		}
		System.out.print("��������:");
		double money = sc.nextDouble();
		System.out.print("���������� (yyyy-mm-dd):");
		String createTime = sc.next();
		System.out.print("�������������:");
		String description = sc.next();
		
		Bill bill = new Bill(category,money,createTime,description);
		service.addBill(bill);
		System.out.println("��ϲ����˵��ɹ�!");
	}
	
	public void editBill(){
		selectAll();
		System.out.println("ѡ����Ǳ༭����");
		Scanner sc = new Scanner(System.in);
		System.out.print("������ID:");
		int bill_id = sc.nextInt();
		System.out.println("��ѡ������:");
		System.out.println("\t\t 1.֧��");
		System.out.println("\t\t 2.����");
		String category;
		int categoryNumber = sc.nextInt();
		if(categoryNumber == 1){
			category = "֧��";
		}else{
			category = "����";
		}
		System.out.print("��������:");
		double money = sc.nextDouble();
		System.out.print("������ʱ��(yyyy-mm-dd):");
		String createTime = sc.next();
		System.out.println("������˵��:");
		String description = sc.next();
		
		Bill bill = new Bill(bill_id,category,money,createTime,description);
		service.editBill(bill);
		System.out.println("�˵����³ɹ�");
	}
	
	public void selectBill(){
		System.out.println("ѡ����ǲ�ѯ�˵����� ");
		System.out.println("\t\t 1.��ѯ����");
		System.out.println("\t\t 2.������ѯ");
		System.out.print("������:");
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
			System.out.println("û�в�ѯ������");
		}
	}
		
	public void select(){
		System.out.println("ѡ�����������ѯ");
		Scanner sc = new Scanner(System.in);
		System.out.print("�����뿪ʼ����(yyyy-mm-dd):");
		String startDate = sc.next();
		System.out.print("�������������(yyyy-mm-dd):");
		String endDate = sc.next();
		List<Bill> list = service.select(startDate, endDate);
		if(list.size()!=0){
			print(list);
		}else{
			System.out.println("û�в�ѯ������");
		}
	}
	
	public void deleteBill(){
		selectAll();
		System.out.println("ѡ�����ɾ���˵�����");
		Scanner sc = new Scanner(System.in);
		System.out.print("������Ҫɾ����ID:");
		int deleteId = sc.nextInt();
		service.deleteBill(deleteId);
		System.out.println("�˵�ɾ���ɹ���");
	}
	
	public void print(List<Bill> list){
		System.out.println("ID\t\t����\t\t���\t\tʱ��\t\t˵��");
		for(Bill bill : list){
			System.out.println(bill.getBillId()+"\t\t"+bill.getCategory()+"\t\t"+
		bill.getMoney()+"\t\t"+bill.getCreateTime()+"\t\t"+bill.getDescription());
		}
	}
}
