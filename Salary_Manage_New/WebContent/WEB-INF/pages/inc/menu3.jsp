<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <a class="close-canvas-menu"><i class="fa fa-times"></i></a>
        <!--右侧菜单开始-->
        <ul class="nav metismenu" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element" align="center">
                    <a data-toggle="dropdown" class="dropdown-toggle"
                       href="off_canvas_menu.html#"> <span class="clear"> <span
                            class="block m-t-xs"> <strong class="font-bold">菜单</strong>
						</span></span>
                    </a>
                </div>
            </li>
            <li><a href="#"><i class="fa fa-th-large"></i> <span
                    class="nav-label">工资项目管理</span> <span class="fa arrow"></span></a>
                <ul class="nav nav-second-level collapse">
                    <li><a
                            href="${pageContext.request.contextPath}/projects/create?form">工资项目增加</a></li>
                    <li><a
                            href="${pageContext.request.contextPath}/projects/list?form">工资项目查询</a></li>

                </ul></li>

            <li><a href="#"><i class="fa fa-edit"></i> <span
                    class="nav-label">报表</span><span class="fa arrow"></span></a>
                <ul class="nav nav-second-level collapse">
                    <li><a
                            href="${pageContext.request.contextPath}/import_project/create?form">报表项目录入</a></li>
                </ul></li>

            <li><a href="#"><i class="fa fa-picture-o"></i> <span
                    class="nav-label">工资</span><span class="fa arrow"></span></a>
                <ul class="nav nav-second-level collapse">
                    <li><a
                            href="${pageContext.request.contextPath}/baobiao/list?form1">工资查询</a>
                    </li>

                </ul></li>

        </ul>
        <!--右侧菜单开结束-->
    </div>
</nav>