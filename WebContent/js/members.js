function infoConfirm(){
	if(document.reg_frm.id.value.length == 0){
		alert("아이디를 입력하세요!");
		reg_frm.id.focus(); //아이디 입력 필드로 포커스를 이동시킴
		return false;//함수 실행 중단, 종료
	}
	// 아이디 길이 확인
	if(document.reg_frm.id.value.length < 4){
		alert("아이디는 4글자 이상이어야 합니다.");
		reg_frm.id.focus();
		return false;
	}
	// 비밀번호 입력 확인
	if(document.reg_frm.pw.value.length == 0){
		alert("비밀번호를 입력하세요!");
		console.log(document.reg_frm.pw.value);
		reg_frm.pw.focus();
		return false;
	}
	// 비밀번호 확인 일치 여부 확인
	if(document.reg_frm.pw.value != document.reg_frm.pw_check.value){
		alert("비밀번호가 일치하지 않습니다.");
		reg_frm.pw_check.focus();
		return false;
	}
	// 이름 입력 확인
	if(document.reg_frm.name.value.length == 0){
		alert("이름을 입력하세요!");
		reg_frm.name.focus();
		return false;
	}
	// 이메일 입력 확인
	if(document.reg_frm.email.value.length == 0){
		alert("메일을 입력하세요!");
		reg_frm.email.focus();
		return false;
	}
	//핸드폰 번호 입력 확인
	if(document.reg_frm.phone.value.length == 0){
		alert("핸드폰 번호를 입력하세요!");
		reg_frm.phone.focus();
		return false;
	}
	//주소 입력 확인
	if(document.reg_frm.address.value.length == 0){
		alert("주소를 입력하세요!");
		reg_frm.address.focus();
		return false;
	}
}

function updateInfoConfirm(){
	// 패스워드 입력 확인
	if(document.reg_frm.pw.value == ""){
		alert("패스워드를 입력하세요.");
		document.reg_frm.pw.focus();
		return
	}
	// 비밀번호 확인 일치 여부 확인
	if(document.reg_frm.pw.value != document.reg_frm.pw_check.value){
		alert("비밀번호가 일치하지 않습니다.");
		reg_frm.pw_check.focus();
		return
	}
	// 이메일 입력 확인
	if(document.reg_frm.email.value.length == 0){
		alert("메일은 필수사항입니다.");
		reg_frm.email.focus();
		return
	}
}
