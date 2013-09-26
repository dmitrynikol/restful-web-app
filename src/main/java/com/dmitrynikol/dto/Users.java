package com.dmitrynikol.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.dmitrynikol.model.User;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Users {
	
	private List<User> users;

	@XmlElement(name = "user")
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
