<%@page import="com.javassem.domain.OrderVO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ page import='com.javassem.domain.ProductVO' %>

<!DOCTYPE>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- <script src="../../resources/js/jquery/jquery.cookie.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="./resources/js/login-userInput.js"></script>

<script type="text/javascript">

</script>

<head>

</style>
<meta http-equiv="X-UA-Compatible" content="IE=edge; charset=UTF-8">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>Coziness - Furniture For Your Home</title>

    <!-- Favicon  -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="css/core-style.css">
    <!-- <link rel="stylesheet" href="style.css"> -->

</head>

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
                            <input type="search" name="search" id="search" placeholder="Type your keyword...">
                            <button type="submit"><img src="img/core-img/search.png" alt=""></button>
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
                    <li><a href="shop.do?p_cat=chair">Shop</a></li>
                    <li class="active"><a href="cart.do">Cart</a></li>
                    
                </ul>
            </nav>
            <!-- Button Group -->
            <div class="amado-btn-group mt-30 mb-100">
                <a ></a>
                <a ></a>
            </div>
            <!-- Cart Menu -->
            <div class="cart-fav-search mb-100">
           		<% if((String)session.getAttribute("userId")==null){//로그인 안했다면 나옴%>                                      
                   <a href="login.do"> Login</a></li>
                   
                    <%}else if(session.getAttribute("userId").equals("admin")) {//관리자로 접속했다면 로그아웃이랑 관리창으로 가는 텝 만듬%>
                     ${userId}님 </li>     
                    <a href="logout.do" >[ Logout ]</a></li>
                    <a href="managerwindow.do" >[ manage ]</a></li>
                    
                    <% }else{//회원이  로그인 했을때  로그아웃, 회원정보수정, 주문내역텝들을 보이게함%>
                     ${userId}님 </li>     
                    <a href="logout.do" >[ Logout ]</a></li>
                    <a  href="member-info.do" >[ edit profile ]</a></li>
                    <a  href="my-order.do" >[ my order ]</a></li>
                    <%}//end of if %> 
                    <%Cookie[] cookies = (Cookie[])request.getAttribute("cookies"); %>
                <a href="cart.do" class="cart-nav"><img src="img/core-img/cart.png" alt=""> Cart <span>(<%=cookies.length-1 %>)</span></a>
                <a href="wishlist.do" class="fav-nav"><img src="img/core-img/favorites1.png" alt=""> Favourite</a>
                <a href="#" class="search-nav"><img src="img/core-img/search.png" alt=""> Search</a>
            </div>
            <!-- Social Button -->
            <div class="social-info d-flex justify-content-between">
                <a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
            </div>
        </header>
        <!-- Header Area End -->
        
        <div class="cart-table-area section-padding-100">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-lg-8">
                        <div class="cart-title mt-50">
                            <h2>My Order</h2>
                        </div>
                        
                        <div class="cart-table clearfix">
                            <table class="table table-responsive" id='table'>
                                <thead>
                                    <tr>
                                        <th>Order number</th>
                                        <th>Date</th>                             
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Quantity</th>                                     
                                        <th>State</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <!-- 리스트들을 가져온다. -->
                                 <% List<OrderVO> list= (List<OrderVO>)request.getAttribute("orderlist");//주문내역리스트를 가져옴 %>
                                 <% List<OrderVO> listsum= (List<OrderVO>)request.getAttribute("orderlist_sum");//주문내역들의 총합을 가져옴 %>
                                <%  
                                OrderVO list_sum = new OrderVO();
								int cnt=0;
                                for(int i=0;i<list.size();i++){ 
                                	//출력리스트
                                	OrderVO vo = new OrderVO();
                                	
                                	//원본 i번째와 i-1값을 검사할때 쓰임
                                	OrderVO check = new OrderVO();
                                	
                                	vo = list.get(i);
                                	int sum = 0;
                                		//검사할때 필요함 오류 안나게하려고 함
	                                	if((i-1)>=0){
	                                		check = list.get(i-1);
	                                	}else{
	                                		check.setO_id(0);
	                                	}
                                	%> 
                                    <tr>   
                                                       	
                                        <td class="cart_product_desc">
                                       	<%if(vo.getO_id()==check.getO_id()){//주문번호가 같으면 아무것도 안찍음=> 깔끔하게 보이게 하기 위해 한번만 찍는다.%>
                                       	
                                       	<%}else{//아이디가 달라지면 찍음//주문번호가 바뀔때 줄표시를 해준다.%>
                                       	<hr> 
                                       	 <%=vo.getO_id() %>                                          
                                		<% }//end of if%>
                                           
                                        </td>
                                        <td class="cart_product_desc">
                                        <%if(vo.getO_id()==check.getO_id()){//주문번호가 같으면 아무것도 안찍음=> 깔끔하게 보이게 하기 위해 한번만 찍는다.%>
                                       	
                                       	<%}else{//아이디가 달라지면 찍음//주문번호가 바뀔때 줄표시를 해준다.%>
                                       	<hr> 
                                            <%=vo.getO_date().substring(0,10) %>
                                         <% }//end of if%>
                                        </td>
                                        <td class="cart_product_desc" >
                                        	<%if(vo.getO_id()!=check.getO_id()){//주문번호가 바뀔때 줄표시를 해준다.%>
                                       	 	<hr>                                        
                                			<% }//end of if%>
                                             <%=vo.getP_name() %>
                                        </td>
                                        <td class="cart_product_desc">
                                            <%if(vo.getO_id()!=check.getO_id()){//주문번호가 바뀔때 줄표시를 해준다.%>
                                       	 	<hr>                                        
                                			<% }//end of if%>
                                            <%=vo.getP_price() %>
                                        </td>
                                        <td class="cart_product_desc">
                                           	<%if(vo.getO_id()!=check.getO_id()){//주문번호가 바뀔때 줄표시를 해준다.%>
                                       	 	<hr>                                        
                                			<% }//end of if%>
                                            <%=vo.getCnt() %>
                                        </td>
                                        <td class="cart_product_desc">
                                        
                                         <%if(vo.getO_id()==check.getO_id()){//주문번호가 바뀔때 줄표시를 해준다.%>
                                       	<%}else{%>
                                            <hr> 
                                            <%=vo.getO_delivery() %>
                                           <% }//end of if%>
                                        </td>  
                                         <td class="cart_product_desc">
                                        
                                         <%if(vo.getO_id()==check.getO_id()){//주문번호가 바뀔때 줄표시를 해준다.%>
                                         
                                       	<%}else{%>
                                            <hr> 
                                            <%=listsum.get(cnt).getP_price() %>
                                            <%cnt++;//아이디가 달라지는 순간 증가시켜서 주문번호에 맞는 총합을 가져옴 %>
                                           <% }//end of if%>
                                        </td> 
                                  
                                  
                                    <%}//end of for %> 
                                </tbody>
                            </table>
                        </div>
                         
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
                        <h2>Subscribe for a <span>25% Discount</span></h2>
                         <p>Subscribe to receive the latest information from our store. And it is 25% discount for one time only.</p>
                    </div>
                </div>
                <!-- Newsletter Form -->
                <div class="col-12 col-lg-6 col-xl-5">
                     <div class="newsletter-form mb-200 mr-100 ">
                       <form  name="subemail2" id='subemail2' method="post">          
                            <input   type="submit" id="subemail" value="Subscribe">
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
                        <p class="copywrite"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->& Re-distributed by <a href="https://themewagon.com/" target="_blank">Themewagon</a>
</p>
                    </div>
                </div>
                <!-- Single Widget Area -->
                <div class="col-12 col-lg-8">
                    <div class="single_widget_area">
                        <!-- Footer Menu -->
                        <div class="footer_menu">
                            <nav class="navbar navbar-expand-lg justify-content-end">
                                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#footerNavContent" aria-controls="footerNavContent" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars"></i></button>
                                <div class="collapse navbar-collapse" id="footerNavContent">
                                    <ul class="navbar-nav ml-auto">
                                        <li class="nav-item active">
                                            <a class="nav-link" href="index.do">Home</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="shop.do">Shop</a>
                                        </li>
                                        
                                        <li class="nav-item">
                                            <a class="nav-link" href="cart.do">Cart</a>
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