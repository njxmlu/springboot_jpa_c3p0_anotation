package com.xmlu.sprigboot.jpa.exam.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "t_user")
public class User {
	@Id
	private int id;
	private String userName;
	private int age;

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", age=" + age +"]";
	}
}