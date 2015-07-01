package com.oracle.mdb;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class StudentProduct {
	public void sendMessage()throws NamingException ,JMSException 
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
				MapMessage msg = session.createMapMessage();
				//������Ϣ����
				msg.setString("name","�����");
				msg.setString("gender","��");
				msg.setInt("age", 500);
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
