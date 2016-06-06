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
	<c:set var="msg_active" scope="request">
		<fmt:message key="tbActive"/>
	</c:set>
	<c:set var="msg_band_type" scope="request">
		<fmt:message key="tbBandType"/>
	</c:set>
	<c:set var="msg_band_set" scope="request">
		<fmt:message key="tbBandSet"/>
	</c:set>
	<c:set var="msg_gain" scope="request">
		<fmt:message key="tbGain"/>
	</c:set>
	<c:set var="msg_frequency" scope="request">
		<fmt:message key="tbFrequency"/>
	</c:set>
	<c:set var="msg_on" scope="request">
		<fmt:message key="on"/>
	</c:set>
	<c:set var="msg_off" scope="request">
		<fmt:message key="off"/>
	</c:set>
	<jsp:include page="../fragments/htmlHeader.jsp"/>
	
	<body onload="setLanguage('${language}', '${msg_search}', 'instruments');">
		<music:bodyHeader menuName="instrumentsView"/>

		<div class="container-fluid">
		    <div class="container xd-container">
		        <h2><fmt:message key="instrumentView"/> <c:out value="${instrument.name}"/></h2>

		        <datatables:table id="instruments" data="${instrument.eqSettings}" row="eqSetting"
		                          cssClass="table table-striped" pageable="false" info="false">
		            <datatables:column title="${msg_active}" property="active" />
		            <datatables:column title="${msg_band_type}" property="bandType" />
		            <datatables:column title="${msg_band_set}" property="bandSet" />
		            <datatables:column title="${msg_gain}" property="gain" />
		            <datatables:column title="${msg_frequency}" property="frequency" />
		            <datatables:column title="${msg_frequency}" property="freqUnits" />
		        </datatables:table>
		
		        <music:footer/>
		    </div>
		</div>
		
		<jsp:include page="../fragments/footer.jsp"/>
	</body>
	</fmt:bundle>
</html>
