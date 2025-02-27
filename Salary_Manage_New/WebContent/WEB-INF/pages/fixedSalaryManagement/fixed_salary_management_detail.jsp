<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>财务管理系统 | 固定项目管理</title>

<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.css"
	rel="stylesheet">

<link href="${pageContext.request.contextPath}/static/css/animate.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet">

</head>

<body>

	<div id="wrapper">

		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="sidebar-collapse">
				<a class="close-canvas-menu"><i class="fa fa-times"></i></a>
				<!--右侧菜单开始-->
				<%@include file="../inc/menu2.jsp"%>
				<!--右侧菜单开结束-->
			</div>
		</nav>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<!--上侧导航条开始-->
				<%@include file="../inc/header.jsp"%>
				<!--上侧导航条结束-->
			</div>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-sm-4">
					<h2>财务管理系统</h2>
					<ol class="breadcrumb">
						<li><a
							href="${pageContext.request.contextPath }/login/list?main">Home</a>
						</li>
						<li class="active"><strong>工资项目管理</strong></li>
					</ol>
				</div>
			</div>

			<div class="wrapper wrapper-content  animated fadeInRight article">
				<div class="row">
					<div class="col-lg-10 col-lg-offset-1">
						<div class="ibox">
							<div class="col-lg-12">
								<div class="ibox float-e-margins">
									<div class="ibox-title">
										<h3 align="center">员工固定工资详情表</h3>
										<div class="ibox-tools">
											<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
											</a>
										</div>
									</div>
									<div class="ibox-content">
										<form method="get" class="form-horizontal"
											action="${pageContext.request.contextPath}/fixed/list">

											<input type="hidden" class="form-control" id="selective"
												name="selective" value="${requestScope.deptId}">
											<div class="form-group">
												<label class="col-lg-2 control-label">员工编号</label>
												<div class="col-lg-10">
													<input type="text" disabled=""
														placeholder="${requestScope.empId }" class="form-control">
												</div>
											</div>

											<div class="hr-line-dashed"></div>

											<div class="form-group">
												<label class="col-sm-2 control-label">员工姓名</label>
												<div class="col-sm-10">
													<c:forEach items="${requestScope.empList }" var="emp">
														<c:if test="${emp.employeeId == requestScope.empId}">
															<input type="text" disabled=""
																placeholder="${emp.employeeName}" class="form-control">
														</c:if>
													</c:forEach>
												</div>
											</div>

											<div class="hr-line-dashed"></div>

											<div class="form-group">
												<label class="col-sm-2 control-label">部门名称</label>
												<div class="col-sm-10">
													<c:forEach items="${requestScope.deptList }" var="dept">
														<c:if test="${dept.departmentId == requestScope.deptId}">
															<input type="text" disabled=""
																placeholder="${dept.departmentName }"
																class="form-control">
														</c:if>
													</c:forEach>
												</div>
											</div>

											<div class="hr-line-dashed"></div>

											<c:forEach items="${requestScope.pojsList }" var="pojs">
												<c:forEach items="${requestScope.fsList }" var="fs">
													<c:if test="${pojs.projectId == fs.projectId}">

														<div class="form-group">
															<label class="col-sm-2 control-label">项目名称</label>
															<div class="col-sm-10">
																<input type="text" disabled=""
																	placeholder="${pojs.projectName }" class="form-control">
															</div>
														</div>
														<div class="hr-line-dashed"></div>
														<div class="form-group">
															<label class="col-sm-2 control-label">项目金额</label>
															<div class="col-sm-10">
																<input type="text" disabled=""
																	placeholder="${fs.salary }" class="form-control">
															</div>
														</div>
														<div class="hr-line-dashed"></div>

													</c:if>
												</c:forEach>
											</c:forEach>

											<div class="form-group">
												<div class="col-sm-3 col-sm-offset-2">
													<button class="btn btn-primary btn-rounded" type="submit">返回</button>
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
	</div>

	<!-- Mainly scripts -->
	<script
		src="${pageContext.request.contextPath}/static/js/jquery-2.1.1.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="${pageContext.request.contextPath}/static/js/inspinia.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/plugins/pace/pace.min.js"></script>

	<script>
		$('body.canvas-menu .sidebar-collapse').slimScroll({
			height : '100%',
			railOpacity : 0.9
		});
	</script>

</body>

</html>