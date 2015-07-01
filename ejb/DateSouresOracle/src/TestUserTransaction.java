import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class TestUserTransaction {
	public static void main(String args[]) {
		UserTransaction utx=null;
		try {
			InitialContext ctx = new InitialContext();
			Object txObj = ctx.lookup("javax/transaction/UserTransaction");
			utx = (UserTransaction) txObj; // �ֲ�ʽ���������
			utx.begin();//�����ֲ�ʽ����

			
			DataSource ds=(DataSource)ctx.lookup("Oracle");          
			Connection conn=ds.getConnection();          
			Statement stmt=conn.createStatement();  
			stmt.executeUpdate("update  emp set name='nem',salary='bb' where id=2");
			stmt.close(); 
			System.out.println("��һ��Ⱥ����������ɹ�:���²���");
			
			
			
			Connection conn2=ds.getConnection();          
			Statement stmt2=conn2.createStatement();  
			ResultSet rs2=stmt2.executeQuery("select  name,salary from emp");   
			while(rs2.next()){
				System.out.print(rs2.getString("name")+"   ");  
				System.out.println(rs2.getString("salary"));  
			}       
			rs2.close();          
			stmt2.close();  
			System.out.println("�ڶ���Ⱥ����������ɹ�:��ѯ����");
			
			
			
			Connection conn3=ds.getConnection();          
			Statement stmt3=conn3.createStatement();  
			stmt3.executeUpdate("insert into emp(id,name,salary)values(3,'bfc2','IT2')");
			stmt3.close(); 
			System.out.println("������Ⱥ����������ɹ�:�������");
			
			
			
			Connection conn4=ds.getConnection();          
			Statement stmt4=conn4.createStatement();  
			ResultSet rs4=stmt4.executeQuery("select  name,salary from emp");   
			while(rs4.next()){
				System.out.print(rs4.getString("name")+"   ");  
				System.out.println(rs4.getString("salary"));  
			}       
			rs4.close();          
			stmt4.close();  
			System.out.println("���ĸ�Ⱥ����������ɹ�:��ѯ����");
			
			
			
			utx.commit();//�ֲ�ʽ�����ύ
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
		}

	}

}
