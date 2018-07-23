package com.litt.nic.mapper;

import java.util.List;

import com.litt.nic.entity.ShareType;
import com.litt.nic.entity.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer userId);

	int updateUser(User record);

	int updateByPrimaryKey(User record);

	User selectByOpenid(String openid);
	//通过uid查询出微信用户
	
	//查询出所有微信用户；
	List<User> findAllUsers();
	//id,查找出微信用户；
	User findUserById(Integer userId);
	//根据share的shid查询ShareType类
	ShareType findShareType(Integer stid);
	//根据部门，和共享级别，选出用户
	List<User> findUsersByDepart(String userDepartment, int share);
	//查找出共享级别为全校共享用户
	List<User> findUsersByShareType(int type);
}