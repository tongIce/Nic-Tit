package com.litt.wechat.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.litt.nic.entity.Department;
import com.litt.nic.entity.User;
import com.litt.nic.service.IDepartmentService;
import com.litt.nic.service.IUserService;
import com.litt.wechat.Util.GetUserInfo.GetUserInfo;
import com.litt.wechat.Util.Token.WeixinUtil;

/**
 * 用户身份信息
 * @author Lenovo
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IDepartmentService departmentService;
	
	private User user;
	
	@RequestMapping(value = "/load")
	public String loadWorkJsp(String code) {
		System.out.println("code------>" + code);
		String openid = WeixinUtil.getOpenid(code);

		return "redirect:/user/loadInfo?openid=" + openid;
	}

	/**
	 * 加载完善用户信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/loadInfo")
	public String loadInfo(HttpServletRequest request,
			HttpServletResponse response, String openid) throws IOException {
		
		System.out.println("--------" + openid);
		request.setAttribute("openid", openid);
		// 加载页面
		List<Department> listDepartment = departmentService.findAllInfo();
		/*for (Department department : listDepartment) {
			System.out.println(department.getDepartmentName());
		}*/
		request.setAttribute("listDepartment", listDepartment);
		User DataBaseUser = userService.findByOpenid(openid);
		// 数据库已存在此人
		if (DataBaseUser != null) {
			System.out.println(DataBaseUser.getUserName());
			request.setAttribute("dbuser", DataBaseUser);
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			/*out.println("<script>");
			out.println("alert('此用户名已存在,请点击确定更改信息');");
			out.println("history.back();");
			out.println("</script>");
			out.flush();*/
			System.out.println("要返回值了");
			
			return "/jsp/user_updateinfo";
		}
		request.setAttribute("openid", openid);
		return "/jsp/user_info";
	}

	/**
	 * 搜索全部信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/adduser")
	public String addUser(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {

		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		String depart = request.getParameter("department");
		System.out.println("depart----" + depart);
		if ("0".equals(depart) || "".equals(name) || "".equals(telephone)) {

			if ("0".equals(depart)) {
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("alert('请选择所属部门！');");
				out.println("history.back();");
				out.println("</script>");
			} else if ("".equals(name)) {
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("alert('请输入您的姓名！');");
				out.println("history.back();");
				out.println("</script>");
			} else if ("".equals(telephone)) {
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("alert('请输入您的联系电话！');");
				out.println("history.back();");
				out.println("</script>");
			}

		} else {
			String openid = request.getParameter("openid");
			System.out.println("openid=" + openid);
			User DataBaseUser = userService.findByOpenid(openid);
			// 数据库已存在此人
			if (DataBaseUser != null) {
				System.out.println(DataBaseUser.getUserName());
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				DataBaseUser.setUserName(name);
				DataBaseUser.setUserDepartment(depart);
				DataBaseUser.setUserTelephone(telephone);
				userService.updateUser(DataBaseUser);
				
				out.println("<script>");
				out.println("alert('更新信息成功');");
				out.println("</script>");
				out.flush();
				request.setAttribute("openid", openid);
				return "/jsp/work_info";
				
			} else {
				user = GetUserInfo.getUserInfo(WeixinUtil.getAccessToken()
						.getAccessToken(), openid);
				user.setUserName(request.getParameter("name"));
				user.setUserTelephone(request.getParameter("telephone"));
				user.setUserDepartment(request.getParameter("department"));
				
				System.out.println("OpenID：" + user.getUserOpenid());
				System.out.println("关注状态：" + user.getUserSubscribe());
				System.out.println("关注时间：" + user.getUserSubscribetime());
				System.out.println("昵称：" + user.getUserNickname());
				System.out.println("性别：" + user.getUserSex());
				System.out.println("国家：" + user.getUserCountry());
				System.out.println("省份：" + user.getUserProvince());
				System.out.println("城市：" + user.getUserCity());
				System.out.println("语言：" + user.getUserLanguage());
				System.out.println("头像：" + user.getUserHeadimgurl());
				// 添加微信用户到数据库
				userService.addUser(user);
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('添加成功！');");
				// out.println("history.back();");
				out.println("</script>");
				out.flush();
				request.setAttribute("openid", openid);
				return "/jsp/work_info";
			}
		}
		System.out.println("name=" + name + "tele" + telephone);
		return null;
	}

	
	
}
