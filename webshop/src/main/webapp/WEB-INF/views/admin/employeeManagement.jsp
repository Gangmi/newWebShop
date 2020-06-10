<%@page contentType="text/html; charset=utf-8"%>
<% request.setCharacterEncoding("utf-8"); %>
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
<link rel="stylesheet" type="text/css" href="resources/css/a_css/animate.css">
<link rel="stylesheet" type="text/css" href="resources/css/a_css/select2.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/a_css/perfect-scrollbar.css">
<link rel="stylesheet" type="text/css" href="resources/css/a_css/util.css">
<link rel="stylesheet" type="text/css" href="resources/css/a_css/main.css">

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!--Custom Font-->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" style="padding:3px"><img src="img/core-img/logo3.png" alt=""/></a>

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
			<div class="profile-usertitle">
				<div class="profile-usertitle-name">ADMIN</div>
				<div class="profile-usertitle-status">
					<span class="indicator label-success"></span>Online
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>

		<ul class="nav menu">
			<li><a href="dashBoard.do"><em class="fa fa-dashboard">&nbsp;</em>
					Dashboard</a></li>
			<li><a href="charts.do"><em class="fa fa-bar-chart">&nbsp;</em>
					Charts</a></li>
			<li><a href="memberManagement.do"><em
					class="fa fa-clone">&nbsp;</em> Member Management</a></li>
			<li class="active"><a href="employeeManagement.do"><em
					class="fa fa-clone">&nbsp;</em> Employee Management</a></li>
			<li><a href="inventorySituation.do"><em class="fa fa-clone">&nbsp;</em>
					Inventory Situation</a></li>
			<li><a href="deliverySituation.do"><em
					class="fa fa-clone">&nbsp;</em> Delivery Situation</a></li>
			<li><a href="consultingReservation.do"><em
					class="fa fa-clone">&nbsp;</em> Consulting Reservation</a></li>
			<li><a href="index.do"><em class="fa fa-toggle-off">&nbsp;</em> shop</a></li>
			<li><a href="logout.do"><em class="fa fa-power-off">&nbsp;</em>
					Logout</a></li>
		</ul>
	</div>
	<!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Employee Management</h1>
			</div>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">



				<script>
					$(document)
							.ready(
									function() {
										var activeSystemClass = $('.list-group-item.active');

										//something is entered in search form
										$('#system-search')
												.keyup(
														function() {
															var that = this;
															// affect all table rows on in systems table
															var tableBody = $('.table-list-search tbody');
															var tableRowsClass = $('.table-list-search tbody tr');
															$('.search-sf')
																	.remove();
															tableRowsClass
																	.each(function(
																			i,
																			val) {

																		//Lower text for case insensitive
																		var rowText = $(
																				val)
																				.text()
																				.toLowerCase();
																		var inputText = $(
																				that)
																				.val()
																				.toLowerCase();
																		if (inputText != '') {
																			$(
																					'.search-query-sf')
																					.remove();
																			tableBody
																					.prepend('<tr class="search-query-sf"><td colspan="6"><strong>Searching for: "'
																							+ $(
																									that)
																									.val()
																							+ '"</strong></td></tr>');
																		} else {
																			$(
																					'.search-query-sf')
																					.remove();
																		}

																		if (rowText
																				.indexOf(inputText) == -1) {
																			//hide rows
																			tableRowsClass
																					.eq(
																							i)
																					.hide();

																		} else {
																			$(
																					'.search-sf')
																					.remove();
																			tableRowsClass
																					.eq(
																							i)
																					.show();
																		}
																	});
															//all tr elements are hidden
															if (tableRowsClass
																	.children(':visible').length == 0) {
																tableBody
																		.append('<tr class="search-sf"><td class="text-muted" colspan="6">No entries found.</td></tr>');
															}
														});


										
$(document).on("click","#del",function(evt){
alert("dd")
window.location.href="memberDelete.do?m_id="+$(evt.target).parent().siblings().eq(0).val()



})


});
				</script>

<!-- 			<div class="wrap-table100"> -->
<!-- 				<div class="table100"> -->
<!-- 		********************* 검색 창 ****************** -->
						<div class="col-md-3">
							<form action="#" method="get">
								<div class="input-group">
									<!-- USE TWITTER TYPEAHEAD JSON WITH API TO SEARCH -->
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
								<th class="column1">NAME</th>
								<th class="column2">ID</th>
								<th class="column3">PASSWORD</th>
								<th class="column4">TEL</th>
								<th class="column5">EMAIL</th>
								<th class="column6">ADDRESS</th>
								<th class="column7">POST_CODE</th>
								<th class="column8">GRADE</th>
								<th class="column9">SUBSCRIBE</th>
								<th class="column10">DELETE</th>
							</tr>
								</thead>
<c:forEach items="${listVO }" var="list">
								
	<tr>
		<input id="m_id" type="hidden" value="${list.m_id }"/>
		<td class="column1">${list.m_name }</td>
		<td class="column2">${list.m_id }</td>
		<td class="column3">${list.m_pass }</td>
		<td class="column4">${list.m_tel }</td>
		<td class="column5">${list.m_email }</td>
		<td class="column6">${list.m_addr }</td>
		<td class="column7">${list.m_postcode }</td>
		<td class="column8">${list.m_grade }</td>
		<td class="column9">${list.m_sub }</td>
		<td class="column10"><input id="del" type="button" value="delete"/></td>
	</tr>
</c:forEach>
							</table>
						</div> <!-- ****************** 테이블 end*********************** -->
						</div>
<!-- 					</div> -->
<!-- 				</div> -->
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
