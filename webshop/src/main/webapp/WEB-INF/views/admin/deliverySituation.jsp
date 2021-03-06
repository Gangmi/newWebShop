<%@page contentType="text/html; charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Coziness</title>
<link href="resources/css/a_css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/font-awesome.min.css" rel="stylesheet">
<link href="resources/css/a_css/datepicker3.css" rel="stylesheet">
<link href="resources/css/a_css/styles.css" rel="stylesheet">
<!-- ***************** new **************** -->
<link rel="stylesheet" type="text/css"
	href="resources/css/a_css/animate.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/a_css/select2.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/a_css/perfect-scrollbar.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/a_css/util.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/a_css/main.css">


<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!--Custom Font-->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

</head>
<body>
<!-- 	최상단 로고 -->
	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" style="padding: 3px"><img
					src="img/core-img/logo3.png" alt="" /></a>

			</div>
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<div class="profile-sidebar">
			<div class="profile-userpic">
				<img src="http://placehold.it/50/30a5ff/fff" class="img-responsive"
					alt="">
			</div>
<!-- 			로그인 프로필 -->
			<div class="profile-usertitle">
				<div class="profile-usertitle-name">ADMIN</div>
				<div class="profile-usertitle-status">
					<span class="indicator label-success"></span>Online
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>
<!-- 		*************** 네비게이션 탭 *************** -->
		<ul class="nav menu">
			<li><a href="dashBoard.do"><em class="fa fa-dashboard">&nbsp;</em>
					Dashboard</a></li>
			<li><a href="charts.do"><em class="fa fa-bar-chart">&nbsp;</em>
					Charts</a></li>
			<li><a href="memberManagement.do"><em class="fa fa-clone">&nbsp;</em>
					Member Management</a></li>
			<li><a href="employeeManagement.do"><em class="fa fa-clone">&nbsp;</em>
					Employee Management</a></li>
			<li><a href="inventorySituation.do"><em class="fa fa-clone">&nbsp;</em>
					Inventory Situation</a></li>
			<li class="active"><a href="deliverySituation.do"><em
					class="fa fa-clone">&nbsp;</em> Order Situation</a></li>
			<li><a href="consultingReservation.do"><em
					class="fa fa-clone">&nbsp;</em> Consulting Reservation</a></li>
			<li><a href="index.do"><em class="fa fa-toggle-off">&nbsp;</em>
					Shop</a></li>
			<li><a href="logout.do"><em class="fa fa-power-off">&nbsp;</em>
					Logout</a></li>
		</ul>
	</div>
	<!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Order Situation</h1>
			</div>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">

<script>
// 검색창 검색기능
$(document).ready(function() {
	var activeSystemClass = $('.list-group-item.active');
		$('#system-search').keyup(function() {
			var that = this;
			var tableBody = $('.table-list-search tbody');
			var tableRowsClass = $('.table-list-search tbody tr');
		$('.search-sf').remove();
			tableRowsClass.each(function(i,val) {
			var rowText = $(val).text().toLowerCase();
			var inputText = $(that).val().toLowerCase();
				if (inputText != '') {$('.search-query-sf').remove();
					tableBody.prepend('<tr class="search-query-sf"><td colspan="6"><strong>Searching for: "'+ $(that).val()+ '"</strong></td></tr>');
				} else {$('.search-query-sf').remove();
				}
if (rowText.indexOf(inputText) == -1) {
	tableRowsClass.eq(i).hide();
} else {$('.search-sf').remove();
tableRowsClass.eq(i).show();
}
});
if (tableRowsClass.children(':visible').length == 0) {
	tableBody.append('<tr class="search-sf"><td class="text-muted" colspan="6">No entries found.</td></tr>');
}
});

$('.selectbox').on("change",function() {
	// 해당하는 주문의 id값 가져오기
	alert($(this).parent().siblings().eq(0).text())
$.ajax({
	type : 'GET',
	url : "updateDeli.do",
	data : "o_delivery="+ $(this).val()+
	 "&o_id="+ $(this).parent().siblings().eq(0).text(),
	contentType : "application/x-www-form-urlencoded; charset=UTF-8",
	success : function(data) {
	}
})
})
});
</script>

<div class="wrap-table100">
	<div class="table100">
		<!-- 		********************* 검색 창 ****************** -->
		<div class="col-md-3">
			<form action="#" method="get">
				<div class="input-group">
					<input class="form-control" id="system-search" name="q"
						placeholder="Search for" required> <span
						class="input-group-btn">
						<button type="submit" class="btn btn-default">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</span>
				</div>
			</form>
		</div>

		<!--		************************* 테이블 *********************** -->
		<div class="wrap-table100">
			<div class="col-md-9">
				<table class="table table-list-search">
					<thead>
						<tr class="table100-head">
							<th class="column1">주문번호</th>
							<th class="column2">주문날짜</th>
							<th class="column3">회원아이디</th>
							<th class="column4">결제방법</th>
							<th class="column5">배송상태</th>
						</tr>
					</thead>
<!-- 					동적 배열로 주문 목록 가져오기 -->
					<c:forEach items="${listVO }" var="list">
						<tr>
							<td class="column1">${list.o_id }</td>
							<td class="column2">${list.o_date }</td>
							<td class="column3">${list.m_id }</td>
							<td class="column4">${list.o_payment }</td>
<!-- 							배송 상황 변경 셀렉트 박스 -->
							<td class="column5"><select name="delivery"
								class="selectbox">
									<option selected disabled hidden value="">${list.o_delivery }</option>
									<option value="배송전">배송전</option>
									<option value="배송중">배송중</option>
									<option value="배송완료">배송완료</option>
							</select></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!-- ****************** 테이블 end*********************** -->
		</div>
	</div>
</div>
			</div>
			<!-- /.panel-->
		</div>
		<!-- /.col-->

	</div>
	<!-- /.row -->
	</div>
	<!--/.main-->

	<script src="js/a_js/jquery-1.11.1.min.js"></script>
	<script src="js/a_js/bootstrap.min.js"></script>
	<script src="js/a_js/chart.min.js"></script>
	<script src="js/a_js/chart-data.js"></script>
	<script src="js/a_js/easypiechart.js"></script>
	<script src="js/a_js/bootstrap-datepicker.js"></script>
	<script src="js/a_js/custom.js"></script>
	<!-- 	*********************************** -->
	<script src="js/a_js/jquery-3.2.1.min.js"></script>
	<script src="js/a_js/popper.js"></script>
	<script src="js/a_js/bootstrap.min.js"></script>
	<script src="js/a_js/select2.min.js"></script>
	<script src="js/a_js/main.js"></script>


</body>
</html>
