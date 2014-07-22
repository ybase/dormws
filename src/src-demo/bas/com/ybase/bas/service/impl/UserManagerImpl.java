package com.ybase.bas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ybase.bas.annotation.Component;
import com.ybase.bas.exception.BasException;
import com.ybase.bas.jdbc.JdbcEntityDaoTemplate;
import com.ybase.bas.service.UserManager;
import com.ybase.bas.util.BasUtil;
import com.ybase.bas.vo.Page;
import com.ybase.demo.vo.User;

@Component("userManager")
public class UserManagerImpl extends JdbcEntityDaoTemplate implements UserManager {

	@Override
	public List<User> queryAllUser() {
		Map<String, Object> queryCon = new HashMap<String, Object>();
		queryCon.put("status_Eq", 0);
		try {
			return executeQuery(User.class, queryCon);
		} catch (BasException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User queryOneUser(String id) {
		Map<String, Object> queryCon = new HashMap<String, Object>();
		queryCon.put("id", 0);
		try {
			return BasUtil.getOne(executeQuery(User.class, queryCon));
		} catch (BasException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean addUser(User user) {
		try {
			User newUser = addVO(user);
			if (newUser.getId() != null) {
				return true;
			}
			return false;
		} catch (BasException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<User> queryPageUser(Page page) {
		Map<String, Object> queryCon = new HashMap<String, Object>();
		queryCon.put("status_Eq", 0);
		try {
			return executeQuery(User.class, queryCon, page);
		} catch (BasException e) {
			throw new RuntimeException(e);
		}
	}

}
