package com.ybase.bas.service;

import java.util.List;

import com.ybase.bas.vo.Page;
import com.ybase.demo.vo.User;

public interface UserManager {

	public List<User> queryAllUser();
	
	public User queryOneUser(String id);
	
	public boolean addUser(User user);
	
	public List<User> queryPageUser(Page page);	
}
