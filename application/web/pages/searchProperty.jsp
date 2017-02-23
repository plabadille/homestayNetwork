<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="fragments/head.jsp" />

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/nav.jsp" />

        <main>

            <h1>Rechercher une propriété</h1>
        <c:choose>
           <c:when test="${empty activeUser}">
               <p>Vous devez être connecté pour accéder à cette page</p>
           </c:when>
           <c:otherwise>
                <p>W.I.P</p>
           </c:otherwise>
        </c:choose>

        </main>
        
<jsp:include page="fragments/footer.jsp" />