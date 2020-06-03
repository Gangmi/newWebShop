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
		//폼아이를 불러와 넣음
		document.insertmember.submit();
		
	});
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
		document.signinmember.submit();
	});	
	
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
	
	$('#allcheckNext').hover(function(){
		//만약 두개다 체크됐다면
		if($("#c1").is(":checked") == true && $("#c2").is(":checked") == true ){
			$('#allcheckNext').attr("href","sign-up.do");
		}else{
			$('#allcheckNext').attr("href","#");
			
		}
	});
	

	
	
	
	
	
	
})