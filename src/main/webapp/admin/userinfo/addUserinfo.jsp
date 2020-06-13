<%--
  Created by IntelliJ IDEA.
  User: 26053
  Date: 2020/5/27
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
</head>
<body>
   <form method="post" action="">
       uname<input type="text" id="uname"><br>
       upass<input type="password" id="upass"><br>
       请选择你要的角色：
       <div id="roles">
       </div>
       请选择你的状态:
       <input type="radio" name="isdelete" value="1" checked>正常
       <input type="radio" name="isdelete" value="0">删除<br>
       <button type="button" onclick="dosave()">保存</button>
   </form>
</body>
</html>
<script>
    var flag = true;
    function getQueryVariable(variable) {
        //获取请求路径信息
        var query = window.location.search.substring(1);
        //截取参数
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }
    $(function () {
        findRoles();

        let uid = getQueryVariable("uid");
        if(uid!=false){//查询所有值
            flag =false;
            findById(uid);
        }
    })
    function findById(uid) {
        $.getJSON("findById",{uid:uid},function(json){
            console.log(json);
            $("#uname").val(json.uname);
            $("#upass").val(json.upass);
            if (json.isdelete==true){
                $("input[name=isdelete][value=1]").attr("checked",true);//value=1的radio被选中
            }else{
                $("input[name=isdelete][value=0]").attr("checked",true);//value=0的radio被选中
            }
            $.each(json.roles, function(i,item){
                $("input[name=rids][value="+item.rid+"]").prop("checked",true);//checkbox被选中
            });
        });
    }
    function findRoles() {
        $.getJSON("findRoles",{},function (json) {
            $.each(json, function(i,item){
                $("#roles").append("<input type='checkbox' name='rids' value='"+item.rid+"'>"+item.rolename+"");
            });
        });
    }
    function dosave() {
        var unameVal = $("#uname").val();
        var upassVal = $("#upass").val();
        var  isdeleteVal =  $('input:radio:checked').val();
        //获取选中的复选框的值
        var rids = [];
        $("input[name='rids']:checked").each(function(i){
            rids.push($(this).val())
        })
        rids = rids + "";

        console.log(unameVal+"==="+upassVal+"==="+rids);
        if(rids.length!=0){
            let uid = getQueryVariable("uid");
            if(flag){
                $.post("save",{uname:unameVal,upass:upassVal,rids:rids,isdelete:isdeleteVal},function(){
                    window.location="index.jsp";
                    console.log("success");
                });
            }else{
                $.post("update",{uid:uid,uname:unameVal,upass:upassVal,rids:rids,isdelete:isdeleteVal},function(){
                    window.location="index.jsp";
                    console.log("success");
                });
            }

        }else{
            alert("请选择相应的角色");
        }

    }
</script>
