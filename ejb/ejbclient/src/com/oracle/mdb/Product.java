package com.oracle.mdb;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * ��Ϣ����bean������
 * @author Administrator
 *
 */
public class Product {
	public void sendMessage()throws NamingException,JMSException
	{
		//����WeblogicĬ�����ӹ�����JNDI
		final String CONNECTION_FACTORY_JNDI
			  ="ConnectionFactory";
		//��ȡJNDI���������Context
		Context ctx=new InitialContext();
		//ͨ��jndi���һ�ȡ���ӵĹ���
		ConnectionFactory connFactory = (ConnectionFactory)
				ctx.lookup(CONNECTION_FACTORY_JNDI);
		//ͨ��jndi���һ�ȡ��ϢĿ��
		Destination dest = (Destination)ctx.lookup("MessageQueue");
		//���ӹ���
		Connection conn = connFactory.createConnection();
		//JMS���Ӵ���JMS�Ự
		Session session = conn.createSession(false/*���������ԻỰ*/, Session.AUTO_ACKNOWLEDGE);
		//JMS�Ự������Ϣ������
		MessageProducer sender = session.createProducer(dest);
		//������Ϣ������������������Ϣ����ģʽ����Чʱ��
		sender.setDeliveryMode(DeliveryMode.PERSISTENT);
		sender.setTimeToLive(200000);
		//ͨ��JMS�Ự����һ���ı���Ϣ
		TextMessage msg = session.createTextMessage();
		//msg.setStringProperty("ConType", "txt");
		//������Ϣ����
		msg.setText("hello");
		sender.send(msg);
		msg.setText("Welcome to JMS ");
		sender.send(msg);
		session.close();
		conn.close();
	}
	//ʡ�Ի�ȡ���������Context����Ĺ��߷���
	@SuppressWarnings("unused")
	private Context getInitialContext()
	{
		return null;
		
	}
	public  static void main(String args[]){
		try {
			new Product().sendMessage();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
