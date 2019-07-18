<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019-7-16
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function onCheck() {
            var flag=true;
            // 1、验证数据是否合法
            flag = flag && onValidateUsername();
            flag = flag && onValidatePassword();
            flag = flag && onValidateConfPassword();
            flag = flag && onValidateSex();
            flag = flag && onValidatePhone();
            flag = flag && onValidateEmail();
            flag = flag && onValidateAddrs();
           //2.如果合法则提交
            if(flag){
                return true;
            }else {
                return false;
            }
        }
        //验证用户名，不能为空
        function onValidateUsername() {
            var usernameObj = document.getElementsByName("username")[0];
            if(usernameObj.value!=null){
                return true;
            }else{
                alert("用户名不能为空");
                return false;
            }
        }
        //验证密码，不能为空
        function onValidatePassword() {
            var passwordObj = document.getElementById("psw1");
            if(passwordObj.value){
                return true;
            }else{
                alert("密码不能为空");
                return false;
            }
        }
        // 验证确认密码,密码与确认密码必须一致
        function onValidateConfPassword() {
            var psw1Obj = document.getElementById("psw1");
            var psw2Obj = document.getElementById("psw2");
            if(psw1Obj.value!=psw2Obj.value){
                return false;
            }
            return true;
        }
        //验证性别不能为空
        function onValidateSex() {
            var sexObj = document.getElementsByName("sex")[0];
            if(sexObj.value){
                return true;
            }else{
                alert("性别必须选择");
                return false;
            }
        }
        //验证手机号码不为空
        function onValidatePhone() {
            var phoneObj = document.getElementsByName("phone")[0];
            if(phoneObj.value){
                return true;
            }else{
                alert("手机必须填写");
                return false;
            }
        }
        // 验证邮箱格式是否合法
        function onValidateEmail() {
            var emailObj = document.getElementsByName("email")[0];
            if(emailObj.value){
                // 判断是否包含@和.符号
                var index1 = emailObj.value.indexOf("@");
                var index2 = emailObj.value.indexOf(".");
                if(index1==-1 || index2==-1){
                    alert("邮箱格式非法：必须包含@和.符号");
                    return false;
                }
                // @和.符号都不能在首尾字符
                if(index1==0 || index2==0
                    || index1==emailObj.value.length-1
                    || index2==emailObj.value.length-1){
                    alert("邮箱格式非法：@和.符号都不能在首尾字符");
                    return false;
                }
                // @不能在.符号之后
                if(index1>index2){
                    alert("邮箱格式非法：@不能在.符号之后");
                    return false;
                }
                // @和.符号不能相邻
                if(index1==index2-1){
                    alert("邮箱格式非法：@和.符号不能相邻");
                    return false;
                }
                return true;
            }else{
                alert("邮箱不能为空");
                return false;
            }
        }
        //验证地址不为空
        function onValidateAddrs() {
            var addrsObj = document.getElementsByName("addrs")[0];
            if(addrsObj.value){
                return true;
            }else{
                alert("地址必须填写");
                return false;
            }
        }
    </script>
</head>
<body>
<form action="registerServlet" method="post" onsubmit="return onCheck();">
    <table border="1" cellspacing="0">
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" id="psw1"></td>
        </tr>
        <tr>
            <td>确认密码：</td>
            <td><input type="password" name="password2" id="psw2"></td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>
                <select name="sex">
                    <option >  </option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>爱好：</td>
            <td>
                <input type="checkbox" name="hobbys" value="运动">运动
                <input type="checkbox" name="hobbys" value="看电影">看电影
                <input type="checkbox" name="hobbys" value="旅游">旅游
            </td>
        </tr>
        <tr>
            <td>手机号码：</td>
            <td><input type="text" name="phone"></td>
        </tr>
        <tr>
            <td>电子邮箱：</td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td>地址：</td>
            <td><input type="text" name="addrs"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交" >
            </td>
        </tr>
    </table>
</form>

</body>
</html>
