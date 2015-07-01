package com.oracle.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.oracle.mdb.remote.Student;


//ָ��MDB��������ϢĿ�ĵ�����
@MessageDriven(activationConfig=
{
		@ActivationConfigProperty(propertyName="destinationType"
				,propertyValue="javax.jms.Queue"),
		@ActivationConfigProperty(propertyName="acknowledgeMode"
				,propertyValue="Auto-acknowledge"),
				//ָ��MDB����������ϢĿ�ĵ�JNDI����
		@ActivationConfigProperty(propertyName="destination"
				,propertyValue="MessageQueue")
}
		//ָ��MDB����������ϢĿ�ĵ�JNDI��
		,mappedName="MessageQueue"
		)
//ֱ��ʵ��MessageListener�ӿ�
public class StudentMesageListener implements MessageListener{

	@EJB(name="StudentBean")
	private Student student;
	public void onMessage(Message msg) {
		try{
			if(msg instanceof MapMessage)
			{
				MapMessage map = (MapMessage)msg;
				String name = map.getString("name");
				String gender = map.getString("gender");
				int age = map.getInt("age");
				student.add(name, gender, age);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
