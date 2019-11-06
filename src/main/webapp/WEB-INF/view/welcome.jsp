<%--
  Created by IntelliJ IDEA.
  User: huang
  Date: 2019/7/17
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //  System.out.println(basePath);
%>
<html>
<head>

    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">

    function download() {
        $.ajax({
            url:"${pageContext.request.contextPath}/download",
            type: "GET",
            async: false,
            success: function (args) {

            }
        })
    }
</script>
<body>

           <h1>hello world!!</h1>

          <div>
              <button onclick="download()">下载</button>
          </div>
</body>
</html>
