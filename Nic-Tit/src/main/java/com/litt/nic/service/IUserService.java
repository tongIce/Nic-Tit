package com.litt.nic.service;

import java.util.List;

import com.litt.nic.entity.User;

public interface IUserService {
	User findById(int id);

	void addUser(User user);

	User findByOpenid(String openid);

	List<User> findAllUsers();
	//更新微信用户信息
	void updateUser(User user);
	//通过id，完成对用户的共享信息级别的修改；
	void updateShareType(Integer userId, Integer userShareType);
	//根据部门，和共享级别，选出用户
	List<User> findUsersByDepart(String userDepartment, int share);
	//查找出共享级别为全校共享用户
	List<User> findUsersByShareType(int type);
}
