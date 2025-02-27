<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>财务管理系统 | 工资项目管理</title>

<link
	href="${pageContext.request.contextPath }/static/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/font-awesome/css/font-awesome.css"
	rel="stylesheet">


<link href="${pageContext.request.contextPath }/static/css/animate.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath }/static/css/style.css"
	rel="stylesheet">

<!-- Sweet Alert -->
<link
	href="${pageContext.request.contextPath }/static/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">

</head>

<body>

	<div id="wrapper">

		<!--右侧菜单开始-->
		<%@ include file="../inc/menu.jsp"%>
		<!--右侧菜单开结束-->

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<!--上侧导航条开始-->
				<%@ include file="../inc/header.jsp"%>
				<!--上侧导航条结束-->
			</div>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-sm-4">
					<h2>财务管理系统</h2>
					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath }/login/list?form">Home</a></li>
						<li class="active"><strong>计算公式添加</strong></li>
					</ol>
				</div>
			</div>

			<div class="wrapper wrapper-content  animated fadeInRight article">
				<div class="row">
					<div class="col-lg-10 col-lg-offset-1">
						<div class="ibox">

							<!--内容增加区域-->
							<div class="ibox-title">
								<h5>
									计算公式添加 <small>(请填写相应信息)</small>
								</h5>
								<div class="ibox-tools">
									<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
									</a>
								</div>
							</div>
							<div class="ibox-content">
								<form id="form" method="post"
									action="${pageContext.request.contextPath}/calculation/create"
									class="form-horizontal">
									<div class="form-group">
										<label class="col-sm-3 control-label">操作数一</label>

										<div class="col-sm-5">
											<input type="hidden" name="projectId" id="projectId"
												class="form-control" value="${projects.projectId }">
											<input type="text" class="form-control"
												value="${projects.projectName }" readonly="readonly">



										</div>
									</div>
									<div class="hr-line-dashed"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label">操作符</label>
										<div class="col-sm-5">
											<select class="form-control m-b" name="operator"
												id="operator">
												<option value="">请选择...</option>
												<option value="1">加</option>
												<option value="2">减</option>
												<option value="3">乘</option>
												<option value="3">除</option>
											</select> <span class="help-block m-b-none"></span>
										</div>
									</div>

									<div class="hr-line-dashed"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label">操作数二</label>
										<div class="col-sm-5">
											<input type="text" name="operandtwo" id="operandtwo"
												class="form-control">
										</div>
									</div>


									<div class="hr-line-dashed"></div>
									<div class="form-group">
										<label class="col-sm-7 control-label"></label>
										<div class="col-sm-5">
											<button class="btn btn-primary" type="reset">重置</button>
											<button class="btn btn-primary" type="button" onclick="one()">添加</button>
										</div>
									</div>

								</form>

							</div>
						</div>
					</div>
				</div>

			</div>
			<!--底部显示-->

		</div>
	</div>

	<!-- projectsAddAddAddly scripts -->
	<script
		src="${pageContext.request.contextPath }/static/js/jquery-2.1.1.js "></script>
	<script
		src="${pageContext.request.contextPath }/static/js/bootstrap.min.js "></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/metisMenu/jquery.metisMenu.js "></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/slimscroll/jquery.slimscroll.min.js "></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/jeditable/jquery.jeditable.js "></script>


	<!-- Custom and plugin javascript -->
	<script
		src="${pageContext.request.contextPath }/static/js/inspinia.js "></script>
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/pace/pace.min.js "></script>


	<!-- Sweet alert -->
	<script
		src="${pageContext.request.contextPath }/static/js/plugins/sweetalert/sweetalert.min.js"></script>


	<script>
		$('body.canvas-menu .sidebar-collapse').slimScroll({
			height : '100%',
			railOpacity : 0.9
		});
	</script>
	<script type="text/javascript">
		function one() {
			if ( $("#operator").val() == "") {
				swal({
					title : "操作符不能为空!",
					text : "请选择操作符!"
				});
				return;
			}
			if (!(/^\d*\.?\d+$/).test($("#operandtwo").val())) {
				swal({
					title : "操作数二只能为正数!",
					text : "请输入正确的操作数二!"
				});
				return;
			}
			$('#form').submit();
		}
	</script>

</body>

</html>