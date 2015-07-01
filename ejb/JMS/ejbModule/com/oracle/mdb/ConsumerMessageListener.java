package com.oracle.mdb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ��Ϣ����bean
 * @author Administrator
 * ��Ϣ����Bean������
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
}
		//ָ��MDB����������ϢĿ�ĵ�JNDI��
		,mappedName="MessageQueue"
		)
//ֱ��ʵ��MessageListener�ӿ�
public class ConsumerMessageListener implements MessageListener{
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
	
	@PostConstruct
	public void myInit(){
		System.out.println("--��ʼ������--");
	}
	
	@PreDestroy
	//�����ڸ÷����й�ϵ��@@PostConstruct�������ϵͳ��Դ
	public void myDestory(){
		System.out.println("--����֮ǰ�ķ���--");
	}

}
