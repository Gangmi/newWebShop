<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Lumino ㅇㅁㅁㅁ</title>
<link href="resources/css/a_css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/a_css/font-awesome.min.css" rel="stylesheet">
<link href="resources/css/a_css/datepicker3.css" rel="stylesheet">
<link href="resources/css/a_css/styles.css" rel="stylesheet">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!-- ***************** new **************** -->
<link rel="stylesheet" type="text/css" href="resources/css/a_css/animate.css">
<link rel="stylesheet" type="text/css" href="resources/css/a_css/select2.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/a_css/perfect-scrollbar.css">
<link rel="stylesheet" type="text/css" href="resources/css/a_css/util.css">
<link rel="stylesheet" type="text/css" href="resources/css/a_css/main.css">

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
				<a class="navbar-brand" href="#"><span>Lumino</span>Admin</a>
				<ul class="nav navbar-top-links navbar-right">
					<li class="dropdown"><a class="dropdown-toggle count-info"
						data-toggle="dropdown" href="#"> <em class="fa fa-envelope"></em><span
							class="label label-danger">15</span>
					</a>
						<ul class="dropdown-menu dropdown-messages">
							<li>
								<div class="dropdown-messages-box">
									<a href="profile.html" class="pull-left"> <img alt="image"
										class="img-circle" src="http://placehold.it/40/30a5ff/fff">
									</a>
									<div class="message-body">
										<small class="pull-right">3 mins ago</small> <a href="#"><strong>John
												Doe</strong> commented on <strong>your photo</strong>.</a> <br /> <small
											class="text-muted">1:24 pm - 25/03/2015</small>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="dropdown-messages-box">
									<a href="profile.html" class="pull-left"> <img alt="image"
										class="img-circle" src="http://placehold.it/40/30a5ff/fff">
									</a>
									<div class="message-body">
										<small class="pull-right">1 hour ago</small> <a href="#">New
											message from <strong>Jane Doe</strong>.
										</a> <br /> <small class="text-muted">12:27 pm -
											25/03/2015</small>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="all-button">
									<a href="#"> <em class="fa fa-inbox"></em> <strong>All
											Messages</strong>
									</a>
								</div>
							</li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle count-info"
						data-toggle="dropdown" href="#"> <em class="fa fa-bell"></em><span
							class="label label-info">5</span>
					</a>
						<ul class="dropdown-menu dropdown-alerts">
							<li><a href="#">
									<div>
										<em class="fa fa-envelope"></em> 1 New Message <span
											class="pull-right text-muted small">3 mins ago</span>
									</div>
							</a></li>
							<li class="divider"></li>
							<li><a href="#">
									<div>
										<em class="fa fa-heart"></em> 12 New Likes <span
											class="pull-right text-muted small">4 mins ago</span>
									</div>
							</a></li>
							<li class="divider"></li>
							<li><a href="#">
									<div>
										<em class="fa fa-user"></em> 5 New Followers <span
											class="pull-right text-muted small">4 mins ago</span>
									</div>
							</a></li>
						</ul></li>
				</ul>
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
			<div class="profile-usertitle-name">Username</div>
			<div class="profile-usertitle-status">
				<span class="indicator label-success"></span>Online
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="divider"></div>
	<form role="search">
		<div class="form-group">
			<input type="text" class="form-control" placeholder="Search">
		</div>
	</form>
	<ul class="nav menu">
		<li><a href="dashBoard.do"><em class="fa fa-dashboard">&nbsp;</em>
				Dashboard</a></li>
		<li><a href="widgets.html"><em class="fa fa-calendar">&nbsp;</em>
				Widgets</a></li>
		<li><a href="charts.do"><em class="fa fa-bar-chart">&nbsp;</em>
				Charts</a></li>
		<li><a href="memberManagement.do"><em
				class="fa fa-toggle-off">&nbsp;</em> Member Management</a></li>
		<li><a href="employeeManagement.do"><em
				class="fa fa-toggle-off">&nbsp;</em> Employee Management</a></li>
		<li class="active"><a href="inventorySituation.do"><em
				class="fa fa-clone">&nbsp;</em> Inventory Situation</a></li>
		<li><a href="deliverySituation.do"><em
				class="fa fa-bar-chart">&nbsp;</em> Delivery Situation</a></li>
		<li><a href="consultingReservation.do"><em
				class="fa fa-bar-chart">&nbsp;</em> Consulting Reservation</a></li>
		<li><a href="login.html"><em class="fa fa-power-off">&nbsp;</em>
				Logout</a></li>
	</ul>
</div>
<!--/.sidebar-->

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="#"> <em class="fa fa-home"></em>
			</a></li>
			<li class="active">Forms</li>
		</ol>
	</div>
	<!--/.row-->

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Inventory Situation</h1>
	</div>
</div>
<!--/.row-->

		<div class="row">
			<div class="col-lg-12">

<script>
$(document).ready(
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



// 클릭하면 위에 올라가게 
$(document).on("click",".p_vo",function(){

	var str = ""
	var tdArr = new Array();
	var tr = $(this);
	var td = tr.children();

	td.each(function(i){
        tdArr.push(td.eq(i).text());
    });

	$(".receive").children().children().eq(0).val(tdArr[0])
	$(".receive").children().children().eq(1).val(tdArr[1])
	$(".receive").children().children().eq(2).val(tdArr[2])
	$(".receive").children().children().eq(3).val(tdArr[3])
	$(".receive").children().children().eq(4).val(tdArr[4])
	$(".receive").children().children().eq(5).val(tdArr[5])
	$(".receive").children().children().eq(6).val(tdArr[6])
	$(".receive").children().children().eq(7).val(tdArr[7])
	$(".receive").children().children().eq(8).val(tdArr[8])
	$(".receive").children().children().eq(9).val(tdArr[9])
})


// ajax function
function fileSubmit(){
	var form = $("MultiUpload")[0];
	var formData = new FormData(form);
	$.ajax({
		type : 'post',
		url : 'MultiUpload.do',
		data : formData,
		processData : false,
		contentType : false,
		success : function(html){
			alert("파일 업로드 하였습니다");
		},
		error : function(error){
			alert("파일 업로드에 실패하였습니다");
			console.log(error);
			console.log(error.status);
		}
	});
}







});
</script>
<script>
var header = {
        pageSubmitFn : function(pageName, form) {
           $("#pageName").val(pageName); // input 태그의 value를 설정한다.
            $("#"+form).attr("action",pageName+".do");             
            $("#"+form).submit();
//             $("#frmfile").submit();
        }






    }
</script>

			<div class="wrap-table100">
				<div class="table100">
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
								<th class="column1">ID</th>
								<th class="column2">PASSWORD</th>
								<th class="column3">TEL</th>
								<th class="column4">EMAIL</th>
								<th class="column5">ADDRESS</th>
								<th class="column6">POST_CODE</th>
								<th class="column7">GRADE</th>
								<th class="column8">SUBSCRIBE</th>
								<th class="column9">DELETE</th>
							</tr>
						</thead>

<c:forEach items="${listVO }" var="list">
	<tr>
		<td>${list.m_id }</td>
		<input id="m_id" type="hidden" value="${list.m_id }">
		<td class="column1">${list.m_pass }</td>
		<td class="column2">${list.m_tel }</td>
		<td class="column3">${list.m_email }</td>
		<td class="column4">${list.m_addr }</td>
		<td class="column5">${list.m_postcode }</td>
		<td class="column6">${list.m_grade }</td>
		<td class="column7">${list.m_sub }</td>
		<td class="column8"><input id="del" type="button" value="delete"/></td>
	</tr>
</c:forEach>
</table>
						</div> <!-- ****************** 테이블 end*********************** -->
						</div>
					</div>
				</div>
			</div>
	<!-- /.panel-->
</div>
<!-- /.col-->
	<div class="col-sm-12">
		<p class="back-link">
			Lumino Theme by <a href="https://www.medialoot.com">Medialoot</a>
		</p>
	</div>
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
