package com.oracle.mdb.remote;

import javax.ejb.Remote;

/**
 * ��Ϣ����bean�ӿ�
 * @author Administrator
 *
 */
@Remote
public interface Student {
	void add(String name,String gender,int age)throws Exception;
}
