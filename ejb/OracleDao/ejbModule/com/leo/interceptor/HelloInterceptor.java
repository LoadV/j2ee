package com.leo.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class HelloInterceptor {
	  //ʹ��@AroundInvokeע�ͣ�ָ����Ҫ���������ķ�����
	  //ʹ�ô�ע�͵ĸ�ʽ��������public Object XXX(InvocationContext ctx)throws Exception
	  @AroundInvoke
	  public Object log(InvocationContext ctx) throws Exception{
	    System.out.println("interceptor.....");
	    System.out.println("��ʱִ�еķ�����"+ctx.getMethod().getName());
	    Object obj=ctx.proceed();
	    System.out.println(ctx.getMethod().getName()+"�Ѿ�ִ�гɹ���");
	    return obj;
	  }

	}