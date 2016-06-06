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
	<c:set var="msg_mode" scope="request">
		<fmt:message key="tbMode"/>
	</c:set>
	<c:set var="msg_attack" scope="request">
		<fmt:message key="tbAttack"/>
	</c:set>
	<c:set var="msg_release" scope="request">
		<fmt:message key="tbRelease"/>
	</c:set>
	<c:set var="msg_threshold" scope="request">
		<fmt:message key="tbThreshold"/>
	</c:set>
	<c:set var="msg_ratio" scope="request">
		<fmt:message key="tbRatio"/>
	</c:set>
	<c:set var="msg_presence" scope="request">
		<fmt:message key="tbPresence"/>
	</c:set>
	<c:set var="msg_make_up" scope="request">
		<fmt:message key="tbMakeUp"/>
	</c:set>
	<jsp:include page="../fragments/htmlHeader.jsp"/>
	
	<body onload="setLanguage('${language}', '${msg_search}', 'instruments');">
		<music:bodyHeader menuName="instrumentsView"/>

		<div class="container-fluid">
		    <div class="container xd-container">
		        <h2><fmt:message key="instrumentView"/> <c:out value="${instrument.name}"/></h2>

				<div>
					<table style="margin: 0 auto;">
						<tbody>
							<tr>
								<th style="width: 100px;"><fmt:message key="tbName"/></th>
								<td><c:out value="${instrument.name}"/></td>
							</tr>
							<tr>
								<th><fmt:message key="tbMode"/></th>
								<td><c:out value="${instrument.mode}"/></td>
							</tr>
							<tr>
								<th><fmt:message key="tbAttack"/></th>
								<td><c:out value="${instrument.attack}"/></td>
							</tr>
							<tr>
								<th><fmt:message key="tbRelease"/></th>
								<td><c:out value="${instrument.release}"/></td>
							</tr>
							<tr>
								<th><fmt:message key="tbThreshold"/></th>
								<td><c:out value="${instrument.threshold}"/></td>
							</tr>
							<tr>
								<th><fmt:message key="tbRatio"/></th>
								<td><c:out value="${instrument.ratio}"/>:<c:out value="${instrument.ratioOf}"/></td>
							</tr>
							<tr>
								<th><fmt:message key="tbPresence"/></th>
								<td><c:out value="${instrument.presence}"/></td>
							</tr>
							<tr>
								<th><fmt:message key="tbMakeUp"/></th>
								<td><c:out value="${instrument.makeUp}"/></td>
							</tr>
						</tbody>
					</table>
				</div>
		
		        <music:footer/>
		    </div>
		</div>
		
		<jsp:include page="../fragments/footer.jsp"/>
	</body>
	</fmt:bundle>
</html>
