(function($) {
	'use strict';

	
	var $window = $(window);

	// :: 1.0 Masonary Gallery Active Code

	var proCata = $('.amado-pro-catagory');
	var singleProCata = ".single-products-catagory";

	if ($.fn.imagesLoaded) {
		proCata.imagesLoaded(function() {
			proCata.isotope({
				itemSelector : singleProCata,
				percentPosition : true,
				masonry : {
					columnWidth : singleProCata
				}
			});
		});
	}

	// :: 2.1 Search Active Code
	var amadoSearch = $('.search-nav');
	var searchClose = $('.search-close');

	amadoSearch.on('click', function() {
		$('body').toggleClass('search-wrapper-on');
	});

	searchClose.on('click', function() {
		$('body').removeClass('search-wrapper-on');
	});

	// :: 2.2 Mobile Nav Active Code
	var amadoMobNav = $('.amado-navbar-toggler');
	var navClose = $('.nav-close');

	amadoMobNav.on('click', function() {
		$('.header-area').toggleClass('bp-xs-on');
	});

	navClose.on('click', function() {
		$('.header-area').removeClass('bp-xs-on');
	});

	// :: 3.0 ScrollUp Active Code
	if ($.fn.scrollUp) {
		$.scrollUp({
			scrollSpeed : 1000,
			easingType : 'easeInOutQuart',
			scrollText : '<i class="fa fa-angle-up" aria-hidden="true"></i>'
		});
	}

	// :: 4.0 Sticky Active Code
	$window.on('scroll', function() {
		if ($window.scrollTop() > 0) {
			$('.header_area').addClass('sticky');
		} else {
			$('.header_area').removeClass('sticky');
		}
	});

	// :: 5.0 Nice Select Active Code
	if ($.fn.niceSelect) {
		$('select').niceSelect();
	}

	// :: 6.0 Magnific Active Code
	if ($.fn.magnificPopup) {
		$('.gallery_img').magnificPopup({
			type : 'image'
		});
	}

	// :: 7.0 Nicescroll Active Code
	if ($.fn.niceScroll) {
		$(".cart-table table").niceScroll();
	}

	// :: 8.0 wow Active Code
	if ($window.width() > 767) {
		new WOW().init();
	}

	// :: 9.0 Tooltip Active Code
	if ($.fn.tooltip) {
		$('[data-toggle="tooltip"]').tooltip();
	}

	// :: 10.0 PreventDefault a Click
	$("a[href='#']").on('click', function($) {
		$.preventDefault();
	});

	// :: 11.0 Slider Range Price Active Code
	$('.slider-range-price').each(
			function() {
				var min = jQuery(this).data('min');
				var max = jQuery(this).data('max');
				var unit = jQuery(this).data('unit');
				//var value_min = jQuery(this).data('value-min');
				//var value_max = jQuery(this).data('value-max');
				var value_min = $("#startprice").val();
				var value_max = $("#endprice").val();
				var label_result = jQuery(this).data('label-result');
				var t = $(this);
				$(this).slider(
						{
							range : true,
							min : min,
							max : max,
							values : [ value_min, value_max ],
							slide : function(event, ui) {
								
								$("#startprices").val(ui.values[0]);
								$("#endprices").val(ui.values[1]);

								var result = label_result + " " + unit
										+ ui.values[0] + ' - ' + unit
										+ ui.values[1];
								console.log(t);
								t.closest('.slider-range').find('.range-price')
										.html(result);
								// 슬라이더를 움직였을 때 이 값을 hidden input에 저장함
							
							}
						});
			});

	$(".catagories-menu li").click(function(evt) {

		$(".catagories-menu li").removeClass("active");

		$(evt.target).addClass("active");

	});

	// 페이지당 아이템 갯수를 설정하는 부분
	$("#viewProduct").change(
			function(evt) {
				// 선택된 아이템 갯수를 가져옴
				var itemQuan = $(evt.target).val();

				// 카테고리와 페이지정보 그리고 브랜드 체크 여부를 가져옴
				var cat = $("#p_cat").val();
				var page = $("#page").val();
				var selectcolor = $("#selectcolor").val();
				var search = $("#search").val();

				// 아이템 갯수 선택시 브랜드 클릭 정보가 있으면
				if ($("#rawbrand").val() != "null") {

					var brand = $("#rawbrand").val();

					// 아이템 갯수 선택시 브랜드 클릭 정보가 있고 색이 있으면
					if (selectcolor != "null") {
						window.location.href = "shop.do?" + "p_cat=" + cat
								+ "&page=" + page + "&itemQuan=" + itemQuan+"&search="+search
								+ "&p_brand=" + brand + "&p_color="
								+ selectcolor
						// 아이템 갯수 선택시 브랜드 클릭 정보가 있고 색은 없으면
					} else {
						window.location.href = "shop.do?" + "p_cat=" + cat
								+ "&page=" + page + "&itemQuan=" + itemQuan+"&search="+search
								+ "&p_brand=" + brand
					}

					// 아이템 갯수 선택시 브랜드 클릭 정보가 없으면
				} else if ($("#rawbrand").val() == "null") {
					// 아이템 갯수 선택시 브랜드 클릭정보가 없고 색이 있으면
					if (selectcolor != "null") {
						window.location.href = "shop.do?" + "p_cat=" + cat
								+ "&page=" + page + "&itemQuan=" + itemQuan+"&search="+search
								+ "&p_color=" + selectcolor
						// 아이템 갯수 선택시 브랜드 클릭 정보가 없고 색도 없으면
					} else {
						window.location.href = "shop.do?" + "p_cat=" + cat
								+ "&page=" + page + "&itemQuan=" + itemQuan +"&search="+search

					}
				}

			});

	// 브랜드 별 상품을 보여주기 위한 부분
	$(".form-check input").click(
			function() {
				// 브랜드의 체크박스 객체 가져오기
				var chkbox = $(".form-check-input");
				// 브랜드를 저장할 변수
				var send_array = "";

				// 안에 체크가 있는지 확인하고 위의 변수에 붙여넣기
				for (var i = 0; i < chkbox.length; i++) {
					if (chkbox[i].checked == true) {
						send_array += chkbox[i].value + "/";
					}
				}

				// 로케이션 넘길때 사용해야할 변수들
				var itemQuan = $("#itemQuan").val();
				var cat = $("#p_cat").val();
				var selectcolor = $("#selectcolor").val();
				var startprice = $("#startprices").val();

				// 만약 브랜드에 체크된 것이 없다면 - 브랜드를 체크했다가 지워서 아무것도 없으면
				if (send_array == "") {
					// 그런데 그때 선택한 색이 있다면
					if (selectcolor != "null") {
						window.location.href = "shop.do?" + "p_cat=" + cat
								+ "&itemQuan=" + itemQuan + "&p_color="
								+ selectcolor 
						// 브랜드에 체크된것도 없고 색도 없으면
					} else {
						window.location.href = "shop.do?" + "p_cat=" + cat
								+ "&itemQuan=" + itemQuan

					}
					// 브랜드에 체크된 것이 있으면
				} else if (send_array != "") {

					// 브랜드에 체크된 것이 있고 , 선택된 색도 있으면
					if (selectcolor != "null") {
						window.location.href = "shop.do?" + "p_cat=" + cat
								+ "&itemQuan=" + itemQuan + "&p_brand="
								+ send_array + "&p_color=" + selectcolor

						// 브랜드에 체크된것은 있고 색은 없으면
					} else {
						window.location.href = "shop.do?" + "p_cat=" + cat
								+ "&itemQuan=" + itemQuan + "&p_brand="
								+ send_array;

					}
				}

			});
	
	$("#sortitem").change(function(evt){
		$("#sortitem").submit();
	});
	
	$(".cart").click(function(){
		var p_id = $(this).find($('input[name=p_id]')).val();
		var p_quan = $(this).find($('input[name=p_quan]')).val();
		
		 $.ajax({
			 type:'post', // 원래 포스트 방식
			 async: true, // 비동기 통신 실행 여부
			 url:'cartadd.do',
			 contentType:'application/x-www-form-urlencoded;charset=UTF-8', //한글 설정
			 data:"p_id="+p_id+"&p_quan="+p_quan,
			 success:function(resultData){
			 if(resultData>0)
				 {
				 	alert("장바구니 담겼습니다.");
				 }
			 else
				 {
				 	alert("재고가 없습니다~ㅜㅜ 다른 상품 살펴주세요~.");
				 }
				 
			 }
			 });
		
	});
	//댓글작성을 눌렀을 때
	$("#commentForm").submit(function(){

		
		if($("#m_id").val()=="null"){
			alert("로그인이 필요한 서비스 입니다.");
			return;
		}
	
		
		  $.ajax({
		        type:'get',
		        url : "addComment.do?",
		        data:"m_id="+$("#m_id").val()+"&p_id="+$("#p_id").val()+"&commentary="+$("#comment").val(),
		        success : function(data){
		            if(data=="success")
		            {
		                getCommentList();
		                $("#comment").val("");
		            }
		        },
		        error:function(request,status,error){
		            //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		       }
		        
		    });
		
	})
	    
	  
	
	/**
	 * 초기 페이지 로딩시 댓글 불러오기
	 */
	$(function(){
	    
	    getCommentList();
	    
	});
	 
	/**
	 * 댓글 불러오기(Ajax)
	 */
	function getCommentList(){
	    
	    $.ajax({
	        type:'GET',
	        url : "commentList.do",
	        dataType : "json",
	        data:"p_id="+$("#p_id").val(),
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
	        success : function(data){
	        	var dat =eval(data);
	        
	            var html = "";
	            var cCnt = data.length;
	            
	            if(data.length > 0){
	                
	                for(var i=0; i<data.length; i++){
	                    html += "<div>";
	                    html += "<div><table class='table'><h6><strong>"+dat[i].m_id+"</strong>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].com_date+"</h6>";
	                    html += dat[i].commentary + "<tr><td></td></tr>";
	                    
	                    html += "</table></div>";
	                    
	                    html += "</div>";
	                }
	                
	            } else {
	                
	                html += "<div>";
	                html += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
	                html += "</table></div>";
	                html += "</div>";
	                
	            }
	            
	            $("#cCnt").html(cCnt);
	            $("#commentList").html(html);
	            
	        },
	        error:function(request,status,error){
	            
	       }
	        
	    });
	}
	 

	

	// 가격 조정 슬라이드를 사용하고 아래에 search 를 했을 때
//	$(".price-search").click(
//			function() {
//
//				window.location.href = "shop.do?"+"p_cat=" + cat
//				+ "&itemQuan=" + itemQuan + "&p_brand=" + send_array;
//				alert();
//
//				// 시작값 끝 값을 가져옴
//				var startprice = $("#startprice").val();
//				var endprice = $("#endprice").val();
//
//				alert()
//				// 넘겨야할 모든 값을 가져옴
//				var itemQuan = $("#itemQuan").val();
//				var cat = $("#p_cat").val();
//				var selectcolor = $("#selectcolor").val();
//				var brand = $("#rawbrand").val();
//
//				// 넘기기
//				// window.location.href = "shop.do?" + "p_cat=" + cat
//				// + "&itemQuan=" + itemQuan + "&p_brand="
//				// +
//				// send_array+"&p_color="+selectcolor+"&p_price"+startprice+"&p_date"+endprice;
//			
//
//			});


})(jQuery);
