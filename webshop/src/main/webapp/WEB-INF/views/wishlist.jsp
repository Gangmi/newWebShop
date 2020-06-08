<%@page contentType="text/html; charset=UTF-8"%>
<%@ page import='com.javassem.domain.ProductVO' %>
<%@page import="java.util.List"%>
<!DOCTYPE>
<html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">


$(function(){
    $("#selectall").click(function(){
        var chk = $(this).is(":checked");//.attr('checked');
        if(chk) $(".check").prop('checked', true);
        else  $(".check").prop('checked', false);
    });

    $('#addtocart').click(function(){
    	var id = new Array();

    	$(".check:checked").each(function (index) {  
            id.push($(this).parents().prevAll(".quantity").find(".p_id").val());
            alert($(this).parents().prevAll(".quantity").find(".p_id").val());
       });

    	window.location.href = "wishtocart.do?id="+id;
        });

    $("#delsel").click(function(){
    	var str="";
        $(".check:checked").each(function (index) {  
            str += $(this).val()+",";  
        });  
        $("#delstr").val(str);
    });
});

</script>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge; charset=UTF-8">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>Amado - Furniture Ecommerce Template | Cart</title>

    <!-- Favicon  -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="css/core-style.css">
    <!-- <link rel="stylesheet" href="style.css"> -->

</head>

<body>
<%
List<ProductVO> list =(List<ProductVO>) request.getAttribute("list");
%>
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
                    <li><a href="shop.do">Shop</a></li>
                  
                    <li><a href="cart.do">Cart</a></li>
                    
                </ul>
            </nav>
            <!-- Button Group -->
             <div class="amado-btn-group mt-30 mb-100">
                <a ></a>
                <a ></a>
            </div>
            <!-- Cart Menu -->
            <div class="cart-fav-search mb-100">
            		<% if((String)session.getAttribute("userId")==null){%>                                      
                   <a href="login.do"> Login</a></li>
                   
                    <%}else{ %>
                     ${userId}님 </li>     
                    <a href="logout.do" >[ Logout ]</a></li>
                    <a  href="member-info.do" >[ edit profile ]</a></li>
                    <%} %>
                <a href="cart.do" class="cart-nav"><img src="img/core-img/cart.png" alt=""> Cart <span>(<%= request.getCookies().length-1 %>)</span></a>
                <a href="wishlist.do" class="fav-nav"><img src="img/core-img/favorites.png" alt=""> Favourite</a>
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
                            <h2>Wish List</h2>
                        </div>
							<form action="wishlist.do" method="post"> 
                        	
							
							<div style = "float:right;">
							<input type='submit' id='delsel' value='Delete'  />
							</div>
							<input type="hidden" value='hi' id="delstr" name='delstr' class='delstr'/>
							<br>
							</form> 
                        <div class="cart-table clearfix">
                            <table class="table table-responsive">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Select</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%  for(int i=0;i<list.size();i++){ 
                                	ProductVO vo = new ProductVO();
                                	vo = list.get(i);
                                %>
                                	
                                    <tr>
                                        <td class="cart_product_img">
                                            <a href="img/product-img/<%=vo.getP_cat()%><%=vo.getP_id()%>_1.jpg"><img src="img/product-img/<%=vo.getP_cat()%><%=vo.getP_id()%>_1.jpg" alt="Product"></a>
                                        </td>
                                        <td class="cart_product_desc">
                                            <h5><%=vo.getP_name() %></h5>
                                        </td>
                                        <td class="price" >
                                            <span>$<%=vo.getP_price() %></span>
                                        </td>
                                        <td class="qty">
                                            <div class="qty-btn d-flex">
                                                
                                                <div class="quantity">
                                                    
                                                    <input type="hidden" value='<%=vo.getP_price() %>' name='p_price' class='price'/>
                                                    <input type="hidden" value='<%=vo.getP_id() %>' name='p_id' class='p_id'/>
                                                   
                                                </div>
                                                &nbsp;
                                                <label><input type="checkbox" name="check" class='check'  checked="checked" value='<%=vo.getP_id()%>' ></label>
                                            </div>
                                        </td>
                                    </tr>
                                    <%  } %>
                                    
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-12 col-lg-4">
                        <div class="cart-summary">
                            <h5>Select Product<h5>
                            <ul class="summary-table">
                                <li> <label><input type="checkbox" name="selectall" id='selectall' value="blue"> All Select</label></li>
                            </ul>
                            <div class="cart-btn mt-100">
                                <a href="cart.do" id ='addtocart' class="btn amado-btn w-100">Add To Cart</a>
                            </div>
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
                        <p>Nulla ac convallis lorem, eget euismod nisl. Donec in libero sit amet mi vulputate consectetur. Donec auctor interdum purus, ac finibus massa bibendum nec.</p>
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
                                        
                                        <li class="nav-item">
                                            <a class="nav-link" href="login.do">Login</a>
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