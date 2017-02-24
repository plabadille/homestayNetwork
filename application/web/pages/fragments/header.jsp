<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
  <p>the header</p>
  <c:if test="${!empty activeUser}">
    <p style="color:blue;">Bonjour ${activeUser.firstName}</p>
    <a href="<c:url value='/disconect' />">Déconnection</a>
  </c:if>
  <c:if test="${!empty admin}">
    <p style="color:blue;">Bonjour admin</p>
    <a href="<c:url value='/disconect' />">Déconnection</a>
  </c:if>
</header>
