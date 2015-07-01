import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;


public class MoreUserTransaction {
	public static void main(String args[]) {
		try {
			InitialContext ctx = new InitialContext();
			Object txObj = ctx.lookup("javax/transaction/UserTransaction");
			UserTransaction utx = (UserTransaction) txObj; // �ֲ�ʽ���������
			utx.begin();//�����ֲ�ʽ����
			DataSource ds=(DataSource)ctx.lookup("Oracle");          
			Connection conn=ds.getConnection();          
			Statement stmt=conn.createStatement();  
			stmt.executeUpdate("update  emp set name='nem',salary='bb' where id=2");
			stmt.close(); 
			System.out.println("��һ��Ⱥ����������ɹ�:���²���Oracle");
			
			
			
			
			DataSource ds1=(DataSource)ctx.lookup("Mysql");          
			Connection conn1=ds1.getConnection();          
			Statement stmt1=conn1.createStatement();        //��ѯ��test��������         
			stmt1.executeUpdate("update  emp set name='nem',salary='bb' where id=1");   
			stmt1.close();   
			System.out.println("�ڶ���Ⱥ����������ɹ�:���²���Mysql");
			
			
			Connection conn2=ds1.getConnection();          
			Statement stmt2=conn2.createStatement();        //��ѯ��test��������         
			ResultSet rs2=stmt2.executeQuery("select  name,salary,age from emp");   
			while(rs2.next()){
				System.out.print(rs2.getString("name")+"   ");  
				System.out.print(rs2.getString("salary")+"   ");  
				System.out.println(rs2.getString("age"));  
			}       
			rs2.close();          
			stmt2.close();   
			System.out.println("�ڶ���Ⱥ����������ɹ�:��ѯ����Mysql");
			
			
			Connection conn4=ds.getConnection();          
			Statement stmt4=conn4.createStatement();  
			ResultSet rs4=stmt4.executeQuery("select  name,salary from emp");   
			while(rs4.next()){
				System.out.print(rs4.getString("name")+"   ");  
				System.out.println(rs4.getString("salary"));  
			}       
			rs4.close();          
			stmt4.close();  
			System.out.println("���ĸ�Ⱥ����������ɹ�:��ѯ����Oracle");
			
			utx.commit();//�ֲ�ʽ�����ύ
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
