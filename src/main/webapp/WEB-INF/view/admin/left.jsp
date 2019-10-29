<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Sidebar -->
<ul class="sidebar navbar-nav" >
	<li class="nav-item active"><a class="nav-link" href="/admin/index">
			<i class="fas fa-fw fa-tachometer-alt"></i> <span>后台首页</span>
	</a></li>
	<li class="nav-item"><a class="nav-link"  href="javascript:void(0)" data="/admin/manArticle">
			<i class="fas fa-fw fa-folder"></i> <span>文章管理</span>
	</a></li>

	<li class="nav-item"><a class="nav-link"  href="javascript:void(0)" data="/admin/managerUser">
			<i class="fas fa-fw fa-chart-area"></i> <span>用户管理</span>
	</a></li>
	<li class="nav-item"><a class="nav-link" href="javascript:void(0)" data="/special/list">
			<i class="fas fa-fw fa-chart-area"></i> <span>专题管理</span>
	</a></li>
	<li class="nav-item"><a class="nav-link" href="javascript:void(0)" data="待开发">
			<i class="fas fa-fw fa-chart-area"></i> <span>分类管理</span>
	</a></li>
	<li class="nav-item"><a class="nav-link" href="javascript:void(0)" data="待开发">
			<i class="fas fa-fw fa-table"></i> <span>系统设置</span>
	</a></li>
	<li class="nav-item"><a class="nav-link" href="javascript:void(0)" data="/blogroll/friendship">
			<i class="fas fa-fw fa-table"></i> <span>友情链接</span>
	</a></li>
</ul>