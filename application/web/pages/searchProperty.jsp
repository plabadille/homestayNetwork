<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="fragments/head.jsp"/>

<jsp:include page="fragments/header.jsp"/>

<main>
  <h1>Rechercher une propriété</h1>
  <c:choose>
    <c:when test="${empty activeUser}">
      <p>Vous devez être connecté pour accéder à cette page</p>
    </c:when>
    <c:otherwise>
      <form method="post" action="<c:url value='/searchProperty' />">
        <p>
          <div>Pays: <input type="text" name="country" required></div>
          <div>Date de début: <input type="text" name="startDate" placeholder="15/10/2017" required></div>
          <div>Date de fin: <input type="text" name="endDate" placeholder="20/10/2017" required></div>
          <input type="submit" value="Rechercher">
        </p>
      </form>
    </c:otherwise>
  </c:choose>

  <c:if test="${!empty offers}">
    <h2>Liste des propriétés</h2>
    <ul>
      <c:forEach var="offer" varStatus="loop" items="${offers}">
        <li>
          <ul>
            <li>Du ${offer.beginDateObject} au ${offer.endDateObject}</li>
            <li>Adresse: ${housings[loop.index].address}</li>
            <li>Pays: ${housings[loop.index].country}</li>
            <li>Surface: ${housings[loop.index].surface}m²</li>
            <li>Nombre de pièces: ${housings[loop.index].nbRoom}</li>
            <li>-> <a href="<c:url value='/bookOffer?idOffer=${offer.id}' />">Rejoindre</a></li>
          </ul>
        </li>
      </c:forEach>
    </ul>
    <p style="color:green;">${message}</p>
  </c:if>
</main>

<jsp:include page="fragments/footer.jsp"/>
