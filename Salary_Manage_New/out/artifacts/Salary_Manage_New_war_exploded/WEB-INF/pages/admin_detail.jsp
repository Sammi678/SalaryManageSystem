<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2024/6/27
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<<head>
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

    <!-- 超级大表单 -->
    <div class="wrapper wrapper-content animated fadeInRight article">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <div class="ibox">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="ibox float-e-margins">
                                <form
                                        action="${pageContext.request.contextPath}/login/admin_detail/${user.userId}"
                                        method="post" class="form-horizontal" id="Update">

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">用户ID</label>
                                        <div class="col-sm-10">
                                            <input type="hidden" name="userId2"
                                                   id="hiddenUserId2" value="" class="form-control">
                                            <input type="text" name="userId2" id="userId2" value=""
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">用户姓名</label>
                                        <div class="col-sm-10">
                                            <input type="hidden" name="userName"
                                                   id="hiddenUserName" value="" class="form-control">
                                            <input type="text" name="userName" id="userName" value=""
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">用户密码</label>
                                        <div class="col-sm-10">
                                            <input type="hidden" name="userPassword"
                                                   id="hiddenUserPassword" value="" class="form-control">
                                            <input type="text" name="userPassword" id="userPassword" value=""
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <!-- 新添加的修改 -->
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">操作权限</label>
                                        <div class="col-sm-10">
                                            <input type="text" name="managetype" id="managetype" class="form-control" placeholder="输入操作权限">
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <!-- 新添加的隐藏字段 -->
                                    <input type="hidden" name="managetype" id="hiddenManagetype" value="">

                                    <div class="form-group">
                                        <div class="col-sm-3 col-sm-offset-2">
                                            <button class="btn btn-primary demo3" id="save_btn" type="button" onclick="submitForm()">新增</button>
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

</div>

<script>
    function submitForm() {
        document.getElementById('hiddenUserId2').value = document.getElementById('userId2').value;
        document.getElementById('hiddenUserName').value = document.getElementById('userName').value;
        document.getElementById('hiddenUserPassword').value = document.getElementById('userPassword').value;
        document.getElementById('hiddenManagetype').value = document.getElementById('managetype').value;
        document.getElementById('Update').submit();
    }
</script>
</body>
</html>
