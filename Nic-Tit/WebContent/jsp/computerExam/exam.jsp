<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/test.css">
    <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js"></script>
<title >计算机等级考试报名</title>
</head>
<body>
	<div class="login" style="border: 1px black solid; width: 95%; height: 1450px; margin-top: 50px;  ">
    <form action="${pageContext.request.contextPath }/exam/signUp" method="post" enctype="multipart/form-data" >
        <ul>
            <li style="margin-top: 25px;" class=" input">
                <a href="#" class="icon user"></a>
                <input type="text"   class="text" style="font-size: 35px;font-weight: bold; color: black" name="name" value="${student.stuName }" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '${student.stuName}';}" >
           		<input type="hidden"  name="openid" value="${student.stuOpenId }" >
            </li>
            <li style="margin-top: 25px;" class=" input">
                <a href="#" class="icon number"></a>
                <input type="text"  class="text" style="font-size: 35px;font-weight: bold;color: black" name="number" value="${student.stuCardNumber }" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '${student.stuCardNumber}';}" >
            </li>
            <li class=" input">
                <a href="#" class=" icon mail"></a>
                <input type="text" class="text" style="font-size: 35px;font-weight: bold; color: black" name="tele" value="${student.stuPhone }" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '${student.stuPhone }';}" >
            </li>
             <li class=" input">
                <a href="#" class=" icon id"></a>
                <input type="text" class="text" style="font-size: 35px;font-weight: bold; color: black" name="ID" value="请填入身份证号" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '身份证号';}" >
            </li>
            <li>
                <a href="#" class=" icon"></a>
                <select  name="nation" style=" border:2px black solid; width: 300px;height: 90px; font-size: 40px;font-weight: bold;"   >
                    <option >汉族</option>
                    <option>蒙古族</option>
                    <option>回族</option>
                    <option>藏族 </option>
                    <option>维吾尔族</option>
                    <option>苗族</option>
                    <option>彝族</option>
                    <option>壮族</option>
                    <option>布依族 </option>
                    <option>朝鲜族</option>
                    <option>满族  </option>
                    <option>侗族</option>
                    <option>瑶族</option>
                    <option>白族 </option>
                    <option>土家族</option>
                    <option>哈尼族</option>
                    <option>傣族 </option>
                    <option>黎族</option>
                    <option>僳僳族 </option>
                    <option>佤族</option>
                    <option>畲族 </option>
                    <option>高山族</option>
                    <option>拉祜族</option>
                    <option>水族</option>
                    <option>东乡族</option>
                    <option>纳西族 </option>
                    <option>景颇族 </option>
                    <option>柯尔克孜族</option>
                    <option>土族</option>
                    <option>达斡尔族</option>
                    <option>仫佬族</option>
                    <option>羌族</option>
                    <option>布朗族</option>
                    <option>撒拉族 </option>
                    <option>毛难族</option>
                    <option>仡佬族</option>
                    <option>锡伯族</option>
                    <option>阿昌族</option>
                    <option>普米族</option>
                    <option>塔吉克族</option>
                    <option>怒族</option>
                    <option>乌孜别克族</option>
                    <option>俄罗斯族</option>
                    <option>鄂温克族</option>
                    <option>崩龙族</option>
                    <option>保安族</option>
                    <option>裕固族</option>
                    <option>京族</option>
                    <option>塔塔尔族</option>
                    <option>独龙族</option>
                    <option>鄂伦春族</option>
                    <option>赫哲族 </option>
                    <option>门巴族</option>
                    <option>珞巴族 </option>
                    <option>基诺族 </option>
                </select>
            </li>
            <li>
            
                <select  name="level" style=" border:2px black solid; width: 300px;height: 90px; font-size: 40px;font-weight: bold;" id="address-first"  value="address-first"  >
                    <option selected="selected" >二级</option>
                    <option>三级</option>
                    <option>四级</option>
                </select>
                <select  name="type" style="  border:2px black solid;width: 300px;height: 90px; font-size: 40px;font-weight: bold;" id="address-second"  value="address-second" >
                    <option selected="selected" value="24">C语言程序设计</option>
                    <option value="26">VB语言程序设计</option>
                    <option value="28">Java语言程序设计</option>
                    <option value="29">Access数据库程序设计</option>
                    <option value="61">C++语言程序设计</option>
                    <option value="63">MySQL数据库程序设计</option>
                    <option value="64">Web程序设计</option>
                    <option value="65">MS Office高级应用</option>
                </select>
            </li>
            <li>
                <div class="form-group form-group-lg">
                    <div class="col-xs-11 clearfix">
                        <a href="javascript:;" style="font-size: 30px;font-weight: bold;" class="file">选择文件
                            <input class="js-upload-img-trigger" type="file" name="files" id=""  accept="image/jpeg, image/jpg, image/png, image/git">
                        </a>
                        <div class="pull-left js-upload-img-wrapper">
                            <img class="js-upload-img" src="${pageContext.request.contextPath }/img/default.jpg"
                                 onerror="this.src='${pageContext.request.contextPath }/img/default.jpg'" style="height:450px;width:450px;border:1px black solid;alt:''">
                        </div>
                    </div>
                </div>
            </li>
            <li > 
            <center>
            	<button   style="width: 160px;height: 80px; border: 2px black solid; font-size:30px;" value="提交"  type="submit">
        		 	提交
        	 	</button>
            </center>
            </li>
        </ul>
        
    </form>

</div>

    <script type="text/javascript">
        $('.js-upload-img-trigger').change(function(event){
            var node = event.target;
            var imgURL = "";
            try{
                var file = null;
                if(node.files && node.files[0] ){
                    file = node.files[0];
                }else if(node.files && node.files.item(0)) {
                    file = node.files.item(0);
                }
                //Firefox 因安全性问题已无法直接通过input[file].value 获取完整的文件路径
                try{
                    imgURL =  file.getAsDataURL();
                }catch(e){
                    imgURL = window.URL.createObjectURL(file);
                }
                console.log(node+"      "+imgURL);
            }catch(e){
                if (node.files && node.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        imgURL = e.target.result;
                    };
                    reader.readAsDataURL(node.files[0]);
                }
            }
            $(".js-upload-img-wrapper").html("<img class='js-upload-img' src='"+imgURL+ "' height='450px;'width='450px;'/>");
        })
        
        
        $('#address-first').change(function(){
            var x = document.getElementById("address-first");
            var y = document.getElementById("address-second");
            if(x.selectedIndex == 0)
            {

                y.innerHTML = "";
                y.options.add(new Option("C语言程序设计", "24", false, true) );  // 默认选中省会城市
                y.options.add(new Option("VB语言程序设计", "26"));
                y.options.add(new Option("Java语言程序设计", "28"));
                y.options.add(new Option("Access数据库程序设计", "29"));
                y.options.add(new Option("C++语言程序设计", "61"));
                y.options.add(new Option("MySQL数据库程序设计", "63"));
                y.options.add(new Option("Web程序设计", "64"));
                y.options.add(new Option("MS Office高级应用", "65"));
            }
            if(x.selectedIndex == 1)
            {
                y.innerHTML = "";
                y.options.add(new Option("网络技术", "35", false, true));  // 默认选中省会城市
                y.options.add(new Option("数据库技术", "36"));
                y.options.add(new Option("信息安全技术", "38"));
                y.options.add(new Option("嵌入式系统开发技术", "39"));
            }
            if(x.selectedIndex == 2)
            {
                y.innerHTML = "";
                y.options.add(new Option("网络工程师", "41", false, true));  // 默认选中省会城市
                y.options.add(new Option("数据库工程师", "42"));
                y.options.add(new Option("信息安全工程师", "44"));
                y.options.add(new Option("嵌入式系统开发工程师", "45"));
            }
        })
    </script>	
	
	
</body>
</html>