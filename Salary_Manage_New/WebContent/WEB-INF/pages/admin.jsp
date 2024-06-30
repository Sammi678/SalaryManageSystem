<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

    <nav class="navbar navbar-static-top  " role="navigation"
         style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
               href="#"><i class="fa fa-bars"></i> </a>
            <form role="search" class="navbar-form-custom"
                  action="search_results.html">
                <div class="form-group">
                    <input type="text" placeholder="Menu" class="form-control"
                           name="top-search" id="top-search" disabled="disabled">
                </div>
            </form>
        </div>
        <ul class="nav navbar-top-links navbar-right">
            <li><span class="m-r-sm text-muted welcome-message">财务管理系统</span>
            </li>
            <%--		<li class="dropdown"><a class="dropdown-toggle count-info"--%>
            <%--			data-toggle="dropdown" href="main.html#"> <i class="fa fa-bell"></i>--%>
            <%--				<span>${sessionScope.userName }</span>--%>
            <%--		</a></li>--%>

            <li><a href="${pageContext.request.contextPath }/login/list?form"> <i class="fa fa-sign-out"></i> 退出
            </a></li>
        </ul>

    </nav>
    <!-- 超级大表单 -->
    <div class="wrapper wrapper-content animated fadeInRight article">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <div class="ibox">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="ibox float-e-margins">
                                <form action="${pageContext.request.contextPath}/fixed/batchUpdate?batchUpdate"
                                      method="post" id="batchUpdate">
                                    <div class="ibox-title">
                                        <br />
                                        <h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户管理权限：</h3>
                                        <div class="ibox-tools">
                                            <a class="collapse-link"> <i class="fa fa-chevron-up"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="ibox float-e-margins">
                                                <div class="ibox-content">
                                                    <div class="table-responsive">
                                                        <table class="table table-striped table-bordered table-hover dataTables-example">
                                                            <thead>
                                                            <tr>
                                                                <th>选中</th>
                                                                <th>用户编号</th>
                                                                <th>用户姓名</th>
                                                                <th>操作权限</th>
                                                                <th>修改</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <c:forEach items="${userList}" var="user">
                                                                <tr>
                                                                    <td><input type="checkbox" name="selectedUsers" value="${user.userId}"></td>
                                                                    <td>${user.userId}</td>
                                                                    <td>${user.userName}</td>
                                                                    <td>${user.managetype}</td>
                                                                    <td>
                                                                        <a href="${pageContext.request.contextPath}/login/admin/update/${user.userId}"><i class="fa fa-pencil"></i></a>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                        <a href="${pageContext.request.contextPath}/login/admin/detail/${user.userId}"><i class="fa fa-list-ul"></i></a>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>



                                                        <label>全选</label>
                                                        <button type="button" id="selectAll">
                                                            <i class="fa fa-check-circle-o"></i>
                                                        </button>
                                                        <label>反选</label>
                                                        <button type="button" id="changeSelect">
                                                            <i class="fa fa-check-circle"></i>
                                                        </button>
                                                        <label>全不选</label>
                                                        <button type="button" id="selectNot">
                                                            <i class="fa fa-circle-o"></i>
                                                        </button>

<%--                                                        <div class="col-sm-3 col-sm-offset-2">--%>
<%--                                                            <button class="btn btn-primary" id="redirect_btn">用户</button>--%>
<%--                                                        </div>--%>
<%--                                                        <li><a href="${pageContext.request.contextPath }/login/admin/detail/${user.userId}"> <i class="fa fa-sign-out"></i> 退出--%>
<%--                                                        </a></li>--%>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <c:if test="${empty userList}">
                                    <script type="text/javascript">
                                        window.alert("无查询结果！");
                                    </script>
                                </c:if>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script>
    // 获取按钮元素
    var redirectButton = document.getElementById("redirect_btn");

    // 添加点击事件监听
    redirectButton.addEventListener("click", function() {
        // 获取当前页面的contextPath
        var contextPath = "${pageContext.request.contextPath}";

        // 构建完整的URL
        var redirectUrl = contextPath + "/login/admin/detail/${user.userId}";

        // 页面跳转
        window.location.href = redirectUrl;
    });
</script>
</body>

</html>
