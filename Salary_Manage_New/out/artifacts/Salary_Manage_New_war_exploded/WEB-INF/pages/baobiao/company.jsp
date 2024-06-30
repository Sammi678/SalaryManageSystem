<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>财务管理系统 | 报表管理</title>

<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/plugins/datapicker/datepicker3.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
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
<%--					<h2>DHC工资管理系统</h2>--%>
					<ol class="breadcrumb">
						<li><a
							href="${pageContext.request.contextPath }/login/list?form">Home</a></li>
						<li class="active"><strong>/公司查询</strong></li>
					</ol>
				</div>
			</div>

			<div class="wrapper wrapper-content  animated fadeInRight article">
				<div class="row">
					<div class="col-lg-20 col-lg-offset-1">
						<div class="ibox">

							<div class="col-md-10">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">公司年度/月度工资统计</h3>
									</div>
									<div class="panel-body">
										<form method="post"
											action="${pageContext.request.contextPath }/baobiao/list"
											class="form-horizontal ">

											<div class="form-group" id="data_4">
												<label for="departmentName" class="col-sm-2 control-label">请选择日期</label>
												<div class="input-group date">
													<span class="input-group-addon"> <i
														class="fa fa-calendar"></i>
													</span> <input type="text" class="form-control"
														readonly="readonly" name="importDate" id="date1"
														style="width: 400px; height: 30px;" value="">
													&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
													<button type="submit" class="btn btn-default"
														style="height: 30px;">查询</button>
												</div>
											</div>


										</form>
									</div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<h3 class="panel-title">查询结果</h3>
										</div>
										<c:if test="${empty requestScope.listDateDepartmentsForm}">

											<tr>无查询结果。
											</tr>
										</c:if>
										<c:if test="${not empty requestScope.listDateDepartmentsForm}">
											<div class="panel-body">
												<table
													class="table table-striped table-bordered table-hover">
													<thead>
														<tr>
															<th>年度/月度</th>
															<th>总工资</th>
															<th>最高工资</th>
															<th>最低工资</th>
															<th>平均工资</th>

														</tr>
													</thead>
													<tbody>

														<c:forEach
															items="${requestScope.listDateDepartmentsForm }"
															var="dateDepartments">
															<tr>
																<td><fmt:formatDate pattern="yyyy-MM"
																		value="${dateDepartments.importDate}" /></td>
																<td>${dateDepartments.sumSalary}</td>
																<td>${dateDepartments.maxSalary}</td>
																<td>${dateDepartments.minSalary}</td>
																<td>${dateDepartments.avgSalary}</td>
															</tr>
														</c:forEach>


													</tbody>
												</table>
											</div>
										</c:if>
									</div>
								</div>

							</div>
						</div>
					</div>

				</div>


			</div>
			<!--底部显示-->
			<div class="footer">

			</div>
		</div>

		<!-- Mainly scripts -->
		<script src="${pageContext.request.contextPath}/static/js/jquery-2.1.1.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

		<!-- Data picker -->
		<script src="${pageContext.request.contextPath}/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>
		<!-- Custom and plugin javascript -->
		<script src="${pageContext.request.contextPath}/static/js/inspinia.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/plugins/pace/pace.min.js"></script>

		<script>
			$('body.canvas-menu .sidebar-collapse').slimScroll({
				height : '100%',
				railOpacity : 0.9
			});
			$(document).ready(function() {
				$('#data_4 .input-group.date').datepicker({
					minViewMode : 1,
					keyboardNavigation : false,
					forceParse : false,
					autoclose : true,
					todayHighlight : true
				});
			});
		</script>
</body>

</html>