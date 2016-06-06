<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="music" tagdir="/WEB-INF/tags" %>

<%@ attribute name="menuName" required="true" rtexprvalue="true"
              description="Name of the active menu: home, instruments or products" %>

<music:menu name="${menuName}"/>
