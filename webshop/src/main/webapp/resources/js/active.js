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

	// 가격슬라이더에서 변화가 일어났을 때 , 실행되는 코드
	$('.slider-range-price').each(
			function() {
				//슬라이더의 초기 값 설정
				var min = jQuery(this).data('min');
				var max = jQuery(this).data('max');
				var unit = jQuery(this).data('unit');
			
				//만약 가격검색을 해서 저장된 값이 있다면 가져오는 부분
				var value_min = $("#startprice").val();
				var value_max = $("#endprice").val();
				var label_result = jQuery(this).data('label-result');
				var t = $(this);
				//슬라이더 속성을 사용
				$(this).slider(
						{
							range : true,
							min : min,
							max : max,
							values : [ value_min, value_max ],
							//슬라이딩이 발생했을 때
							slide : function(event, ui) {
								//그때 사용자가 설정한 값을 화면의 Startprice 와 endprice로 설정
								
								$("#startprices").val(ui.values[0]);
								$("#endprices").val(ui.values[1]);

								//화면에 보여줄 값을 설정
								var result = label_result + " " + unit
										+ ui.values[0] + ' - ' + unit
										+ ui.values[1];
								// 슬라이더를 움직였을 때 해당 값을 화면에 html로 추가
								t.closest('.slider-range').find('.range-price')
										.html(result);
								
							
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
	
	// shop에서 장바구니 아이콘을 눌렀을때 재고의 유무에 따른 이벤트 처리
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
				 alert(resultData);
				 
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
	        	//String 으로 넘겨받은 데이터를 json타입으로 변환
	        	var dat =eval(data);
	        
	            var html = "";
	            var cCnt = data.length;
	            
	            if(data.length > 0){
	                //가져온 json data 를 반복문을 통해 풀어내면서 화면에 덧붙이는 코딩
	                for(var i=0; i<data.length; i++){
	                    html += "<div>";
	                    html += "<div><table class='table'><h6><strong>"+dat[i].m_id+"</strong>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].com_date+"</h6>";
	                    html += dat[i].commentary + "<tr><td></td></tr>";
	                    
	                    html += "</table></div>";
	                    
	                    html += "</div>";
	                }
	                //만약 가져온 데이터가 없다면,
	            } else {
	                
	                html += "<div>";
	                html += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
	                html += "</table></div>";
	                html += "</div>";
	                
	            }
	            // 최종적으로 위에서 만들어진 html을 붙이는 곳
	            $("#cCnt").html(cCnt);
	            $("#commentList").html(html);
	            
	        },
	        error:function(request,status,error){
	            
	       }
	        
	    });
	}
	 

	


})(jQuery);
