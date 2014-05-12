package manager.impl;

import manager.UserManager;

import org.springframework.stereotype.Service;

import common.ibatis.IBatisDaoSupport;

import domain.User;

@Service
public class UserManagerImpl extends IBatisDaoSupport implements UserManager {

	public void save(User user) {
		System.out.println("save a user!");
	}
	
	public User getUserById(Integer id) {
		User user = null;
		try {
			user = this.getSqlSession().selectOne("getUserById", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
