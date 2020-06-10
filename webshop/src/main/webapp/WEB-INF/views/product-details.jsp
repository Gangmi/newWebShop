<%@page import="com.javassem.domain.ProductVO"%>

<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% response.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE>

<head>
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






</head>


<%
	//request.setCharacterEncoding("UTF-8");

ProductVO vo = (ProductVO) request.getAttribute("product");
%>



<body>
	<!-- Search Wrapper Area Start -->
	<div class="search-wrapper section-padding-100">
		<div class="search-close">
			<i class="fa fa-close" aria-hidden="true"></i>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="search-content">
						<form action="#" method="get">
							<input type="search" name="search" id="search"
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
					<li><a href="shop.do">Shop</a></li>

					<li><a href="cart.do">Cart</a></li>

				</ul>
			</nav>
			<!-- Button Group -->
			<div class="amado-btn-group mt-30 mb-100">
				<a></a> <a></a>
			</div>
			<!-- Cart Menu -->
			<div class="cart-fav-search mb-100">
				<%
					if ((String) session.getAttribute("userId") == null) {
				%>
				<a href="login.do"> Login</a>
				</li>

				<%
					} else {
				%>
				${userId}님
				</li> <a href="logout.do">[ Logout ]</a>
				</li> <a href="member-info.do">[ edit profile ]</a>
				</li>
				<%
					}
				%>
				<a href="cart.do" class="cart-nav"><img
					src="img/core-img/cart.png" alt=""> Cart <span>(<%=request.getCookies().length - 1%>)
				</span></a> <a href="wishlist.do" class="fav-nav"><img
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

		<!-- Product Details Area Start -->
		<div class="single-product-area section-padding-100 clearfix">
			<div class="container-fluid">

				<div class="row">
					<div class="col-12">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb mt-50">
								<li class="breadcrumb-item"><a href="index.do">Home</a></li>
								<li class="breadcrumb-item"><a href="shop.do?p_cat=<%=vo.getP_cat() %>"><%=vo.getP_cat() %></a></li>
								<li class="breadcrumb-item"><%=vo.getP_name() %></li>
							</ol>
						</nav>
					</div>
				</div>

				<div class="row">
					<div class="col-12 col-lg-7">
						<div class="single_product_thumb">
							<div id="product_details_slider" class="carousel slide"
								data-ride="carousel">
								<ol class="carousel-indicators">
									<li class="active" data-target="#product_details_slider"
										data-slide-to="0"
										style="background-image: url(img/product-img/<%=vo.getP_cat()%><%=vo.getP_id()%>_1.jpg);">
									</li>
									<li data-target="#product_details_slider" data-slide-to="1"
										style="background-image: url(img/product-img/<%=vo.getP_cat()%><%=vo.getP_id()%>_2.jpg);">
									</li>
									<li data-target="#product_details_slider" data-slide-to="2"
										style="background-image: url(img/product-img/<%=vo.getP_cat()%><%=vo.getP_id()%>_3.jpg);">
								</ol>
								<div class="carousel-inner">
									<div class="carousel-item active">
										<a class="gallery_img"
											href="img/product-img/<%=vo.getP_cat()%><%=vo.getP_id()%>_1.jpg">
											<img class="d-block w-70"
											src="img/product-img/<%=vo.getP_cat()%><%=vo.getP_id()%>_1.jpg"
											alt="First slide">
										</a>
									</div>
									<div class="carousel-item">
										<a class="gallery_img"
											href="img/product-img/<%=vo.getP_cat()%><%=vo.getP_id()%>_2.jpg">
											<img class="d-block w-70"
											src="img/product-img/<%=vo.getP_cat()%><%=vo.getP_id()%>_2.jpg"
											alt="Second slide">
										</a>
									</div>
									<div class="carousel-item">
										<a class="gallery_img"
											href="img/product-img/<%=vo.getP_cat()%><%=vo.getP_id()%>_3.jpg">
											<img class="d-block w-70"
											src="img/product-img/<%=vo.getP_cat()%><%=vo.getP_id()%>_3.jpg"
											alt="Third slide">
										</a>
									</div>

								</div>
							</div>
						</div>
					</div>
					<div class="col-12 col-lg-5">
						<div class="single_product_desc">
							<!-- Product Meta Data -->
							<div class="product-meta-data">
								<div class="line"></div>
								<p class="product-price">
									$<%=vo.getP_price()%></p>
								<a href="product-details.do">
									<h6><%=vo.getP_name()%></h6>

								</a>
								<!-- Ratings & Review -->
								<div
									class="ratings-review mb-15 d-flex align-items-center justify-content-between">
									<div class="ratings">
										<i class="fa fa-star" aria-hidden="true"></i> <i
											class="fa fa-star" aria-hidden="true"></i> <i
											class="fa fa-star" aria-hidden="true"></i> <i
											class="fa fa-star" aria-hidden="true"></i> <i
											class="fa fa-star" aria-hidden="true"></i>
									</div>
									<div class="review">
										<a href="shop.do?p_brand=<%=vo.getP_brand()%>"><%=vo.getP_brand()%></a>
									</div>
								</div>
								<!-- Avaiable -->

								<p class="avaibility">
									<%
										if (vo.getP_quan() != 0) {
									%><i class="fa fa-circle"></i> In Stock<%
										} else {
									%><i
										class="fa fa-circle nostock"></i>No Stock<%
										}
									%>
								</p>

							</div>

							<div class="short_overview my-5">

								<p><%=vo.getP_detail()%></p>
							</div>

							<!-- Add to Cart Form -->

							<form action="cart.do" class="cart clearfix" method="post">

								<div class="cart-btn d-flex mb-50">
									<p>Qty</p>
									<div class="quantity">
										<span class="qty-minus"
											onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty ) &amp;&amp; qty &gt; 1 ) effect.value--;return false;"><i
											class="fa fa-caret-down" aria-hidden="true"></i></span> <input
											type="number" class="qty-text" id="qty" step="1" min="1"
											max="300" name="quantity" value="1"> <input
											type="hidden" value=<%=vo.getP_id()%> name='p_id' /> <span
											class="qty-plus"
											onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty )) effect.value++;return false;"><i
											class="fa fa-caret-up" aria-hidden="true"></i></span>
									</div>
									&nbsp; &nbsp; &nbsp;
									<div>
										<a href="wishlist.do?p_id=<%=vo.getP_id()%>" class="fav-nav"><img
											src="img/core-img/favorites1.png" alt=""></a>
									</div>
								</div>
								<%
									if (vo.getP_quan() != 0) {
								%>
								<button type="submit" name="addtocart" value="5"
									class="btn amado-btn">Add to cart</button>

								<%
									} else {
								%>
								<button name="addtocart" value="5" class="btn amado-btn"
									disabled>Sold Out</button>
								<%
									}
								%>
							</form>


						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Product Details Area End -->
	</div>
	<!-- ##### Main Content Wrapper End ##### -->
	
	<!--#################################댓글 기능 구현 ##############################################  -->
	<div class="container">
		<form id="commentForm" name="commentForm" method="post">
			<br>
			<br>
			<div>
				<div>
					<span><strong>Comments</strong></span> <span id="cCnt"></span>
				</div>
				<div>
					<table class="table">
						<tr>
							<td><textarea style="width: 1100px" rows="3" cols="30"
									id="comment" name="comment" placeholder="댓글을 입력하세요"></textarea>
								<br>
								<div>
									<button type="submit" class="btn pull-right btn-warning"
										style="color: white;">등록</button>
								</div></td>
						</tr>
					</table>
				</div>
			</div>
			<!--상품의 id와 , 작성자를 가져옴  -->
			<input type="hidden" id="m_id" name="m_id"
				value="<%=session.getAttribute("userId")%>" /> 
			<input type="hidden" id="p_id" name="p_id" value="<%=vo.getP_id()%>" />
		</form>
	</div>
	<div class="container">
		<form id="commentListForm" name="commentListForm" method="post">
			<div id="commentList"></div>
		</form>
	</div>

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
						<p>Nulla ac convallis lorem, eget euismod nisl. Donec in
							libero sit amet mi vulputate consectetur. Donec auctor interdum
							purus, ac finibus massa bibendum nec.</p>
					</div>
				</div>
				<!-- Newsletter Form -->
				<div class="col-12 col-lg-6 col-xl-5">
					<div class="newsletter-form mb-200 mr-100 ">
						<form action="subemail.do" name="subemail2" id='subemail2'
							method="post">
							<!--                             <input type="email" id="subemail1" name="subemail1" class="nl-email" placeholder="Your E-mail"> -->
							<input action="subemail.do" type="submit" id="subemail"
								value="Subscribe">
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
							<script>document.write(new Date().getFullYear());</script>
							All rights reserved | This template is made with <i
								class="fa fa-heart-o" aria-hidden="true"></i> by <a
								href="https://colorlib.com" target="_blank">Colorlib</a> &
							Re-distributed by <a href="https://themewagon.com/"
								target="_blank">Themewagon</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</p>
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
										<li class="nav-item"><a class="nav-link"
											href="shop.do?p_cat=chair">Shop</a></li>

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
