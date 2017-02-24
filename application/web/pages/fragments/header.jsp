<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
  <jsp:include page="nav.jsp"/>
  <div class="user">
    <c:if test="${!empty activeUser}">
      <span>Bonjour ${activeUser.firstName}</span>
      (<a href="<c:url value='/disconect' />">Déconnection</a>)
    </c:if>
    <c:if test="${!empty admin}">
      <span>Bonjour admin</span>
      (<a href="<c:url value='/disconect' />">Déconnection</a>)
    </c:if>
  </div>
</header>
