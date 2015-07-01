import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TestDataSoures {
	//��������Դ
	public static void main(String args[]){
		try{          
			Context initCtx=new InitialContext();   
			DataSource ds=(DataSource)initCtx.lookup("Oracle");          
			Connection conn=ds.getConnection();          
			Statement stmt=conn.createStatement();        //��ѯ��test��������         
			ResultSet rs=stmt.executeQuery("select  name,salary from emp");   
			while(rs.next()){
				System.out.println(rs.getString("name"));  
				System.out.println(rs.getString("salary"));  
			}       
			rs.close();          
			stmt.close();      
			}catch(Exception e){          
				e.printStackTrace();
		}
	}

}
