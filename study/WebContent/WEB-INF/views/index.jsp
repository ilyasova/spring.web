<%@ include file="/WEB-INF/views/includes.jsp" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<div id="left-menu">
<s:message code="enter"/><br />
<f:form method="post" action="login.html" commandName="user"><br />
<f:label path="login"><s:message code="login"/></f:label>
<f:input path="login" disabled="false"/>
<label class="error" for="left-menu">${loginError}</label> <br />
<f:errors path="login" cssClass="error" />

<f:label path="password"><s:message code="password"/></f:label>
<f:password path="password"/><br />

<input type="submit" value="<s:message code="buttonsLogin"/>"/> 	
</f:form>
</div>
<div id="right-menu">
<s:message code="justReg"/><br />
<label class="error" for="right-menu">${regError}</label><br />
<f:form method="post" action="register.html" commandName="regUser">
<f:label path="login"><s:message code="loginReg"/></f:label>
<f:input path="login" disabled="false"/><br />
<f:errors path="login" cssClass="error" />
<f:label path="password"><s:message code="passwordReg"/></f:label>
<f:password path="password"/><br /><br />
<input type="submit" value="<s:message code="buttonsRegister"/>"/> 	
</f:form>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>