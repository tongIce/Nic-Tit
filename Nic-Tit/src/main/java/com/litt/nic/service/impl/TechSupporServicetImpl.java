package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.entity.Techsupport;
import com.litt.nic.entity.User;
import com.litt.nic.mapper.TechsupportMapper;
import com.litt.nic.mapper.UserMapper;
import com.litt.nic.service.ITechSupportService;

@Service
public class TechSupporServicetImpl implements ITechSupportService {

	@Autowired
	private TechsupportMapper techSupportMapper;

	public List<Techsupport> findAllTS() {

		return techSupportMapper.selectAllTS();
	}

	public List<Techsupport> findByMutilInfo(String key, String val) {
		// TODO Auto-generated method stub
		return techSupportMapper.selectTSMultiInfo(key, val);
	}

	@Override
	public List<Techsupport> findAllUnfinished() {
		List<Techsupport> List = techSupportMapper.findAllUnfinished();
		for (Techsupport techsupport : List) {

			System.out.println("未完成的状态为：" + techsupport.getStatusId());
		}
		System.out.println("执行了service的方法");
		return List;
	}

	@Override
	public Techsupport findById(Integer techsupportId) {

		return techSupportMapper.selectByPrimaryKey(techsupportId);
	}

	@Override
	public void updateStatus_id(int techsupport_id, int status_id) {
		techSupportMapper.updateStatus_id(techsupport_id, status_id);

	}

	@Override
	public void updateManager_id(int techsupport_id, int manager_id) {

		techSupportMapper.updateManager_id(techsupport_id, manager_id);
	}

	@Override
	public void updateEndTime(int techsupport_id, String endtime) {
		techSupportMapper.updateEndTime(techsupport_id, endtime);

	}

	@Override
	public List<Techsupport> findUnFinishedTSByMultiInfo(String key, String val) {
		// TODO Auto-generated method stub
		return techSupportMapper.selectUnFinishedTSByMultiInfo(key, val);
	}

	public void updateFeedback(int id, String info) {

		techSupportMapper.updateFeedback(id, info);
	}

	@Override
	public List<Techsupport> findByEnd(String dateString) {
		// TODO Auto-generated method stub
		System.out.println("reservice");
		return techSupportMapper.selectfinish(dateString);
	}
	@Override
	public void addtech(Techsupport techsupport) {
		techSupportMapper.insert(techsupport);
	}
	@Override
	public List<Techsupport> findFeedback(Integer userId) {
		return techSupportMapper.findFeedback(userId);
	}
	@Override
	public List<Techsupport> findByType(String type) {
		return techSupportMapper.selectByType(type);
	}
	//微信用户提交的业务，在未接受之前可以撤回
	@Override
	public void deleteSupport(Integer techsupportId) {
		techSupportMapper.deleteByPrimaryKey(techsupportId);
	}
	//更新业务
	@Override
	public void updateSupport(Techsupport techId) {
		//techSupportMapper.updateSupport(techId);
		int updateId = techSupportMapper.updateByPrimaryKeySelective(techId);
	}
	//去查询本部门的用户共享的业务
	@Override
	public List<Techsupport> findShareSupport(List<Integer> lists) {
		List<Techsupport> list = techSupportMapper.findShareSupport(lists);
		return list;
	}
	
	
	
}