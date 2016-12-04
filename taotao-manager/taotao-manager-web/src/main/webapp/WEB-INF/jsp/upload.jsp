<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="./js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript">
</script>

	

<body>


<table border="1" align="center">
	
	<c:forEach items="${itemParamitem}" var="item" varStatus="status">
	
		 <tr>
			<th align="center" colspan="2">${item.group}</th>
		 </tr>
		 <c:forEach items="${item.params}" var="detail">
		 	<tr>
				<td width="400">${detail.k}</td>
				<td width="400">${detail.v}</td>
		 	</tr>
		 </c:forEach>
		 
	</c:forEach>

</table>
	

</body>
</html>