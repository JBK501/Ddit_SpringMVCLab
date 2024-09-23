<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="upload" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile" />
		<button type="submit">업로드</button>
	</form>
	
	<!--  
		value : url에 쓰일값
		var : 내가 사용할 변수 
	-->
	<c:if test="${not empty uploadedFile }">
		<c:url value="download01" var="downloadUrl01">
			<c:param name="what" value="${uploadedFile.atchFileId }"/>
		</c:url>
		<h4>업로드된 파일 : <a href="${downloadUrl01}">${uploadedFile.orignlFileNm}_케이스1</a></h4>
	</c:if>
	
	
	<c:if test="${not empty uploadedFile }">
		<c:url value="download02" var="downloadUrl02">
			<c:param name="what" value="${uploadedFile.atchFileId }"/>
		</c:url>
		<h4>업로드된 파일 : <a href="${downloadUrl02}">${uploadedFile.orignlFileNm}_케이스2</a></h4>
	</c:if>
	
</body>
</html>