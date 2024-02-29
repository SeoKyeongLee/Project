function validateForm(){
	//제목 입력 확인
	var title = document.getElementById("title").value.trim();
	if(title.length == 0){
		alert("제목을 입력하세요!");
		console.log(title);
		document.getElementById("title").focus();
		return false;
	} 
	//내용 입력 확인
	var content = document.getElementById("content").value.trim();
	if(content.length == 0){
		alert("내용을 입력하세요!");
		console.log(content);
		document.getElementById("content").focus();
		return false;
	}
	return true;
}