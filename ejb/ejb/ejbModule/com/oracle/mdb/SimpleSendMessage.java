package com.oracle.mdb;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SimpleSendMessage {
	public void sendMessage() throws JMSException, NamingException{
		//����WeblogicĬ�����ӹ�����JNDI
		final String CONNECTION_FACTORY_JNDI = "ConnectionFactory" ; 
		//��ȡJNDI���������Cotext
		Context ctx = getInitialContext();
		//ͨ��JNDI���һ�ȡ���ӹ���
		ConnectionFactory connFactory = (ConnectionFactory)ctx.lookup(CONNECTION_FACTORY_JNDI);
		//���ӹ�����������
		Connection conn = connFactory.createConnection();
		//ͨ��JNDI���һ�ȡ��ϢĿ��
		Destination dest = (Destination)ctx.lookup("MessageQueue");
		//JMS���Ӵ���JMS�Ự
		Session session = conn.createSession(false /*���������ԻỰ*/
				, Session.AUTO_ACKNOWLEDGE);
		//JMS�Ự������Ϣ������
		MessageProducer sender = session.createProducer(dest);
		//������Ϣ������������������Ϣ�Ĵ���ģʽ,��Чʱ��
		sender.setDeliveryMode(20000);
		//ͨ��JMS�Ự����һ���ı���Ϣ
		TextMessage msg = session.createTextMessage();
		//������Ϣ����
		msg.setText("Hello");
		//������Ϣ
		sender.send(msg);
		//�ٴη���
		msg.setText("Wellocome to JMS");
		//�ر���Դ
		session.close();
		conn.close();
	}
	
	private Context getInitialContext(){
		Context ctx = null;
		Properties props = new Properties();
        props.setProperty( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        props.setProperty( Context.PROVIDER_URL, "t3://localhost:7001");    
        try {
			 ctx = new InitialContext(props);
		} catch (NamingException e) {
			e.printStackTrace();
		}
        
        return ctx;
	}
	
	
	public static void main(String args[]){
		SimpleSendMessage mp = new SimpleSendMessage();
		try {
			mp.sendMessage();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
