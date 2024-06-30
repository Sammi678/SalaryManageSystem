<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2024/6/26
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>财务管理系统</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="${pageContext.request.contextPath}/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
</head>

<body>
<div id="wrapper">

    <!--  加载菜单-->
    <%@include file="inc/menu3.jsp"%>


    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <!-- 上侧导航条开始 -->
            <%@include file="inc/header.jsp"%>
            <!-- 上侧导航条结束 -->
        </div>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-sm-4">
                <h2>财务管理系统</h2>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/login/list?form">Home</a></li>
                </ol>
            </div>
        </div>
        <div class="row border-bottom white-bg dashboard-header">
            <div class="wrapper wrapper-content animated fadeInRight article">
                <div class="row">
                    <div class="col-lg-10 col-lg-offset-1">
                        <div class="ibox">
                            <div class="ibox-content">
                                <!-- 内容增加区域 -->
                                <div>
                                    <img alt="" src="${pageContext.request.contextPath}/static/img/main.jpg">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 底部显示 -->
        <div class="footer">
            <div class="pull-right">
                <strong>财务管理系统</strong>
            </div>
        </div>
    </div>
</div>

<!-- Mainly scripts -->
<script src="${pageContext.request.contextPath}/static/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>

<!-- Custom and plugin javascript -->
<script src="${pageContext.request.contextPath}/static/js/inspinia.js"></script>
<script src="${pageContext.request.contextPath}/static/js/plugins/pace/pace.min.js"></script>

<!-- 弹出对话框 -->
<script src="${pageContext.request.contextPath}/static/js/plugins/sweetalert/sweetalert.min.js"></script>

<script>
    $('body.canvas-menu .sidebar-collapse').slimScroll({
        height: '100%',
        railOpacity: 0.9
    });
</script>
</body>
</html>