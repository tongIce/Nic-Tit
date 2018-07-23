package com.litt.micro.util.prompt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

//在后台逻辑出来之后，将提示信息alert到前台
public class HintoFront {	

    /** 
     * 优点：
     	1、调用简单，不用修改前端页面，更改代码少
 		2、刷新不会重新提交
 		3、跳转后的页面再刷新不会重新alert提示
     * @param msg   alert提示信息 
     * @param redirectUrl  重定向url，如果为空，则返回到操作当前页 
     */  
    public static void alertMsg(String msg,String redirectUrl,HttpServletResponse response){  
        if(msg==null||"".equals(msg.trim())){  
            return ;  
        }  
        PrintWriter out=null;   
         try {   
         //设置回发内容编码     
        	response.setContentType("text/html; charset=UTF-8"); // 转码
 			out = response.getWriter();
         } catch (IOException e) {   
              e.printStackTrace();   
         }   
         StringBuilder sb=new StringBuilder();  
         sb.append("<script>alert('" +msg+"！');");  
         if(redirectUrl==null||"".equals(redirectUrl.trim())){  
             sb.append("history.go(-1);");  
         }else{  
             sb.append("location='"+redirectUrl+"';");  
         }  
         sb.append("</script>");  
         out.print(sb.toString());  
         out.flush();  
         out.close();  
    }  
	
	
}
