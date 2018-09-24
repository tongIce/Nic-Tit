package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.micro.datasourse.DynamicDataSourceHolder;
import com.litt.nic.entity.ShareType;
import com.litt.nic.entity.User;
import com.litt.nic.mapper.UserMapper;
import com.litt.nic.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User findById(int id) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		return userMapper.selectByPrimaryKey(id);
	}
	@Override
	public void addUser(User user) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		userMapper.insert(user);
	}
	@Override
	public User findByOpenid(String openid) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		return userMapper.selectByOpenid(openid);
	}
	//数据库查询出所有的用户；
	public List<User> findAllUsers() {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		List<User> list= userMapper.findAllUsers();
		return list;
	}
	//通过id，完成对用户共享级别修改；
	@Override
	public void updateShareType(Integer userId, Integer stid) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		User user=userMapper.findUserById(userId);
		//根据userShareType,查询出ShareType
		ShareType st=  userMapper.findShareType(stid);
		user.setShareType(st);
		//保存修改的用户信息
		int updateUser = userMapper.updateUser(user);
		
	}
	//更新微信用户信息
	@Override
	public void updateUser(User user) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		 int updateUser = userMapper.updateUser(user);
	}
	//根据部门，和共享级别，选出用户
	@Override
	public List<User> findUsersByDepart(String userDepartment, int share) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		List<User> list = userMapper.findUsersByDepart( userDepartment,  share);
		
		return list;
	}
	//查找出共享级别为全校共享用户
	@Override
	public List<User> findUsersByShareType(int type) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		 List<User> list = userMapper.findUsersByShareType(type);
		return list;
	}
}
