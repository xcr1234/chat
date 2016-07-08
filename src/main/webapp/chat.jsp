<%@ page language="java" import="java.util.*,com.oraclewdp.chat.commons.*" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute(FieldNames.sessionLogin);
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>


    <head>
        <meta charset="UTF-8">

        <title>简易聊天室系统</title>
        <script src="plugins/jquery/jquery.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="plugins/base64/base64.js" type="text/javascript"></script>
        <script>
            $(function () {
                
                var iframe = document.createElement("iframe");
                iframe.id = "comet-frame";
                iframe.onload = iframeRefresh;
                iframe.style.display="none";
                $("body").prepend(iframe);
              
                
                iframeRefresh();
                $("input:radio").click(function(){
                     input_click.call(this);
                    
                });
            });
            function update(data) {
                data = Base64.decode(data);
                if (console && console.log) {
                    console.log(data);
                }
                var json = JSON.parse(data);
                handle(json);
            }
            function handle(json) {
                if (json.type == 1) {
                    var li = document.createElement("li");
                    var cb = document.createElement("input");
                    $(cb).attr("type", "radio").val(json.target).data("usr",json.target).data("nick",json.content).click(function(){
                        input_click.call(this);
                    });
                    $(li).addClass("logined-check").prepend(cb).append(json.content);
                    $("#allone").append(li);
                } else if (json.type == 2) {
                    $(".logined-check").each(function () {
                        if ($(this).find("input:radio").val() == json.target) {
                            $(this).remove();
                        }
                    });
                }else if(json.type==3 || json.type==4){
                    append_msg(json.content);
                }
            }
            function iframeRefresh() {
                var iframe1 = document.getElementById("comet-frame");
                iframe1.src = "chat.comet?"+Math.random();
            }
            
            function input_click(){
                $("#target").text($(this).data("nick"));
                $("#target_val").val($(this).data("usr"));
            }
            function send_message(){
                var target = $("#target_val").val();
                var content = $("#text").val();
                $.ajax({
                    method:"post",
                    url:"send.do",
                    timeout:5000,
                    data:{
                        target:target,
                        content:content
                    }
                });
                if(target!=""){
                    append_msg("你说："+content);
                }
                
            }
            function append_msg(msg){
                var li = document.createElement("li");
                $(li).text(msg);
                $("#msg_history").append(li);
            }
        </script>


        <style type="text/css">
            ul {
                list-style: none;
            }
            ul.left li {
                float: left;
                padding-right: 10px;
            }
        </style>
    </head>

    <body>
        
        <p>欢迎你，<%=user.getNick()%></p>
        <div>
            <ul id="msg_history">
                
            </ul>
        </div>
        <p>&nbsp;</p>
        <div>
            <ul class="left" id="allone">
                <li><input type="button" value="给所有人" onclick="$('#target_val').val('')"></li>
                    <%
                        //获取所有在线的人
                        Set<User> set = (Set<User>) application.getAttribute(FieldNames.allLogined);
                        
                        for (User u : set) {
                            
                            if (!u.getUsr().equals(user.getUsr())) {
                                
                    %>
                <li  class="logined-check">
                    <input type="radio" value="<%=u.getUsr()%>" data-usr="<%=u.getUsr()%>" data-nick="<%=u.getNick()%>"><%=u.getNick()%>
                </li>
                <%}
                    }%>
            </ul>
        </div>
        <p>&nbsp;</p>
        <div>
            对<span id="target">所有人</span>说:<input type="text" name="text" id="text" /> <input type="button" value="发送" onclick="send_message()"/>
            <input type="button" value="退出" onclick="location.href = 'logout.do'">
            <input id="target_val" name="target_val" type="hidden" value="">

        </div>
    </body>

</html>
