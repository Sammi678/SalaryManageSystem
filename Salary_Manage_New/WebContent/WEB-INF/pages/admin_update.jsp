<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户权限管理</title>

    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <link
            href="${pageContext.request.contextPath }/static/css/bootstrap.min.css"
            rel="stylesheet">
    <link
            href="${pageContext.request.contextPath }/static/font-awesome/css/font-awesome.css"
            rel="stylesheet">
    <link
            href="${pageContext.request.contextPath }/static/css/plugins/datapicker/datepicker3.css"
            rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/css/animate.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/css/style.css"
          rel="stylesheet">
    <link
            href="${pageContext.request.contextPath }/static/css/plugins/chosen/chosen.css"
            rel="stylesheet">
    <link
            href="${pageContext.request.contextPath }/static/css/plugins/dataTables/datatables.min.css"
            rel="stylesheet">
    <link
            href="${pageContext.request.contextPath }/static/css/plugins/sweetalert/sweetalert.css"
            rel="stylesheet">
</head>
<body>

<%--    <div class="row border-bottom">--%>
<%--        <!--上侧导航条开始-->--%>
<%--        <%@include file="../inc/header.jsp"%>--%>
<%--        <!--上侧导航条结束-->--%>
<%--    </div>--%>

<div class="wrapper wrapper-content animated fadeInRight article">
    <div class="row">
        <div class="col-lg-10 col-lg-offset-1">
            <div class="ibox">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h3 align="center">用户管理权限修改</h3>
                            <div class="ibox-tools">
                                <a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <form action="${pageContext.request.contextPath}/login/admin_update/${user.userId}" method="post" class="form-horizontal" id="Update">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">用户ID</label>
                                    <div class="col-lg-10">
                                        <input type="text" disabled="" placeholder="${requestScope.userId}" class="form-control">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">用户姓名</label>
                                    <div class="col-lg-10">
                                        <input type="text" disabled="" placeholder="${user.userName}" class="form-control">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">管理权限修改为</label>
                                    <div class="col-sm-10">
                                        <input type="hidden" name="newManagetype" id="hiddenNewManagetype" value="" class="form-control">
                                        <input type="text" name="newManagetype" id="newManagetype" value="" class="form-control">
                                    </div>
                                </div>
                                <div class="hr-line-dashed"></div>

                                <div class="form-group">
                                    <div class="col-sm-3 col-sm-offset-2">
                                        <button class="btn btn-primary demo3" id="save_btn" type="button" onclick="submitForm()">保存</button>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Mainly scripts -->
<script src="${pageContext.request.contextPath}/static/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<!-- Custom and plugin javascript -->
<script src="${pageContext.request.contextPath}/static/js/inspinia.js"></script>
<script src="${pageContext.request.contextPath}/static/js/plugins/pace/pace.min.js"></script>
<!-- Sweet alert -->
<script src="${pageContext.request.contextPath}/static/js/plugins/sweetalert/sweetalert.min.js"></script>

<script>
    function submitForm() {
        document.getElementById('hiddenNewManagetype').value = document.getElementById('newManagetype').value;
        document.getElementById('Update').submit();
    }
</script>
</body>
</html>
