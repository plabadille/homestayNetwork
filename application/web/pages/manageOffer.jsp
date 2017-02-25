<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="fragments/head.jsp"/>

<jsp:include page="fragments/header.jsp"/>

<main>
  <h1>Gestion d'offre</h1>

  <c:if test="${!empty message}">
    <p style="color:green;">${message}</p>
  </c:if>

  <c:choose>
    <c:when test="${empty activeUser}">
      <p>Vous devez être connecté pour accéder à cette page</p>
    </c:when>
    <c:when test="${!activeUser.id eq offer.idOwner }">
      <p>Vous ne pouvez pas visualiser une propriété qui ne vous appartient pas.</p>
    </c:when>
    <c:otherwise>
      <h2>Modifier une offre</h2>
      <form method="post" action="<c:url value='/editOffer' />">
        <p>
          <div>Date de début: <input type="text" name="startDate" value="${offer.beginDateObject}" required></div>
          <div>Date de fin: <input type="text" name="endDate" value="${offer.endDateObject}" required></div>
          <input type="hidden" name="housingId" value="${offer.id}">
          <input type="submit" value="Envoyer">
        </p>
      </form>

      <h2>Supprimer une offre</h2>
      <a href="<a href="<c:url value="/editHousing/${offer.id}" />">Supprimer</a>

    </c:otherwise>
  </c:choose>
</main>

<jsp:include page="fragments/footer.jsp"/>
