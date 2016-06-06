<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="music" tagdir="/WEB-INF/tags" %>

<html lang="${language}">
	<fmt:setLocale value="${language}"/>
	<fmt:bundle basename="messages.messages">
	<jsp:include page="../fragments/htmlHeader.jsp"/>
	
	<body onload="setLanguage('${language}', '${msg_search}', 'instruments');">
		<music:bodyHeader menuName="instrumentsEdit"/>

		<div ng-app="myApp" class="container-fluid">
		    <div class="container xd-container">
		        <h2><fmt:message key="instrumentView"/></h2>
		
				<div ng-controller="instrumentController">
				    <div id="instrument" data-ng-init="getInstrument(${id}, '${type}')">
				    	<table>
				        	<tr>
				        		<th style="width: 100px;"><fmt:message key="tbName"/>:</th>
				        		<td class="field_data">{{instrument.name}}</td>
				        	</tr>
				        </table>
					    <table id="instrument_tb" class="table table-striped">
				            <thead>
				                <tr>
				                	<th><fmt:message key="tbActive"/></th>
		            				<th><fmt:message key="tbBandType"/></th>
		            				<th><fmt:message key="tbBandSet"/></th>
		            				<th><fmt:message key="tbGain"/></th>
		            				<th><fmt:message key="tbFrequency"/></th>
		            			</tr>
				            </thead>
							<tbody ng-repeat="setting in instrument.eqSettings | orderBy:sortingOrder:reverse">
				                <tr>
				                    <td><input type="checkbox" name="active" ng-model="setting.active" ng-true-value="true" ng-false-value="false"></td>
				                    <td><select name="bandType" id="bandType" ng-model="setting.bandType">
				                    	  <option value="LOW">LOW</option>
				                    	  <option value="LOW_MID">LOW_MID</option>
				                    	  <option value="HI_MID">HI_MID</option>
				                    	  <option value="HI">HI</option>
				                        </select></td>
				                    <td><select name="bandSet" id="bandSet" ng-model="setting.bandSet">
				                    	  <option ng-repeat="opt in options(setting.bandType)" value="{{opt}}" >{{opt}}</option>
				                        </select></td>
				                    <td><input type="number" name="gain" ng-model="setting.gain" style="text-align: right;" required></td>
				                    <td><input type="number" name="frequency" ng-model="setting.frequency" style="text-align: right;" required>
				                        <select name="freqUnits" id="freqUnits" ng-model="setting.freqUnits">
				                    	  <option value="Hz">Hz</option>
				                    	  <option value="KHz">KHz</option>
				                    	  <option value="MHz">MHz</option>
				                    	  <option value="GHz">GHz</option>
				                        </select></td>
				                </tr>
		                	</tbody>
		                </table>
		                <div id="footer" class="footer">
							<button type="submit" form="" value="Checkout" ng-click="update(); $event.stopPropagation();" class="btn_bottom"><fmt:message key="checkout"/></button>
		               	</div>
				    </div>
		        </div>
		
		        <music:footer/>
		    </div>
		</div>
		
		<jsp:include page="../fragments/footer.jsp"/>
	</body>
	</fmt:bundle>
</html>
