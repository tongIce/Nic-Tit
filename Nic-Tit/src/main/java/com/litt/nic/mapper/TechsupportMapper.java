package com.litt.nic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.litt.nic.entity.Techsupport;
import com.litt.nic.entity.User;

public interface TechsupportMapper {
	int deleteByPrimaryKey(Integer techsupportId);

	int insert(Techsupport record);

	int insertSelective(Techsupport record);

	Techsupport selectByPrimaryKey(Integer techsupportId);

	int updateByPrimaryKeySelective(Techsupport record);

	int updateByPrimaryKey(Techsupport record);

	List<Techsupport> selectAllTS();

	List<Techsupport> selectTSMultiInfo(@Param("key") String key,
			@Param("val") String val);

	List<Techsupport> selectByType(String type);
	
	/**
	 * 查询所有业务未完成的信息
	 */
	List<Techsupport> findAllUnfinished();

	/**
	 * 修改状态
	 * 
	 * @param techsupport_id
	 * @param status_id
	 */
	void updateStatus_id(int techsupport_id, int status_id);

	/**
	 * 修改负责人
	 * 
	 * @param techsupport_id
	 * @param manager_id
	 */
	void updateManager_id(int techsupport_id, int manager_id);

	void updateEndTime(int techsupport_id, String endtime);

	void updateFeedback(int id, String info);

	List<Techsupport> selectfinish(String dateString);

	List<Techsupport> selectUnFinishedTSByMultiInfo(@Param("key") String key,
			@Param("val") String val);

	List<Techsupport> findFeedback(Integer userId);
	//更新业务
	void updateSupport(Techsupport techId);
	//去查询本部门的用户共享的业务
	List<Techsupport> findShareSupport(List<Integer> lists);
	
}