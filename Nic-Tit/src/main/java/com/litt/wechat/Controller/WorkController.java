package com.litt.wechat.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.ParseException;
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

import net.sf.json.JSONArray;
@Controller
@RequestMapping(value = "/work")
public class WorkController {
	@Autowired
	private IUserService userService;
	@Autowired
	private ITechSupportService techSupportService;
	@Autowired
	private IManagerService managerService;
	@Autowired
	private IStatusService statusService;
	private User user = null;
	
	private List<Techsupport> techsupports = null;
	
	/**
	 * 判断微信用户是否绑定个人信息
	 */
	@RequestMapping("/loadWork")
	public String loadWokeJsp(HttpServletRequest request,
			HttpServletResponse response, String code) throws IOException {
		String openid = WeixinUtil.getOpenid(code);
		System.out.println("---------------------------------------");
		request.setAttribute("openid", openid);
		User DataBaseUser = userService.findByOpenid(openid);
		if (DataBaseUser == null) {
			response.setContentType("text/html; charset=UTF-8"); // 转码
			//可以先跳转后提示信息
			/*PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('请完善个人信息后再提交相关业务信息！');");
			// out.println("history.back();");
			out.println("</script>");*/
			return "forward:/user/loadInfo?openid=" + openid;
		} else {
			request.setAttribute("openid", openid);
			return "/jsp/work_info";
		}
	}
	/**
	 * 对已经提交的事务，修改内容
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping("/updatework")
	public String updateWork(HttpServletRequest request,HttpServletResponse response,
		Integer techsupportId, String worktype,String description,String location,String devicename) throws Exception{
		String pictureName = request.getParameter("filename");
		//传递openid，继续业务服务
		String openid = request.getParameter("openid");
		request.setAttribute("openid", openid);
		System.out.println("这是openID=$$$$$$$$$$$$$$$$$$$$$$"+openid);
		
		//根据id查询到服务
		Techsupport techId = techSupportService.findById(techsupportId);
		System.out.println(techId.getTechsupportDepartment()+"+++++++++++++++++++++++++++++");
		
		if ("0".equals(worktype) || "".equals(description) || "".equals(location)
				|| "".equals(devicename)) {

			if ("0".equals(worktype)) {
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("alert('请选择业务类型！');");
				out.println("history.back();");
				out.println("</script>");
			} else if ("".equals(description)) {
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("alert('请填写具体描述！');");
				out.println("history.back();");
				out.println("</script>");
			} else if ("".equals(location)) {
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("alert('请填写具体地点！');");
				out.println("history.back();");
				out.println("</script>");
			} else if ("".equals(devicename)) {
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("alert('请输入设备名称！');");
				out.println("history.back();");
				out.println("</script>");
			}
		} else {
			//根据techid，查询出业务并进行更新
			
			techId.setType(worktype);
			techId.setTechsupportDescribe(description);
			techId.setTechsupportLocation(location);
			techId.setTechsupportDevicename(devicename);
			String uptime = new SimpleDateFormat("yyyy-MM-dd HH:mm")
					.format(new Date());
			techId.setTechsupportUptime(uptime);
			techId.setTechsupportEndtime(uptime);
			// 只保存了图片的名字
			if ("null".equals(pictureName)) {
				System.out.println("用户没提交照片");
			} else {
				techId.setTechsupportPicture(pictureName);
			}
			techSupportService.updateSupport(techId);
			// 转码
			/*response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('我们已经收到您的请求，请耐心等待处理结果！');");
			out.println(" window.location.href='${ pageContext.request.contextPath }/work/showmsg?openid= ' "+ openid+" ; ");
			out.println("</script>");
			out.flush();
			out.close();*/
			return "redirect:/work/showmsg?openid=" + openid;
		}
		return null;
	}
	/**
	 * 进行提交业务
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addwork")
	public String addWork(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		String pictureName = request.getParameter("filename");
		System.out.println("文件名字=" + pictureName);
		// 获取当前提交信息的联系人
		String openid = request.getParameter("openid");
		System.out.println("openid" + openid);
		user = userService.findByOpenid(openid);
		System.out.println(user.getUserName() + "===================");
		String uptime = new SimpleDateFormat("yyyy-MM-dd HH:mm")
				.format(new Date());
		System.out.println("当前时间是：" + uptime);
		// 获取表单信息
		String type = request.getParameter("worktype");
		String desc = request.getParameter("description");
		String location = request.getParameter("location");
		String devicename = request.getParameter("devicename");
		if ("0".equals(type) || "".equals(desc) || "".equals(location)
				|| "".equals(devicename)) {

			if ("0".equals(type)) {
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("alert('请选择业务类型！');");
				out.println("history.back();");
				out.println("</script>");
			} else if ("".equals(desc)) {
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("alert('请填写具体描述！');");
				out.println("history.back();");
				out.println("</script>");
			} else if ("".equals(location)) {
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("alert('请填写具体地点！');");
				out.println("history.back();");
				out.println("</script>");
			} else if ("".equals(devicename)) {
				response.setContentType("text/html; charset=UTF-8"); // 转码
				PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("alert('请输入设备名称！');");
				out.println("history.back();");
				out.println("</script>");
			}
		} else {
			// 提交信息
			System.out.println(user.getUserDepartment());
			Techsupport techsupport = new Techsupport();
			techsupport.setTechsupportDepartment(user.getUserDepartment());
			techsupport.setTechsupportDescribe(desc);
			techsupport.setTechsupportLocation(location);
			techsupport.setStatusId(1);
			techsupport.setTechsupportDevicename(devicename);
			techsupport.setTechsupportUptime(uptime);
			techsupport.setTechsupportEndtime(uptime);
			//没有关联user对象，直接存userid
			techsupport.setUserId(user.getUserId());
			techsupport.setType(type);
			// 只保存了图片的名字
			if ("null".equals(pictureName)) {
				System.out.println("用户没提交照片");
			} else {
				techsupport.setTechsupportPicture(pictureName);
			}
			techSupportService.addtech(techsupport);

			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('我们已经收到您的请求，请耐心等待处理结果！');");
			out.println("</script>");
		}
		request.setAttribute("openid", openid);
		return "/jsp/work_info";

	}

	/**
	 * 校验js-sdk
	 * @param request
	 * @param response
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping("/config")
	public void getConfig(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		response.setCharacterEncoding("UTF-8");
		// 获取appId
		String appId = WeixinUtil.getAppid();
		// GlobalConstants.getInterfaceUrl("appid");
		System.out.println(appId);
		// 获取页面路径(前端获取时采用location.href.split('#')[0]获取url)
		String url = request.getParameter("url");
		System.out.println("url=" + url);
		// 获取access_token
		String access_token = WeixinUtil.getAccessToken().getAccessToken();
		System.out.println("access_token=" + access_token);
		// 获取ticket
		String jsapi_ticket = WeixinUtil.getTickect(access_token);
		System.out.println("jsapi_ticket=" + jsapi_ticket);
		// 获取Unix时间戳(java时间戳为13位,所以要截取最后3位,保留前10位)
		String timestamp = String.valueOf(System.currentTimeMillis())
				.substring(0, 10);
		String noncestr = UUID.randomUUID().toString();
		System.out.println(timestamp + "noncestr=" + noncestr);
		// 创建有序的Map用于创建签名串
		SortedMap<String, String> params = new TreeMap<String, String>();
		params.put("jsapi_ticket", jsapi_ticket);
		params.put("timestamp", timestamp);
		params.put("nonceStr", noncestr);
		params.put("url", url);
		// 注意这里参数名必须全部小写，且必须有序
		// String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" +
		// noncestr + "&timeStamp=" + timestamp + "&url=" + url;
		// 签名
		String signature = WeixinUtil.getSignature(jsapi_ticket, timestamp,
				noncestr, url);
		System.out.println(signature);
		// 得到签名再组装到Map里
		params.put("signature", signature);
		// 传入对应的appId
		params.put("appId", appId);
		// 组装完毕，回传
		try {
			JSONArray jsonArray = JSONArray.fromObject(params);
			// System.out.println(jsonArray.toString());
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("js-sdk配置错误");
		}
	}
	/**
	 * 保存图片
	 * @param request
	 * @param response
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping("/savePicture")
	public void savePicture(HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		String mediaId = request.getParameter("mediaId");
		System.out.println("mediaId=" + mediaId);
		// 保存图片 路径 PathKit.getWebRootPath() + "/vehicleupload/" + filename;
		String filename = WeixinUtil.saveImageToDisk(request, mediaId);
		// 打印文件名
		System.out.println(filename);
		// 返回jquery 参数
		response.getWriter().print(filename);
	}
	/**
	 * 反馈信息
	 * @param request
	 * @param response
	 * @param code
	 * @return
	 */
	@RequestMapping("/loadCheck")
	public String loadCheckJsp(HttpServletRequest request,
			HttpServletResponse response, String code) {
		//接受获取网页授权的token的code
		String openid = WeixinUtil.getOpenid(code);
		System.out.println("--------");
		return "redirect:/work/showmsg?openid=" + openid;

	}
	/**
	 * 查看反馈消息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/showmsg")
	public String showmsg(HttpServletRequest request,
			HttpServletResponse response, String openid) throws Exception {
		System.out.println("这是Showmsg方法");
		System.out.println("openid===123======" + openid);
		//request.getSession().setAttribute("openid", openid);
		request.setAttribute("openid", openid);
		user = userService.findByOpenid(openid);
		if (user != null) {
			//userid放到request域中，备修改和撤回使用
			request.setAttribute("userId", user.getUserId());
			System.out.println("user=========================="
					+ user.toString());
			// 根据用户id、未完成状态id 查找该用户提交并且有反馈的业务
			techsupports = techSupportService.findFeedback(user.getUserId());
			// 根据状态id查找对应的状态名称
			if (techsupports.isEmpty()) {
				return "/jsp/error/feedbacknull";

			} else {
				getTSLists(request, techsupports);
				// 显示信息（密码提交的业务处于什么状态+反馈内容）
				request.setAttribute("techlist", techsupports);
				return "/jsp/showmsg_info";
			}
		} else {
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('请完善个人信息后再查看相关信息！');");
			// out.println("history.back();");
			out.println("</script>");
			return "forward:/user/loadInfo?openid=" + openid;
		}

	}
	/**
	 * 在mapper文件中没有关联，分部进行查询放到request域中
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
	
}
