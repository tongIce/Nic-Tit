package com.litt.nic.service;

import java.util.List;

import com.litt.nic.entity.Techsupport;
import com.litt.nic.entity.User;

public interface ITechSupportService {
	List<Techsupport> findAllTS();

	List<Techsupport> findByType(String type);

	List<Techsupport> findByMutilInfo(String key, String val);

	/**
	 * 查询所有业务未完成的信息
	 */
	public List<Techsupport> findAllUnfinished();

	/**
	 * 修改状态
	 */
	public void updateStatus_id(int techsupport_id, int status_id);

	/**
	 * 修改负责人
	 * 
	 * @param techsupport_id
	 * @param manager_id
	 */
	void updateManager_id(int techsupport_id, int manager_id);

	Techsupport findById(Integer techsupportId);

	List<Techsupport> findUnFinishedTSByMultiInfo(String key, String val);

	void updateEndTime(int techsupport_id, String endtime);

	void updateFeedback(int id, String info);

	List<com.litt.nic.entity.Techsupport> findByEnd(String dateString);

	void addtech(Techsupport techsupport);

	List<Techsupport> findFeedback(Integer userId);
	//微信用户提交的业务，在未接受之前可以撤回
	void deleteSupport(Integer techsupportId);
	//更新业务
	void updateSupport(Techsupport techId);
	//去查询本部门的用户共享的业务
	List<Techsupport> findShareSupport(List<Integer> lists);
	
}