package com.litt.wechat.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.entity.Manager;
import com.litt.nic.entity.Techsupport;
import com.litt.nic.entity.User;
import com.litt.nic.service.IManagerService;
import com.litt.nic.service.IStatusService;
import com.litt.nic.service.ITechSupportService;
import com.litt.nic.service.IUserService;
import com.litt.wechat.Util.Token.WeixinUtil;

/**
 * 处理反馈信息
 * @author Lenovo
 *
 */
@Controller
@RequestMapping("/handle")
public class FeedbackController {
	@Autowired
	private ITechSupportService techSupportService;
	@Autowired
	private IManagerService managerService;
	@Autowired
	private IStatusService statusService;
	@Autowired
	private IUserService userService;
	private User user = null;
	private List<Techsupport> techsupports = null;
	
	@RequestMapping("shareCheck")
	public String shareCheck(HttpServletRequest request,
			HttpServletResponse response, String code) throws IOException{
		//通过code获得openid
		String openid = WeixinUtil.getOpenid(code);
		request.setAttribute("openid", openid);
		user = userService.findByOpenid(openid);
		if (user != null) {
			/**
			 * 1、查询本user部门的所有共享信息,根据用户部门类别
			 * 2、查询所有业务信息，为全校共享的信息
			 * 3、第一步首先查询用户的本部共享信息
			 */
			int share = 2;
			List<User> users = userService.findUsersByDepart(user.getUserDepartment(),share);
			List<Integer> lists= new ArrayList<Integer>();
			Iterator<User> uiterator = users.iterator();
			while (uiterator.hasNext()) {
				User user = (User) uiterator.next();
				if(user.getShareType().getShareType().equals("本部共享")){
					lists.add(user.getUserId());
				}
			}
			//去查询本部门的用户共享的业务
			 List<Techsupport> techsupports=techSupportService.findShareSupport(lists);
			 /**
				 * 第二大步工作：查询出学院共享信息
				 * 	先查找用户全院共享，根据用户id查找出业务
				 */
					/*Integer share_type =3;
					List<User> userlist = userService.findUsersByShareType(share_type);
					List<Integer> list= new ArrayList<Integer>();
					Iterator<User> ushareiterator = userlist.iterator();
					while (ushareiterator.hasNext()) {
						User user = (User) ushareiterator.next();
						if(user.getShareType().getShareType().equals("本院共享")){
							list.add(user.getUserId());
						}
					}
				//查找出全院共享业务
					List<Techsupport> sharesupport = techSupportService.findShareSupport(list);*/
			 
			if (techsupports.isEmpty()/*&&sharesupport.isEmpty()*/) {
				return "/jsp/error/sharefeedbacknull";
			} else 
				{
				getTSLists(request, techsupports);
				//gettsLists(request, sharesupport);
				//request.setAttribute("sharelist", sharesupport);
				request.setAttribute("techlist", techsupports);
				return "/jsp/sharemsg_info";
			}
			
		} else {
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('请完善个人信息后再查看相关信息！');");
			out.println("</script>");
			return "forward:/user/loadInfo?openid=" + openid;
		}
	}
	/**
	 * 中转进入到，业务回显页面
	 * @return
	 */
	@RequestMapping("update")
	public String update(HttpServletRequest request,HttpServletResponse response,
		String worktype,Integer techsupportId,
		String openid,String techsupportDevicename ,String techsupportLocation,String techsupportDescribe ){
		System.out.println("进入update页面");
		System.out.println(openid+techsupportDescribe+"----------------------------------");
		//得到用户需要修改的内容，回显到提交业务页面
		request.setAttribute("openid", openid);
		Techsupport tech =new Techsupport();
		request.setAttribute("worktype", worktype);
		tech.setTechsupportId(techsupportId);
		tech.setTechsupportDevicename(techsupportDevicename);
		tech.setTechsupportDescribe(techsupportDescribe);
		tech.setTechsupportLocation(techsupportLocation);
		request.setAttribute("support", tech);
		System.out.println("这是维修类型：++++++++++++++++++++++++++++++++++++++++++++++++"+worktype);
		
		return "/jsp/work_updateinfo";
	}
	/**
	 * 撤回业务处理
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("withdraw")
	public String withdraw(HttpServletRequest request,HttpServletResponse response,
			Integer techsupportId,Integer userId) throws IOException{
		System.out.println("接收到的事务id=========="+techsupportId);
		System.out.println("接收到的userid=========="+userId);
		//数据库查询，是否该业务已经被接受
		Techsupport findById = techSupportService.findById(techsupportId);
		if(findById.getStatusId()!=1){
				//转码
			    response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('业务已被接收，不能更改');");
				out.println("history.back();");
				out.println("</script>");
				out.flush();
				out.close();
				
		}else{
			//点击撤回，弹出确定按钮,删除业务。
			techSupportService.deleteSupport(techsupportId);
			// 根据用户id、未完成状态id 查找该用户提交
			techsupports = techSupportService.findFeedback(userId);
			// 根据状态id查找对应的状态名称
			if (techsupports.isEmpty()) {
				return "/jsp/error/feedbacknull";
			} else {
				getTSLists(request, techsupports);
				// 显示信息（密码提交的业务处于什么状态+反馈内容）
				request.setAttribute("techlist", techsupports);
				return "/jsp/showmsg_info";
			}
		}
		return null;
	}
	/**
	 * 因为没有级联，单独在查找出来业务处理状态和处理人
	 * @param request
	 * @param techSupportList
	 */
	public void getTSLists(HttpServletRequest request,
			List<Techsupport> techSupportList) {
		List<String> tsStatusList = new ArrayList<String>();
		List<Manager> tsManagerList = new ArrayList<>();
		for (int i = 0; i < techSupportList.size(); i++) {
			tsStatusList.add(statusService.findById(
					techSupportList.get(i).getStatusId()).getStatusName());

			if (techSupportList.get(i).getManagerId() != null) {
				System.out.println("处理人==="
				+ managerService.findById(techSupportList.get(i).getManagerId()));
				tsManagerList.add(managerService.findById(techSupportList.get(i).getManagerId()));
			} else {
				tsManagerList.add(null);
			}
		}
		request.setAttribute("tsStatus", tsStatusList);
		request.setAttribute("tsLen", techSupportList.size());
		request.setAttribute("tsManagerList", tsManagerList);
	}
	/**
	 * 因为没有级联，单独在查找出来业务处理状态和处理人,都显示在showmsg页面
	 * @param request
	 * @param techSupportList
	 */
	public void gettsLists(HttpServletRequest request,
			List<Techsupport> techSupportList) {
		List<String> tsStatusList = new ArrayList<String>();
		List<Manager> tsManagerList = new ArrayList<>();
		for (int i = 0; i < techSupportList.size(); i++) {
			tsStatusList.add(statusService.findById(
					techSupportList.get(i).getStatusId()).getStatusName());

			if (techSupportList.get(i).getManagerId() != null) {
				System.out.println("处理人==="
				+ managerService.findById(techSupportList.get(i).getManagerId()));
				tsManagerList.add(managerService.findById(techSupportList.get(i).getManagerId()));
			} else {
				tsManagerList.add(null);
			}
		}
		request.setAttribute("status", tsStatusList);
		request.setAttribute("len", techSupportList.size());
		request.setAttribute("managerList", tsManagerList);
	}
	
}
