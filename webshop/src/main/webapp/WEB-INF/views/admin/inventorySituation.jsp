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
        }






    }
</script>

<div class="container" style="margin: 1px;padding:1px">
<div class="row" style="margin: 1px">
					
<!-- 	@@@@@@@@@@@@@@@@@@@@ 삼품 검색 @@@@@@@@@@@@@@@@@@@@ -->

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
	<br><br>
	
</div>

<!-- <input type="button" value="update"/> -->
<!-- <input type="button"value="insert"/> -->

<div class="col-md-6">




<div class="col-md-6">
	<table class="table table-list-search1">
		<thead>
			<tr>
				<th>ID</th>
				<th>NAME</th>
				<th>CATEGORY</th>
				<th>PRICE</th>
				<th>QUANTITY</th>
				<th>BRAND</th>
				<th>COLOR</th>
				<th>DETAIL</th>
				<th>DATE</th>
			</tr>
		</thead>
		<form id="frm" id="frmfile" method="post" enctype="multipart/form-data">
			<tr class="receive">
				<td><input type="text" style="width:50px;border:none" name="p_id" value="${productVO.p_id}" readonly/></td>
				<td><input type="text" style="width:150px;border:none" name="p_name"/></td>
				<td><input type="text" style="width:80px;border:none" name="p_cat"/></td>
				<td><input type="text" style="width:70px;border:none" name="p_price"/></td>
				<td><input type="text" style="width:50px;border:none" name="p_quan"/></td>
				<td><input type="text" style="width:70px;border:none" name="p_brand"/></td>
				<td><input type="text" style="width:50px;border:none" name="p_color"/></td>
				<td><input type="text" style="width:70px;border:none" name="p_detail"/></td>
				<td><input type="text" style="width:160px;border:none" id="txt2" disabled/></td>
			</tr>
			<tr>
				<td colspan="3"><input multiple="multiple" type="file" name="file" maxlength="60" size="40"></td>
				<td><input id="update" onclick="javascript:header.pageSubmitFn('inventoryUpdate','frm')" style="float:right" type="button" value="UPDATE"/></td>
				<td><input id="insert" onclick="javascript:header.pageSubmitFn('inventoryInsert','frm')" style="float:right" type="button"value="INSERT"/></td>
			</tr>
<!-- 		</form> -->
<!-- 		<form action="MultiUpload.do" id="frmfile" method="post" enctype="multipart/form-data"> -->
<!-- 		</form>	 -->
	</table>
</div>



	<table class="table table-list-search" style="margin:15px">
		<thead>
			<tr>
				<th>ID</th>
				<th>NAME</th>
				<th>CATEGORY</th>
				<th>PRICE</th>
				<th>QUANTITY</th>
				<th>BRAND</th>
				<th>COLOR</th>
				<th>DETAIL</th>
				<th>DATE</th>
				<th>SOLD COUNT</th>
			</tr>
		</thead>


		<c:forEach items="${listVO }" var="list">
		<tr class="p_vo">
			<td>${list.p_id}</td>
			<td>${list.p_name}</td>
			<td>${list.p_cat}</td>
			<td>${list.p_price}</td>
			<td>${list.p_quan}</td>
			<td>${list.p_brand}</td>
			<td>${list.p_color}</td>
			<td>${list.p_detail}</td>
			<td>${list.p_date}</td>
			<td>${list.soldcount}</td>
		</tr>
		</c:forEach>
	</table>
</div>


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
	<script src="js/a_js/easypiechart-data.js"></script>
	<script src="js/a_js/bootstrap-datepicker.js"></script>
	<script src="js/a_js/custom.js"></script>

</body>
</html>
