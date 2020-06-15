$(function(){
	 //회원가입 항목이 비었으면 alert창으로 경고 하고 보냄
	
	$('#confirm').click(function(){
		if( $.trim($("#mid").val()) == '' ){
            alert("아이디를 입력해 주세요.");
            $("#mid").focus();
            return;
        }
	 
		if( $.trim($("#mpass").val()) == '' ){
            alert("비밀번호를 입력해 주시요.");
            $("#mpass").focus();
            return;
        }
		
		//비밀번호 확인 비밀번호가 맞지않으면 경고창
    	if($.trim($('#mpass').val()) != $.trim($('#mpasshwak').val())){
    		alert("비밀번호가 일치하지 않습니다..");
    		$('#mpasshwak').focus();
    		return;
    	}
    			
		if( $.trim($("#mname").val()) == '' ){
            alert("이름를 입력해 주세요.");
            $("#mname").focus();
            return;
        }
		
		if( $.trim($("#memail").val()) == '' ){
            alert("이메일을 입력해 주세요.");
            $("#memail").focus();
            return;
        }
		
		if( $.trim($("#mtel").val()) == '' ){
            alert("전화번호를 입력해 주세요.");
            $("#mtel").focus();
            return;
        }
		
		if( $.trim($("#mpostCode").val()) == '' ){
            alert("우편번호를 입력해 주세요.");
            $("#mpostCode").focus();
            return;
        }
		//정규식들로 형식 체크함
		var emailRule =RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
		var telRule = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-([0-9]{3,4})-([0-9]{4})$/;
		if(!emailRule.test($("#memail").val())) {  
			alert("이메일이 형식에 맞지 않습니다.");
	            return false;
		}
		
		if(!telRule.test($("#mtel").val())) {            
			alert("전화번호에 맞지 않습니다.");
	            return false;
		}

		//폼아이를 불러와 넣음
		document.insertmember.submit();
		
	});
	//아이디 중복체크
	$('#mid').keyup(function(){//키가 눌릴때마다
        
		//비동기통신 = ajax
		$.ajax({
			type:'post',
			async:true,
			url:'idCheck.do',
			contentType:'application/x-www-form-urlencoded;charset=UTF-8',
			data :'mid='+$('#mid').val(),
			success:function(resultData){
				$('#idCheckResult').html(resultData);
			}
		});
       
	})
	
		 //회원정보수정 항목이 비었으면 alert창으로 경고 하고 보냄
		$('#update').click(function(){
			if( $.trim($("#mid").val()) == '' ){
	            alert("아이디를 입력해 주세요.");
	            $("#mid").focus();
	            return;
	        }
			
			if( $.trim($("#mpass").val()) == '' ){
	            alert("비밀번호를 입력해 주시요.");
	            $("#mpass").focus();
	            return;
	        }
			

	    	if($.trim($('#mpass').val()) != $.trim($('#mpasshwak').val())){
	    		alert("비밀번호가 일치하지 않습니다..");
	    		$('#mpasshwak').focus();
	    		return;
	    	}
	    	
			
			if( $.trim($("#mname").val()) == '' ){
	            alert("이름를 입력해 주세요.");
	            $("#mname").focus();
	            return;
	        }
			
			if( $.trim($("#memail").val()) == '' ){
	            alert("이메일을 입력해 주세요.");
	            $("#memail").focus();
	            return;
	        }
			
			if( $.trim($("#mtel").val()) == '' ){
	            alert("전화번호를 입력해 주세요.");
	            $("#mtel").focus();
	            return;
	        }
			
			if( $.trim($("#mpostCode").val()) == '' ){
	            alert("우편번호를 입력해 주세요.");
	            $("#mpostCode").focus();
	            return;
	        }
			
			//정규식들로 형식 체크함
			var emailRule =RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
			var telRule = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-([0-9]{3,4})-([0-9]{4})$/;
			if(!emailRule.test($("#memail").val())) {  
				alert("이메일이 형식에 맞지 않습니다.");
		            return false;
			}
			
			if(!telRule.test($("#mtel").val())) {            
				alert("전화번호에 맞지 않습니다.");
		            return false;
			}

			
			//폼아이를 불러와 넣음
			document.updatemember.submit();
		});
		
//==================================================================
	//로그인//아이디 비번 안쓰면 경고하고 다 입력하면 보냄
	$('#conf').click(function(){
		if( $.trim($("#mid").val()) == '' ){
            alert("아이디를 입력해 주세요.");
            $("#mid").focus();
            return;
        }
		
		if( $.trim($("#mpass").val()) == '' ){
            alert("비밀번호를 입력해 주시요.");
            $("#mpass").focus();
            return;
        }
		//폼아이를 불러와 넣음
		//로그인을 누르면 왼쪽 로그인텝 안보이게하고 로그아웃이랑 개인정보창 보이게하기
		document.signinmember.submit(); 
	});	
	

//==================================================================	
	//아이디찾기
	$('#id_conf').click(function(){
		if( $.trim($("#mname").val()) == '' ){
            alert("이름를 입력해 주세요.");
            $("#mname").focus();
            return;
        }
		
		if( $.trim($("#mtel").val()) == '' ){
            alert("전화번호를 입력해 주세요.");
            $("#mtel").focus();
            return;
        }
		
		
		var mtel = $('#mtel').val()
		var mname = $('#mname').val()
		
		window.location.href = "find_Id.do?" + "mtel=" + mtel
		+ "&mname=" + mname; 
		
		
		
		
//		document.findid.submit();
	});
//==================================================================	
	//sign-up-clause.jsp 전체체크
	$('#allcheck').click(function(){
		//만약 전체 선택 체크박스가 체크된상태일경우
		if($("#allcheck").prop("checked")) {
			//해당화면에 전체 checkbox들을 체크해준다
			$("input[type=checkbox]").prop("checked",true);
			// 전체선택 체크박스가 해제된 경우
		} else {
			//해당화면에 모든 checkbox들의 체크를해제시킨다.
			$("input[type=checkbox]").prop("checked",false);
		}
	});
	//sign-up-clause.jsp 둘다 체크되면 전체체크
	$('#c1').click(function(){
		//만약 두개다 체크됐다면
		if($("#c1").is(":checked") == true && $("#c2").is(":checked") == true ){
			$("#allcheck").prop("checked",true);
		}else{
			$("#allcheck").prop("checked",false);
		}
	});
	//sign-up-clause.jsp 둘다 체크되면 전체체크
	$('#c2').click(function(){
		//만약 두개다 체크됐다면
		if($("#c1").is(":checked") == true && $("#c2").is(":checked") == true ){
			$("#allcheck").prop("checked",true);
		}else{
			$("#allcheck").prop("checked",false);
		}
	});
	
	//체크항목들 전부 체크했다면 회원가입페이지로 넘어가게함
	$('#allcheckNext').hover(function(){
		//만약 두개다 체크됐다면
		if($("#c1").is(":checked") == true && $("#c2").is(":checked") == true ){
			$('#allcheckNext').attr("href","sign-up.do");
		}else{
			$('#allcheckNext').attr("href","#");
			
		}
		
	});
//==================================================================
	
	//비밀번호 찾을때 아이디를 넘기면 비밀번호 찾아줌
	$('#findPasswordNext').click(function(){
		if( $.trim($("#mid").val()) == '' ){
            alert("아이디를 입력해 주세요.");
            $("#mid").focus();
            return;
        }
		
	document.findPassword.submit();
	
	});
//==================================================================	
	//구독을 했는지 안했는지 비동기통신으로 경고창을 띄워줌
	$('#subemail').click(function(){
		
		//비동기통신 = ajax
		$.ajax({
			type:'post',
			async:true,
			url:'subemail.do',
			success:function(resultData){
				alert(resultData);
			}
		});
		
	});
	
	
	
})