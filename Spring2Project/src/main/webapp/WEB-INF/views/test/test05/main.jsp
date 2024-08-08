<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-primary">
					<div class="card-header">
						<div class="card-title">
							문제
						</div>
					</div>
					<div class="card-body">
						<h5>
							305호 학생들이 대덕인재개발원 건물을 이용합니다! 이때, 엘리베이터를 이용하는데요
							올라가려고 하는 층수를 입력하고 이용하기를 누르면 엘리베이터가 올라가는 현황이 보여지게해주세요. 
							그리고, 도착하면 다시 층수를 입력받게 하고 엘리베이터를이용할 수 있게해주세요.
						</h5>
					</div>
					<div class="card-footer">
					</div>
				</div>
			</div>
		</div>
		<div class="row mt-4">
			<div class="col-md-3">
				<h5>어디층으로 가시나요?</h5>
				<div class="row">
					<div class="col-md-9">
						<input type="text" id="floor" class="form-control" placeholder="층수를 입력해주세요."/>	
					</div>
					<div class="col-md-3">
						<button class="btn btn-primary" id="moveBtn">이동</button>	
					</div>
				</div>
				<p>** 숫자로 입력해주세요.</p>
			</div>
			<div class="col-md-6">
				<table class="table table-bordered text-center">
					<thead>
						<tr>
							<th colspan="2">대덕인재개발원 건물</th>
						</tr>
						<tr>
							<th>층</th>
							<th>-</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<tr data-floor="12">
							<td>12층</td><td class="flo"></td>
						</tr>
						<tr data-floor="11">
							<td>11층</td><td class="flo"></td>
						</tr>
						<tr data-floor="10">
							<td>10층</td><td class="flo"></td>
						</tr>
						<tr data-floor="9">
							<td>9층</td><td class="flo"></td>
						</tr>
						<tr data-floor="8">
							<td>8층</td><td class="flo"></td>
						</tr>
						<tr data-floor="7">
							<td>7층</td><td class="flo"></td>
						</tr>
						<tr data-floor="6">
							<td>6층</td><td class="flo"></td>
						</tr>
						<tr data-floor="5">
							<td>5층</td><td class="flo"></td>
						</tr>
						<tr data-floor="4">
							<td>4층</td><td class="flo"></td>
						</tr>
						<tr data-floor="3">
							<td>3층</td><td class="flo"></td>
						</tr>
						<tr data-floor="2">
							<td>2층</td><td class="flo"></td>
						</tr>
						<tr data-floor="1">
							<td>1층</td><td style="background-color:yellow;" class="flo"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-md-3">
				<h5>현재 멈추어있는 층수</h5>
				<hr/>
				<h6 id="result">현재 층수 1층입니다.</h6>
			</div>
		</div>
	</div>
</body>
</html>




















