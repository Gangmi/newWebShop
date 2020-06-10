<%@page contentType="text/html; charset=utf-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE>
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
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

</head>
<body>
	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse"><span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span></button>
				<a class="navbar-brand" style="padding:3px"><img src="img/core-img/logo3.png" alt=""/></a>
				
			</div>
		</div><!-- /.container-fluid -->
	</nav>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<div class="profile-sidebar">
			<div class="profile-userpic">
				<img src="http://placehold.it/50/30a5ff/fff" class="img-responsive" alt="">
			</div>
			<div class="profile-usertitle">
				<div class="profile-usertitle-name">ADMIN</div>
				<div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>
		<ul class="nav menu">
			<li><a href="dashBoard.do"><em class="fa fa-dashboard">&nbsp;</em> Dashboard</a></li>
			<li class="active"><a href="charts.do"><em class="fa fa-bar-chart">&nbsp;</em> Charts</a></li>
			<li><a href="memberManagement.do"><em class="fa fa-clone">&nbsp;</em> Member Management</a></li>
			<li><a href="employeeManagement.do"><em class="fa fa-clone">&nbsp;</em> Employee Management</a></li>	
			<li><a href="inventorySituation.do"><em class="fa fa-clone">&nbsp;</em> Inventory Situation</a></li>
			<li><a href="deliverySituation.do"><em class="fa fa-clone">&nbsp;</em> Delivery Situation</a></li>
			<li><a href="consultingReservation.do"><em class="fa fa-clone">&nbsp;</em> Consulting Reservation</a></li>
			<li><a href="index.do"><em class="fa fa-toggle-off">&nbsp;</em> shop</a></li>
			<li><a href="logout.do"><em class="fa fa-power-off">&nbsp;</em> Logout</a></li>
		</ul>
	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Charts</h1>
			</div>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						최근 일간 매출
</div>
					<div class="panel-body">
						<div class="canvas-wrapper">
							<canvas class="main-chart" id="line-chart" height="200" width="600"></canvas>
						</div>
					</div>
				</div>
			</div>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						월별 매출
</div>
					<div class="panel-body">
						<div class="canvas-wrapper">
							<canvas class="main-chart" id="bar-chart" height="200" width="600"></canvas>
						</div>
					</div>
				</div>
			</div>
		</div><!--/.row-->		
		
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						카테고리 매출
</div>
					<div class="panel-body">
						<div class="canvas-wrapper">
							<canvas class="chart" id="pie-chart" ></canvas>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						연령 매출
</div>
					<div class="panel-body">
						<div class="canvas-wrapper">
							<canvas class="chart" id="doughnut-chart" ></canvas>
						</div>
					</div>
				</div>
			</div>
		</div><!--/.row-->
		

	</div>	<!--/.main-->
	  

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

// 최근 30일간 매출 라인 차트
a = new Array();
for(var i=30;i>=0;i--){
	a[30-i] = "DAY-"+i;
	};

	var lineChartData = {
// 	        labels : ["DAY-1","DAY-2","DAY-3","DAY-4","DAY-5","DAY-6","DAY-7"],
	        labels : a,
	        datasets : [ 
	        	{
					fillColor : "rgba(48, 164, 255, 0.2)",
					strokeColor : "rgba(48, 164, 255, 1)",
					pointColor : "rgba(48, 164, 255, 1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(48, 164, 255, 1)",
	        		data : [${day[30]},${day[29]},${day[28]},${day[27]},${day[26]},${day[25]},${day[24]},${day[23]},${day[22]},${day[21]},${day[20]},${day[19]},${day[18]},${day[17]},${day[16]},${day[15]},${day[14]},${day[13]},${day[12]},${day[11]},${day[10]},${day[9]},${day[8]},${day[7]},${day[6]},${day[5]},${day[4]},${day[3]},${day[2]},${day[1]},${day[0]}]
// 	        	data : [${day[6]},${day[5]},${day[4]},${day[3]},${day[2]},${day[1]},${day[0]}]
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

// 월별 매출 바 차트
	var barChartData = {
	        labels : ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
	        datasets : [
            {
				fillColor : "rgba(48, 164, 255, 0.2)",
				strokeColor : "rgba(48, 164, 255, 1)",
				pointColor : "rgba(48, 164, 255, 1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(48, 164, 255, 1)",
           		data : [${month[0]},${month[1]},${month[2]},${month[3]},${month[4]},${month[5]},${month[6]},${month[7]},${month[8]},${month[9]},${month[10]},${month[11]}]

            }
	    ]
	}
	var chart2 = document.getElementById("bar-chart").getContext("2d");
	window.myBar = new Chart(chart2).Bar(barChartData, {
	responsive: true,
	scaleLineColor: "rgba(0,0,0,.2)",
	scaleGridLineColor: "rgba(0,0,0,.05)",
	scaleFontColor: "#c5c7cc"
	});

// 도넛 차트 카테고리 별 매출
	var doughnutData = [
		{
			value: ${age[0]},
			color:"#30a5ff",
			highlight: "#62b9fb",
			label: "10대"
		},
		{
			value: ${age[1]},
			color:"#CC2EFA",
			highlight: "#BE81F7",
			label: "20대"
		},
		{
			value: ${age[2]},
			color: "#ffb53e",
			highlight: "#fac878",
			label: "30대"
		},
		{
			value: ${age[3]},
			color: "#1ebfae",
			highlight: "#3cdfce",
			label: "40대"
		},
		{
			value: ${age[4]},
			color: "#f9243f",
			highlight: "#f6495f",
			label: "50대"
		},
		{
			value: ${age[5]},
			color: "#642EFE",
			highlight: "#8181F7",
			label: "60대 이상"
		}

	];
	var chart3 = document.getElementById("doughnut-chart").getContext("2d");
	window.myDoughnut = new Chart(chart3).Doughnut(doughnutData, {
	responsive: true,
	segmentShowStroke: false
	});

	var pieData = [
		{
			value: ${category[0]},
			color:"#30a5ff",
			highlight: "#62b9fb",
			label: "table"
		},
		{
			value: ${category[1]},
			color:"#CC2EFA",
			highlight: "#BE81F7",
			label: "bed"
		},
		{
			value: ${category[2]},
			color: "#ffb53e",
			highlight: "#fac878",
			label: "furniture"
		},
		{
			value: ${category[3]},
			color: "#1ebfae",
			highlight: "#3cdfce",
			label: "dressings"
		},
		{
			value: ${category[4]},
			color: "#f9243f",
			highlight: "#f6495f",
			label: "chair"
		}

	];
	var chart4 = document.getElementById("pie-chart").getContext("2d");
	window.myPie = new Chart(chart4).Pie(pieData, {
	responsive: true,
	segmentShowStroke: false
	});

	

};
	</script>	
</body>
</html>
