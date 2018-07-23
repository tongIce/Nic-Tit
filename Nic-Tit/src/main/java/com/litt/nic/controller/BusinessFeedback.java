package com.litt.nic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.entity.Department;
import com.litt.nic.entity.Manager;
import com.litt.nic.entity.Status;
import com.litt.nic.entity.Techsupport;
import com.litt.nic.entity.Type;
import com.litt.nic.entity.User;
import com.litt.nic.service.IDepartmentService;
import com.litt.nic.service.IManagerService;
import com.litt.nic.service.IStatusService;
import com.litt.nic.service.ITechSupportService;
import com.litt.nic.service.IUserService;

/**
 * 业务反馈
 * 
 * @author Liar
 * 
 */
@Controller
@RequestMapping(value = "/feedback")

public class BusinessFeedback {

	@Autowired
	private ITechSupportService techSupportService;
	
	@Autowired
	private IStatusService statusService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IManagerService managerService;
	@Autowired
	private IDepartmentService departmentService;

	private List<Techsupport> techsupportList;
	

	private List<Department> departList;
	private List<String> departNameList;

	/**
	 * 加载所有未完成的信息
	 * @throws IOException 
	 */
	@RequestMapping(value = "/unfinishedlist")
	public String loadAllUnfinished(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("执行了未完成加载");
		techsupportList = techSupportService.findAllUnfinished();


		List<Status> listStatus = statusService.findAllStatus();
		request.setAttribute("listStatus", listStatus);

		List<Manager> listManager = managerService.selectAllManager();
		request.setAttribute("listManager", listManager);

		if (techsupportList.isEmpty()) {
			return "/jsp/error/null";

		} else {
			getDPNameList(request);
			getTSLists(request, techsupportList);
			
			request.setAttribute("tsList", techsupportList);
			
			return "/WEB-INF/views/serviceDock/businessFadeback";
		}
	}

	/**
	 * 根据techsupport_id修改状态和处理人
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toUpdateStatus")
	public String updateStatus(HttpServletRequest request,
			HttpServletResponse response) {
		int techsupportId = 0;
		int idnow=0;
		try {
			idnow=Integer.parseInt(request.getParameter("tsid"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("异常");
		}
		
		System.out.println(idnow+"pppppppppppppppp");
		Status status = statusService
				.findByName(request.getParameter("status"));
		Manager manager = managerService.findByName(request
				.getParameter("manager"));
		if(idnow==0){
			System.out.println("*************多天信息********");
				String[] techsupportIdArray = request
						.getParameterValues("techsupportId");
			String info = request.getParameter("content").trim();
			System.out.println(info+"反馈信息");
			if (info.equals("")) {
				System.out.println("全是空格");
			} else {
				System.out.println("不为空吧，要反馈了啊");
			
				addinfo(request,techsupportIdArray,info);
			}
			try {// 技术支持
	
				System.out.println(techsupportIdArray.length + "到底是几个数组");
				for (int i = 0; i < techsupportIdArray.length; i++) {
					techsupportId = Integer.parseInt(techsupportIdArray[i]);
					System.out
							.println("后台获取需要修改的技术支持id数组：" + techsupportIdArray[i]);
					System.out.println("此时的技术支持id为：" + techsupportId);
					Techsupport techsupport = techSupportService
							.findById(techsupportId);
					request.setAttribute("techsupportId", techsupport
							.getTechsupportId().toString());
	
					System.out.println("========");
					String techsupportEndtime = new SimpleDateFormat(
							// @formatter:on
"yyyy-MM-dd HH:mm:ss").format(new Date());
	
					System.out.println(status.getStatusName());
	
					techSupportService.updateEndTime(techsupportId,
							techsupportEndtime);
					System.out.println("更新了时间");
					if (status != null) {
						techSupportService.updateStatus_id(techsupportId,
								status.getStatusId());
						System.out.println("更新了状态");
					}
					if (manager != null) {
						techSupportService.updateManager_id(techsupportId,
								manager.getManagerId());
						System.out.println("更新了处理人");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("技术支持出现异常");
			}
		}else{
			System.out.println("*************单条信息********");
			int techsupportIdArray = idnow;
		String info = request.getParameter("content").trim();
		System.out.println(info+"反馈信息");
		if (info.equals("")) {
			System.out.println("全是空格");
		} else {
			System.out.println("不为空吧，要反馈了啊");
		
			addoneinfo(request,techsupportIdArray,info);
		}
		try {// 技术支持

			
				techsupportId = techsupportIdArray;
				System.out.println("此时的技术支持id为：" + techsupportId);
				Techsupport techsupport = techSupportService
						.findById(techsupportId);
				request.setAttribute("techsupportId", techsupport
						.getTechsupportId().toString());

				System.out.println("========");
				String techsupportEndtime = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(new Date());

				System.out.println(status.getStatusName());

				techSupportService.updateEndTime(techsupportId,
						techsupportEndtime);
				System.out.println("更新了时间");
				if (status != null) {
					techSupportService.updateStatus_id(techsupportId,
							status.getStatusId());
					System.out.println("更新了状态");
				}
				if (manager != null) {
					techSupportService.updateManager_id(techsupportId,
							manager.getManagerId());
					System.out.println("更新了处理人");
				}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("技术支持出现异常");
		}
		
		}
		return "redirect:unfinishedlist";
	}

	/**
	 * 搜索表单，多条件搜索
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/searchLists")
	public String searchByInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Status> listStatus = statusService.findAllStatus();
		request.setAttribute("listStatus", listStatus);

		List<Manager> listManager = managerService.selectAllManager();
		request.setAttribute("listManager", listManager);

		String key = request.getParameter("key");
		String value = request.getParameter("val");
		request.setAttribute("typekey", key);
		request.setAttribute("typeval", value);
		System.out.println("key=" + key + "value=" + value);
		
		if (key != null) {

			if ("业务类型".equals(key)) {
				
			    techsupportList=techSupportService.findByType(value);
			    System.out.println("techsupportList="+techsupportList);
			    getTSLists(request, techsupportList);
			    
			    response.setContentType("text/html; charset=UTF-8"); // 转码
			    PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("$('#btnClose').click(function() { $('#BgDiv').css('display', 'none'); $('#DialogDiv').css('display', 'none'); });$('#btnCancel').click(function() {$('#BgDiv').css('display', 'none');$('#DialogDiv').css('display', 'none');});$('#btnTest').click(function() {$('#BgDiv').css({ display: 'block', height: $(document).height() });var yscroll = document.documentElement.scrollTop;$('#DialogDiv').css('top', '100px').css('display', 'block');})");
				out.println("</script>");
				
			    return "/WEB-INF/views/serviceDock/businessFadeback";
			} else {
				techsupportList = techSupportService
						.findUnFinishedTSByMultiInfo(key, value);
				System.out.println("techsupportList="+techsupportList);
				getTSLists(request, techsupportList);
				
				getDPNameList(request);
				
				 response.setContentType("text/html; charset=UTF-8"); // 转码
				    PrintWriter out = response.getWriter();
					out.flush();
					out.println("<script>");
					out.println("$('#btnClose').click(function() { $('#BgDiv').css('display', 'none'); $('#DialogDiv').css('display', 'none'); });$('#btnCancel').click(function() {$('#BgDiv').css('display', 'none');$('#DialogDiv').css('display', 'none');});$('#btnTest').click(function() {$('#BgDiv').css({ display: 'block', height: $(document).height() });var yscroll = document.documentElement.scrollTop;$('#DialogDiv').css('top', '100px').css('display', 'block');})");
					out.println("</script>");

				return "/WEB-INF/views/serviceDock/businessFadeback";
			} 
		}else {
			System.out.println("空值的情况-----------");
			return loadAllUnfinished(request, response);
		}
	}

	/**
	 * 跳转到反馈信息页面
	 * 
	 * @return
	 * @throws IOException
	 */

	@SuppressWarnings("unused")
	@RequestMapping(value = "/toaddinfo")
	public String toaddinfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		

		String[] techIdArray = request.getParameterValues("techsupportId");

		List<Integer> teachIdList = new ArrayList<Integer>();
		if (techIdArray != null) {
			for (int i = 0; i < techIdArray.length; i++) {

				teachIdList.add(Integer.parseInt(techIdArray[i]));
			}
			request.setAttribute("teachIdList", teachIdList);
		}
		
		if (techIdArray == null) {
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('请选择需要反馈的信息后，再点击反馈');");
			out.println("history.back();");
			out.println("</script>");
			return "redirect:unfinishedlist";
		} else {
			return "/WEB-INF/views/work/feedback_info";
		}
	}

	/**
	 * 提交反馈信息
	 * 
	 */
	@RequestMapping(value = "/addinfo")
	public String addinfo(HttpServletRequest request, String[] techIdArray,
			 String info) {
		
		System.out.println("不为空，并且真该反馈");

		System.out.println(info);
		int techsupportId = 0;
		techIdArray = request.getParameterValues("techsupportId");
		if (techIdArray != null) {
			for (int i = 0; i < techIdArray.length; i++) {
				System.out.println("techId=" + techIdArray[i]);
				techsupportId = Integer.parseInt(techIdArray[i]);

				System.out.println("技术支持-------" + info + techsupportId);
				techSupportService.updateFeedback(techsupportId, info);
			}

		}
		
		return "redirect:unfinishedlist";

	}
	
	/**
	 * 提交单条反馈信息
	 * 
	 */
	@RequestMapping(value = "/addoneinfo")
	public String addoneinfo(HttpServletRequest request, int techIdArray,
			 String info) {
		
		System.out.println("不为空，并且真该反馈");

		System.out.println(info);
		if (techIdArray != 0) {
				System.out.println("techId=" + techIdArray);
				int techsupportId = techIdArray;
				System.out.println("技术支持-------" + info + techsupportId);
				techSupportService.updateFeedback(techsupportId, info);
		}
		
		return "redirect:unfinishedlist";

	}

	/**
	 * 查找今天已完成的任务列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/finishedlist")
	public String finishedlist(HttpServletRequest request,
			HttpServletResponse response) {

		// 获取到当前的年月日,转换成对应的格式
		Date date = new Date();
		System.out.println(date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		System.out.println(dateString.toString());
		techsupportList = techSupportService.findByEnd(dateString);
		
		if (techsupportList.isEmpty() ) {
			return "/jsp/error/null";

		} else {

			getTSLists(request, techsupportList);
			
			request.setAttribute("tsList", techsupportList);
		
			return "/WEB-INF/views/serviceDock/finished";
		}
	}

	public void getTSLists(HttpServletRequest request,
			List<Techsupport> techSupportList) {
		List<String> tsStatusList = new ArrayList<String>();
		List<User> tsUserList = new ArrayList<User>();
		List<String> tsManagerList = new ArrayList<String>();
	    List<String> tsTypeNameList=new ArrayList<String>();
	    List<String> tsDepartNameList=new ArrayList<String>();
		for (int i = 0; i < techSupportList.size(); i++) {

			tsTypeNameList.add(techSupportList.get(i).getType());
			System.out.println("typeName="+tsTypeNameList.get(0));
			
			tsStatusList.add(statusService.findById(
					techSupportList.get(i).getStatusId()).getStatusName());
			
			System.out.println(tsStatusList.get(i)
					+ "ppppppppppppppppppp"
					+ techSupportList.get(i).getStatusId()
					+ "---------"
					+ statusService.findById(
							techSupportList.get(i).getStatusId())
							.getStatusName());

			tsUserList.add(userService.findById(
					techSupportList.get(i).getUserId()));
			if (techSupportList.get(i).getManagerId() != null) {
				System.out.println("处理人==="
						+ managerService.findById(
								techSupportList.get(i).getManagerId())
								.getManagerName());
				tsManagerList
						.add(managerService.findById(
								techSupportList.get(i).getManagerId())
								.getManagerName());
			} else {
				tsManagerList.add("");
			}
		}
		request.setAttribute("tsType", tsTypeNameList);
		request.setAttribute("tsStatus", tsStatusList);
		request.setAttribute("tsList", techSupportList);
		request.setAttribute("tsLen", techSupportList.size());
		request.setAttribute("tsUser", tsUserList);
		request.setAttribute("tsManagerList", tsManagerList);
		
		
	}


	public void getDPNameList(HttpServletRequest request) {
		departNameList=new ArrayList<String>();
		departList = departmentService.findAllInfo();
		for (int i = 0; i < departList.size(); i++) {
			departNameList.add(departList.get(i).getDepartmentName());
		}
		request.setAttribute("dpNameList", departNameList);
	}
}