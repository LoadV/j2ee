package com.leo.dao.impl;

import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import com.leo.dao.HelloChinaRemote;

@Stateless(mappedName="HelloChinaBeanInner")
public class HelloChinaBeanInner implements HelloChinaRemote {
	public String Myname() {
		return "���Ǹ�ɽ��";
	}

	public String sayHello(String name) {
		return name + "���������й���";
	}

	// ʹ��@AroundInvokeע�ͣ�ָ����Ҫ���������ķ�����
	// ʹ�ô�ע�͵ĸ�ʽ��������public Object XXX(InvocationContext ctx)throws Exception
	@AroundInvoke
	public Object log(InvocationContext ctx) throws Exception {
		System.out.println("interceptor.....");
		System.out.println("��ʱִ�еķ�����" + ctx.getMethod().getName());
		Object obj = ctx.proceed();
		System.out.println(ctx.getMethod().getName() + "�Ѿ�ִ�гɹ���");
		return obj;
	}
}
