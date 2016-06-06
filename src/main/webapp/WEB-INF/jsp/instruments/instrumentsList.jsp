<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="music" tagdir="/WEB-INF/tags" %>

<html lang="${language}">
	<fmt:setLocale value="${language}"/>
	<fmt:bundle basename="messages.messages">
	<c:set var="msg_name" scope="request">
		<fmt:message key="tbName"/>
	</c:set>
	<c:set var="msg_actions" scope="request">
		<fmt:message key="tbActions"/>
	</c:set>
	<c:set var="msg_viewElement" scope="request">
		<fmt:message key="viewElement"/>
	</c:set>
	<c:set var="msg_changeElement" scope="request">
		<fmt:message key="changeElement"/>
	</c:set>
	<c:set var="msg_deleteElement" scope="request">
		<fmt:message key="deleteElement"/>
	</c:set>
	<c:set var="msg_addEqInstrument" scope="request">
		<fmt:message key="addEqInstrument"/>
	</c:set>
	<c:set var="msg_addVocalComp" scope="request">
		<fmt:message key="addVocalComp"/>
	</c:set>
	<c:set var="msg_search" scope="request">
		<fmt:message key="search"/>
	</c:set>
	<jsp:include page="../fragments/htmlHeader.jsp"/>
	
	<body onload="setLanguage('${language}', '${msg_search}', 'instruments');">
		<music:bodyHeader menuName="instruments"/>

		<div class="container-fluid">
		    <div class="container xd-container">
		        <h2><fmt:message key="instruments"/></h2>
		
		        <datatables:table id="instruments" data="${instruments}" row="instrument"
		                          cssClass="table table-striped" pageable="false" info="false">
		            <datatables:column title="${msg_name}">
		                <c:out value="${instrument.name}"/>
		            </datatables:column>
		            <datatables:column title="${msg_actions}">
		            	<a href="/music/instruments/view/${instrument.id}_${instrument.type}?lang=${language}" title="${msg_viewElement}"><span class="glyphicon glyphicon-th-list icon-blue"></span></a> <a href="/music/instruments/edit/${instrument.id}_${instrument.type}?lang=${language}" title="${msg_changeElement}"><span class="glyphicon glyphicon-pencil icon-blue"></span></a> <a href="/music/instruments/delete/${instrument.id}_${instrument.type}?lang=${language}" title="${msg_deleteElement}"><span class="glyphicon glyphicon-trash icon-blue"></span></a>
		            </datatables:column>
		        </datatables:table>
		        <div class="tb_actions">
		        	<a href="/music/instruments/addView/${instrument.id}_ei?lang=${language}" title="${msg_addEqInstrument}"><span class="glyphicon glyphicon-plus icon-blue"></span></a>
		        </div>
		
		        <music:footer/>
		    </div>
		</div>
		
		<jsp:include page="../fragments/footer.jsp"/>
	</body>
	</fmt:bundle>
</html>
