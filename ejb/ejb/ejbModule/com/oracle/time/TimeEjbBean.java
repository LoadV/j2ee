package com.oracle.time;

import java.sql.Time;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;


@Stateless(mappedName = "TimeEjb")
public class TimeEjbBean implements TimeEjb {

	//�������ö�ʱ���ķ���
	@Resource
	TimerService timeService;
	
	@Override
	public void setTime(Date init, long interval) {
		//����һ��ÿ��intervalʱ��ͻ������Ķ�ʱ��
		timeService.createTimer(init, interval,"�µĶ�ʱ��");
	}

	//���嶨ʱִ�еķ���
	@Timeout
	public void check(Time timer) {
		System.out.println("��ʱ����Ϣ: " + ((Timer) timer).getInfo());
		System.out.println("ģ��ϵͳ���");
	}

}
