package com.oracle.mdb;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import com.oracle.mdb.remote.Student;
/**
 * Studentʵ����
 * @author Administrator
 *
 */
@Stateless(name="StudentBean")
public class StudentBean implements Student {

	//��������ע������Դ
	@Resource(name="Oracle")
	private DataSource ds;
	public void add(String name, String gender, int age) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			//ͨ������Դ��ȡ���ݿ�����
			conn = ds.getConnection();
			//ʹ��PreparedStatementִ��SQL���
			pstmt = conn.prepareStatement("select * from emp where id=?");
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		}finally{
			pstmt.close();
			conn.close();
		}
	}

}
