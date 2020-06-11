<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.List"%>
<%@page import="com.javassem.domain.ProductVO"%>

<!DOCTYPE html>
<html lang="en">




<head>

<%
//전체 페이지

int totalpage = (Integer) request.getAttribute("totalpage");

//선택된 브랜드들의 리스트
List<String> select = (List)request.getAttribute("brand");

//브랜드전체목록 한줄로
String rawbrand = (String)request.getAttribute("rawbrand");

//색을 선택
String selectcolor = (String)request.getAttribute("selectcolor");

//현재 카테고리
String nowcate = (String)request.getAttribute("nowcat");

//현재 갯수
int nowquan = Integer.parseInt((String)request.getAttribute("nowquan"));


//현재 페이지
int nowpage = Integer.parseInt((String)request.getAttribute("nowpage"));



int startrow = (nowquan*nowpage)-nowquan+1;


//가격검색에 대한 정보

int startprice = (Integer)request.getAttribute("startprice");
int endprice =  (Integer)request.getAttribute("endprice");

//선택된 정렬기준
int selectorder = (Integer)request.getAttribute("selectorder");

//결과로 받아온 상품리스트
List<ProductVO> result = (List) request.getAttribute("details");


%>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title  -->
<title>Coziness - Furniture For Your Home</title>

<!-- Favicon  -->
<link rel="icon" href="img/core-img/favicon.ico">

<!-- Core Style CSS -->
<link rel="stylesheet" href="css/core-style.css">
<!-- <link rel="stylesheet" href="style.css"> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="./resources/js/login-userInput.js"></script>
</head>

<body>
<!--다음페이지 요청을 위한 현재 페이지 정보  -->
	<input type="hidden" id="p_cat" value=<%=request.getAttribute("nowcat")%>>
	<input type="hidden" id="page" value=<%=request.getAttribute("nowpage")%>>
	<input type="hidden" id="itemQuan" value=<%=request.getAttribute("nowquan")%>>
	<input type="hidden" id="rawbrand" value="<%=request.getAttribute("rawbrand") %>">
	<input type="hidden" id="selectcolor" value=<%=selectcolor%>>
	<input type="hidden" id="startprice" value=<%=startprice %>>
	<input type="hidden" id="endprice" value=<%=endprice%>>
	<% if(request.getAttribute("search")!=null){%>
	<input type="hidden" id="search" value="<%=request.getAttribute("search")%>">
			
	<%} %>
	<!-- Search Wrapper Area Start -->
	<div class="search-wrapper section-padding-100">
		<div class="search-close">
			<i class="fa fa-close" aria-hidden="true"></i>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="search-content">
						<form action="shop.do" method="get">
							<input type="search" name="search" 
								placeholder="Type your keyword...">
							<button type="submit">
								<img src="img/core-img/search.png" alt="">
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Search Wrapper Area End -->

	<!-- ##### Main Content Wrapper Start ##### -->
	<div class="main-content-wrapper d-flex clearfix">

		<!-- Mobile Nav (max width 767px)-->
		<div class="mobile-nav">
			<!-- Navbar Brand -->
			<div class="amado-navbar-brand">
				<a href="index.do"><img src="img/core-img/logo.png" alt=""></a>
			</div>
			<!-- Navbar Toggler -->
			<div class="amado-navbar-toggler">
				<span></span><span></span><span></span>
			</div>
		</div>

		<!-- Header Area Start -->
		<header class="header-area clearfix">
			<!-- Close Icon -->
			<div class="nav-close">
				<i class="fa fa-close" aria-hidden="true"></i>
			</div>
			<!-- Logo -->
			<div class="logo">
				<a href="index.do"><img src="img/core-img/logo.png" alt=""></a>
			</div>
			<!-- Amado Nav -->
			<nav class="amado-nav">
				<ul>
					<li><a href="index.do">Home</a></li>
					<li class="active"><a href="shop.do">Shop</a></li>

					<li><a href="cart.do">Cart</a></li>
					
				</ul>
			</nav>
			<!-- Button Group -->
			<div class="amado-btn-group mt-30 mb-100">
				<a href="#"></a> <a href="#"
					></a>
			</div>
			<!-- Cart Menu -->
			<div class="cart-fav-search mb-100">
				<% if((String)session.getAttribute("userId")==null){%>                                      
                   <a href="login.do"> Login</a></li>
                   
                    <%}else if(session.getAttribute("userId").equals("admin")) {%>
                     ${userId}님 </li>     
                    <a href="logout.do" >[ Logout ]</a></li>
                    <a href="managerwindow.do" >[ manage ]</a></li>
                    
                    <% }else{ %>
                     ${userId}님 </li>     
                    <a href="logout.do" >[ Logout ]</a></li>
                    <a  href="member-info.do" >[ edit profile ]</a></li>
                    <a  href="my-order.do" >[ my order ]</a></li>
                    <%}//end of if %> 
				<a href="cart.do" class="cart-nav"><img
					src="img/core-img/cart.png" alt=""> Cart <span>(<%= request.getCookies().length-1 %>)</span></a> <a
					href="wishlist.do" class="fav-nav"><img
					src="img/core-img/favorites1.png" alt=""> Favourite</a> <a
					href="#" class="search-nav"><img src="img/core-img/search.png"
					alt=""> Search</a>
			</div>
			<!-- Social Button -->
			<div class="social-info d-flex justify-content-between">
				<a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a> <a
					href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a> <a
					href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a> <a
					href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
			</div>
		</header>
		<!-- Header Area End -->

		<div class="shop_sidebar_area">

			<!-- ##### Single Widget ##### -->
			<div class="widget catagory mb-50">
				<!-- Widget Title -->
				<h6 class="widget-title mb-30">Catagories</h6>

				<!--  Catagories  -->
				<div class="catagories-menu">
					<ul>
						<li class="active"><a href="shop.do?startprice=<%=startprice%>&endprice=<%=endprice%>">All Products</a></li>
						<li><a href="shop.do?p_cat=chair&startprice=<%=startprice%>&endprice=<%=endprice%>">Chairs</a></li>
						<li><a href="shop.do?p_cat=bed&startprice=<%=startprice%>&endprice=<%=endprice%>">Beds</a></li>
						<li><a href="shop.do?p_cat=furniture&startprice=<%=startprice%>&endprice=<%=endprice%>">Furniture</a></li>
						<li><a href="shop.do?p_cat=dressings&startprice=<%=startprice%>&endprice=<%=endprice%>">Dressings</a></li>
						<li><a href="shop.do?p_cat=table&startprice=<%=startprice%>&endprice=<%=endprice%>">Tables</a></li>
					</ul>
				</div>
			</div>

			<!-- ##### Single Widget ##### -->
			<div class="widget brands mb-50">
				<!-- Widget Title -->
				<h6 class="widget-title mb-30">Brands</h6>


				
				<div class="widget-desc">
					<!-- Single Form Check -->
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value="Amado"
							id="amado"<%if(select!=null) {%><% if(select.contains("Amado")){%>checked<%}}%>> <label class="form-check-label" for="amado">Amado</label>
					</div>
					<!-- Single Form Check -->
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value="Ikea" id="ikea" <%if(select!=null) {%><% if(select.contains("Ikea")){%>checked<%}}%>>
						<label class="form-check-label" for="ikea">Ikea</label>
					</div>
					<!-- Single Form Check -->
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value="Furniture Inc"
						
							id="furniture" <%if(select!=null) {%><% if(select.contains("Furniture Inc")){%>checked<%}}%>> <label class="form-check-label"
							for="furniture">Furniture Inc</label>
					</div>
					<!-- Single Form Check -->
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value="The factory"
							id="factory" <%if(select!=null) {%><% if(select.contains("The factory")){%>checked<%}}%>> <label class="form-check-label"
							for="factory">The factory</label>
					</div>
					<!-- Single Form Check -->
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value="Artdeco"
							id="artdeco" <%if(select!=null) {%><% if(select.contains("Artdeco")){%>checked<%}}%>> <label class="form-check-label"
							for="artdeco">Artdeco</label>
					</div>
				</div>
			</div>

			<!-- ##### Single Widget ##### -->
			<div class="widget color mb-50">
				<!-- Widget Title -->
				<h6 class="widget-title mb-30">Color</h6>
				
				<div class="widget-desc">
					<ul class="d-flex">
						<%if(selectcolor!=null){ %>
						<li>now<a class="<%=selectcolor%>"></a></li><li></li><li><li>
						<%} %>
						<li><a href="shop.do?itemQuan=<%=request.getAttribute("nowquan")%><%if(rawbrand!=null){%>&p_brand=<%=rawbrand%><%}%>&p_cat=<%=nowcate%>&page=<%=request.getAttribute("nowpage")%>&p_color=white" class="white"></a></li>
						<li><a href="shop.do?itemQuan=<%=request.getAttribute("nowquan")%><%if(rawbrand!=null){%>&p_brand=<%=rawbrand%><%}%>&p_cat=<%=nowcate%>&page=<%=request.getAttribute("nowpage")%>&p_color=gray" class="gray"></a></li>
						<li><a href="shop.do?itemQuan=<%=request.getAttribute("nowquan")%><%if(rawbrand!=null){%>&p_brand=<%=rawbrand%><%}%>&p_cat=<%=nowcate%>&page=<%=request.getAttribute("nowpage")%>&p_color=black" class="black"></a></li>
						<li><a href="shop.do?itemQuan=<%=request.getAttribute("nowquan")%><%if(rawbrand!=null){%>&p_brand=<%=rawbrand%><%}%>&p_cat=<%=nowcate%>&page=<%=request.getAttribute("nowpage")%>&p_color=blue" class="blue"></a></li>
						<li><a href="shop.do?itemQuan=<%=request.getAttribute("nowquan")%><%if(rawbrand!=null){%>&p_brand=<%=rawbrand%><%}%>&p_cat=<%=nowcate%>&page=<%=request.getAttribute("nowpage")%>&p_color=red" class="red"></a></li>
						<li><a href="shop.do?itemQuan=<%=request.getAttribute("nowquan")%><%if(rawbrand!=null){%>&p_brand=<%=rawbrand%><%}%>&p_cat=<%=nowcate%>&page=<%=request.getAttribute("nowpage")%>&p_color=yellow" class="yellow"></a></li>
						<li><a href="shop.do?itemQuan=<%=request.getAttribute("nowquan")%><%if(rawbrand!=null){%>&p_brand=<%=rawbrand%><%}%>&p_cat=<%=nowcate%>&page=<%=request.getAttribute("nowpage")%>&p_color=green" class="green"></a></li>
						<li><a href="shop.do?itemQuan=<%=request.getAttribute("nowquan")%><%if(rawbrand!=null){%>&p_brand=<%=rawbrand%><%}%>&p_cat=<%=nowcate%>&page=<%=request.getAttribute("nowpage")%>&p_color=brown" class="brown"></a></li>
					</ul>
					<a href="shop.do?itemQuan=<%=request.getAttribute("nowquan")%><%if(rawbrand!=null){%>&p_brand=<%=rawbrand%><%}%>&p_cat=<%=nowcate%>&page=<%=request.getAttribute("nowpage")%>&startprice=<%=startprice%>&endprice=<%=endprice%>">reset</a>
				</div>
			</div>

			<!-- ##### Single Widget ##### -->
			<div class="widget price mb-50">
				<!-- Widget Title -->
				<h6 class="widget-title mb-30">Price</h6>

				<div class="widget-desc">
					<div class="slider-range">
						<div data-min="1000" data-max="25000" data-unit="$"
							class="slider-range-price ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all"
				 			data-value-min="1000" data-value-max="25000" data-label-result="">
							<div class="ui-slider-range ui-widget-header ui-corner-all"></div>
							<span class="ui-slider-handle ui-state-default ui-corner-all"
								tabindex="0"></span> <span
								class="ui-slider-handle ui-state-default ui-corner-all"
								tabindex="0"></span>
						</div>
						<div class="range-price">$<%=startprice %> - $<%=endprice %></div>
						
				 
						<form action="shop.do?">
						<input name="itemQuan" type ="hidden" value="<%=request.getAttribute("nowquan")%>">
						<%if(rawbrand!=null){%><input name="p_brand" type ="hidden" value="<%=rawbrand%>"><%}%>
						<%if(nowcate!=null && !nowcate.equals("null")){%><input name="p_cat" type ="hidden" value="<%=nowcate%>"><%}%>	
						<input id="startprices" name="startprice" type ="hidden" value=<%=startprice%>>
						<input id="endprices" name="endprice" type ="hidden" value=<%=endprice%>>
						<%if(selectcolor!=null){%><input name="p_color" type ="hidden" value=<%=selectcolor%>><%}%>
						
						<input type="submit" class="price-search" value="search">
						</form>
						
					</div>
				</div>
			</div>
		</div>

		<div class="amado_product_area section-padding-100">
			<div class="container-fluid">

				<div class="row">
					<div class="col-12">
						<div
							class="product-topbar d-xl-flex align-items-end justify-content-between">
							<!-- Total Products -->
							<div class="total-products">
							<!--카테고리 , 브랜드 , -->
							<%if(request.getAttribute("search")!=null && !request.getAttribute("search").equals("undefined")) { %>
							<h3>"<%=request.getAttribute("search") %>" 검색결과</h3>
							<%} %>
								<p><h6>Showing <%if(nowcate!=null&& !nowcate.equals("null")){ %><%=nowcate %><% }else{%>All<%}%> <%=startrow%>- <%if((nowquan*nowpage)<(Integer)request.getAttribute("totalitems")){%><%=nowquan*nowpage%><%}else{%><%=request.getAttribute("totalitems")%><%}%> 0f <%=request.getAttribute("totalitems")%><h6></p>
								<div class="view d-flex">
									<a href="#"><i class="fa fa-th-large" aria-hidden="true"></i></a>
									
								</div>
							</div>
							<!-- Sorting -->
							<div class="product-sorting d-flex">
								<div class="sort-by-date d-flex align-items-center mr-15">
									<p>Sort by</p>
									<form id="sortitem" action="shop.do" method="get">
										<select name="ordermethod" id="sortBydate">
											<option name=ordermethod value="2" <% if(selectorder==0){ %>selected="selected"<%} %>>Newest</option>										
											<option name=ordermethod value="1"<% if(selectorder==1){ %>selected="selected"<%} %>>Popular</option>
										</select>
											<input name="itemQuan" type ="hidden" value="<%=request.getAttribute("nowquan")%>">
											<%if(rawbrand!=null){%><input name="p_brand" type ="hidden" value="<%=rawbrand%>"><%}%>
											<%if(nowcate!=null && !nowcate.equals("null")){%><input name="p_cat" type ="hidden" value="<%=nowcate%>"><%}%>	
											<input id="startprices" name="startprice" type ="hidden" value=<%=startprice%>>
											<input id="endprices" name="endprice" type ="hidden" value=<%=endprice%>>
											<%if(selectcolor!=null){%><input name="p_color" type ="hidden" value=<%=selectcolor%>><%}%>
						
									</form>
								</div>
								
								
								<div class="view-product d-flex align-items-center">
									<p>View</p>
									<form action="shop.do" method="get">
										<select name="itemquan" id="viewProduct">
											<option <% if(request.getAttribute("nowquan").equals("4")){ %>selected="selected"<%} %> value="4">4</option>	
											<option <% if(request.getAttribute("nowquan").equals("8")){ %>selected="selected"<%} %> value="8">8</option>
											<option <% if(request.getAttribute("nowquan").equals("12")){ %>selected="selected"<%} %> value="12">12</option>
										</select>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">

					<!-- Single Product Area  ---------------------start-->

					<%
					if(result.size()>0){
						for (int i = 0; i < result.size(); i++) {
					%>
					<div class="col-12 col-sm-6 col-md-12 col-xl-6">
						<div class="single-product-wrapper">
							<!-- Product Image -->
							<div class="product-img">
								<img
									src="img/product-img/<%=result.get(i).getP_cat()%><%=result.get(i).getP_id()%>_4.jpg"
									alt="">
								<!-- Hover Thumb -->
								<img class="hover-img"
									src="img/product-img/<%=result.get(i).getP_cat()%><%=result.get(i).getP_id()%>_4.jpg"
									alt="">
							</div>

							<!-- Product Description -->
							<div
								class="product-description d-flex align-items-center justify-content-between">
								<!-- Product Meta Data -->
								<div class="product-meta-data">
									<div class="line"></div>

									<p class="product-price">
										$<%=result.get(i).getP_price()%></p>
									<a href="product-details.do?p_id=<%=result.get(i).getP_id()%>">
										<h3><%=result.get(i).getP_name()%></h3>
										 <a href="shop.do?p_brand=<%=result.get(i).getP_brand()%>"><h6><%=result.get(i).getP_brand() %><h6></a>



									</a>
								</div>
								<!-- Ratings & Cart -->
								<div class="ratings-cart text-right">
									<div class="ratings">
										<i class="fa fa-star" aria-hidden="true"></i> <i
											class="fa fa-star" aria-hidden="true"></i> <i
											class="fa fa-star" aria-hidden="true"></i> <i
											class="fa fa-star" aria-hidden="true"></i> <i
											class="fa fa-star" aria-hidden="true"></i>
									</div>
									<div class="cart">
										<a data-toggle="tooltip"
											data-placement="left" title="Add to Cart">
											<input type="hidden" name='p_id' class="p_id" value=<%=result.get(i).getP_id()%>>
											<input type="hidden" name='p_quan' class="p_quan" value=<%=result.get(i).getP_quan()%>><img
											src="img/core-img/cart.png" alt=""></a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<%
						}
					}else{
					%>	
						검색목록 없음
						
					<% }
					%>

				</div>

				<div class="row">
					<div class="col-12">
						<!-- Pagination -->
						<nav aria-label="navigation">
							<ul class="pagination justify-content-end mt-50">
								<%
									for (int i = 1; i <= totalpage; i++) {
								%>


								<li class="page-item"><a class="page-link" href="shop.do?
								startprice=<%=startprice%>&endprice=<%=endprice%>
								&itemQuan=<%=request.getAttribute("nowquan")%>
								<%if(rawbrand!=null){%>&p_brand=<%=rawbrand%><%}%>
								<%if(nowcate!=null && !nowcate.equals("null")){%>&p_cat=<%=result.get(0).getP_cat()%><%}%>
								<%if(selectcolor!=null){%>&p_color=<%=selectcolor%><%}%><%if(request.getAttribute("search")!=null){%>&search=<%=request.getAttribute("search")%><%}%>
								&page=<%=i%>"><%=i%></a></li>

								<%
									}
								%>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ##### Main Content Wrapper End ##### -->

	<!-- ##### Newsletter Area Start ##### -->
	<section class="newsletter-area section-padding-100-0">
		<div class="container">
			<div class="row align-items-center">
				<!-- Newsletter Text -->
				<div class="col-12 col-lg-6 col-xl-7">
					<div class="newsletter-text mb-100">
						<h2>
							Subscribe for a <span>25% Discount</span>
						</h2>
						<p>	
							Nulla ac convallis lorem, eget euismod nisl. Donec in
							libero sit amet mi vulputate consectetur. Donec auctor interdum
							purus, ac finibus massa bibendum nec.</p>
					</div>
				</div>
				<!-- Newsletter Form -->
				<div class="col-12 col-lg-6 col-xl-5">
					 <div class="newsletter-form mb-200 mr-100 ">
                        <form action="subemail.do" name="subemail2" id='subemail2' method="post">
<!--                             <input type="email" id="subemail1" name="subemail1" class="nl-email" placeholder="Your E-mail"> -->
                            <input  action="subemail.do" type="submit" id="subemail" value="Subscribe">
                        </form>
                    </div>
				</div>
			</div>
		</div>
	</section>
	<!-- ##### Newsletter Area End ##### -->

	<!-- ##### Footer Area Start ##### -->
	<footer class="footer_area clearfix">
		<div class="container">
			<div class="row align-items-center">
				<!-- Single Widget Area -->
				<div class="col-12 col-lg-4">
					<div class="single_widget_area">
						<!-- Logo -->
						<div class="footer-logo mr-50">
							<a href="index.do"><img src="img/core-img/logo2.png" alt=""></a>
						</div>
						<!-- Copywrite Text -->
						<p class="copywrite">
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;
							<script>
								document.write(new Date().getFullYear());
							</script>
							All rights reserved | This template is made with <i
								class="fa fa-heart-o" aria-hidden="true"></i> by <a
								href="https://colorlib.com" target="_blank">Colorlib</a> &
							Re-distributed by <a href="https://themewagon.com/"
								target="_blank">Themewagon</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</div>
				</div>
				<!-- Single Widget Area -->
				<div class="col-12 col-lg-8">
					<div class="single_widget_area">
						<!-- Footer Menu -->
						<div class="footer_menu">
							<nav class="navbar navbar-expand-lg justify-content-end">
								<button class="navbar-toggler" type="button"
									data-toggle="collapse" data-target="#footerNavContent"
									aria-controls="footerNavContent" aria-expanded="false"
									aria-label="Toggle navigation">
									<i class="fa fa-bars"></i>
								</button>
								<div class="collapse navbar-collapse" id="footerNavContent">
									<ul class="navbar-nav ml-auto">
										<li class="nav-item active"><a class="nav-link"
											href="index.do">Home</a></li>
										<li class="nav-item"><a class="nav-link" href="shop.do">Shop</a>
										</li>

										<li class="nav-item"><a class="nav-link" href="cart.do">Cart</a>
										</li>
										
										
										
									</ul>
								</div>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- ##### Footer Area End ##### -->
	
	
	
	
	<!-- ##### jQuery (Necessary for All JavaScript Plugins) ##### -->
	<script src="js/jquery/jquery-2.2.4.min.js"></script>
	<!-- Popper js -->
	<script src="js/popper.min.js"></script>
	<!-- Bootstrap js -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Plugins js -->
	<script src="js/plugins.js"></script>
	<!-- Active js -->
	<script src="js/active.js"></script>

</body>

</html>
