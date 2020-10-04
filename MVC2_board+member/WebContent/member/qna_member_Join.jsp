<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../Top.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">

	function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();
    }

	function check() {

		var number1 = document.getElementById("number1");
		var number2 = document.getElementById("number2");

		var year = document.getElementById("year");
		var month = document.getElementById("month");
		var day = document.getElementById("day");

		
		//주민등록번호 > 생일 자동 입력
		
		if (number1.value.length > 0 || number1.value.length < 7) {

			var a1 = number1.value.substring(0, 2);//앞자리 년 
			var a2 = number1.value.substring(2, 4);//앞자리 월
			var a3 = number1.value.substring(4, 6);//앞자리 일
			var a4 = number2.value.substring(0, 1);//뒷자리 1글자   
			var total;
			//뒷번호 앞자리가 0 ,9 이면 1800년생
			if (0 == a4 || 9 == a4) {
				total = 18 + a1;
			}
			//뒷번호 앞자리가 1,2 이면 1900년생
			else if (1 == a4 || 2 == a4) {
				total = 19 + a1;
			}
			//뒷번호 앞자리가 3 ,4 이면 2000년생
			else if (3 == a4 || 4 == a4) {
				total = 20 + a1;
			}
			year.value = total;
			month.value = a2;
			day.value = a3;
		}
		
		
		//주민등록번호 유효성 검사 공식
		chk1  = number1.value.charAt(0) * 2;
		chk2  = number1.value.charAt(1) * 3;
		chk3  = number1.value.charAt(2) * 4;
		chk4  = number1.value.charAt(3) * 5;
		chk5  = number1.value.charAt(4) * 6;
		chk6  = number1.value.charAt(5) * 7;
		chk7  = number2.value.charAt(0) * 8;
		chk8  = number2.value.charAt(1) * 9;
		chk9  = number2.value.charAt(2) * 2;
		chk10 = number2.value.charAt(3) * 3;
		chk11 = number2.value.charAt(4) * 4;
		chk12 = number2.value.charAt(5) * 5;
		
		chk13 = number2.value.charAt(6);	// 주민등록 번호 마지막 번호
		
		num = chk1+chk2+chk3+chk4+chk5+chk6+chk7+chk8+chk9+chk10+chk11+chk12;
		
		numChk = 11 - (num % 11);
		
		
		//유효성 검사 시작
		
		if (idid.value.length<4 || idid.value.length>12) {
			alert("ID의 길이가 올바르지 않습니다.");
			return false;
		}
		for (i = 0; i < idid.value.length; i++) {
			ch = idid.value.charAt(i);
			if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z')) {
				alert("ID가 올바르지 않습니다.");
				return false;
			}
		}
		if (pw.value.length<4 || pw.value.length>12) {
			alert("비밀번호의 길이가 올바르지 않습니다.");
			return false;
		}
		for (i = 0; i < pw.value.length; i++) {
			ch = pw.value.charAt(i);
			if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z')) {
				alert("비밀번호가 올바르지 않습니다.");
				return false;
			}
		}
		if (idid.value == pw.value) {
			alert("ID와 비밀번호가 같습니다.");
			return false;
		}
		if (pw.value != pwchk.value) {
			alert("비밀번호를 다시 확인하세요.");
			return false;
		}
		if (mail.value == "") {
			alert("이메일을 입력하세요.");
			return false;
		}
		if (mail.value.indexOf("@") == -1) {
			alert("이메일이 올바르지 않습니다.");
			return false;
		}
		if (name1.value == "") {
			alert("이름을 입력하세요.");
			return false;
		}
		if (chk13 != numChk) {
			alert("잘못된 주민등록 번호 입니다.");
			return false;
		}
		if (my_intro.value == "") {
			alert("자기소개를 입력하세요.");
			return false;
		}
		alert("회원 가입을 축하합니다.");
	}
</script>
</head>									<!-- return false를 만나지 않으면  check() 정상 submit -->
<form action="./MemberJoinAction.me" method="post" onsubmit="return check()">
	<audio autoplay controls>
		<source src="audio/song.mp3" type="audio/mp3">
	</audio>
	<table border="1" style="border-collapse: collapse">
		<tr>
			<th colspan="2" bgcolor="skyblue">회원 기본 정보</th>
		</tr>
		<tr>
			<th>아이디</th>
			<td><input type="text" id="idid" name="idid" size="20" minlength="4" maxlength="12"> 4~12자의 영문 대소문자와 숫자로만 입력</td>
		</tr>
		<tr>
			<th>비밀 번호</th>
			<td><input type="password" id="pw" name="pw" size="20" minlength="4" maxlength="12"> 4~12자의 영문 대소문자와 숫자로만 입력</td>
		</tr>
		<tr>
			<th>비밀 번호 확인</th>
			<td><input type="password" id="pwchk" name="pwchk" size="20" minlength="4" maxlength="12"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="email" id="mail" name="mail" size="25"> 예)YoonEui@domain.com</td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" id="name1" name="name1" size="25"></td>
		</tr>
		<tr>
			<th colspan="2" bgcolor="skyblue">개인 신상 정보</th>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<input type="text" id="postcode" name="postcode" placeholder="우편번호">
				<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="address" name="address" placeholder="주소"><br>
				<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소">
				<input type="text" id="extraAddress" name="extraAddress" placeholder="참고항목">
			</td>
		</tr>
		<tr>
			<th>주민등록번호</th>
			<td><input type="text" id="number1" name="number1" size="10"> - <input type="password" id="number2" name="number2" size="10"> 예) 123456 - 1234567
			</td>
		</tr>
		<tr>
			<th>생일</th>
			<td><input type="text" id="year" name="year" size="10">년 <input type="text" id="month" name="month" size="5">월 <input type="text" id="day" name="day" size="5">일
			</td>
		</tr>
		<tr>
			<th>관심 분야</th>
			<td>
				<input type="checkbox" name="chk" value="컴퓨터">컴퓨터
				<input type="checkbox" name="chk" value="인터넷">인터넷 
				<input type="checkbox" name="chk" value="여행">여행 
				<input type="checkbox" name="chk" value="영화 감상">영화 감상 
				<input type="checkbox" name="chk" value="음악 감상">음악 감상
			</td>
		</tr>
		<tr>
			<th>자기 소개</th>
			<td><textarea id="my_intro" name="my_intro" cols="60" rows="10"></textarea></td>
		</tr>
	</table>
<pre>
				<input type="submit" value="회원 가입" > <input type="reset" value="다시 입력">
</pre>
</form>
</head>
</html>