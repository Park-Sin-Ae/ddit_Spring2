<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<nav class="main-header navbar navbar-expand navbar-white navbar-light">
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
			href="#" role="button"><i class="fas fa-bars"></i></a></li>
	</ul>
	<ul class="navbar-nav ml-auto">
		<li class="nav-item dropdown">
			<a class="nav-link" data-toggle="dropdown" href="#">
				<i class="far fa-user"></i>
			</a>
			<div class="dropdown-menu dropdown-menu-sm dropdown-menu-right">
				<a href="/notice/profile.do" class="dropdown-item dropdown-footer">
					마이페이지
				</a>
				<div class="dropdown-divider"></div>
				<form action="/logout" method="post" class="dropdown-item dropdown-footer">
					<input type="submit" value="로그아웃"/>
					<sec:csrfInput/>
				</form>			
			</div>
		</li>
	</ul>	
</nav>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<a href="../../index3.html" class="brand-link"> <img
		src="${pageContext.request.contextPath }/resources/dist/img/AdminLTELogo.png" alt="AdminLTE Logo"
		class="brand-image img-circle elevation-3" style="opacity: .8">
		<span class="brand-text font-weight-light">SPRING</span>
	</a>

	<div class="sidebar">
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image">
				<img src="${pageContext.request.contextPath }/resources/dist/img/user2-160x160.jpg"
					class="img-circle elevation-2" alt="User Image">
			</div>
			<div class="info">
				<a href="" class="d-block">DDIT Spring</a>
			</div>
		</div>
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-tachometer-alt"></i>
						<p>공지사항</p>
				</a></li>
			</ul>
		</nav>
	</div>
</aside>