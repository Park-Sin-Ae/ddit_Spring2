<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>공지사항 게시판</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">DDIT HOME</a></li>
					<li class="breadcrumb-item active">공지사항 게시판</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<section class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-dark card-outline">
					<div class="card-header">
						<div class="card-tools">
							<form class="input-group input-group-sm" id="searchForm" style="width: 440px;">
								<input type="hidden" name="page" id="page"/>
								<select class="form-control" name="searchType">
									<option value="title" <c:if test="${searchType eq 'title' }">selected</c:if>>제목</option>
									<option value="writer" <c:if test="${searchType eq 'writer' }">selected</c:if>>작성자</option>
								</select>
								<input type="text" name="searchWord" value="${searchWord }" class="form-control float-right" placeholder="Search">
								<div class="input-group-append">
									<button type="submit" class="btn btn-default">
										<i class="fas fa-search"></i>검색
									</button>
								</div>
								<sec:csrfInput/>
							</form>
						</div>
						<h3 class="card-title">공지사항</h3>
					</div>
					<div class="card-body">
						<table class="table table-bordered">
							<thead class="table-dark">
								<tr>
									<th style="width: 6%">#</th>
									<th style="width: px">제목</th>
									<th style="width: 12%">작성자</th>
									<th style="width: 12%">작성일</th>
									<th style="width: 10%">조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:set value="${pagingVO.dataList }" var="noticeList" />
								<c:choose>
									<c:when test="${empty noticeList }">
										<tr>
											<td colspan="5">조회하실 게시글이 존재하지 않습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${noticeList }" var="notice">
											<c:set value="primary" var="color" />
											<c:if test="${notice.boWriter eq 'admin' }">
												<c:set value="danger" var="color" />
											</c:if>
											<tr>
												<td>${notice.boNo }</td>
												<td><a href="/notice/detail.do?boNo=${notice.boNo}">
														${notice.boTitle } </a></td>
												<td><font class="badge badge-${color }"
													style="font-size: 14px;"> ${notice.boWriter } </font></td>
												<td>${notice.boDate }</td>
												<td>${notice.boHit }</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
					<div class="card-footer" align="right">
						<button type="button" class="btn btn-dark" onclick="javascript:location.href='/notice/form.do'">등록</button>
					</div>
					<div class="card-footer cearfix" id="pagingArea">
						${pagingVO.pagingHTML }
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
$(function() {
	var pagingArea = $("#pagingArea");
	var searchForm = $("#searchForm");
	
	pagingArea.on("click","a", function(event) {
		event.preventDefault();
		var pageNo = $(this).data("page");
		// 검색 시 사용할 form 태그안에 넣어준다.
		// 검색 시 사용할 form 태그를 활용하여 검색도 하고 페이징 처리도 같이 진행함
		searchForm.find("#page").val(pageNo);
		searchForm.submit();
	});
});

</script>
