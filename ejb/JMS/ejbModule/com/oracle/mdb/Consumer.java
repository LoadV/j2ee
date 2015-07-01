package com.oracle.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * ��Ϣ����bean
 * @author Administrator
 * ��Ϣ����Bean������
 * ���ʵ��MesageListener�ӿ�
 *
 */
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
		//��MDB��Beanʵ�����ӵ�ʵ��MessageListener�ӿ�
},messageListenerInterface=javax.jms.MessageListener.class
		//ָ��MDB����������ϢĿ�ĵ�JNDI��
		,mappedName="MessageQueue"
		)
public class Consumer {
	//ʵ��onMessage��������--��JMS��Ϣ���ʹ���ϢĿ��ʱ���÷���������
	public void onMessage(Message msg){
		try{
			if(msg instanceof TextMessage)
			{
				TextMessage txt=(TextMessage)msg;
				String content = txt.getText();
				System.out.println("JMS ��ϢΪ:"+content);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
