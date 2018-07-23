package com.litt.nic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.litt.nic.entity.Department;
import com.litt.nic.entity.Techsupport;
import com.litt.nic.service.IDepartmentService;
import com.litt.nic.service.IManagerService;
import com.litt.nic.service.IStatusService;
import com.litt.nic.service.ITechSupportService;
import com.litt.nic.service.IUserService;

/**
 * 业务对接
 * 
 * @author ljx
 * 
 */
@Controller
@RequestMapping(value = "/serviceDock")
@SessionAttributes("manag")
public class ServiceDockController {

	@Autowired
	private ITechSupportService techSupportService;
	
	@Autowired
	private IStatusService statusService;
	@Autowired
	private IManagerService managerService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IDepartmentService departmentService;

	private List<Techsupport> techSupportList;
	

	List<String> tsStatusList = new ArrayList<String>();
	List<String> tsManagerList = new ArrayList<String>();
	List<String> tsUserList = new ArrayList<String>();

	List<String> rpStatusList = new ArrayList<String>();
	List<String> rpManagerList = new ArrayList<String>();
	List<String> rpUserList = new ArrayList<String>();

	List<String> mtStatusList = new ArrayList<String>();
	List<String> mtManagerList = new ArrayList<String>();
	List<String> mtUserList = new ArrayList<String>();

	List<Department> departList = new ArrayList<Department>();
	List<String> departNameList = new ArrayList<String>();

	/**
	 * 搜索全部信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "/showList")
	public String ShowServiceList(HttpServletRequest request,
			HttpServletResponse response) {
		departList = departmentService.findAllInfo();

		techSupportList = techSupportService.findAllTS();
		
		if (techSupportList.isEmpty()) {
			return "/jsp/error/null";

		} else {
			getDPNameList(request, departList);
		
			getTSLists(request, techSupportList);
			return "/WEB-INF/views/serviceDock/serviceDockList";
		}
	}

	/**
	 * 搜索表单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/searchLists")
	public String searchByInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String key = request.getParameter("key");
		String value = request.getParameter("val");
		System.out.println("key=" + key + "value=" + value);
		if (key != null) {
			// key是否匹配给定的正则表达式
			if (key.equals("service")) {
				
				return "/WEB-INF/views/serviceDock/serviceDockList";
			} else {

				techSupportList = techSupportService
						.findByMutilInfo(key, value);
				getTSLists(request, techSupportList);
				
				return "/WEB-INF/views/serviceDock/serviceDockList";

			}
			
		} else {
			return ShowServiceList(request, response);
		}
	}

	public void getTSLists(HttpServletRequest request,
			List<Techsupport> techSupportList) {
		for (int i = 0; i < techSupportList.size(); i++) {
			tsStatusList.add(statusService.findById(
					techSupportList.get(i).getStatusId()).getStatusName());
			tsManagerList.add(managerService.findById(
					techSupportList.get(i).getManagerId()).getManagerName());
			tsUserList.add(userService.findById(
					techSupportList.get(i).getUserId()).getUserName());
		}
		System.out.println("tsList=" + techSupportList);
		request.setAttribute("tsStatus", tsStatusList);
		request.setAttribute("tsManager", tsManagerList);
		request.setAttribute("tsList", techSupportList);
		request.setAttribute("tsUser", tsUserList);
		request.setAttribute("tsLen", techSupportList.size());
	}

	public void getDPNameList(HttpServletRequest request,
			List<Department> departList) {
		departNameList.clear();
		for (int i = 0; i < departList.size(); i++) {
			departNameList.add(departList.get(i).getDepartmentName());
		}
		request.setAttribute("dpNameList", departNameList);
	}

}
