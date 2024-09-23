<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>파일 업로드 UI case 1</h4>
	<!-- 
		multipart : body를 여러 파트로 쪼개겠다.
		form-data : body를 폼 데이터에 맞춰서 쪼개겠다. 
	-->
	<form method="post" enctype="multipart/form-data" action="upload03">
		<input type="text" name="uploader" />
		<input type="file" name="uploadFile" accept="image/*"/>
		<input type="file" name="uploadFile" accept="image/*"/>
		<button type="submit">전송</button>
	</form>
</body>
</html>