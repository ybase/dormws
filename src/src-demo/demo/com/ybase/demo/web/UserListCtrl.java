package com.ybase.demo.web;

import com.ybase.bas.service.ContainerHodler;
import com.ybase.bas.service.UserManager;
import com.ybase.bas.web.AbstractCommonDispatch;

public class UserListCtrl extends AbstractCommonDispatch {

	private static final long serialVersionUID = 1L;

	@Override
	public void process() throws Exception {
		UserManager userManager = ContainerHodler.getComponent("userManager");
		setPageAttr("userList", userManager.queryPageUser(wrappPage.get()));
		setDUrl("pages/demo/user.jsp");
	}

}
