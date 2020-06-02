$(function(){
	 
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
		
	})
	
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
	})	
	
	
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
		//폼아이를 불러와 넣음
		document.findid.submit();
	})
	
	
	
})