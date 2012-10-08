<%@ include file="/WEB-INF/views/includes.jsp" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<s:message code="chooseLanguageFull"/><br /><br />
<f:form method="post" action="languageSelected.html" commandName="phrases">
<f:select path="language">
	<f:options items="${languagesList}"></f:options>                                                                                                       
</f:select>  
<input type="submit" value="<s:message code="buttonsChooseLanguage"/>"/> 	
</f:form>
<%@ include file="/WEB-INF/views/footer.jsp" %>