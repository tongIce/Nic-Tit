<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/test.css">
    <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js"></script>
</head>
<body>
<div class="login" style="border: 1px black solid;height: 500px;">
    <form>
        <ul>
            <li style="margin-top: 35px;" class=" input">
                <a href="#" class="icon user"></a>
                <input type="text" class="text" value="姓名" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '姓名';}" >
            </li>
            <li class=" input">
                <a href="#" class=" icon mail"></a>
                <input type="text" class="text" value="电话" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '电话';}" >
            </li>
            <li>
                <a href="#" class=" icon"></a>
                <select  style="width: 95%;" value="民族" >
                    <option>汉族</option><option>壮族</option>
                    <option>藏族</option><option>裕固族</option>
                    <option>彝族</option><option>瑶族</option>
                    <option>锡伯族</option><option>乌孜别克族</option>
                    <option>维吾尔族</option><option>佤族</option>
                    <option>土家族</option><option>土族</option>
                    <option>塔塔尔族 </option><option>塔吉克族</option>
                    <option>水族</option><option>畲族</option>
                    <option>撒拉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                    <option>汉族</option><option>汉族</option>
                </select>
            </li>
            <li>
                <select id="address-first"  value="address-first" >
                    <option selected="selected">山西</option>
                    <option>北京</option>
                </select>
                <select id="address-second"  value="address-second" >
                    <option>运城</option>
                    <option selected="selected">太原</option>
                </select>
            </li>
            <li>
                <div class="form-group form-group-lg">
                    <div class="col-xs-11 clearfix">
                        <a href="javascript:;" class="file">选择文件
                            <input class="js-upload-img-trigger" type="file" name="files" id=""  accept="image/jpeg, image/jpg, image/png, image/git">
                        </a>
                        <div class="pull-left js-upload-img-wrapper">

                            <img class="js-upload-img" src="${pageContext.request.contextPath }/img/default.jpg"
                                 onerror="this.src='${pageContext.request.contextPath }/img/default.jpg'" style="height:100px;width:100px;border:1px black solid;alt:''">
                        </div>
                    </div>
                </div>
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

            $(".js-upload-img-wrapper").html("<img class='js-upload-img' src='"+imgURL+ "' height='100px;'width='100px;'/>");
        })
        $('#address-first').change(function(){
            var x = document.getElementById("address-first");
            var y = document.getElementById("address-second");
            if(x.selectedIndex == 0)
            {

                y.innerHTML = "";
                y.options.add(new Option("运城", "0"));
                y.options.add(new Option("太原", "1", false, true));  // 默认选中省会城市
            }
            if(x.selectedIndex == 1)
            {
                y.innerHTML = "";
                y.options.add(new Option("深圳", "0"));
                y.options.add(new Option("广州", "1", false, true));  // 默认选中省会城市
                y.options.add(new Option("肇庆", "2"));
            }
        })
    </script>
</body>
</html>