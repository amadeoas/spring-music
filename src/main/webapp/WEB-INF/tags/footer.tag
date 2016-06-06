<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="msg_currentLanguage" scope="request">
	<fmt:message key="currentLanguage"/>
</c:set>
<c:set var="msg_altLanguage" scope="request">
	<fmt:message key="altLanguage"/>
</c:set>
<c:set var="msg_by" scope="request">
	<fmt:message key="by"/>
</c:set>

<div class="container">
    <div class="row">
        <div class="col-12 text-center">
        	<hr style="margin-bottom: 1px;" />
	        <c:choose>
				<c:when test="${language=='es_ES'}">
		        	<div class="language">
			        	<img src="<spring:url value="/resources/images/es.png" htmlEscape="true" />"
			                                             alt="${msg_currentLanguage}" class="footer" />
			        </div>
		        	<div class="language">
			            <a href="?lang=en_GB" title="${msg_altLanguage}">
			        		<img src="<spring:url value="/resources/images/en.png" htmlEscape="true" />"
			                                             alt="${msg_altLanguage}" class="footer" />
			            </a>
			        </div>
				</c:when>    
			    <c:otherwise>
		        	<div class="language">
			        	<img src="<spring:url value="/resources/images/en.png" htmlEscape="true" />"
			                                             alt="${msg_currentLanguage}" class="footer" />
			        </div>
		        	<div class="language">
			        	<a href="?lang=es_ES" title="${msg_altLanguage}">
			        		<img src="<spring:url value="/resources/images/es.png" htmlEscape="true" />"
			                                             alt="${msg_altLanguage}" class="footer" />
			            </a>
			        </div>
			    </c:otherwise>
			</c:choose>
			<span>
        	<a href="https://uk.linkedin.com/in/dr-amadeo-ascó-signes-57716883" title="${msg_by}">
        		<img src="<spring:url value="/resources/images/music-logo.png" htmlEscape="true" />"
                                             alt="${msg_by}"/>
            </a>
            </span>
        </div>
    </div>
</div>
