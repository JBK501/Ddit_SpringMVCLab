<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.text-danger{
		color: red;
	}
</style>
</head>
${requestScope['org.springframework.validation.BindingResult.board']}
<body>
	<form:form modelAttribute="board">
		<pre>
			<form:input path="boardNo" type="number" required="required"/>
			<form:errors path="boardNo" cssClass="text-danger" element="span"/>
			<form:input path="boardTitle" required="true"/>
			<form:errors path="boardTitle" cssClass="text-danger" element="span" />
			<form:input path="boardWriter" placeholder="작성자" required="true"/>
			<form:errors path="boardWriter" cssClass="text-danger" element="span" />
			<form:input path="boardDate" type="datetime-local"/>
			<form:errors path="boardDate" cssClass="text-danger" element="span" />
			<form:textarea path="boardContent" minlength="5"/>
			<form:errors path="boardContent" cssClass="text-danger" element="span" />
			<button type="submit" class="btn btn-primary">전송</button>
		</pre>
	</form:form>
</body>
</html>