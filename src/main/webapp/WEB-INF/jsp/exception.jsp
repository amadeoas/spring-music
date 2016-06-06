<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="music" tagdir="/WEB-INF/tags" %>

<html lang="en">
<jsp:include page="fragments/htmlHeader.jsp"/>

<body>
<music:bodyHeader menuName="error"/>
<div class="container-fluid">
    <div class="container xd-container">
		<div class="left">
			<div>
		        <spring:url value="/resources/images/music.png" var="cpImage"/>
		        <img src="${cpImage}"/>
	        </div>
        </div>

		<div class="right">
	        <h2>Something happened...</h2>
	
	        <p>${exception.message}</p>
	
	        <!-- Exception: ${exception.message}.
			  	<c:forEach items="${exception.stackTrace}" var="stackTrace"> 
					${stackTrace} 
				</c:forEach>
		  	-->
	  	</div>
    </div>
    <music:footer/>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>

</html>
