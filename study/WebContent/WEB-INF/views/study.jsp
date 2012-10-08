<%@ include file="/WEB-INF/views/includes.jsp" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<a href='<c:url value="logout?id=${phrases.id}"/>'><s:message code="logout"/></a><br /><br />

<s:message code="infoLogout"/><br /><br />
<f:form method="post" action="phraseAction.html" commandName="phrases">
	<s:message code="phrase" /> ${phrases.order} <br />
<f:hidden path="language"/><f:hidden path="order"/><f:hidden path="textTo"/><f:hidden path="textFrom"/>
	${phrases.textFrom} 	<br />
	<label class="info" for="right-menu">${answer}</label><br />
	<f:errors cssClass="error" />
	<input type="submit" name="showRightAnswer"  value="<s:message code="buttonsShowRightAnswer"/>"/>
	<input type="submit" name="showNextPhrase" value="<s:message code="buttonsShowNextPhrase"/>"/>
</f:form>

<%@ include file="/WEB-INF/views/footer.jsp" %>