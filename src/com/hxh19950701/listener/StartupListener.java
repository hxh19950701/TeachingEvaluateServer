package com.hxh19950701.listener;

import java.util.List;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.hxh19950701.comm.Constant;
import com.hxh19950701.comm.MD5Util;
import com.hxh19950701.dao.UserDao;
import com.hxh19950701.pojos.User;

public class StartupListener extends UserDao implements ApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("服务器启动啦~~~");
		List<User> users = getAllUser(Constant.IDENTITY_ADMINISTRATOR);
		if (users == null) {
			System.out.println("没有管理员用户，将创建默认管理员用户");
			createDefaultAdministratorUser();
		}
		else {
			System.out.println("管理员用户有" + users.size() + "位，分别是：");
			for (User user : users) {
				System.out.println(user.getUsername() + "， 创建于" + user.getCreateTime());
			}
		}
	}

	private void createDefaultAdministratorUser() {
		String username = "administrator";
		String password = username;
		User user = new User(username, MD5Util.encipher(password),
				Constant.IDENTITY_ADMINISTRATOR, Constant.CREATE_BY_ADMINISTRATOR);
		save(user);
		System.out.println("已创建默认管理员账户，用户名：" + username + "， 密码：" + password);
	}
}