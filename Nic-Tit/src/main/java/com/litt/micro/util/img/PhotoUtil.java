package com.litt.micro.util.img;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.enterprise.deploy.spi.TargetModuleID;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.litt.micro.entity.Student;

//上传图片到服务器
public class PhotoUtil {
	 /**
    * 功能描述   保存图片
    * @param filedata 文件数据
    *　　返回图片位置
    */
   public static String saveFile( MultipartFile filedata, Student student, HttpServletRequest request) {
	   // 根据配置文件获取服务器图片存放路径
       /*String pathval = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/";
       String newFileName = String.valueOf( System.currentTimeMillis());
       String saveFilePath = "images/uploadFile";*/
       //创建新的图片路径,一个班级，添加到一个文件夹
       String num = student.getStuCardNumber();
       String team= num.substring(0, 7);
       // 保存图片路径
		String targetFile = System.getProperty("catalina.home") + "/webapps"
				+ "/upload" + "/studentImg"+"/"+team+"/";
       /* 构建文件目录 */
       File fileDir = new File(targetFile);
       if (!fileDir.exists()) {
           fileDir.mkdirs();
       }
       //构建新的文件名
        String newName = student.getStuName(); 
       //上传的文件名
       String filename=filedata.getOriginalFilename();
       //文件的扩张名
       String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
       try {
           String imgPath = targetFile + newName + "." +extensionName;
           FileOutputStream out = new FileOutputStream(imgPath);
           // 写入文件
           out.write(filedata.getBytes());
           out.flush();
           out.close();
           return imgPath;
       } catch (Exception e) {
    	   System.out.println("写入图片发生错误！！！");
       }
       return null;
   }

   /**
    * 功能描述：删除图片
    * @param oldPic
    *
    */
   private void deleteFile(String oldPic) {
       // TODO Auto-generated method stub

       /* 构建文件目录 */
       File fileDir = new File(oldPic);
       if (fileDir.exists()) {
           //把修改之前的图片删除 以免太多没用的图片占据空间
           fileDir.delete();
       }

   }
}
