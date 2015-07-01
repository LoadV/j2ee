package com.oracle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

//��һ��ʵ��ӳ�䵽�ײ���ű���
@Entity
@Table(name = "person_tiable")
//�ƶ��ڶ�����
@SecondaryTable(name = "person_detail" , pkJoinColumns=@PrimaryKeyJoinColumn(name= "person_id"))
public class EntityTwo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//Ĭ�ϱ�
	@Column(name = "name" ,length = 50)
	private String name;
	//�±�
	@Column(table="person_detail" , name = "email")
	private String email;
	
	//�޲ι�����
	public EntityTwo(){
		
	}
	
	public EntityTwo(int id , String name , String email){
		this.id = id;
		this.name = name;
		this .email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
