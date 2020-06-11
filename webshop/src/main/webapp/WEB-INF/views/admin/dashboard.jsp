<%@page contentType="text/html; charset=EUC-KR"%>
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

<!--Custom Font-->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
			<div class="profile-usertitle">
				<div class="profile-usertitle-name">ADMIN</div>
				<div class="profile-usertitle-status">
					<span class="indicator label-success"></span>Online
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>

		<!-- 		@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 네비게이션 탭 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
		<ul class="nav menu">
			<li class="active"><a href="dashBoard.do"><em
					class="fa fa-dashboard">&nbsp;</em> Dashboard</a></li>
			<li><a href="charts.do"><em class="fa fa-bar-chart">&nbsp;</em>
					Charts</a></li>
			<li><a href="memberManagement.do"><em class="fa fa-clone">&nbsp;</em>
					Member Management</a></li>
			<li><a href="employeeManagement.do"><em class="fa fa-clone">&nbsp;</em>
					Employee Management</a></li>
			<li><a href="inventorySituation.do"><em class="fa fa-clone">&nbsp;</em>
					Inventory Situation</a></li>
			<li><a href="deliverySituation.do"><em class="fa fa-clone">&nbsp;</em>
					Delivery Situation</a></li>
			<li><a href="consultingReservation.do"><em
					class="fa fa-clone">&nbsp;</em> Consulting Reservation</a></li>
			<li><a href="index.do"><em class="fa fa-toggle-off">&nbsp;</em>
					shop</a></li>
			<li><a href="logout.do"><em class="fa fa-power-off">&nbsp;</em>
					Logout</a></li>
		</ul>
	</div>
	<!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Dashboard</h1>
			</div>
		</div>
		<!--/.row-->


		<div class="panel panel-container">
			<div class="row">
				<div class="col-xs-6 col-md-3 col-lg-3 no-padding">
					<div class="panel panel-teal panel-widget border-right">
						<div class="row no-padding">
							<em class="fa fa-xl fa-shopping-cart color-blue"></em>
							<div class="large">${orderCount}</div>
							<div class="text-muted">New Orders</div>
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-md-3 col-lg-3 no-padding">
					<div class="panel panel-orange panel-widget border-right">
						<div class="row no-padding">
							<em class="fa fa-xl fa-users color-teal"></em>
							<div class="large">${memberCount}</div>
							<div class="text-muted">New Users</div>
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-md-3 col-lg-3 no-padding">
					<div class="panel panel-red panel-widget ">
						<div class="row no-padding">
							<em class="fa fa-xl fa-search color-red"></em>
							<div class="large">${viewCount[0]}</div>
							<div class="text-muted">Page Views</div>
						</div>
					</div>
				</div>
			</div>
			<!--/.row-->
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">최근 방문자 수 통계</div>
					<div class="panel-body">
						<div class="canvas-wrapper">
							<canvas class="main-chart" id="line-chart" height="200"
								width="600"></canvas>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/.row-->
	</div>
	<!--/.main-->

	<script src="js/a_js/jquery-1.11.1.min.js"></script>
	<script src="js/a_js/bootstrap.min.js"></script>
	<script src="js/a_js/chart.min.js"></script>
	<script src="js/a_js/chart-data.js"></script>
	<script src="js/a_js/easypiechart.js"></script>
	<script src="js/a_js/easypiechart-data.js"></script>
	<script src="js/a_js/bootstrap-datepicker.js"></script>
	<script src="js/a_js/custom.js"></script>
	<script>
		window.onload = function () {

// 최근 30일 간 방문자 수 그래프
a = new Array();
for(var i=30;i>=0;i--){
	a[30-i] = "DAY-"+i;
	};

	var lineChartData = {
	        labels : a,
	        datasets : [
	        	{
					fillColor : "rgba(48, 164, 255, 0.2)",
					strokeColor : "rgba(48, 164, 255, 1)",
					pointColor : "rgba(48, 164, 255, 1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(48, 164, 255, 1)",
 	        		data : [${viewCount[30]},${viewCount[29]},${viewCount[28]},${viewCount[27]},${viewCount[26]},${viewCount[25]},${viewCount[24]},${viewCount[23]},${viewCount[22]},${viewCount[21]},${viewCount[20]},${viewCount[19]},${viewCount[18]},${viewCount[17]},${viewCount[16]},${viewCount[15]},${viewCount[14]},${viewCount[13]},${viewCount[12]},${viewCount[11]},${viewCount[10]},${viewCount[9]},${viewCount[8]},${viewCount[7]},${viewCount[6]},${viewCount[5]},${viewCount[4]},${viewCount[3]},${viewCount[2]},${viewCount[1]},${viewCount[0]}]
				}
	    ]
	}
	var chart1 = document.getElementById("line-chart").getContext("2d");
	window.myLine = new Chart(chart1).Line(lineChartData, {
	responsive: true,
	scaleLineColor: "rgba(0,0,0,.2)",
	scaleGridLineColor: "rgba(0,0,0,.05)",
	scaleFontColor: "#c5c7cc"
	});


	$("#btn-input").keyup(function(e){
		if(e.keyCode==13 && $("#btn-input").val() != ""){
			var text = $("#btn-input").val();

 	var inputList = "<li class='todo-list-item'><div class='checkbox'><input type='checkbox' class='checkbox'/><label for='checkbox'>"+text+"</label></div><div class='pull-right action-buttons'><a class='trash'><em class='fa fa-trash'></em></a></div></li>"

			$(".todo-list").append(inputList)

	$(document).on("click",".trash",function(){
		$(this).parent().parent().remove();
		})

		$("#btn-input").val("");

		}
		})
};
	</script>

</body>
</html>