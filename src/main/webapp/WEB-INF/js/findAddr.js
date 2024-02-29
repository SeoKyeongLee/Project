//주소 검색
function findAddr(){
	console.log("findAddr 함수 호출됨")
            new daum.Postcode({
                oncomplete: function (data){
                	console.log("주소검색 완료 : ", data);
                    var addr = '';
                    if(data.userSelectedType === 'R'){
                        addr = data.roadAddress;
                    } else{
                        addr = data.jibunAddress;
                    }
                    console.log("선택된 주소 : ", addr);
                    document.getElementById("address").value = addr;
                }
            }).open();
        }